package no.statkart.wsclient.grunnbokv2.innsending;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.statkart.wsclient.grunnbokv2.innsending.domene.*;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus.Behandlingsutfall;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Rettsstiftelse.Rettsstiftelsestype;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.*;
import org.joda.time.LocalDateTime;

import java.util.*;

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

      forsendelsesstatus.getTinglysingsinformasjon().getDokumentinformasjon().iterator().next().setDokumentnummer(new Random().nextInt(Integer.MAX_VALUE));
      return forsendelsesstatus;
   }

   private static String getNextInnseningsIdAndIncreaseSequence() {
      String innsendingsId = Integer.toString(nextInnsendingId);
      nextInnsendingId++;
      return innsendingsId;
   }

   public static void forsendelseNektetTinglyst(String innsendingId) {
      Forsendelsesstatus forsendelsesstatus = forsendelsesstatusByInnsendingIdMap.get(innsendingId);
      if(forsendelsesstatus==null) {
         throw new RuntimeException("The stub has no knowledge of any entry linked to innsendingId: "+innsendingId);
      }
      forsendelsesstatus.setBehandlingsutfall(Behandlingsutfall.NEKTET.name());
   }

   public static void forsendelseTinglyst(String innsendingId) {
      Forsendelsesstatus forsendelsesstatus = forsendelsesstatusByInnsendingIdMap.get(innsendingId);
      if(forsendelsesstatus==null) {
         throw new RuntimeException("The stub has no knowledge of any entry linked to innsendingId: "+innsendingId);
      }
      forsendelsesstatus.setBehandlingsutfall(Behandlingsutfall.TINGLYST.name());
   }

   private static class ForsendelsesstatusProvider {

      public Map<String, Forsendelsesstatus> createInitState() {
         Map<String, Forsendelsesstatus> forsendelsesstatusByInnsendingIdMap = Maps.newHashMap();

         MatrikkelenhetBuilder enhetINittedalBuilder = MatrikkelenhetBuilder.aMatrikkelenhet()
               .withKommunenummer("100000201")
               .withKommunenavn("Nittedal")
               .withGaardsnummer(2)
               .withBruksnummer(45)
               .withFestenummer(0)
               .withSeksjonsnummer(0);
         Matrikkelenhet enhetINittedal = enhetINittedalBuilder.build();
         SignertGrunnboksutskrift grunnboksutskrift = createGrunnboksutskrift(enhetINittedal);
         Forsendelsesstatus forsendelsesstatus = createForsendelsestatus(Lists.newArrayList(grunnboksutskrift));
         forsendelsesstatusByInnsendingIdMap.put("1", forsendelsesstatus);

         Forsendelsesstatus forsendelsesstatus2 = createForsendelsestatusTinglyst(Lists.newArrayList(createGrunnboksutskrift(
               MatrikkelenhetBuilder.aMatrikkelenhet()
                     .withKommunenummer("100001201")
                     .withKommunenavn("Nittedal")
                     .withGaardsnummer(2)
                     .withBruksnummer(90)
                     .withFestenummer(0)
                     .withSeksjonsnummer(0)
                     .build()
         ), createGrunnboksutskrift(
               MatrikkelenhetBuilder.aMatrikkelenhet()
                     .withKommunenummer("100001201")
                     .withKommunenavn("Nittedal")
                     .withGaardsnummer(2)
                     .withBruksnummer(91)
                     .withFestenummer(0)
                     .withSeksjonsnummer(0)
                     .build()
         )), "2");
         forsendelsesstatusByInnsendingIdMap.put("2", forsendelsesstatus2);


         return forsendelsesstatusByInnsendingIdMap;
      }

      private Forsendelsesstatus createForsendelsestatusTinglyst(List<SignertGrunnboksutskrift> grunnboksutskrifter, String id) {
         return ForsendelsesstatusBuilder.aBehandlingsstatus()
               .withInnsendingId(id)
               .withForsendelsesreferanse("1")
               .withRegistreringstidspunkt(new LocalDateTime())
               .withBehandlingsutfall(Behandlingsutfall.TINGLYST.name())
               .withSaksstatus("TINGLYST")
               .withTinglysingsinformasjon(TinglysingsinformasjonBuilder.aTinglysingsinformasjon()
                     .withDokumentinformasjon(Lists.newArrayList(DokumentinformasjonBuilder.aDokumentinformasjon()
                           .withDokumentnummer(2)
                           .withEmbetenummer("34")
                           .withDokumentaar(2016)
                           .withDokumentreferanse("1")
                           .withRettsstiftelsesinformasjonList(Lists.newArrayList(RettsstiftelsesinformasjonBuilder.aRettsstiftelsesinformasjon()
                                 .withRettsstiftelsesnummer(238)
                                 .withRettsstiftelsesreferanse("Xyz")
                                 .build()))
                           .withPaavirkerRegisterenheterList(Lists.newArrayList(RegisterenhetBuilder.aRegisterenhet()
                                 .withMatrikkelenhet(MatrikkelenhetBuilder.aMatrikkelenhet()
                                       .withKommunenummer("0233")
                                       .withGaardsnummer(12)
                                       .withBruksnummer(13)
                                       .build())
                                 .build()))
                           .build()))
                     .withSignerteGrunnboksutskrifter(grunnboksutskrifter)
                     .build())
               .build();
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
                        .withPaavirkerRegisterenheterList(Lists.newArrayList(RegisterenhetBuilder.aRegisterenhet()
                              .withMatrikkelenhet(MatrikkelenhetBuilder.aMatrikkelenhet()
                                    .withKommunenummer("0233")
                                    .withGaardsnummer(12)
                                    .withBruksnummer(13)
                                    .build())
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
