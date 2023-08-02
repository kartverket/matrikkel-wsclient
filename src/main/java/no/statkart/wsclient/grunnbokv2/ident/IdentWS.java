package no.statkart.wsclient.grunnbokv2.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.DokumentIdentTilDokumentIdMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;

public interface IdentWS {

    MatrikkelenhetIdentTilMatrikkelenhetIdMap findMatrikkelenhetIdsForIdents(MatrikkelenhetIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException;

    DokumentIdentTilDokumentIdMap findDokumentIdsForIdents(DokumentIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException;

}
