package no.statkart.wsclient.grunnbokv2.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.BorettslagsandelIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.KommuneIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.ident.IdentService;
import no.kartverket.grunnbok.wsapi.v2.service.ident.IdentServiceWS;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.BorettslagsandelIdentTilBorettslagsandelIdMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.KommuneIdentTilKommuneIdMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;


public class DefaultIdentWS implements IdentWS {

   private final IdentService identService;

   public DefaultIdentWS(String brukernavn, String passord, String endpointUrl) {
      identService = WebServiceBuilder.builderv2(new IdentServiceWS().getIdentServicePort(), IdentService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
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
   public KommuneIdentTilKommuneIdMap findKommuneIdsForIdents(KommuneIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException {
      return identService.findKommuneIdsForIdents(idents, grunnbokContext);
   }
}
