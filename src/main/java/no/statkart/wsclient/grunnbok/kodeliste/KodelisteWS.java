package no.statkart.wsclient.grunnbok.kodeliste;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.Timestamp;
import no.kartverket.grunnbok.wsapi.v1.domain.kodeliste.KodelisteTransfer;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;


public interface KodelisteWS {

   //TODO: Kun i bruk fra test. Kan fjernes?
   KodelisteTransfer getKodelister(Timestamp snapshotVersion, GrunnbokContext grunnbokContext) throws ServiceException;
}
