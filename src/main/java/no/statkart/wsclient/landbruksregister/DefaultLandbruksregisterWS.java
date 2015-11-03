package no.statkart.wsclient.landbruksregister;

import no.slf.lib.server.ws.LIBWebService;
import no.slf.lib.server.ws.LIBWebServiceBeanService;
import no.slf.lib.server.ws.WsEiendomDTO;
import no.statkart.wsclient.HandlerResolverBuilder;
import no.statkart.wsclient.WebServiceBuilder;

public class DefaultLandbruksregisterWS implements LandbruksregisterWS {

   private final LIBWebService landbruksregisterService;

   public DefaultLandbruksregisterWS(final String brukernavn, final String passord, final String endpointUrl) {
      LIBWebServiceBeanService libWebServiceBeanService = new LIBWebServiceBeanService();
      libWebServiceBeanService.setHandlerResolver(HandlerResolverBuilder.builder()
            .enableLogging()
            .enableWSSecurity(brukernavn, passord)
            .build());

      int timeoutMillis = 10000;
      landbruksregisterService = WebServiceBuilder.builder(libWebServiceBeanService.getLIBWebServiceBeanPort(), LIBWebService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withTimeout(timeoutMillis)
            .doCreateProxy()
            .build();
   }

   @Override
   public WsEiendomDTO getEiendom(String kommunenr, String gardsnr, String bruksnr, String festenr, int landbrukseiendom, int eiere, int bedrifter, int skonti, int grunneiendommer) {
      return landbruksregisterService.getEiendom(kommunenr, gardsnr, bruksnr, festenr, landbrukseiendom, eiere, bedrifter, skonti, grunneiendommer);
   }

}
