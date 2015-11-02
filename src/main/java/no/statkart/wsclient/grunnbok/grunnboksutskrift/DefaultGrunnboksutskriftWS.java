package no.statkart.wsclient.grunnbok.grunnboksutskrift;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.internutskrift.GrunnboksutskriftInternService;
import no.kartverket.grunnbok.wsapi.v1.service.internutskrift.GrunnboksutskriftInternServiceWS;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultGrunnboksutskriftWS implements GrunnboksutskriftWS {

   private final GrunnboksutskriftInternService service;

   public DefaultGrunnboksutskriftWS(String brukernavn, String passord, String endpointUrl, HostnameVerifier hostnameVerifier) {
      service = WebServiceBuilder.builder(new GrunnboksutskriftInternServiceWS().getGrunnboksutskriftInternServicePort())
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }

   @Override
   public String ubekreftetHistoriskGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.ubekreftetHistoriskGrunnboksutskrift(registerenhetId, grunnbokContext);
   }

   @Override
   public String ubekreftetGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.ubekreftetGrunnboksutskrift(registerenhetId, grunnbokContext);
   }

   @Override
   public String grunnboksutskriftInfo(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.grunnboksutskriftInfo(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftTidligereOverdragelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftTidligereOverdragelser(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftTidligerePengeheftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftTidligerePengeheftelser(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftTidligereServitutter(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftTidligereServitutter(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftTidligereRegisterenhetsendringer(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftTidligereRegisterenhetsendringer(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftOverdragelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftOverdragelser(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftPengeheftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftPengeheftelser(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftServitutter(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftServitutter(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftRegisterenhetsendringer(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftRegisterenhetsendringer(registerenhetId, grunnbokContext);
   }
}
