package no.statkart.wsclient.grunnbok.innsending;

import no.statkart.wsclient.grunnbok.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse;
import no.statkart.wsclient.grunnbok.innsending.domene.UsignertMelding;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus.RettsstiftelsesinformasjonBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.DokumentBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.ForsendelseBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.MatrikkelenhetsendringBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.UsignertMeldingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class InnsendingServiceWSProvider {

   private static Logger logger = LoggerFactory.getLogger(InnsendingServiceWSProvider.class);

   private InnsendingServiceWSProvider() {
   }

   public interface InitRequirements {

      boolean inProductionMode();

      /**
       * Only relevant if inProductionMode returns false
       *
       * @return true if a stubbed version is wanted
       */
      boolean doReturnStub();

      String getBruker();

      String getPassord();

      String getEndpointUrl();
   }

   public static InnsendingServiceWS getInnsendingServiceImplementation(InitRequirements initRequirements) {
      if (initRequirements.inProductionMode()) {
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
/*

   public static void testSendInnTilTinglysing() {
      ArrayList<Rettsstiftelse> rettstiftelser = new ArrayList<Rettsstiftelse>();
      rettstiftelser.add(MatrikkelenhetsendringBuilder.aFradeling().withFra(null).withTil(null).build());
      List<Dokument> dokument = new ArrayList<Dokument>();
      dokument.add(DokumentBuilder.aDokument().withRettsstiftelser(rettstiftelser).build());
      UsignertMelding usignertMelding = UsignertMeldingBuilder.anUsignertMelding().withDokumenter(dokument).build();
      getInnsendingServiceImplementation().sendTilTinglysing(ForsendelseBuilder.aForsendelse().withUsignertMelding(usignertMelding));
   }
*/

}
