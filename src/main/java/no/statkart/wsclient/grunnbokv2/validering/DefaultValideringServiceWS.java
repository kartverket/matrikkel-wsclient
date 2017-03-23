package no.statkart.wsclient.grunnbokv2.validering;

import com.sun.xml.ws.developer.SchemaValidationFeature;
import no.kartverket.grunnbok.wsapi.v2.service.validering.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.validering.ValideringService;
import no.kartverket.grunnbok.wsapi.v2.service.validering.ValideringServiceFaultInfo;
import no.statkart.skif.exception.OperationalException;
import no.statkart.wsclient.HandlerResolverBuilder;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.InnsendingServiceMapper;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;


public class DefaultValideringServiceWS implements ValideringServiceWS {
   private static volatile no.kartverket.grunnbok.wsapi.v2.service.validering.ValideringServiceWS webServiceClient;
   private final ValideringService valideringWebService;
   private final InnsendingServiceMapper innsendingServiceMapper = new InnsendingServiceMapper();

   public DefaultValideringServiceWS(String brukernavn, String passord, String endpointUrl, boolean enableSchemaValidation) {
      if(webServiceClient == null){
         synchronized (this) {
            if(webServiceClient == null){
               webServiceClient = new no.kartverket.grunnbok.wsapi.v2.service.validering.ValideringServiceWS();
               webServiceClient.setHandlerResolver(HandlerResolverBuilder.builder()
                                                                         .enableLogging().build());
            }
         }
      }

      ValideringService valideringServicePort = enableSchemaValidation
                                                ? webServiceClient.getValideringServicePort(new SchemaValidationFeature())
                                                : webServiceClient.getValideringServicePort();

      valideringWebService = WebServiceBuilder.builderv2(valideringServicePort, ValideringService.class)
                                              .withBruker(brukernavn)
                                              .withPassord(passord)
                                              .withEndpointUrl(endpointUrl)
                                              .build();
   }

   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) {
      try {
         final no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse mappedArgs =
               innsendingServiceMapper.mapForsendelse(forsendelse);
         return innsendingServiceMapper.mapForsendelsesstatus(
                 valideringWebService.valider(mappedArgs));
      } catch (ServiceException se) {
         OperationalException oe = new OperationalException(
               se.getClass().getName() + ": " + se.getMessage());
         ValideringServiceFaultInfo faultInfo = se.getFaultInfo();
         if (faultInfo != null) {
            oe.setFeilkode(faultInfo.getFeiltype());
            oe.setFeilkodebeskrivelse(faultInfo.getFeilbeskrivelse());
         }
         oe.setStackTrace(se.getStackTrace());
         throw oe;
      }
   }
}
