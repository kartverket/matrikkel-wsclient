package no.statkart.wsclient.grunnbok.store;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObject;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObjectId;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObjectIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObjectList;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.store.StoreService;
import no.kartverket.grunnbok.wsapi.v1.service.store.StoreServiceWS;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;


public class DefaultStoreWS implements StoreWS {

   private final StoreService storeService;

   public DefaultStoreWS(final String brukernavn, final String passord, final String endpointUrl, HostnameVerifier hostnameVerifier) {
      storeService = WebServiceBuilder.builder(new StoreServiceWS().getStoreServicePort(), StoreService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }

   @Override
   public GrunnbokBubbleObject getObject(GrunnbokBubbleObjectId id, GrunnbokContext grunnbokContext) throws ServiceException {
      return storeService.getObject(id, grunnbokContext);
   }

   @Override
   public GrunnbokBubbleObjectList getObjects(GrunnbokBubbleObjectIdList ids, GrunnbokContext grunnbokContext) throws ServiceException {
      return storeService.getObjects(ids, grunnbokContext);
   }
}
