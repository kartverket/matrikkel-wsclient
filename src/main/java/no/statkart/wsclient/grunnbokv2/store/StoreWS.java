package no.statkart.wsclient.grunnbokv2.store;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.*;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

/**
 *
 */
public interface StoreWS {

   GrunnbokBubbleObject getObject(GrunnbokBubbleObjectId id, GrunnbokContext grunnbokContext) throws ServiceException;

   GrunnbokBubbleObjectList getObjects(GrunnbokBubbleObjectIdList ids, GrunnbokContext grunnbokContext) throws ServiceException;

   GrunnbokBubbleObjectList getObjectsIgnoreMissing(GrunnbokBubbleObjectIdList ids, GrunnbokContext grunnbokContext) throws ServiceException;
}
