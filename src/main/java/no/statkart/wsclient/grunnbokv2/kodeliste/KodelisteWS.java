package no.statkart.wsclient.grunnbokv2.kodeliste;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.Timestamp;
import no.kartverket.grunnbok.wsapi.v2.domain.kodeliste.KodelisteTransfer;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

public interface KodelisteWS {

    KodelisteTransfer getKodelister(Timestamp timestamp, GrunnbokContext context) throws ServiceException;
}
