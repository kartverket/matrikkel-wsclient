package no.statkart.wsclient.grunnbokv2.internutskrift;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.internutskrift.GrunnboksutskriftInternService;
import no.kartverket.grunnbok.wsapi.v2.service.internutskrift.GrunnboksutskriftInternServiceWS;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultGrunnboksutskriftInternWS implements GrunnboksutskriftInternWS {

   private static GrunnboksutskriftInternServiceWS grunnboksutskriftInternServiceWS;

   private final GrunnboksutskriftInternService service;

   public DefaultGrunnboksutskriftInternWS(String brukernavn, String passord, String endpointUrl) {
      if(grunnboksutskriftInternServiceWS == null){
         synchronized (this) {
            if(grunnboksutskriftInternServiceWS == null){
               grunnboksutskriftInternServiceWS = new GrunnboksutskriftInternServiceWS();
            }
         }
      }

      service = WebServiceBuilder.builderv2(grunnboksutskriftInternServiceWS.getGrunnboksutskriftInternServicePort(), GrunnboksutskriftInternService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
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
   public String delAvGrunnboksutskriftTidligereHeftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftTidligereHeftelser(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftOverdragelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftOverdragelser(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftHeftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftHeftelser(registerenhetId, grunnbokContext);
   }

   @Override
   public String delAvGrunnboksutskriftRegisterenhetsendringer(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return service.delAvGrunnboksutskriftRegisterenhetsendringer(registerenhetId, grunnbokContext);
   }
}
