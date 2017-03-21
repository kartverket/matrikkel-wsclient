package no.statkart.wsclient.grunnbokv2.innsending;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.ByteStreams;
import no.statkart.wsclient.grunnbokv2.innsending.domene.*;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus.Behandlingsutfall;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Rettsstiftelse.Rettsstiftelsestype;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.DokumentinformasjonBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.ForsendelsesstatusBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.MatrikkelenhetBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.RegisterenhetBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.RettsstiftelsesinformasjonBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.SDODokumentBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.SignertGrunnboksutskriftBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.TinglysingsinformasjonBuilder;
import org.joda.time.LocalDateTime;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.Collections.singletonList;
import static no.statkart.wsclient.grunnbokv2.innsending.DefaultInnsendingServiceWS.pakkUtBekreftetGrunnboksutskrift;
import static no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.AvvisningsinformasjonBuilder.anAvvisningsinformasjon;
import static no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.BegrunnelseBuilder.aBegrunnelse;
import static no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.KontrollresultatBuilder.aKontrollresultat;

/**
 * Stub implementation.
 */
public class InnsendingServiceWSStub implements InnsendingServiceWS {

   private static int nextInnsendingId = 1;
   private static final String UAVKLART = "1";
   private static final String TINGLYST = "2";
   private static final String NEKTET = "3";
   private static final String AVVIST = "4";
   private static Map<String, Forsendelsesstatus> forsendelsesstatusByInnsendingIdMap = new ForsendelsesstatusProvider().createInitState();

   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) {
      return ForsendelsesstatusBuilder
            .aBehandlingsstatus()
            .withAvvisningsinformasjon(
                  anAvvisningsinformasjon()
                        .withKontrollresultater(Collections.singletonList(
                              aKontrollresultat().withDokumentindeks(1)
                                                 .withRettsstiftelsesindeks(1)
                                                 .withUtfall("GODKJENT")
                                                 .build()))
                        .build())
            .build();
   }

   @Override
   public Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse) {
      new InnsendingServiceMapper().mapForsendelse(forsendelse);
      Forsendelsesstatus forsendelsesstatus = createForsendelsestatus(forsendelse);
      forsendelsesstatus.setInnsendingId(createInnsendingIdFromForsendelse(forsendelse));
      return forsendelsesstatus;
   }

   @Override
   public Forsendelsesstatus hentStatus(String innsendingId) {
      final Forsendelsesstatus returnvalue = hentStatusFraStub(innsendingId);
      pakkUtBekreftetGrunnboksutskrift(returnvalue);
      return returnvalue;
   }

   private static String getNextInnseningsIdAndIncreaseSequence() {
      String innsendingsId = Integer.toString(nextInnsendingId);
      nextInnsendingId++;
      return innsendingsId;
   }

   public static Forsendelsesstatus hentStatusFraStub(String innsendingId) {
      if (Pattern.matches("\\d+,\\d+,\\d+,\\d+,\\d+", innsendingId)) {
         Forsendelsesstatus forsendelsesstatus = hentForsendelsesstatusForInnsendingId(innsendingId);
         Dokumentinformasjon dokumentinformasjon = forsendelsesstatus.getTinglysingsinformasjon().getDokumentinformasjon().iterator().next();
         dokumentinformasjon.setDokumentnummer(new Random().nextInt(Integer.MAX_VALUE));
         dokumentinformasjon.setDokumentaar(new Random().nextInt(Integer.MAX_VALUE));
         dokumentinformasjon.setEmbetenummer(getNextInnseningsIdAndIncreaseSequence());
         return forsendelsesstatus;
      } else {
         Forsendelsesstatus forsendelsesstatus = forsendelsesstatusByInnsendingIdMap.get(innsendingId);
         if (forsendelsesstatus == null) {
            throw new RuntimeException("The stub must be provided with a Forsendelsesstatus for innsendingId: " + innsendingId);
         }

         return forsendelsesstatus;
      }
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
         Forsendelsesstatus forsendelsesstatusUavklart = createForsendelsestatus(Behandlingsutfall.UAVKLART);
         forsendelsesstatusByInnsendingIdMap.put(UAVKLART, forsendelsesstatusUavklart);

         Forsendelsesstatus forsendelsestatusTinglyst = createForsendelsestatusTinglyst(Lists.newArrayList(createGrunnboksutskrift(
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
         forsendelsesstatusByInnsendingIdMap.put(TINGLYST, forsendelsestatusTinglyst);

         Forsendelsesstatus forsendelsesstatusNektet = createForsendelsestatus(Behandlingsutfall.NEKTET);
         forsendelsesstatusNektet.setBehandlingsinformasjon(anAvvisningsinformasjon()
               .withKontrollresultater(singletonList(aKontrollresultat()
                     .withUtfall("NEKTET")
                     .withNavn("TEST")
                     .withKodeverdi("9999")
                     .withDokumentindeks(1)
                     .withRettsstiftelsesindeks(1)
                     .withBegrunnelser(singletonList(aBegrunnelse()
                           .withKodeverdi("9999")
                           .withTekst("TestAvNektet")
                           .withUtfall("NEKTET")
                           .build()
                     )).build()))
               .build()
         );
         forsendelsesstatusByInnsendingIdMap.put(NEKTET, forsendelsesstatusNektet);

         Forsendelsesstatus forsendelsesstatusAvvist = createForsendelsestatus(Behandlingsutfall.AVVIST);
         forsendelsesstatusNektet.setBehandlingsinformasjon(anAvvisningsinformasjon()
               .withKontrollresultater(singletonList(aKontrollresultat()
                     .withUtfall("IKKE_GODKJENT")
                     .withNavn("TEST")
                     .withKodeverdi("9999")
                     .withDokumentindeks(1)
                     .withRettsstiftelsesindeks(1)
                     .withBegrunnelser(singletonList(aBegrunnelse()
                           .withKodeverdi("9999")
                           .withTekst("TestAvAvvist")
                           .withUtfall("IKKE_GODKJENT")
                           .build()
                     )).build()))
               .build()
         );
         forsendelsesstatusByInnsendingIdMap.put(AVVIST, forsendelsesstatusAvvist);

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
      return createForsendelsestatus(Behandlingsutfall.UAVKLART, grunnboksutskrifter);
   }

   private static Forsendelsesstatus createForsendelsestatus(Behandlingsutfall behandlingsutfall) {
      return createForsendelsestatus(behandlingsutfall, Lists.<SignertGrunnboksutskrift>newArrayList());
   }

   private static Forsendelsesstatus createForsendelsestatus(Behandlingsutfall behandlingsutfall, List<SignertGrunnboksutskrift> grunnboksutskrifter) {
      return ForsendelsesstatusBuilder.aBehandlingsstatus()
            .withInnsendingId(getNextInnseningsIdAndIncreaseSequence())
            .withForsendelsesreferanse("1")
            .withRegistreringstidspunkt(LocalDateTime.now())
            .withBehandlingsutfall(behandlingsutfall.name())
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
      List<SignertGrunnboksutskrift> grunnboksutskrifter = new ArrayList<>();
      Collection<Matrikkelenhet> matrikkelenheterIForsendelse = new ArrayList<>();
      for (Dokument dokument : forsendelse.getUsignertMelding().getDokumenter()) {
         for (Rettsstiftelse rettsstiftelse : dokument.getRettsstiftelser()) {
            if (rettsstiftelse.getRettstiftelsestype() == Rettsstiftelsestype.MATRIKKELENHETSENDRING) {
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
      String resourceName = "sdo/eksempel-SDOv1.0.xml";
      final byte[] bytes;
      try {
         bytes = ByteStreams.toByteArray(InnsendingServiceWSStub.class.getClassLoader().getResourceAsStream(resourceName));
      } catch (IOException e) {
         throw Throwables.propagate(e);
      }

      return SignertGrunnboksutskriftBuilder.aSignertGrunnboksutskrift()
            .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                  .withMatrikkelenhet(matrikkelenhet)
                  .build())
            .withSignertUtskrift(SDODokumentBuilder.aSDODokument()
                  .withSignertDokument(bytes)
                  .build()).build();
   }

   private static String createInnsendingIdFromForsendelse(Forsendelse forsendelse) {
      List<Dokument> dokumenter = forsendelse.getUsignertMelding().getDokumenter();
      List<Rettsstiftelse> rettsstiftelser = dokumenter.get(0).getRettsstiftelser();
      Rettsstiftelse rettsstiftelse = rettsstiftelser.get(0);
      if (rettsstiftelse instanceof Matrikkelenhetsendring) {
         List<Matrikkelenhet> til = ((Matrikkelenhetsendring) rettsstiftelse).getTil();
         Matrikkelenhet matrikkelenhet = til.get(0);
         return matrikkelenhet.getKommunenummer() + "," +
               String.valueOf(matrikkelenhet.getGaardsnummer()) + "," +
               String.valueOf(matrikkelenhet.getBruksnummer()) + "," +
               String.valueOf(matrikkelenhet.getFestenummer()) + "," +
               String.valueOf(matrikkelenhet.getSeksjonsnummer());
      }

      return null;
   }

   private static Forsendelsesstatus hentForsendelsesstatusForInnsendingId(String innsendingId) {
      int bruksnr = Integer.parseInt(innsendingId.split(",")[2]);

      if (bruksnr > 0 && bruksnr < 200) {
         return forsendelsesstatusByInnsendingIdMap.get(TINGLYST);
      } else if (bruksnr < 300) {
         return forsendelsesstatusByInnsendingIdMap.get(NEKTET);
      } else if (bruksnr < 400) {
         return forsendelsesstatusByInnsendingIdMap.get(AVVIST);
      } else {
         return forsendelsesstatusByInnsendingIdMap.get(UAVKLART);
      }
   }
}
