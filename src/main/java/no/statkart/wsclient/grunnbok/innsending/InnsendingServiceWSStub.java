package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus.*;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;

import java.util.Map;

/**
 * Stub implementation.
 */
public class InnsendingServiceWSStub implements InnsendingServiceWS {

   private Map<String, Forsendelsesstatus> forsendelsesstatusByInnsendingIdMap = new ForsendelsesstatusProvider().createInitState();

   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) {
      throw new UnsupportedOperationException("Koster penger!");
   }

   @Override
   public Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse) {
      String forsendelsesreferanse = forsendelse.getForsendelsesreferanse();
      Forsendelsesstatus forsendelsesstatus = forsendelsesstatusByInnsendingIdMap.get(forsendelsesreferanse);
      if (forsendelsesstatus == null) {
         throw new RuntimeException("The stub must be provided with a Forsendelsesstatus for forsendelsesreferanse: " + forsendelsesreferanse);
      }
      return forsendelsesstatus;
   }

   @Override
   public Forsendelsesstatus hentStatus(String forsendelsesreferanse) {
      Forsendelsesstatus forsendelsesstatus = forsendelsesstatusByInnsendingIdMap.get(forsendelsesreferanse);
      if (forsendelsesstatus == null) {
         throw new RuntimeException("The stub must be provided with a Forsendelsesstatus for forsendelsesreferanse: " + forsendelsesreferanse);
      }
      return forsendelsesstatus;
   }

   public void behandlingStatusForInnsendingId(String forsendelsesreferanse, ForsendelsesstatusBuilder statusBuilder) {
      forsendelsesstatusByInnsendingIdMap.put(forsendelsesreferanse, statusBuilder.build());
   }

   private static class ForsendelsesstatusProvider {

      public Map<String, Forsendelsesstatus> createInitState() {
         Map<String, Forsendelsesstatus> forsendelsesstatusByInnsendingIdMap = Maps.newHashMap();

         MatrikkelenhetBuilder enhetINittedalBuilder = MatrikkelenhetBuilder.aMatrikkelenhet()
               .withKommunenummer("100000201")
               .withKommunenavn("Nittedal")
               .withGaardsnummer(2)
               .withBruksnummer(126)
               .withFestenummer(0)
               .withSeksjonsnummer(0);
         Matrikkelenhet enhetINittedal = enhetINittedalBuilder.build();

         SignertGrunnboksutskriftBuilder utskriftBuilder1 = SignertGrunnboksutskriftBuilder.aSignertGrunnboksutskrift()
               .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                     .withMatrikkelenhet(enhetINittedal)
                     .build())
               .withSignertUtskrift(SDODokumentBuilder.aSDODokument()
                     .withSignertDokument("Dokument1".getBytes())
                     .build());

         Forsendelsesstatus forsendelsesstatus = ForsendelsesstatusBuilder.aBehandlingsstatus()
               .withInnsendingId("1")
               .withForsendelsesreferanse("67XY")
               .withRegistreringstidspunkt(new LocalDateTime(2015, DateTimeConstants.OCTOBER, 16, 12, 5, 6, 178))
               .withBehandlingsutfall("OK")
               .withSaksstatus("Prosessert")
               .withTinglysingsinformasjon(TinglysingsinformasjonBuilder.aTinglysingsinformasjon()
                     .withDokumentinformasjon(Lists.newArrayList(DokumentinformasjonBuilder.aDokumentinformasjon()
                           .withDokumentnummer(22)
                           .withEmbetenummer("34")
                           .withDokumentaar(2015)
                           .withDokumentreferanse("Referanse1")
                           .withRettsstiftelsesinformasjonList(Lists.newArrayList(RettsstiftelsesinformasjonBuilder.aRettsstiftelsesinformasjon()
                                 .withRettsstiftelsesnummer(235)
                                 .withRettsstiftelsesreferanse("Xyz")
                                 .build()))
                           .build()))
                     .withSignerteGrunnboksutskrifter(Lists.newArrayList(utskriftBuilder1.build()))
                     .build())
               .build();

         forsendelsesstatusByInnsendingIdMap.put("1", forsendelsesstatus);

         return forsendelsesstatusByInnsendingIdMap;
      }
   }

}
