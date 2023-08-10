package no.statkart.wsclient.grunnbokv2.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.MatrikkelenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.ident.IdentService;
import no.kartverket.grunnbok.wsapi.v2.service.ident.IdentServiceWS;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.DokumentIdentTilDokumentIdMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;
import no.statkart.wsclient.WebServiceBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class DefaultIdentWS implements IdentWS {

    private static IdentServiceWS identServiceWS;

    private final IdentService identService;

    public DefaultIdentWS(String brukernavn, String passord, String endpointUrl) {
        if (identServiceWS == null) {
            synchronized (this) {
                if (identServiceWS == null) {
                    identServiceWS = new IdentServiceWS();
                }
            }
        }


        identService = WebServiceBuilder.builderv2(identServiceWS.getIdentServicePort(), IdentService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
    }

    @Override
    public Map<MatrikkelenhetIdent, MatrikkelenhetId> findMatrikkelenhetIdsForIdents(Collection<MatrikkelenhetIdent> idents, GrunnbokContext grunnbokContext) throws ServiceException {
        MatrikkelenhetIdentList matrikkelenhetIdentList = new MatrikkelenhetIdentList();
        matrikkelenhetIdentList.getItem().addAll(idents);
        return unwrap(identService.findMatrikkelenhetIdsForIdents(matrikkelenhetIdentList, grunnbokContext));
    }

    static Map<MatrikkelenhetIdent, MatrikkelenhetId> unwrap(MatrikkelenhetIdentTilMatrikkelenhetIdMap matrikkelenhetIdsForIdents) {
        final HashMap<MatrikkelenhetIdent, MatrikkelenhetId> matrikkelenhetIdentTilIdMap = new HashMap<>(matrikkelenhetIdsForIdents.getEntry().size());
        for (MatrikkelenhetIdentTilMatrikkelenhetIdMap.Entry entry : matrikkelenhetIdsForIdents.getEntry()) {
            matrikkelenhetIdentTilIdMap.put(entry.getKey(), entry.getValue());
        }
        return matrikkelenhetIdentTilIdMap;
    }

    @Override
    public Map<DokumentIdent, DokumentId> findDokumentIdsForIdents(Collection<DokumentIdent> idents, GrunnbokContext grunnbokContext) throws ServiceException {
        DokumentIdentList identList = new DokumentIdentList();
        identList.getItem().addAll(idents);
        return unwrap(identService.findDokumentIdsForIdents(identList, grunnbokContext));
    }

    private Map<DokumentIdent, DokumentId> unwrap(DokumentIdentTilDokumentIdMap dokumentIdsForIdents) {
        final HashMap<DokumentIdent, DokumentId> dokumentIdentTilIdMap = new HashMap<>(dokumentIdsForIdents.getEntry().size());
        for (DokumentIdentTilDokumentIdMap.Entry entry : dokumentIdsForIdents.getEntry()) {
            dokumentIdentTilIdMap.put(entry.getKey(), entry.getValue());
        }
        return dokumentIdentTilIdMap;
    }
}
