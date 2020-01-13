package no.statkart.wsclient.grunnbokv2.innsending;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class InnsendingServiceWSProvider {

   private static final Logger logger = LoggerFactory.getLogger(InnsendingServiceWSProvider.class);

   private InnsendingServiceWSProvider() {
   }

   public interface InitRequirements {

      boolean inProductionMode();

      boolean inProductionDeployment();

      /**
       * Only relevant if inProductionDeployment returns false
       *
       * @return true if a stubbed version is wanted
       */
      boolean doReturnStub();

      String getBruker();

      String getPassord();

      String getEndpointUrl();
   }

   public static InnsendingServiceWS getInnsendingServiceImplementation(InitRequirements initRequirements) {
      if (initRequirements.inProductionDeployment()) {
         logger.info("In production mode: Returning default implementation " + DefaultInnsendingServiceWS.class);
         return new DefaultInnsendingServiceWS(initRequirements.getBruker(), initRequirements.getPassord(), initRequirements.getEndpointUrl());
      } else {
         if (initRequirements.doReturnStub()) {
            logger.info("Not in production mode and configured to return stub: " + InnsendingServiceWSStub.class);
            return new InnsendingServiceWSStub();
         } else {
            logger.info("Not in production mode and returning default implementation " + DefaultInnsendingServiceWS.class);
            return new DefaultInnsendingServiceWS(initRequirements.getBruker(), initRequirements.getPassord(), initRequirements.getEndpointUrl());
         }
      }
   }

}
