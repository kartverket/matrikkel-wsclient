package no.statkart.wsclient.stedsnavn.impl;

import no.statkart.stedsnavn.matrikkelinndata.v1.adressenavn.*;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.stedsnavn.*;

@SuppressWarnings("unused")
public class DefaultStedsnavnWS implements StedsnavnWS {

   private static AdressenavnInndataWS webserviceClient;
   private final AdressenavnInndata webservice;

   public DefaultStedsnavnWS(final String brukernavn, final String passord, final String endpointUrl) {
      if (webserviceClient == null) {
         synchronized (this) {
            if (webserviceClient == null) {
               webserviceClient = new AdressenavnInndataWS();
            }
         }
      }

      webservice = WebServiceBuilder.builderv2(webserviceClient.getAdressenavnInndata(), AdressenavnInndata.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
   }

   //For test
   DefaultStedsnavnWS(AdressenavnInndata webservice) {
      this.webservice = webservice;
   }

   @Override
   public void sendNyVegTilStedsnavn(NyVegRequest nyVegRequest) {
      try {
         webservice.sendNyVegTilStedsnavn(nyVegRequest.getVegId(), nyVegRequest.getKommunenummer(), nyVegRequest.getOst(), nyVegRequest.getNord(),
               nyVegRequest.getKoordinatsystem(), nyVegRequest.getAdressekode(), nyVegRequest.getAdressenavn(), nyVegRequest.getKortnavn(), nyVegRequest.getVedtaksdato());
      } catch (ApplicationException | SystemException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public void sendEndretVegTilStedsnavn(EndretVegRequest endretVegRequest) {
      try {
         webservice.sendEndretVegTilStedsnavn(endretVegRequest.getVegId(), endretVegRequest.getKommunenummer(), endretVegRequest.getOst(),
               endretVegRequest.getNord(), endretVegRequest.getKoordinatsystem(), endretVegRequest.getAdressekode(), endretVegRequest.getAdressenavn(), endretVegRequest.getKortnavn(),
               endretVegRequest.getVedtaksdato(), mapAarsakTilEndring(endretVegRequest.getAarsakTilEndring()));
      } catch (ApplicationException | SystemException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public void sendSlettetVegTilStedsnavn(SlettetVegRequest slettetVegRequest) {
      try {
         webservice.sendSlettetVegTilStedsnavn(slettetVegRequest.getVegId(), slettetVegRequest.getKommunenummer(), slettetVegRequest.getAdressekode());
      } catch (ApplicationException | SystemException e) {
         throw new RuntimeException(e);
      }
   }

   private AarsakTilEndringFraMatrikkelenKode mapAarsakTilEndring(AarsakTilEndringFraMatrikkelen aarsakTilEndring) {
      AarsakTilEndringFraMatrikkelenKode wsKode;
      switch (aarsakTilEndring) {
         case RETTELSE_AV_FEILFOERING:
            wsKode = AarsakTilEndringFraMatrikkelenKode.RETTELSE_AV_FEILFOERING;
            break;
         case VEDTAK_GJORT_AV_KLAGENEMNDA:
            wsKode = AarsakTilEndringFraMatrikkelenKode.VEDTAK_GJORT_AV_KLAGENEMNDA;
            break;
         case VEDTAK_I_KLAGESAK:
            wsKode = AarsakTilEndringFraMatrikkelenKode.VEDTAK_I_KLAGESAK;
            break;
         case VEDTAK_I_NY_NAVNESAK:
            wsKode = AarsakTilEndringFraMatrikkelenKode.VEDTAK_I_NY_NAVNESAK;
            break;
         default:
            throw new RuntimeException(String.format("Kodeverdi som ikke er lagt inn støtte for: %s", aarsakTilEndring.getKodeverdi()));
      }
      return wsKode;
   }
}
