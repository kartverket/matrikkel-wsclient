package no.statkart.wsclient.grunnbok.endringslogg;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.Domainklasse;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.EndringId;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.Endringer;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.ReturnerBobler;
import no.kartverket.grunnbok.wsapi.v1.service.endringslogg.EndringsloggService;
import no.kartverket.grunnbok.wsapi.v1.service.endringslogg.EndringsloggServiceWS;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultEndringsloggWS implements EndringsloggWS {

   private final EndringsloggService endringsloggService;

   public DefaultEndringsloggWS(final String brukernavn, final String passord, final String endpointUrl, HostnameVerifier hostnameVerifier) {
      endringsloggService = WebServiceBuilder.builder(new EndringsloggServiceWS().getEndringsloggServicePort())
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }

   @Override
   public EndringId findSisteEndringId(GrunnbokContext grunnbokContext) throws ServiceException {
      return endringsloggService.findSisteEndringId(grunnbokContext);
   }

   @Override
   public Endringer findEndringer(EndringId id, Domainklasse domainklasse, String filter, ReturnerBobler returnerBobler, int maksAntall, GrunnbokContext grunnbokContext) throws ServiceException {
      return endringsloggService.findEndringer(id, domainklasse, filter, returnerBobler, maksAntall, grunnbokContext);
   }

}
