package no.statkart.wsclient.grunnbokv2.rettsstiftelse;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.RegisterenhetsrettsandelIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.RettsstiftelseIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.heftelse.HeftelseIdList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.*;

public interface RettsstiftelseWS {
   RegisterenhetsendringIdListTransfer findRegisterenhetsendringer(RegisterenhetId registerenhetId, TransferMode transferMode,
                                                                   GrunnbokContext grunnbokContext) throws ServiceException;

   HeftelseIdListTransfer findHeftelser(RegisterenhetId registerenhetId, TransferMode transferMode, GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findOverdragelserMedAktiveAndelerIRegisterenhet(RegisterenhetId registerenhetId,
                                                                                                                      GrunnbokContext grunnbokContext) throws ServiceException;

   RettsstiftelseIdList findRettsstiftelserForDokument(DokumentId dokumentId, GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findRettsstiftelserMedNyeAndeler(RegisterenhetsrettsandelIdList andeler,
                                                                                                       GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findRettsstiftelserMedUtgaatteAndeler(RegisterenhetsrettsandelIdList andeler,
                                                                                                            GrunnbokContext grunnbokContext) throws ServiceException;
}
