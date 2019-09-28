package no.statkart.wsclient.byggesak.fiksintegrasjon;


import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.OperationalException;
import org.apache.commons.io.IOUtils;
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
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Validerer velformet xml på bakgrunn av modellen (matrikkelfoering.xsd)
 */
class ByggesakXMLValidator {

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
               throw new RuntimeException(e.getMessage());
            }
   }

   static void validateByggesakXML(String xmlString) throws IOException, SAXException {
      validateByggesakXML(new ByteArrayInputStream(xmlString.getBytes()));
   }

   private static void validateByggesakXML(InputStream inputStream) throws IOException, SAXException {
      final byte[] xmlBytes = IOUtils.toByteArray(inputStream);
      try {
         schema.newValidator().validate(new StreamSource(new ByteArrayInputStream(xmlBytes)));
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
               sb.append("^--- HERE ---");
               sb.append('\n');
               break;
            } else {
               ++lineNumber;
            }
         }
         throw new OperationalException("OBS: SJEKK OBLIGATORISKE ELEMENTER ELLER REKKEFOLGE\n"+sb.toString());
      }
   }
}
