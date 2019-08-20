package no.statkart.wsclient.ebyggesakmatrikkelfoering.validate;

import no.geointegrasjon.rep.matrikkel.foering.v1.ByggesakType;
import no.statkart.wsclient.ebyggesakmatrikkelfoering.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Set;

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

   @Test
   public void testHentSkjema() {
      EByggesakBehandlerService eByggesakBehandlerService = new ExampleByggesakBehandler();
      Set<MeldingFraFIKS> meldingerFraFIKS = eByggesakBehandlerService.hentNyeMeldinger();

      String xml = null;

      for (MeldingFraFIKS meldingFraFIKS : meldingerFraFIKS) {
         xml = meldingFraFIKS.getXml();
         Assert.assertTrue(xml.contains("matrikkelopplysninger"));
      }
   }

   @Test
   public void testUnmarshal() throws JAXBException, XMLStreamException {
      EByggesakBehandlerService eByggesakBehandlerService = new ExampleByggesakBehandler();
      Set<MeldingFraFIKS> meldingerFraFIKS = eByggesakBehandlerService.hentNyeMeldinger();

      String xml = meldingerFraFIKS.iterator().next().getXml();

      JAXBContext jaxbContext = JAXBContext.newInstance("no.geointegrasjon.rep.matrikkel.foering.v1");
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

      XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));
      JAXBElement<ByggesakType> rootElement = null;

      try {
         rootElement = unmarshaller.unmarshal(reader, ByggesakType.class);
      } catch(UnmarshalException e) {
         e.printStackTrace();
      }

      ByggesakType byggesakType = rootElement.getValue();

      Assert.assertTrue(byggesakType.getTittel() != null);
   }
}
