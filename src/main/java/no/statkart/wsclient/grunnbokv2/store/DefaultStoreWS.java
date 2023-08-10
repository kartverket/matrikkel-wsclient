package no.statkart.wsclient.grunnbokv2.store;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObject;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectId;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectList;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.store.StoreService;
import no.kartverket.grunnbok.wsapi.v2.service.store.StoreServiceWS;
import no.statkart.wsclient.WebServiceBuilder;

import java.util.Collection;
import java.util.List;


public class DefaultStoreWS implements StoreWS {

    private static StoreServiceWS storeServiceWS;

    private final StoreService storeService;

    public DefaultStoreWS(final String brukernavn, final String passord, final String endpointUrl) {
        if (storeServiceWS == null) {
            synchronized (this) {
                if (storeServiceWS == null) {
                    storeServiceWS = new StoreServiceWS();
                }
            }
        }

        storeService = WebServiceBuilder.builderv2(storeServiceWS.getStoreServicePort(), StoreService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
    }

    @Override
    public GrunnbokBubbleObject getObject(GrunnbokBubbleObjectId id, GrunnbokContext grunnbokContext) throws ServiceException {
        return storeService.getObject(id, grunnbokContext);
    }

    @Override
    public List<GrunnbokBubbleObject> getObjects(Collection<? extends GrunnbokBubbleObjectId> ids, GrunnbokContext grunnbokContext) throws ServiceException {
        GrunnbokBubbleObjectIdList idList = new GrunnbokBubbleObjectIdList();
        idList.getItem().addAll(ids);
        GrunnbokBubbleObjectList objectList = storeService.getObjects(idList, grunnbokContext);
        return objectList.getItem();
    }

    @Override
    public List<GrunnbokBubbleObject> getObjectsIgnoreMissing(Collection<? extends GrunnbokBubbleObjectId> ids, GrunnbokContext grunnbokContext) throws ServiceException {
        GrunnbokBubbleObjectIdList idList = new GrunnbokBubbleObjectIdList();
        idList.getItem().addAll(ids);
        GrunnbokBubbleObjectList objectList = storeService.getObjectsIgnoreMissing(idList, grunnbokContext);
        return objectList.getItem();
    }

}
