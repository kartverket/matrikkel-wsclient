package no.statkart.wsclient.ebyggesakmatrikkelfoering;


import no.statkart.skif.exception.ImplementationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ByggesakXMLValidator {

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
               Thread.currentThread().getContextClassLoader().getResource("ebyggesakmatrikkelfoering/xsd/matrikkelfoering.xsd")
         );
         unmarshaller.setSchema(schema);

      } catch (JAXBException | SAXException e) {
         e.printStackTrace();
         throw new RuntimeException(e.getMessage());
      }
   }

   public static void validateByggesakXML(URL byggesakXML) throws IOException, SAXException {

      InputStream inputStream = byggesakXML.openStream();

      try {
         if(schema != null)
            schema.newValidator().validate(new StreamSource(inputStream));
         else
            throw new ImplementationException("Schema finnes ikke.");
      } catch (SAXParseException e) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
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
         throw new IllegalArgumentException(sb.toString() + "\nOBS: SJEKK OBLIGATORISKE ELEMENTER ELLER REKKEFOLGE");
      }
   }
}
