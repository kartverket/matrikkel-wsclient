package no.statkart.wsclient.grunnbokv2.registerenhetsrettsandel;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.person.PersonIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettIdList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.registerenhetsrettsandel.RegisterenhetsrettsandelService;
import no.kartverket.grunnbok.wsapi.v2.service.registerenhetsrettsandel.RegisterenhetsrettsandelServiceWS;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.PersonIdTilRegisterenhetsrettsandelIdsMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap;
import no.statkart.wsclient.WebServiceBuilder;

public class DefaultRegisterenhetsrettsandelWS implements RegisterenhetsrettsandelWS {

   private static RegisterenhetsrettsandelServiceWS registerenhetsrettsandelServiceWS;

   private final RegisterenhetsrettsandelService registerenhetsrettsandelService;

   public DefaultRegisterenhetsrettsandelWS(String brukernavn, String passord, String endpointUrl) {
      if (registerenhetsrettsandelServiceWS == null) {
         synchronized (this) {
            if (registerenhetsrettsandelServiceWS == null) {
               registerenhetsrettsandelServiceWS = new RegisterenhetsrettsandelServiceWS();
            }
         }
      }

      registerenhetsrettsandelService = WebServiceBuilder.builderv2(
            registerenhetsrettsandelServiceWS.getRegisterenhetsrettsandelServicePort(), RegisterenhetsrettsandelService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
   }


   @Override
   public PersonIdTilRegisterenhetsrettsandelIdsMap findAndelerForRettighetshavere(PersonIdList personIds, GrunnbokContext grunnbokContext) throws ServiceException {
      return registerenhetsrettsandelService.findAndelerForRettighetshavere(personIds, grunnbokContext);
   }

   @Override
   public RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap findAndelerIRetter(RegisterenhetsrettIdList rettIds, GrunnbokContext grunnbokContext) throws ServiceException {
      return registerenhetsrettsandelService.findAndelerIRetter(rettIds, grunnbokContext);
   }
}
