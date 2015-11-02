package no.statkart.wsclient.grunnbok.rettsstiftelse;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.register.RegisterenhetsrettsandelIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.RegisterenhetId;
import no.kartverket.grunnbok.wsapi.v1.domain.register.rettsstiftelse.RettsstiftelseIdList;
import no.kartverket.grunnbok.wsapi.v1.domain.register.rettsstiftelse.heftelse.HeftelseIdList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetsendringIdListTransfer;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.ServituttIdListTransfer;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.TransferMode;

/**
 *
 */
public interface RettsstiftelseWS {
   RegisterenhetsendringIdListTransfer findRegisterenhetsendringer(RegisterenhetId registerenhetId, TransferMode transferMode,
                                                                   GrunnbokContext grunnbokContext) throws ServiceException;

   HeftelseIdList findHeftelser(RegisterenhetId registerenhetId, GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findOverdragelserMedAktiveAndelerIRegisterenhet(RegisterenhetId registerenhetId,
                                                                                                                      GrunnbokContext grunnbokContext) throws ServiceException;

   RettsstiftelseIdList findRettsstiftelserForDokument(DokumentId dokumentId, GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findRettsstiftelserMedNyeAndeler(RegisterenhetsrettsandelIdList andeler,
                                                                                                       GrunnbokContext grunnbokContext) throws ServiceException;

   RegisterenhetsrettsandelIdTilOverdragelseAvRegisterenhetsrettIdMap findRettsstiftelserMedUtgaatteAndeler(RegisterenhetsrettsandelIdList andeler,
                                                                                                            GrunnbokContext grunnbokContext) throws ServiceException;

   //TODO: Hvorfor behøver vi denne dersom den kun kalles fra en test?
   ServituttIdListTransfer findServitutter(RegisterenhetId registerenhetId, TransferMode transferMode, GrunnbokContext grunnbokContext) throws ServiceException;
}
