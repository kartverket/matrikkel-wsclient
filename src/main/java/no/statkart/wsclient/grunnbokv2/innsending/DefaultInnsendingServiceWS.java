package no.statkart.wsclient.grunnbokv2.innsending;

import com.sun.xml.ws.developer.SchemaValidationFeature;
import no.kartverket.grunnbok.wsapi.v2.service.innsending.InnsendingService;
import no.kartverket.grunnbok.wsapi.v2.service.innsending.ServiceException;
import no.statkart.wsclient.HandlerResolverBuilder;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;

public class DefaultInnsendingServiceWS implements InnsendingServiceWS {

   private final InnsendingService innsendingWebService;
   private final InnsendingServiceMapper innsendingServiceMapper = new InnsendingServiceMapper();

   public DefaultInnsendingServiceWS(String brukernavn, String passord, String endpointUrl, boolean enableSchemaValidation) {
      no.kartverket.grunnbok.wsapi.v2.service.innsending.InnsendingServiceWS webServiceClient = new no.kartverket.grunnbok.wsapi.v2.service.innsending.InnsendingServiceWS();
      webServiceClient.setHandlerResolver(HandlerResolverBuilder.builder()
            .enableLogging().build());

      InnsendingService innsendingServicePort = enableSchemaValidation
            ? webServiceClient.getInnsendingServicePort(new SchemaValidationFeature())
            : webServiceClient.getInnsendingServicePort();

      innsendingWebService = WebServiceBuilder.builderv2(innsendingServicePort, InnsendingService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .build();
   }

   public DefaultInnsendingServiceWS(final String brukernavn, final String passord, final String endpointUrl) {
      this(brukernavn, passord, endpointUrl, false);
   }

   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) {
      try {
         final no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse mappedArgs = innsendingServiceMapper.mapForsendelse(forsendelse);
         //noinspection UnnecessaryLocalVariable
         final Forsendelsesstatus returnvalue = innsendingServiceMapper.mapForsendelsesstatus(
               innsendingWebService.valider(mappedArgs));
         return returnvalue;
      } catch( ServiceException e ) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse) {
      try {
         final no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse mappedArgs = innsendingServiceMapper.mapForsendelse(forsendelse);
         //noinspection UnnecessaryLocalVariable
         final Forsendelsesstatus returnvalue = innsendingServiceMapper.mapForsendelsesstatus(
               innsendingWebService.sendTilTinglysing(mappedArgs));
         return returnvalue;
      } catch( ServiceException e ) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Forsendelsesstatus hentStatus(final String innsendingId) {
      try {
         //noinspection UnnecessaryLocalVariable
         final Forsendelsesstatus returnvalue = innsendingServiceMapper.mapForsendelsesstatus(
               innsendingWebService.hentStatus(innsendingId));
         return returnvalue;
      } catch( ServiceException e ) {
         throw new RuntimeException(e);
      }
   }

}
