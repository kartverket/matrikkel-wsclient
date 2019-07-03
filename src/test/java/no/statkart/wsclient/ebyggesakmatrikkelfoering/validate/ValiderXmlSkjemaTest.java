package no.statkart.wsclient.ebyggesakmatrikkelfoering.validate;

import no.statkart.wsclient.ebyggesakmatrikkelfoering.ByggesakXMLValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

import static org.testng.Assert.fail;


public class ValiderXmlSkjemaTest {

   @Test
   public void testXmlSkjemaGodkjent() {
      URL eksempelRiktig = getClass().getClassLoader().getResource("eksempelByggesakRiktig_endret.xml");

      try {
         ByggesakXMLValidator.validateByggesakXML(eksempelRiktig);
      } catch (IOException | SAXException | IllegalArgumentException e) {
         e.printStackTrace();
         fail("Skulle vært godkjent");
      }
   }

   @Test
   public void testXmlSkjemaFeiler() {
      URL eksempelFeil = getClass().getClassLoader().getResource("eksempelByggesakFeilRekkefolge.xml");

      try {
         ByggesakXMLValidator.validateByggesakXML(eksempelFeil);
         fail("Skulle feilet");
      } catch (IOException | SAXException | IllegalArgumentException e) {
         Assert.assertTrue(e instanceof IllegalArgumentException);
         Assert.assertTrue(e.getMessage().contains("SJEKK OBLIGATORISKE ELEMENTER ELLER REKKEFOLGE"), e.getMessage());
      }
   }
}
