package no.statkart.wsclient.byggesak.fiksintegrasjon;


import no.statkart.skif.exception.ImplementationException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Validerer velformet xml på bakgrunn av modellen (matrikkelfoering.xsd)
 */
class ByggesakXMLValidator {
   private static Logger logger = LoggerFactory.getLogger(ByggesakXMLValidator.class);

   private static final JAXBContext jaxbContext;
   private static final Unmarshaller unmarshaller;
   private static Schema schema;

   static {
            try {
               jaxbContext = JAXBContext.newInstance("no.geointegrasjon.rep.matrikkel.foering.v1");
               unmarshaller = jaxbContext.createUnmarshaller();
               SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
               schema =
                     schemaFactory.newSchema(
                           Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("byggesak/xsd/matrikkelfoering.xsd"))
                     );
               if(schema == null) { throw new ImplementationException("Finner ikke Schema 'matrikkelfoering.xsd'"); }
               unmarshaller.setSchema(schema);

            } catch (JAXBException | SAXException e) {
               logger.error("Alvorlig feil i static constructor: "+e.getMessage(), e);
               throw new RuntimeException("Alvorlig feil i ByggesakXMLValidator: "+e.getMessage());
            }
   }

   /**
    *
    * @param xmlString med utf-8 encoding
    */
   static boolean validateByggesakXML(String xmlString) throws IOException, SAXException {
      return validateByggesakXML(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
   }

   public static boolean validateByggesakXML(InputStream inputStream) throws IOException, SAXException {
      final byte[] xmlBytes = IOUtils.toByteArray(inputStream);
      try {
         schema.newValidator().validate(new StreamSource(new ByteArrayInputStream(xmlBytes)));
         return true;
      } catch (SAXParseException e) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(xmlBytes), StandardCharsets.UTF_8));
         StringBuilder sb = new StringBuilder();
         sb.append("An ").append(e.getClass().getName()).append(" was caught:\n");
         sb.append(e.toString());
         String readLine;
         int lineNumber = 1;
         while ((readLine = reader.readLine()) != null) {
            sb.append('\t');
            sb.append(readLine);
            sb.append('\n');
            if (e.getLineNumber() == lineNumber) {
               for (int j = 1; j < e.getColumnNumber(); ++j) {
                  sb.append(' ');
               }
               sb.append("^--- HERE ---\n");
               break;
            } else {
               ++lineNumber;
            }
         }
         logger.error("OBS: SJEKK OBLIGATORISKE ELEMENTER ELLER REKKEFOLGE\n" + sb, e);
         return false;
      }
   }
}
