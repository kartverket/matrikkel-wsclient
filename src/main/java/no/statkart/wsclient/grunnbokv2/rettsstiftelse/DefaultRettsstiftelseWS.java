package no.statkart.wsclient.grunnbokv2.rettsstiftelse;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettsandelIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.RettsstiftelseIdList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.rettsstiftelse.RettsstiftelseService;
import no.kartverket.grunnbok.wsapi.v2.service.rettsstiftelse.RettsstiftelseServiceWS;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.HeftelseIdListTransfer;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.RegisterenhetsendringIdListTransfer;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.TransferMode;
import no.statkart.wsclient.WebServiceBuilder;


public class DefaultRettsstiftelseWS implements RettsstiftelseWS {

    private static RettsstiftelseServiceWS rettsstiftelseServiceWS;

    private final RettsstiftelseService rettsstiftelseService;

    public DefaultRettsstiftelseWS(String brukernavn, String passord, String endpointUrl) {
        if (rettsstiftelseServiceWS == null) {
            synchronized (this) {
                if (rettsstiftelseServiceWS == null) {
                    rettsstiftelseServiceWS = new RettsstiftelseServiceWS();
                }
            }
        }

        rettsstiftelseService = WebServiceBuilder.builderv2(rettsstiftelseServiceWS.getRettsstiftelseServicePort(), RettsstiftelseService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
    }

    @Override
    public RegisterenhetsendringIdListTransfer findRegisterenhetsendringer(RegisterenhetId registerenhetId, TransferMode transferMode, GrunnbokContext grunnbokContext) throws ServiceException {
        return rettsstiftelseService.findRegisterenhetsendringer(registerenhetId, transferMode, grunnbokContext);
    }

    @Override
    public HeftelseIdListTransfer findHeftelser(RegisterenhetId registerenhetId, TransferMode transferMode, GrunnbokContext grunnbokContext) throws ServiceException {
        return rettsstiftelseService.findHeftelser(registerenhetId, transferMode, grunnbokContext);
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

}
