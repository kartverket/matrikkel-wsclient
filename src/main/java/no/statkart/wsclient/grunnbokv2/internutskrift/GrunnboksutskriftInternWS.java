package no.statkart.wsclient.grunnbokv2.internutskrift;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

public interface GrunnboksutskriftInternWS {

   String ubekreftetHistoriskGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String ubekreftetGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String grunnboksutskriftInfo(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftTidligereOverdragelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftTidligereHeftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftOverdragelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftHeftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   String delAvGrunnboksutskriftRegisterenhetsendringer(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;
}
