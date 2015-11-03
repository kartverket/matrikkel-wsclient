package no.statkart.wsclient.grunnbok.registerenhetsrett;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.RegisterenhetIdList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.registerenhetsrett.RegisterenhetsrettService;
import no.kartverket.grunnbok.wsapi.v1.service.registerenhetsrett.RegisterenhetsrettServiceWS;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetIdTilRegisterenhetsrettIdsMap;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultRegisterenhetsrettWS implements RegisterenhetsrettWS {

   private final RegisterenhetsrettService registerenhetsrettService;

   public DefaultRegisterenhetsrettWS(String brukernavn, String passord, String endpointUrl, HostnameVerifier hostnameVerifier) {
      registerenhetsrettService = WebServiceBuilder.builder(new RegisterenhetsrettServiceWS().getRegisterenhetsrettServicePort(), RegisterenhetsrettService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .withHostnameVerifier(hostnameVerifier)
            .doCreateProxy()
            .build();
   }

   @Override
   public RegisterenhetIdTilRegisterenhetsrettIdsMap findRetterForEnheter(RegisterenhetIdList registerenhetIds, GrunnbokContext grunnbokContext) throws ServiceException {
      return registerenhetsrettService.findRetterForEnheter(registerenhetIds, grunnbokContext);
   }
}
