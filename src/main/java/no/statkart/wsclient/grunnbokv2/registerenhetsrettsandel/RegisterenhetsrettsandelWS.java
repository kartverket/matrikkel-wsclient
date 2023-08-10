package no.statkart.wsclient.grunnbokv2.registerenhetsrettsandel;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.person.PersonId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettsandelId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RegisterenhetsrettsandelWS {

    default List<RegisterenhetsrettsandelId> findAndelerForRettighetshavere(PersonId personId, GrunnbokContext grunnbokContext) throws ServiceException {
        return findAndelerForRettighetshavere(List.of(personId), grunnbokContext)
            .entrySet().iterator().next().getValue();
    }
    Map<PersonId, List<RegisterenhetsrettsandelId>> findAndelerForRettighetshavere(Collection<PersonId> personIds, GrunnbokContext grunnbokContext) throws ServiceException;

}
