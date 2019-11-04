package no.statkart.wsclient.byggesak.fiksintegrasjon;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;


public class ValiderXmlSkjemaTest {

   @Test
   public void testXmlSkjemaGodkjent() throws IOException, SAXException {
      URL eksempelRiktig = getClass().getClassLoader().getResource("byggesak/standardEksempelByggesak.xml");
      Objects.requireNonNull(eksempelRiktig, "Filen finnes ikke");

      final boolean b = ByggesakXMLValidator.validateByggesakXML(IOUtils.toString(eksempelRiktig.openStream(), StandardCharsets.UTF_8));
      assertThat(b).as("Godkjent validering av byggesak").isTrue();

   }

   @Test
   public void testXmlSkjemaFeiler() throws IOException, SAXException {
      URL eksempelFeil = getClass().getClassLoader().getResource("byggesak/eksempelByggesakFeilRekkefolge.xml");
      Objects.requireNonNull(eksempelFeil, "Filen finnes ikke");

      final boolean b = ByggesakXMLValidator.validateByggesakXML(IOUtils.toString(eksempelFeil.openStream(), StandardCharsets.UTF_8));
      assertThat(b).as("Validering som skal feile.").isFalse();

   }
}