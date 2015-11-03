package no.statkart.wsclient.grunnbok.registerenhetsrettsandel;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.RegisterenhetsrettIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.person.PersonIdList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.registerenhetsrettsandel.RegisterenhetsrettsandelService;
import no.kartverket.grunnbok.wsapi.v1.service.registerenhetsrettsandel.RegisterenhetsrettsandelServiceWS;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.PersonIdTilRegisterenhetsrettsandelIdsMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultRegisterenhetsrettsandelWS implements RegisterenhetsrettsandelWS {

   private final RegisterenhetsrettsandelService registerenhetsrettsandelService;

   public DefaultRegisterenhetsrettsandelWS(String brukernavn, String passord, String endpointUrl, HostnameVerifier hostnameVerifier) {
      registerenhetsrettsandelService = WebServiceBuilder.builder(
            new RegisterenhetsrettsandelServiceWS().getRegisterenhetsrettsandelServicePort(), RegisterenhetsrettsandelService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }


   @Override
   public PersonIdTilRegisterenhetsrettsandelIdsMap findAndelerForRettighetshaverePerson(PersonIdList personIds, GrunnbokContext grunnbokContext) throws ServiceException {
      return registerenhetsrettsandelService.findAndelerForRettighetshaverePerson(personIds, grunnbokContext);
   }

   @Override
   public RegisterenhetsrettIdTilRegisterenhetsrettsandelIdsMap findAndelerIRetter(RegisterenhetsrettIdList rettIds, GrunnbokContext grunnbokContext) throws ServiceException {
      return registerenhetsrettsandelService.findAndelerIRetter(rettIds, grunnbokContext);
   }
}
