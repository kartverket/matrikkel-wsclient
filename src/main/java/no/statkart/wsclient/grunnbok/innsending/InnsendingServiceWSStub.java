package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.statkart.wsclient.grunnbok.innsending.domene.*;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus.Behandlingsutfall;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse.Rettsstiftelsestype;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus.*;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Stub implementation.
 */
public class InnsendingServiceWSStub implements InnsendingServiceWS {

   private static int nextInnsendingId = 1;
   private static Map<String, Forsendelsesstatus> forsendelsesstatusByInnsendingIdMap = new ForsendelsesstatusProvider().createInitState();

   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) {
      throw new UnsupportedOperationException("Koster penger!");
   }

   @Override
   public Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse) {
      String forsendelsesreferanse = forsendelse.getForsendelsesreferanse();
      new InnsendingServiceMapper().mapForsendelse(forsendelse);
      Forsendelsesstatus forsendelsesstatus = createForsendelsestatus(forsendelse);
      forsendelsesstatusByInnsendingIdMap.put(forsendelsesstatus.getInnsendingId(), forsendelsesstatus);
      return forsendelsesstatus;
   }

   @Override
   public Forsendelsesstatus hentStatus(String innsendingId) {
      Forsendelsesstatus forsendelsesstatus = forsendelsesstatusByInnsendingIdMap.get(innsendingId);
      if (forsendelsesstatus == null) {
         throw new RuntimeException("The stub must be provided with a Forsendelsesstatus for innsendingId: " + innsendingId);
      }
      return forsendelsesstatus;
   }

   private static String getNextInnseningsIdAndIncreaseSequence() {
      String innsendingsId = Integer.toString(nextInnsendingId);
      nextInnsendingId++;
      return innsendingsId;
   }

   public static void forsendelseAvvist(String innsendingId) {
      Forsendelsesstatus forsendelsesstatus = forsendelsesstatusByInnsendingIdMap.get(innsendingId);
      if(forsendelsesstatus==null) {
         throw new RuntimeException("The stub has no knowledge of any entry linked to innsendingId: "+innsendingId);
      }
      forsendelsesstatus.setBehandlingsutfall(Behandlingsutfall.AVVIST.name());
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
         SignertGrunnboksutskrift grunnboksutskrift = createGrunnboksutskrift(enhetINittedal);
         Forsendelsesstatus forsendelsesstatus = createForsendelsestatus(Lists.newArrayList(grunnboksutskrift));
         forsendelsesstatusByInnsendingIdMap.put("1", forsendelsesstatus);
         return forsendelsesstatusByInnsendingIdMap;
      }
   }

   private static Forsendelsesstatus createForsendelsestatus(Forsendelse forsendelse) {
      List<SignertGrunnboksutskrift> grunnboksutskrifter = createGrunnboksutskrifter(forsendelse);
      return createForsendelsestatus(grunnboksutskrifter);
   }

   private static Forsendelsesstatus createForsendelsestatus(List<SignertGrunnboksutskrift> grunnboksutskrifter) {
      return ForsendelsesstatusBuilder.aBehandlingsstatus()
            .withInnsendingId(getNextInnseningsIdAndIncreaseSequence())
            .withForsendelsesreferanse("1")
            .withRegistreringstidspunkt(new LocalDateTime())
            .withBehandlingsutfall(Behandlingsutfall.UAVKLART.name())
            .withSaksstatus("UNDER_BEHANDLING")
            .withTinglysingsinformasjon(TinglysingsinformasjonBuilder.aTinglysingsinformasjon()
                  .withDokumentinformasjon(Lists.newArrayList(DokumentinformasjonBuilder.aDokumentinformasjon()
                        .withDokumentnummer(1)
                        .withEmbetenummer("34")
                        .withDokumentaar(2015)
                        .withDokumentreferanse("1")
                        .withRettsstiftelsesinformasjonList(Lists.newArrayList(RettsstiftelsesinformasjonBuilder.aRettsstiftelsesinformasjon()
                              .withRettsstiftelsesnummer(235)
                              .withRettsstiftelsesreferanse("Xyz")
                              .build()))
                        .build()))
                  .withSignerteGrunnboksutskrifter(grunnboksutskrifter)
                  .build())
            .build();
   }

   private static List<SignertGrunnboksutskrift> createGrunnboksutskrifter(Forsendelse forsendelse) {
      List<SignertGrunnboksutskrift> grunnboksutskrifter = new ArrayList<SignertGrunnboksutskrift>();
      Collection<Matrikkelenhet> matrikkelenheterIForsendelse = new ArrayList<Matrikkelenhet>();
      for (Dokument dokument : forsendelse.getUsignertMelding().getDokumenter()) {
         for (Rettsstiftelse rettsstiftelse : dokument.getRettsstiftelser()) {
            if(rettsstiftelse.getRettstiftelsestype() == Rettsstiftelsestype.MATRIKKELENHETSENDRING){
               Matrikkelenhetsendring matrikkelenhetsendring = (Matrikkelenhetsendring) rettsstiftelse;
               matrikkelenheterIForsendelse.addAll(matrikkelenhetsendring.getFra());
               matrikkelenheterIForsendelse.addAll(matrikkelenhetsendring.getTil());
            }
         }
      }
      for (Matrikkelenhet matrikkelenhet : matrikkelenheterIForsendelse) {
         grunnboksutskrifter.add(createGrunnboksutskrift(matrikkelenhet));
      }
      return grunnboksutskrifter;
   }

   private static SignertGrunnboksutskrift createGrunnboksutskrift(Matrikkelenhet matrikkelenhet) {
      return SignertGrunnboksutskriftBuilder.aSignertGrunnboksutskrift()
               .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                     .withMatrikkelenhet(matrikkelenhet)
                     .build())
               .withSignertUtskrift(SDODokumentBuilder.aSDODokument()
                     .withSignertDokument("Dokument1".getBytes())
                     .build()).build();
   }


}
