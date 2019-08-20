package no.statkart.wsclient.ebyggesakmatrikkelfoering;

import no.statkart.skif.exception.ImplementationException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ExampleByggesakBehandler implements EByggesakBehandlerService {
   @Override
   public Set<MeldingFraFIKS> hentNyeMeldinger() {

      Set<MeldingFraFIKS> nyeMeldinger = new HashSet<>();

      URL eksempel1 = getClass().getClassLoader().getResource("eksempelByggesak1.xml");
      URL eksempel2 = getClass().getClassLoader().getResource("eksempelByggesak2.xml");

      try {
         ByggesakXMLValidator.validateByggesakXML(eksempel1);
         ByggesakXMLValidator.validateByggesakXML(eksempel2);
      } catch (IOException | SAXException e) {
         throw new ImplementationException(e.getClass()+" "+e.getMessage());
      }

      try {
         MeldingFraFIKS melding1 = new MeldingFraFIKS(BuildXMLString.buildStringFromURL(eksempel1));
         MeldingFraFIKS melding2 = new MeldingFraFIKS(BuildXMLString.buildStringFromURL(eksempel1));
         nyeMeldinger.add(melding1);

         nyeMeldinger.add(melding2);
      } catch (IOException e) {
         throw new ImplementationException(e.getClass()+" "+e.getMessage());
      }

      return nyeMeldinger;
   }

   @Override
   public void oppdaterEByggesak(int id) {

      // Too be implemented.....

   }
}
