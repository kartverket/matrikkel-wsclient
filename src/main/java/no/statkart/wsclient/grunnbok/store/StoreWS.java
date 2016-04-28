package no.statkart.wsclient.grunnbok.store;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObject;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObjectId;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObjectIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokBubbleObjectList;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;

/**
 *
 */
public interface StoreWS {

   GrunnbokBubbleObject getObject(GrunnbokBubbleObjectId id, GrunnbokContext grunnbokContext) throws ServiceException;

   GrunnbokBubbleObjectList getObjects(GrunnbokBubbleObjectIdList ids, GrunnbokContext grunnbokContext) throws ServiceException;

   GrunnbokBubbleObjectList getObjectsIgnoreMissing(GrunnbokBubbleObjectIdList ids, GrunnbokContext grunnbokContext) throws ServiceException;
}
