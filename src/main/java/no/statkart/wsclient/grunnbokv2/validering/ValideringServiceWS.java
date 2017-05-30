package no.statkart.wsclient.grunnbokv2.validering;

import no.statkart.wsclient.grunnbokv2.innsending.InnsendingServiceWSProvider;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ValideringServiceWS {
   Forsendelsesstatus valider(Forsendelse forsendelse);

   class Accessor {
      private static Logger logger = LoggerFactory.getLogger(Accessor.class);

      public static ValideringServiceWS get(InnsendingServiceWSProvider.InitRequirements initReqs) {
         ValideringServiceWS valideringService;
         if (!initReqs.inProductionDeployment() && initReqs.doReturnStub()) {
            valideringService = new MockValideringServiceWS();
         } else {
            valideringService = new DefaultValideringServiceWS(
                  initReqs.getBruker(), initReqs.getPassord(), initReqs.getEndpointUrl(), false);
         }
         logger.debug("Returned {} as {} implementation, {}in production mode",
               valideringService.getClass().getSimpleName(),
               ValideringServiceWS.class.getSimpleName(),
               initReqs.inProductionDeployment() ? "" : "not ");
         return valideringService;
      }
   }

}
