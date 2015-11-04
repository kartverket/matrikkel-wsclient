package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.collect.Maps;
import no.statkart.wsclient.grunnbok.innsending.domene.Behandlingsstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus.BehandlingsstatusBuilder;

import java.util.Map;

/**
 * Stub implementation.
 */
public class InnsendingServiceWSStub implements InnsendingServiceWS {

   private int innsendingIdCounter = 0;
   private Map<Integer, Behandlingsstatus> tinglysMeldingResponseByInnsendingId = Maps.newHashMap();

   @Override
   public String allokerInnsendingId() {
      innsendingIdCounter++;
      return String.valueOf(innsendingIdCounter);
   }

   @Override
   public Behandlingsstatus validerMelding(Forsendelse forsendelse) {
      throw new UnsupportedOperationException("Koster penger!");
   }

   @Override
   public Behandlingsstatus tinglysMelding(Forsendelse forsendelse) {
      int innsendingId = Integer.valueOf(forsendelse.getInnsendingId());
      Behandlingsstatus behandlingsstatus = tinglysMeldingResponseByInnsendingId.get(innsendingId);
      if( behandlingsstatus == null ) {
         throw new RuntimeException("The stub must be provided with a Behandlingsstatus for innsendingId: " + innsendingId);
      }
      return behandlingsstatus;
   }

   @Override
   public Behandlingsstatus findBehandlingsstatus(String innsendingId) {
      int innsendingIdAsInt = Integer.valueOf(innsendingId);
      Behandlingsstatus behandlingsstatus = tinglysMeldingResponseByInnsendingId.get(innsendingIdAsInt);
      if( behandlingsstatus == null ) {
         throw new RuntimeException("The stub must be provided with a Behandlingsstatus for innsendingId: " + innsendingIdAsInt);
      }
      return behandlingsstatus;
   }

   public void behandlingStatusForInnsendingId(int innsendingId, BehandlingsstatusBuilder statusBuilder) {
      tinglysMeldingResponseByInnsendingId.put(innsendingId, statusBuilder.build());
   }

}
