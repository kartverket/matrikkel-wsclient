package no.statkart.wsclient.grunnbokv2.registerenhetsrettsandel;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.person.PersonIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettIdList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.PersonIdTilRegisterenhetsrettsandelIdsMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap;

public interface RegisterenhetsrettsandelWS {

   PersonIdTilRegisterenhetsrettsandelIdsMap findAndelerForRettighetshavere(PersonIdList personIds, GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap findAndelerIRetter(RegisterenhetsrettIdList rettIds, GrunnbokContext grunnbokContext) throws ServiceException;
}
