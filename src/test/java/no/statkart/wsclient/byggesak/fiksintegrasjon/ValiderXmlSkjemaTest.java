package no.statkart.wsclient.byggesak.fiksintegrasjon;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;


public class ValiderXmlSkjemaTest {

   @Test
   public void testXmlSkjemaGodkjent() throws IOException, SAXException {
      String eksempelRiktig = contentOf(getClass().getClassLoader().getResource("byggesak/standardEksempelByggesak.xml"), UTF_8);

      assertThat(ByggesakXMLValidator.validateByggesakXML(eksempelRiktig))
              .as("Godkjent validering av byggesak").isTrue();
   }

   @Test
   public void testXmlSkjemaFeiler() throws IOException, SAXException {
      String eksempelFeil = contentOf(getClass().getClassLoader().getResource("byggesak/eksempelByggesakFeilRekkefolge.xml"), UTF_8);

      assertThat(ByggesakXMLValidator.validateByggesakXML(eksempelFeil))
              .as("Validering som skal feile.").isFalse();
   }
}