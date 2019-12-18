package no.statkart.wsclient.byggesak.fiksintegrasjon;

import com.google.common.io.ByteStreams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.bouncycastle.cms.CMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static no.statkart.wsclient.byggesak.BuildString.buildStringFromStream;

/**
 * Hjelpeklasse for å laste ned vedlegg fra FIKS knyttet til 1 forsendelse.
 */
class LastNedVedlegg {
   private Logger logger = LoggerFactory.getLogger(LastNedVedlegg.class);

   private DekrypterVedlegg dekrypterer;
   private String serviceBrukernavn;
   private String servicePassord;

   /**
    * Initialiserer dekryptering
    */
   LastNedVedlegg(String serviceBrukernavn, String servicePassord, URL privateKeyUrl) {
      dekrypterer = new DekrypterVedlegg(privateKeyUrl);
      this.serviceBrukernavn = serviceBrukernavn;
      this.servicePassord = servicePassord;
   }

   /**
    * Gjør kall mot FIKS med download-url fra forsendelse.
    * Laster ned vedleggene (zip-fil).
    * Går gjennom zip-filen og henter ut xml.
    *
    * @param responsMelding ResponsMeldingen xml-fil lagres på.
    */
   String lastNedVedlegg(ResponsMelding responsMelding) {

      String downloadUrl = responsMelding.getDownloadUrl();
      String filnavn = responsMelding.getXmlFilnavn();

      HttpGet getZipFileRequest = new HttpGet(downloadUrl);

      try(final CloseableHttpResponse kryptertFilRespons =
                FiksMottakerRestClient.executeHttpsRequest(getZipFileRequest, serviceBrukernavn, servicePassord)) {
         if (kryptertFilRespons != null) {
            final byte[] kryptertZipBytes = ByteStreams.toByteArray(kryptertFilRespons.getEntity().getContent());
            final byte[] dekryptertZipBytes = dekrypterer.dekrypterBytes(kryptertZipBytes);

            try (ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(dekryptertZipBytes))) {
               ZipEntry entry;
               while ((entry = zipInputStream.getNextEntry()) != null) {
                  if (entry.getName().equals(filnavn)) {
                     final String xmlString = buildStringFromStream(zipInputStream, StandardCharsets.UTF_8);
                     if (ByggesakXMLValidator.validateByggesakXML(xmlString)) {
                        return xmlString;
                     }
                     return null; //ikke gyldig xml
                  }
               }
            }
         }
         logger.error("Ukjent feilsituasjon ved nedlastning for forsendelseid: " + responsMelding.getForsendelseId());
      } catch (IOException | NullPointerException e) {
         logger.error("Nedlasting av fil(er) feilet for " + responsMelding.getForsendelseId(), e);
      } catch (SAXException e) {
         logger.error("Validering av XML feilet for " + responsMelding.getForsendelseId(), e);
      } catch (CMSException e) {
         logger.error("Dekryptering av fil feilet: " + e.getCause() + " " + e.getMessage(), e);
      }
      return null;
   }
}
