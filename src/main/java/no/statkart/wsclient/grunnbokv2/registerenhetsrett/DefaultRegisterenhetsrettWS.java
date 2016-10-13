package no.statkart.wsclient.grunnbokv2.registerenhetsrett;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetIdList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.registerenhetsrett.RegisterenhetsrettService;
import no.kartverket.grunnbok.wsapi.v2.service.registerenhetsrett.RegisterenhetsrettServiceWS;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.RegisterenhetIdTilRegisterenhetsrettIdsMap;
import no.statkart.wsclient.WebServiceBuilder;

import javax.net.ssl.HostnameVerifier;

/**
 *
 */
public class DefaultRegisterenhetsrettWS implements RegisterenhetsrettWS {

   private final RegisterenhetsrettService registerenhetsrettService;

   public DefaultRegisterenhetsrettWS(String brukernavn, String passord, String endpointUrl) {
      registerenhetsrettService = WebServiceBuilder.builderv2(new RegisterenhetsrettServiceWS().getRegisterenhetsrettServicePort(), RegisterenhetsrettService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
   }

   @Override
   public RegisterenhetIdTilRegisterenhetsrettIdsMap findRetterForEnheter(RegisterenhetIdList registerenhetIds, GrunnbokContext grunnbokContext) throws ServiceException {
      return registerenhetsrettService.findRetterForEnheter(registerenhetIds, grunnbokContext);
   }
}
