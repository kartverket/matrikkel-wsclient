package no.statkart.wsclient.grunnbok.rettsstiftelse;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.RegisterenhetsrettsandelIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v1.domain.register.rettsstiftelse.RettsstiftelseIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.rettsstiftelse.heftelse.HeftelseIdList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.rettsstiftelse.RettsstiftelseService;
import no.kartverket.grunnbok.wsapi.v1.service.rettsstiftelse.RettsstiftelseServiceWS;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetsendringIdListTransfer;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.ServituttIdListTransfer;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.TransferMode;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;


public class DefaultRettsstiftelseWS implements RettsstiftelseWS {

   private final RettsstiftelseService rettsstiftelseService;

   public DefaultRettsstiftelseWS(String brukernavn, String passord, String endpointUrl, HostnameVerifier hostnameVerifier) {
      rettsstiftelseService = WebServiceBuilder.builder(new RettsstiftelseServiceWS().getRettsstiftelseServicePort())
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }

   @Override
   public RegisterenhetsendringIdListTransfer findRegisterenhetsendringer(RegisterenhetId registerenhetId, TransferMode transferMode, GrunnbokContext grunnbokContext) throws ServiceException {
      return rettsstiftelseService.findRegisterenhetsendringer(registerenhetId, transferMode, grunnbokContext);
   }

   @Override
   public HeftelseIdList findHeftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return rettsstiftelseService.findHeftelser(registerenhetId, grunnbokContext);
   }

   @Override
   public RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findOverdragelserMedAktiveAndelerIRegisterenhet(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException {
      return rettsstiftelseService.findOverdragelserMedAktiveAndelerIRegisterenhet(registerenhetId, grunnbokContext);
   }

   @Override
   public RettsstiftelseIdList findRettsstiftelserForDokument(DokumentId dokumentId, GrunnbokContext grunnbokContext) throws ServiceException {
      return rettsstiftelseService.findRettsstiftelserForDokument(dokumentId, grunnbokContext);
   }

   @Override
   public RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findRettsstiftelserMedNyeAndeler(RegisterenhetsrettsandelIdList andeler, GrunnbokContext grunnbokContext) throws ServiceException {
      return rettsstiftelseService.findRettsstiftelserMedNyeAndeler(andeler, grunnbokContext);
   }

   @Override
   public RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findRettsstiftelserMedUtgaatteAndeler(RegisterenhetsrettsandelIdList andeler, GrunnbokContext grunnbokContext) throws ServiceException {
      return rettsstiftelseService.findRettsstiftelserMedUtgaatteAndeler(andeler, grunnbokContext);
   }

   @Override
   public ServituttIdListTransfer findServitutter(RegisterenhetId registerenhetId, TransferMode transferMode, GrunnbokContext grunnbokContext) throws ServiceException {
      return rettsstiftelseService.findServitutter(registerenhetId, transferMode, grunnbokContext);
   }
}
