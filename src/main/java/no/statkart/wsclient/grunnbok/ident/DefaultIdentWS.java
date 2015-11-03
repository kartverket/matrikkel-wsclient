package no.statkart.wsclient.grunnbok.ident;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.KommuneIdentList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.BorettslagsandelIdentList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.ident.IdentService;
import no.kartverket.grunnbok.wsapi.v1.service.ident.IdentServiceWS;
import no.kartverket.grunnbok.wsapi.v1.service.ident.ValidationException_Exception;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.BorettslagsandelIdentTilBorettslagsandelIdMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.KommuneIdentTilKommuneIdMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;


public class DefaultIdentWS implements IdentWS {

   private final IdentService identService;

   public DefaultIdentWS(String brukernavn, String passord, String endpointUrl, HostnameVerifier hostnameVerifier) {
      identService = WebServiceBuilder.builder(new IdentServiceWS().getIdentServicePort(), IdentService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }

   @Override
   public MatrikkelenhetIdentTilMatrikkelenhetIdMap findMatrikkelenhetIdsForIdents(MatrikkelenhetIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException {
      return identService.findMatrikkelenhetIdsForIdents(idents, grunnbokContext);
   }

   @Override
   public BorettslagsandelIdentTilBorettslagsandelIdMap findBorettslagsandelIdsForIdents(BorettslagsandelIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException {
      return identService.findBorettslagsandelIdsForIdents(idents, grunnbokContext);
   }

   @Override
   public KommuneIdentTilKommuneIdMap findKommuneIdsForIdents(KommuneIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException, ValidationException_Exception {
      return identService.findKommuneIdsForIdents(idents, grunnbokContext);
   }
}
