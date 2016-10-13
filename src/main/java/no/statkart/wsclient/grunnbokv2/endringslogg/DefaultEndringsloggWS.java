package no.statkart.wsclient.grunnbokv2.endringslogg;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.Domainklasse;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.EndringId;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.Endringer;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.ReturnerBobler;
import no.kartverket.grunnbok.wsapi.v2.service.endringslogg.EndringsloggService;
import no.kartverket.grunnbok.wsapi.v2.service.endringslogg.EndringsloggServiceWS;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultEndringsloggWS implements EndringsloggWS {

   private final EndringsloggService endringsloggService;

   public DefaultEndringsloggWS(final String brukernavn, final String passord, final String endpointUrl) {
      endringsloggService = WebServiceBuilder.builderv2(new EndringsloggServiceWS().getEndringsloggServicePort(), EndringsloggService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
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
