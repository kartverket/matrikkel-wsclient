package no.statkart.wsclient.grunnbokv2.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.DokumentIdentTilDokumentIdMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;

public interface IdentWS {

    MatrikkelenhetIdentTilMatrikkelenhetIdMap findMatrikkelenhetIdsForIdents(MatrikkelenhetIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException;

    DokumentIdentTilDokumentIdMap findDokumentIdsForIdents(DokumentIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException;

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
