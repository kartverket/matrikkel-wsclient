package no.statkart.wsclient.grunnbok.kodeliste;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.Timestamp;
import no.kartverket.grunnbok.wsapi.v1.domain.kodeliste.KodelisteTransfer;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.kodeliste.KodelisteService;
import no.kartverket.grunnbok.wsapi.v1.service.kodeliste.KodelisteServiceWS;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultKodelisteWS implements KodelisteWS {

   private final KodelisteService kodelisteService;

   public DefaultKodelisteWS(String brukernavn, String passord, String endpointUrl, HostnameVerifier hostnameVerifier) {
      kodelisteService = WebServiceBuilder.builder(new KodelisteServiceWS().getKodelisteServicePort(), KodelisteService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }

   @Override
   public KodelisteTransfer getKodelister(Timestamp snapshotVersion, GrunnbokContext grunnbokContext) throws ServiceException {
      return kodelisteService.getKodelister(snapshotVersion, grunnbokContext);
   }
}
