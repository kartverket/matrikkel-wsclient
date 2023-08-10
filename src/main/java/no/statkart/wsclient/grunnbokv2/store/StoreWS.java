package no.statkart.wsclient.grunnbokv2.store;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObject;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectId;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface StoreWS {

    GrunnbokBubbleObject getObject(GrunnbokBubbleObjectId id, GrunnbokContext grunnbokContext) throws ServiceException;

    List<GrunnbokBubbleObject> getObjects(Collection<? extends GrunnbokBubbleObjectId> ids, GrunnbokContext grunnbokContext) throws ServiceException;

    default List<GrunnbokBubbleObject> getObjects(Stream<? extends GrunnbokBubbleObjectId> ids, GrunnbokContext grunnbokContext) throws ServiceException {
        return getObjects(ids.collect(Collectors.toList()), grunnbokContext);
    }

    List<GrunnbokBubbleObject> getObjectsIgnoreMissing(Collection<? extends GrunnbokBubbleObjectId> ids, GrunnbokContext grunnbokContext) throws ServiceException;
}
