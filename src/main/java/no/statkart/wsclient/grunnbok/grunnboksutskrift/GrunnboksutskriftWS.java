package no.statkart.wsclient.grunnbok.grunnboksutskrift;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;

/**
 *
 */
public interface GrunnboksutskriftWS {

   String ubekreftetHistoriskGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String ubekreftetGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String grunnboksutskriftInfo(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftTidligereOverdragelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftTidligerePengeheftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftTidligereServitutter(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftTidligereRegisterenhetsendringer(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftOverdragelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftPengeheftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftServitutter(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftRegisterenhetsendringer(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;
}
