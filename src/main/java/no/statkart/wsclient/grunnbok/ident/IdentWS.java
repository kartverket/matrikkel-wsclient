package no.statkart.wsclient.grunnbok.ident;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.KommuneIdentList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.BorettslagsandelIdentList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.ident.ValidationException_Exception;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.BorettslagsandelIdentTilBorettslagsandelIdMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.KommuneIdentTilKommuneIdMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;

/**
 *
 */
public interface IdentWS {

   MatrikkelenhetIdentTilMatrikkelenhetIdMap findMatrikkelenhetIdsForIdents(MatrikkelenhetIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException;

   //TODO: Denne brukes kun fra test, hvorfor behøver vi den?
   BorettslagsandelIdentTilBorettslagsandelIdMap findBorettslagsandelIdsForIdents(BorettslagsandelIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException;

   //TODO: Denne brukes kun fra test, hvorfor behøver vi den?
   KommuneIdentTilKommuneIdMap findKommuneIdsForIdents(KommuneIdentList idents, GrunnbokContext grunnbokContext)
         throws ServiceException, ValidationException_Exception;

}
