package no.statkart.wsclient.grunnbokv2.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.MatrikkelenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IdentWS {

    Map<MatrikkelenhetIdent, MatrikkelenhetId> findMatrikkelenhetIdsForIdents(Collection<MatrikkelenhetIdent> idents, GrunnbokContext grunnbokContext) throws ServiceException;

    Map<DokumentIdent, DokumentId> findDokumentIdsForIdents(Collection<DokumentIdent> idents, GrunnbokContext grunnbokContext) throws ServiceException;
    default DokumentId findDokumentIdForIdent(DokumentIdent ident, GrunnbokContext grunnbokContext) throws ServiceException {
        Map<DokumentIdent, DokumentId> dokumentIdsForIdents = findDokumentIdsForIdents(List.of(ident), grunnbokContext);
        if (dokumentIdsForIdents.isEmpty()) return null;
        return dokumentIdsForIdents.values().iterator().next();
    }

    static DokumentIdent dokumentIdent(int dokumentaar, long dokumentnummer, String embetenummer) {
        DokumentIdent ident = new DokumentIdent();
        ident.setDokumentaar(dokumentaar);
        ident.setDokumentnummer(dokumentnummer);
        ident.setEmbetenummer(embetenummer);
        return ident;
    }

    static MatrikkelenhetIdent matrikkelenhetIdent(String kommunenummer, int gardsnummer, int bruksnummer) {
        return matrikkelenhetIdent(kommunenummer, gardsnummer, bruksnummer, 0, 0);
    }

    static MatrikkelenhetIdent matrikkelenhetIdent(String kommunenummer, int gardsnummer, int bruksnummer, int festenummer, int seksjonsnummer) {
        final MatrikkelenhetIdent matrikkelenhetIdent = new MatrikkelenhetIdent();
        matrikkelenhetIdent.setKommunenummer(kommunenummer);
        matrikkelenhetIdent.setGaardsnummer(gardsnummer);
        matrikkelenhetIdent.setBruksnummer(bruksnummer);
        matrikkelenhetIdent.setFestenummer(festenummer);
        matrikkelenhetIdent.setSeksjonsnummer(seksjonsnummer);
        return matrikkelenhetIdent;
    }

}
