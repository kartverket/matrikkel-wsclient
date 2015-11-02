package no.statkart.wsclient.grunnbok.innsending;

import com.sun.xml.ws.developer.SchemaValidationFeature;
import no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingServiceWS;
import no.kartverket.grunnbok.wsapi.internal.service.innsending.ServiceException;
import no.statkart.wsclient.HandlerResolverBuilder;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.Behandlingsstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;

public class DefaultInnsendingService implements InnsendingService {

   private final no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingService innsendingWebService;
   private InnsendingServiceMapper innsendingServiceMapper = new InnsendingServiceMapper();

   public DefaultInnsendingService(final String brukernavn, final String passord, final String endpointUrl, boolean enableSchemaValidation) {
      InnsendingServiceWS innsendingServiceWS = new InnsendingServiceWS();
      innsendingServiceWS.setHandlerResolver(HandlerResolverBuilder.builder()
            .enableLogging().build());

      no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingService innsendingServicePort = enableSchemaValidation
            ? innsendingServiceWS.getInnsendingServicePort(new SchemaValidationFeature())
            : innsendingServiceWS.getInnsendingServicePort();

      innsendingWebService = WebServiceBuilder.builder(innsendingServicePort)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .build();
   }

   public DefaultInnsendingService(final String brukernavn, final String passord, final String endpointUrl) {
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
