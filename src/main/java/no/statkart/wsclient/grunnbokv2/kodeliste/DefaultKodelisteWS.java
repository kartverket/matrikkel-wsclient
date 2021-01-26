package no.statkart.wsclient.grunnbokv2.kodeliste;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.Timestamp;
import no.kartverket.grunnbok.wsapi.v2.domain.kodeliste.KodelisteTransfer;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.kodeliste.KodelisteService;
import no.kartverket.grunnbok.wsapi.v2.service.kodeliste.KodelisteServiceWS;
import no.statkart.wsclient.WebServiceBuilder;


public class DefaultKodelisteWS implements KodelisteWS {

    private static KodelisteServiceWS kodelisteServiceWS;

    private final KodelisteService kodelisteService;

    public DefaultKodelisteWS(String brukernavn, String passord, String endpointUrl) {
        if (kodelisteServiceWS == null) {
            synchronized (this) {
                if (kodelisteServiceWS == null) {
                    kodelisteServiceWS = new KodelisteServiceWS();
                }
            }
        }

        kodelisteService = WebServiceBuilder.builderv2(kodelisteServiceWS.getKodelisteServicePort(), KodelisteService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
    }

    @Override
    public KodelisteTransfer getKodelister(Timestamp timestamp, GrunnbokContext context) throws ServiceException {
        return kodelisteService.getKodelister(timestamp, context);
    }
}
