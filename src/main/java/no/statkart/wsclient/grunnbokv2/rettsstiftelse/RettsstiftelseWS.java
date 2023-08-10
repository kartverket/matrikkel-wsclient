package no.statkart.wsclient.grunnbokv2.rettsstiftelse;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettsandelId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.RettsstiftelseId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.overdragelse.OverdragelseAvRegisterenhetsrettId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.HeftelseIdListTransfer;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.RegisterenhetsendringIdListTransfer;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.TransferMode;

import java.util.List;
import java.util.Map;

public interface RettsstiftelseWS {
    RegisterenhetsendringIdListTransfer findRegisterenhetsendringer(RegisterenhetId registerenhetId, TransferMode transferMode,
                                                                    GrunnbokContext grunnbokContext) throws ServiceException;

    HeftelseIdListTransfer findHeftelser(RegisterenhetId registerenhetId, TransferMode transferMode, GrunnbokContext grunnbokContext) throws ServiceException;

    Map<RegisterenhetsrettsandelId, OverdragelseAvRegisterenhetsrettId> findOverdragelserMedAktiveAndelerIRegisterenhet(RegisterenhetId registerenhetId,
                                                                                                                        GrunnbokContext grunnbokContext) throws ServiceException;

    List<RettsstiftelseId> findRettsstiftelserForDokument(DokumentId dokumentId, GrunnbokContext grunnbokContext) throws ServiceException;

}
