package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.skif.exception.OperationalException;
import no.statkart.wsclient.byggesak.fiksintegrasjon.ByggesakXMLValidator;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.testng.Assert.fail;


public class ValiderXmlSkjemaTest {

   @Test
   public void testXmlSkjemaGodkjent() {
      URL eksempelRiktig = getClass().getClassLoader().getResource("byggesak/standardEksempelByggesak.xml");
      Objects.requireNonNull(eksempelRiktig, "Filen finnes ikke");
      try {
         ByggesakXMLValidator.validateByggesakXML(IOUtils.toString(eksempelRiktig.openStream(), StandardCharsets.UTF_8));
      } catch (IOException | SAXException | IllegalArgumentException e) {
         e.printStackTrace();
         Assertions.fail("Skulle vært godkjent");
      }
   }

   @Test
   public void testXmlSkjemaFeiler() {
      URL eksempelFeil = getClass().getClassLoader().getResource("byggesak/eksempelByggesakFeilRekkefolge.xml");
      Objects.requireNonNull(eksempelFeil, "Filen finnes ikke");
      try {
         ByggesakXMLValidator.validateByggesakXML(IOUtils.toString(eksempelFeil.openStream(), StandardCharsets.UTF_8));
         Assertions.fail("Skulle feilet");
      } catch (IOException | SAXException | OperationalException e) {
         Assertions.assertThat(e).isInstanceOf(OperationalException.class);
         Assertions.assertThat(e.getMessage()).contains("SJEKK OBLIGATORISKE ELEMENTER ELLER REKKEFOLGE");
      }
   }
}