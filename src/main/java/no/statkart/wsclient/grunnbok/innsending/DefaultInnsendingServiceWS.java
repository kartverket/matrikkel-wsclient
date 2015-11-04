package no.statkart.wsclient.grunnbok.innsending;

import com.sun.xml.ws.developer.SchemaValidationFeature;
import no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingService;
import no.kartverket.grunnbok.wsapi.internal.service.innsending.ServiceException;
import no.statkart.wsclient.HandlerResolverBuilder;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.Behandlingsstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;

public class DefaultInnsendingServiceWS implements InnsendingServiceWS {

   private final InnsendingService innsendingWebService;
   private InnsendingServiceMapper innsendingServiceMapper = new InnsendingServiceMapper();

   public DefaultInnsendingServiceWS(final String brukernavn, final String passord, final String endpointUrl, boolean enableSchemaValidation) {
      no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingServiceWS webServiceClient = new no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingServiceWS();
      webServiceClient.setHandlerResolver(HandlerResolverBuilder.builder()
            .enableLogging().build());

      InnsendingService innsendingServicePort = enableSchemaValidation
            ? webServiceClient.getInnsendingServicePort(new SchemaValidationFeature())
            : webServiceClient.getInnsendingServicePort();

      innsendingWebService = WebServiceBuilder.builder(innsendingServicePort, no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .build();
   }

   public DefaultInnsendingServiceWS(final String brukernavn, final String passord, final String endpointUrl) {
      this(brukernavn, passord, endpointUrl, false);
   }

   @Override
   public String allokerInnsendingId() {
      try {
         return innsendingWebService.allokerInnsendingId();
      } catch( ServiceException e ) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Behandlingsstatus validerMelding(Forsendelse forsendelse) {
      try {
         return innsendingServiceMapper.mapBehandlingsstatus(innsendingWebService.validerMelding(innsendingServiceMapper.mapForsendelse(forsendelse)));
      } catch( ServiceException e ) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Behandlingsstatus tinglysMelding(Forsendelse forsendelse) {
      try {
         return innsendingServiceMapper.mapBehandlingsstatus(innsendingWebService.tinglysMelding(innsendingServiceMapper.mapForsendelse(forsendelse)));
      } catch( ServiceException e ) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Behandlingsstatus findBehandlingsstatus(String innsendingId) {
      try {
         return innsendingServiceMapper.mapBehandlingsstatus(innsendingWebService.findBehandlingsstatus(innsendingId));
      } catch( ServiceException e ) {
         throw new RuntimeException(e);
      }
   }

}
