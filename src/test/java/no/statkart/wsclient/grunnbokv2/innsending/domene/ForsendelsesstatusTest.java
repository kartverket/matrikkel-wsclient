package no.statkart.wsclient.grunnbokv2.innsending.domene;

import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.DokumentinformasjonBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.ForsendelsesstatusBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.MatrikkelenhetBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.RegisterenhetBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.RettsstiftelsesinformasjonBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.TinglysingsinformasjonBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.UsignertGrunnboksutskriftBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.UsignertPDFDokumentBuilder;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

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

        UsignertGrunnboksutskriftBuilder utskriftBuilder1 = UsignertGrunnboksutskriftBuilder.aUsignertGrunnboksutskrift()
            .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                .withMatrikkelenhet(enhetINittedal)
                .build())
            .withUsignertUtskrift(UsignertPDFDokumentBuilder.aUsignertPDFDokument()
                .withUsignertDokument("Dokument1".getBytes())
                .build());

        byte[] pdf2 = "Dokument2".getBytes();
        UsignertGrunnboksutskriftBuilder utskriftBuilder2 = UsignertGrunnboksutskriftBuilder.aUsignertGrunnboksutskrift()
            .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                .withMatrikkelenhet(enhetINittedalWithDifferentSeksjonsnummer)
                .build())
            .withUsignertUtskrift(UsignertPDFDokumentBuilder.aUsignertPDFDokument()
                .withUsignertDokument(pdf2)
                .build());

        Forsendelsesstatus forsendelsesstatus = ForsendelsesstatusBuilder.aBehandlingsstatus()
            .withInnsendingId("1")
            .withForsendelsesreferanse("67XY")
            .withRegistreringstidspunkt(new LocalDateTime(2015, DateTimeConstants.OCTOBER, 16, 12, 5, 6, 178))
            .withBehandlingsutfall("OK")
            .withSaksstatus("Prosessert")
            .withTinglysingsinformasjon(TinglysingsinformasjonBuilder.aTinglysingsinformasjon()
                .withDokumentinformasjon(List.of(DokumentinformasjonBuilder.aDokumentinformasjon()
                    .withDokumentnummer(22)
                    .withEmbetenummer("34")
                    .withDokumentaar(2015)
                    .withDokumentreferanse("Referanse1")
                    .withRettsstiftelsesinformasjonList(List.of(RettsstiftelsesinformasjonBuilder.aRettsstiftelsesinformasjon()
                        .withRettsstiftelsesnummer(235)
                        .withRettsstiftelsesreferanse("Xyz")
                        .build()))
                    .withPaavirkerRegisterenheterList(List.of(RegisterenhetBuilder.aRegisterenhet()
                        .withMatrikkelenhet(MatrikkelenhetBuilder.aMatrikkelenhet()
                            .withKommunenummer("0233")
                            .withGaardsnummer(12)
                            .withBruksnummer(13)
                            .build())
                        .build()))
                    .build()))
                .withUsignerteGrunnboksutskrifter(List.of(utskriftBuilder1.build(), utskriftBuilder2.build()))
                .build())
            .build();

        UsignertGrunnboksutskrift foundUtskrift = forsendelsesstatus.findUbekreftetGrunnboksutskriftForMatrikkelenhet(enhetINittedalWithDifferentSeksjonsnummer);
        assertEquals(foundUtskrift.getGjelderFor().getMatrikkelenhet().getSeksjonsnummer(), seksjonsnummer);
        assertEquals(foundUtskrift.getUtskrift().getUsignertDokument(), pdf2);
    }
}
