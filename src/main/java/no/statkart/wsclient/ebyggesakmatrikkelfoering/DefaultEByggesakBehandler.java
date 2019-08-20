package no.statkart.wsclient.ebyggesakmatrikkelfoering;

import no.statkart.skif.exception.ImplementationException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class DefaultEByggesakBehandler implements EByggesakBehandlerService {
   @Override
   public Set<MeldingFraFIKS> hentNyeMeldinger() throws ImplementationException  {

      // IKKE KLART ENDA

      return null;
   }

   @Override
   public void oppdaterEByggesak(int id) {

      // Oppdater status på eByggesak

   }
}
