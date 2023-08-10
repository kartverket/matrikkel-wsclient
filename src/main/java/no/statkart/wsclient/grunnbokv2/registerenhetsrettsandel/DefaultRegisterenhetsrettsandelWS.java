package no.statkart.wsclient.grunnbokv2.registerenhetsrettsandel;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.person.PersonId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.person.PersonIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettsandelId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettsandelIdList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.registerenhetsrettsandel.RegisterenhetsrettsandelService;
import no.kartverket.grunnbok.wsapi.v2.service.registerenhetsrettsandel.RegisterenhetsrettsandelServiceWS;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.PersonIdTilRegisterenhetsrettsandelIdsMap;
import no.statkart.wsclient.WebServiceBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<PersonId, List<RegisterenhetsrettsandelId>> findAndelerForRettighetshavere(Collection<PersonId> personIds, GrunnbokContext grunnbokContext) throws ServiceException {
        PersonIdList idList = new PersonIdList();
        idList.getItem().addAll(personIds);
        return unwrap(registerenhetsrettsandelService.findAndelerForRettighetshavere(idList, grunnbokContext));
    }

    private static Map<PersonId, List<RegisterenhetsrettsandelId>> unwrap(PersonIdTilRegisterenhetsrettsandelIdsMap personIdTilRegisterenhetsrettsandelIdsMap) {
        HashMap<PersonId, List<RegisterenhetsrettsandelId>> idMap = new HashMap<>();
        for (var entry : personIdTilRegisterenhetsrettsandelIdsMap.getEntry()) {
            idMap.put(entry.getKey(), unwrap(entry.getValue()));
        }
        return idMap;
    }

    private static List<RegisterenhetsrettsandelId> unwrap(RegisterenhetsrettsandelIdList registerenhetsrettsandelIdList) {
        if (registerenhetsrettsandelIdList == null) return null;
        return registerenhetsrettsandelIdList.getItem();
    }

}
