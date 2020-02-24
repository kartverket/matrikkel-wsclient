package no.statkart.wsclient.grunnbokv2.grunnboksutskrift;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.grunnboksutskrift.GrunnboksutskriftService;
import no.kartverket.grunnbok.wsapi.v2.service.grunnboksutskrift.GrunnboksutskriftServiceWS;
import no.statkart.wsclient.WebServiceBuilder;

public class DefaultGrunnbokutskriftServiceWS implements GrunnboksutskriftService {

   private static GrunnboksutskriftServiceWS grunnboksutskriftServiceWS;

   private final GrunnboksutskriftService service;

   public DefaultGrunnbokutskriftServiceWS(String brukernavn, String passord, String endpointUrl) {
      if(grunnboksutskriftServiceWS == null){
         synchronized (this) {
            if(grunnboksutskriftServiceWS == null){
               grunnboksutskriftServiceWS = new GrunnboksutskriftServiceWS();
            }
         }
      }

      service = WebServiceBuilder.builderv2(grunnboksutskriftServiceWS.getGrunnboksutskriftServicePort(), GrunnboksutskriftService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
   }

   @Override
   public String ubekreftetHistoriskGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {return service.ubekreftetHistoriskGrunnboksutskrift(registerenhetId, grunnbokContext);}

   @Override
   public byte[] ubekreftetHistoriskGrunnboksutskriftPdf(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {return service.ubekreftetHistoriskGrunnboksutskriftPdf(registerenhetId, grunnbokContext);}

   @Override
   public String ubekreftetGrunnboksutskrift(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {return service.ubekreftetGrunnboksutskrift(registerenhetId, grunnbokContext);}

   @Override
   public byte[] ubekreftetGrunnboksutskriftPdf(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {return service.ubekreftetGrunnboksutskriftPdf(registerenhetId, grunnbokContext);}

}
