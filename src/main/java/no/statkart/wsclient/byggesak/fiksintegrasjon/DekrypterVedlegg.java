package no.statkart.wsclient.byggesak.fiksintegrasjon;

import com.google.common.io.ByteStreams;
import no.statkart.skif.exception.OperationalException;
import org.bouncycastle.cms.CMSEnvelopedDataParser;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.RecipientInformationStore;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Objects;

/**
 * Dekrypterer kryptert fil mottatt via fiks. I zip-format
 */
class DekrypterVedlegg {
   private static final Logger logger = LoggerFactory.getLogger(DekrypterVedlegg.class);

   private Provider provider;
   private final PrivateKey privateKey;

   /**
    * Initialiserer sikkerhetsprovider og private key som brukes til å dekryptere zip-fil.
    */
   DekrypterVedlegg(URL privateKeyUrl) {
      provider = Security.getProvider("BC");
      if(provider == null) {
         Security.addProvider(new BouncyCastleProvider());
         provider = Security.getProvider("BC");
      }

      Objects.requireNonNull(privateKeyUrl, "Finner ikke privatekeytest.pem");
      try(PemReader pemReader = new PemReader(new InputStreamReader(privateKeyUrl.openStream()))) {
         byte[] keyBytes = pemReader.readPemObject().getContent();

         PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance("RSA");

         privateKey = keyFactory.generatePrivate(spec);

      } catch (IOException | GeneralSecurityException e) {
         logger.error("Constructor feilet: "+e.getMessage());
         throw new OperationalException("Oppsett av dekrypterer feilet: "+e.getMessage());
      }
   }

   /**
    * Dekrypterer en kryptert forsendelse fra FIKS.
    *
    * @param kryptertBytes Representerer payload fra http-kallet
    * @return dekryptert byte array
    */
   byte[] dekrypterBytes(byte[] kryptertBytes) throws IOException, CMSException {
      try(final ByteArrayInputStream encryptedStream = new ByteArrayInputStream(kryptertBytes)) {
         // Initialise parser
         CMSEnvelopedDataParser envDataParser = new CMSEnvelopedDataParser(encryptedStream);
         RecipientInformationStore recipients = envDataParser.getRecipientInfos();

         RecipientInformation recipient = recipients.getRecipients().iterator().next();

         CMSTypedStream envelopedData = recipient.getContentStream(new JceKeyTransEnvelopedRecipient(privateKey).setProvider(provider));
         try (InputStream inputStream = envelopedData.getContentStream()) {
            return ByteStreams.toByteArray(inputStream);
         }
      }
   }
}