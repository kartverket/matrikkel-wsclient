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
   public void sendNyVegTilStedsnavn(NyVegRequest request) {
      try {
         webservice.sendNyVegTilStedsnavn(request.getTidsstempel(), request.getVegId(), request.getKommunenummer(), request.getOst(), request.getNord(),
               request.getKoordinatsystem(), request.getAdressekode(), request.getAdressenavn(), request.getKortnavn(), request.getVedtaksdato());
      } catch (ApplicationException | SystemException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public void sendEndretVegTilStedsnavn(EndretVegRequest request) {
      try {
         webservice.sendEndretVegTilStedsnavn(request.getTidsstempel(), request.getVegId(), request.getKommunenummer(), request.getOst(),
               request.getNord(), request.getKoordinatsystem(), request.getAdressekode(), request.getAdressenavn(), request.getKortnavn(),
               request.getVedtaksdato(), mapAarsakTilEndring(request.getAarsakTilEndring()));
      } catch (ApplicationException | SystemException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public void sendSlettetVegTilStedsnavn(SlettetVegRequest request) {
      try {
         webservice.sendSlettetVegTilStedsnavn(request.getTidsstempel(), request.getVegId(), request.getKommunenummer(), request.getAdressekode());
      } catch (ApplicationException | SystemException e) {
         throw new RuntimeException(e);
      }
   }

   private AarsakTilEndringFraMatrikkelenKode mapAarsakTilEndring(AarsakTilEndringFraMatrikkelen aarsakTilEndring) {
      try {
         return AarsakTilEndringFraMatrikkelenKode.fromValue(aarsakTilEndring.getKodeverdi());
      } catch (IllegalArgumentException ie) {
         throw new RuntimeException(String.format("Mismatch domene/ws-objekt for kodeverdi: %s", aarsakTilEndring.getKodeverdi()), ie);
      }
   }
}
