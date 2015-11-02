package no.statkart.wsclient.grunnbok.registerenhetsrettsandel;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.RegisterenhetsrettIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.person.PersonIdList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.PersonIdTilRegisterenhetsrettsandelIdsMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap;

/**
 *
 */
public interface RegisterenhetsrettsandelWS {

   PersonIdTilRegisterenhetsrettsandelIdsMap findAndelerForRettighetshaverePerson(PersonIdList personIds, GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap findAndelerIRetter(RegisterenhetsrettIdList rettIds, GrunnbokContext grunnbokContext) throws ServiceException;
}
