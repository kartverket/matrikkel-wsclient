package no.statkart.wsclient.grunnbokv2.registerenhetsrett;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetIdList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.RegisterenhetIdTilRegisterenhetsrettIdsMap;

public interface RegisterenhetsrettWS {

    RegisterenhetIdTilRegisterenhetsrettIdsMap findRetterForEnheter(RegisterenhetIdList registerenhetIds,
                                                                    GrunnbokContext grunnbokContext) throws ServiceException;
}
