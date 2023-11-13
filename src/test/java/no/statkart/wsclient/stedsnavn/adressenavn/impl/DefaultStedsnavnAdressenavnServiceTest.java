package no.statkart.wsclient.stedsnavn.adressenavn.impl;

import no.statkart.stedsnavn.matrikkelinndata.v1.adressenavn.AarsakTilEndringFraMatrikkelenKode;
import no.statkart.stedsnavn.matrikkelinndata.v1.adressenavn.AdressenavnInndata;
import no.statkart.wsclient.stedsnavn.adressenavn.EndretVegRequest;
import no.statkart.wsclient.stedsnavn.adressenavn.StedsnavnAdressenavnService;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static no.statkart.wsclient.stedsnavn.adressenavn.AarsakTilEndringFraMatrikkelen.RETTELSE_AV_FEILFOERING;

@Test
public class DefaultStedsnavnAdressenavnServiceTest {

    @Spy
    private AdressenavnInndata webservice;
    private StedsnavnAdressenavnService stedsnavnAdressenavnService;

    @BeforeMethod
    public void before() {
        MockitoAnnotations.openMocks(this);
        stedsnavnAdressenavnService = new DefaultStedsnavnAdressenavnService(webservice);
    }

    public void sendEndretVeg() throws Exception {
        LocalDate vedtaksdato = LocalDate.of(2019, Month.APRIL, 2);
        LocalDateTime tidsstempel = LocalDateTime.of(2019, Month.APRIL, 4, 16, 33, 28, 200);
        stedsnavnAdressenavnService.sendEndretVegTilStedsnavn(EndretVegRequest.Builder.builder()
            .tidsstempel(tidsstempel)
            .vegId(1L)
            .adressekode(2)
            .adressenavn("Navn")
            .kommunenummer("0301")
            .koordinatsystem("KOORD")
            .kortnavn("kort")
            .nord(23.3)
            .ost(9.8)
            .vedtaksdato(vedtaksdato)
            .aarsakTilEndring(RETTELSE_AV_FEILFOERING)
            .build());

        Mockito.verify(webservice).sendEndretVegTilStedsnavn(tidsstempel, 1L, "0301", 9.8, 23.3, "KOORD", 2,
            "Navn", "kort", vedtaksdato, AarsakTilEndringFraMatrikkelenKode.RETTELSE_AV_FEILFOERING);
    }

}
