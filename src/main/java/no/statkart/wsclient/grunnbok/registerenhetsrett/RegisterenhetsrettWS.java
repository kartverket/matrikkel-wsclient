package no.statkart.wsclient.grunnbok.registerenhetsrett;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.RegisterenhetIdList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetIdTilRegisterenhetsrettIdsMap;

/**
 *
 */
public interface RegisterenhetsrettWS {

   RegisterenhetIdTilRegisterenhetsrettIdsMap findRetterForEnheter(RegisterenhetIdList registerenhetIds,
                                                                   GrunnbokContext grunnbokContext) throws ServiceException;
}
