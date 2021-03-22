package no.statkart.wsclient.grunnbokv2.innsending;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.ByteStreams;
import no.kartverket.grunnbok.wsapi.v2.service.internutskrift.UbekreftetGrunnboksutskrift;
import no.statkart.wsclient.grunnbokv2.innsending.domene.*;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus.Behandlingsutfall;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Rettsstiftelse.Rettsstiftelsestype;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.*;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.Collections.singletonList;
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
   public Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse) {
      new InnsendingServiceMapper().mapForsendelse(forsendelse);
      Forsendelsesstatus forsendelsesstatus = createForsendelsestatus(forsendelse);
      forsendelsesstatus.setInnsendingId(createInnsendingIdFromForsendelse(forsendelse));
      return forsendelsesstatus;
   }

   @Override
   public Forsendelsesstatus hentStatus(String innsendingId) {
      final Forsendelsesstatus returnvalue = hentStatusFraStub(innsendingId);
      return returnvalue;
   }

   private static String getNextInnseningsIdAndIncreaseSequence() {
      String innsendingsId = Integer.toString(nextInnsendingId);
      nextInnsendingId++;
      return innsendingsId;
   }

   public static Forsendelsesstatus hentStatusFraStub(String innsendingId) {
      if (Pattern.matches("\\d+,\\d+,\\d+,\\d+,\\d+(,Forretning\\[(\\d+)\\])?", innsendingId)) {
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

      private Forsendelsesstatus createForsendelsestatusTinglyst(List<UsignertGrunnboksutskrift> grunnboksutskrifter, String id) {
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
                     .withUsignerteGrunnboksutskrifter(grunnboksutskrifter)
                     .build())
               .build();
      }
   }

   private static Forsendelsesstatus createForsendelsestatus(Forsendelse forsendelse) {
      List<UsignertGrunnboksutskrift> grunnboksutskrifter = createGrunnboksutskrifter(forsendelse);
      return createForsendelsestatus(Behandlingsutfall.UAVKLART, grunnboksutskrifter);
   }

   private static Forsendelsesstatus createForsendelsestatus(Behandlingsutfall behandlingsutfall) {
      return createForsendelsestatus(behandlingsutfall, Lists.newArrayList());
   }

   private static Forsendelsesstatus createForsendelsestatus(Behandlingsutfall behandlingsutfall, List<UsignertGrunnboksutskrift> grunnboksutskrifter) {
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
                  .withUsignerteGrunnboksutskrifter(grunnboksutskrifter)
                  .build())
            .build();
   }

   private static List<UsignertGrunnboksutskrift> createGrunnboksutskrifter(Forsendelse forsendelse) {
      List<UsignertGrunnboksutskrift> grunnboksutskrifter = new ArrayList<>();
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

   private static UsignertGrunnboksutskrift createGrunnboksutskrift(Matrikkelenhet matrikkelenhet) {
       String resourceName = "sdo/eksempel-SDOv1.0.pdf";
       final byte[] bytes;
       try {
           bytes = ByteStreams.toByteArray(InnsendingServiceWSStub.class.getClassLoader().getResourceAsStream(resourceName));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       return UsignertGrunnboksutskriftBuilder.aUsignertGrunnboksutskrift()
           .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
               .withMatrikkelenhet(matrikkelenhet)
               .build())
           .withLink("http://www.test.no")
           .withUsignertUtskrift(UsignertPDFDokumentBuilder.aUsignertPDFDokument()
               .withUsignertDokument(bytes)
               .build())
           .build();
   }

   private static String createInnsendingIdFromForsendelse(Forsendelse forsendelse) {
      List<Dokument> dokumenter = forsendelse.getUsignertMelding().getDokumenter();
      Dokument dokument = dokumenter.get(0);
      List<Rettsstiftelse> rettsstiftelser = dokument.getRettsstiftelser();
      Rettsstiftelse rettsstiftelse = rettsstiftelser.get(0);
      if (rettsstiftelse instanceof Matrikkelenhetsendring) {
         List<Matrikkelenhet> til = ((Matrikkelenhetsendring) rettsstiftelse).getTil();
         Matrikkelenhet matrikkelenhet = til.get(0);
         return matrikkelenhet.getKommunenummer() + "," +
               String.valueOf(matrikkelenhet.getGaardsnummer()) + "," +
               String.valueOf(matrikkelenhet.getBruksnummer()) + "," +
               String.valueOf(matrikkelenhet.getFestenummer()) + "," +
               String.valueOf(matrikkelenhet.getSeksjonsnummer()+ "," +
               dokument.getDokumentreferanse());
      }

      return null;
   }

   private static Forsendelsesstatus hentForsendelsesstatusForInnsendingId(String innsendingId) {
      int bruksnr = Integer.parseInt(innsendingId.split(",")[2]);

      if (bruksnr > 0 && bruksnr < 2000) {
         return forsendelsesstatusByInnsendingIdMap.get(TINGLYST);
      } else if (bruksnr < 3000) {
         return forsendelsesstatusByInnsendingIdMap.get(NEKTET);
      } else if (bruksnr < 4000) {
         return forsendelsesstatusByInnsendingIdMap.get(AVVIST);
      } else {
         return forsendelsesstatusByInnsendingIdMap.get(UAVKLART);
      }
   }
}
