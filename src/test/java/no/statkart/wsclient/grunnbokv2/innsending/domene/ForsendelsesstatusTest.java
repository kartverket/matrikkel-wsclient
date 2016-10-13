package no.statkart.wsclient.grunnbokv2.innsending.domene;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.*;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 *
 */
@Test
public class ForsendelsesstatusTest {

   public void findUtskrift() {
      MatrikkelenhetBuilder enhetINittedalBuilder = MatrikkelenhetBuilder.aMatrikkelenhet()
            .withKommunenummer("100000201")
            .withKommunenavn("Nittedal")
            .withGaardsnummer(2)
            .withBruksnummer(29)
            .withFestenummer(0)
            .withSeksjonsnummer(0);
      Matrikkelenhet enhetINittedal = enhetINittedalBuilder.build();

      int seksjonsnummer = 1;
      Matrikkelenhet enhetINittedalWithDifferentSeksjonsnummer = enhetINittedalBuilder.but()
            .withSeksjonsnummer(seksjonsnummer).build();

      SignertGrunnboksutskriftBuilder utskriftBuilder1 = SignertGrunnboksutskriftBuilder.aSignertGrunnboksutskrift()
            .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                  .withMatrikkelenhet(enhetINittedal)
                  .build())
            .withSignertUtskrift(SDODokumentBuilder.aSDODokument()
                  .withSignertDokument("Dokument1".getBytes())
                  .build());

      byte[] pdf2 = "Dokument2".getBytes();
      SignertGrunnboksutskriftBuilder utskriftBuilder2 = SignertGrunnboksutskriftBuilder.aSignertGrunnboksutskrift()
            .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                  .withMatrikkelenhet(enhetINittedalWithDifferentSeksjonsnummer)
                  .build())
            .withSignertUtskrift(SDODokumentBuilder.aSDODokument()
                  .withSignertDokument(pdf2)
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
                        .withPaavirkerRegisterenheterList(Lists.newArrayList(RegisterenhetBuilder.aRegisterenhet()
                              .withMatrikkelenhet(MatrikkelenhetBuilder.aMatrikkelenhet()
                                    .withKommunenummer("0233")
                                    .withGaardsnummer(12)
                                    .withBruksnummer(13)
                                    .build())
                              .build()))
                        .build()))
                  .withSignerteGrunnboksutskrifter(Lists.newArrayList(utskriftBuilder1.build(), utskriftBuilder2.build()))
                  .build())
            .build();

      SignertGrunnboksutskrift foundUtskrift = forsendelsesstatus.findBekreftetGrunnboksutskriftForMatrikkelenhet(enhetINittedalWithDifferentSeksjonsnummer);
      assertEquals(foundUtskrift.getGjelderFor().getMatrikkelenhet().getSeksjonsnummer(), seksjonsnummer);
      assertEquals(foundUtskrift.getSignertUtskrift().getSignertDokument(), pdf2);
   }
}
