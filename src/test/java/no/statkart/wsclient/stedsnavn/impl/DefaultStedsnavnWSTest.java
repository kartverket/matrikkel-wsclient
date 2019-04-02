package no.statkart.wsclient.stedsnavn.impl;

import no.statkart.stedsnavn.matrikkelinndata.v1.adressenavn.AarsakTilEndringFraMatrikkelenKode;
import no.statkart.stedsnavn.matrikkelinndata.v1.adressenavn.AdressenavnInndata;
import no.statkart.wsclient.stedsnavn.EndretVegRequest;
import no.statkart.wsclient.stedsnavn.StedsnavnWS;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

import static no.statkart.wsclient.stedsnavn.AarsakTilEndringFraMatrikkelen.RETTELSE_AV_FEILFOERING;

@Test
public class DefaultStedsnavnWSTest {

   @Spy
   private AdressenavnInndata webservice;
   private StedsnavnWS stedsnavnWS;

   @BeforeMethod
   public void before() {
      MockitoAnnotations.initMocks(this);
      stedsnavnWS = new DefaultStedsnavnWS(webservice);
   }

   public void sendEndretVeg() throws Exception{
      LocalDate vedtaksdato = LocalDate.of(2019, Month.APRIL, 2);
      stedsnavnWS.sendEndretVegTilStedsnavn(EndretVegRequest.Builder.builder()
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

      Mockito.verify(webservice).sendEndretVegTilStedsnavn(1L, "0301", 9.8, 23.3, "KOORD", 2,
            "Navn", "kort", vedtaksdato, AarsakTilEndringFraMatrikkelenKode.RETTELSE_AV_FEILFOERING);
   }

}
