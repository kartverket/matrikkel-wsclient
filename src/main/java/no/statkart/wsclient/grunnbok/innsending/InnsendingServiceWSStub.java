package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.collect.Maps;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus.ForsendelsesstatusBuilder;

import java.util.Map;

/**
 * Stub implementation.
 */
public class InnsendingServiceWSStub implements InnsendingServiceWS {

   private Map<String, Forsendelsesstatus> tinglysMeldingResponseByInnsendingId = Maps.newHashMap();

   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) {
      throw new UnsupportedOperationException("Koster penger!");
   }

   @Override
   public Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse) {
      String forsendelsesreferanse = forsendelse.getForsendelsesreferanse();
      Forsendelsesstatus forsendelsesstatus = tinglysMeldingResponseByInnsendingId.get(forsendelsesreferanse);
      if (forsendelsesstatus == null) {
         throw new RuntimeException("The stub must be provided with a Forsendelsesstatus for forsendelsesreferanse: " + forsendelsesreferanse);
      }
      return forsendelsesstatus;
   }

   @Override
   public Forsendelsesstatus hentStatus(String forsendelsesreferanse) {
      Forsendelsesstatus forsendelsesstatus = tinglysMeldingResponseByInnsendingId.get(forsendelsesreferanse);
      if (forsendelsesstatus == null) {
         throw new RuntimeException("The stub must be provided with a Forsendelsesstatus for forsendelsesreferanse: " + forsendelsesreferanse);
      }
      return forsendelsesstatus;
   }

   public void behandlingStatusForInnsendingId(String forsendelsesreferanse, ForsendelsesstatusBuilder statusBuilder) {
      tinglysMeldingResponseByInnsendingId.put(forsendelsesreferanse, statusBuilder.build());
   }

}
