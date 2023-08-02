package no.statkart.wsclient.grunnbokv2.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.ident.IdentService;
import no.kartverket.grunnbok.wsapi.v2.service.ident.IdentServiceWS;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.DokumentIdentTilDokumentIdMap;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;
import no.statkart.wsclient.WebServiceBuilder;


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
    public MatrikkelenhetIdentTilMatrikkelenhetIdMap findMatrikkelenhetIdsForIdents(MatrikkelenhetIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException {
        return identService.findMatrikkelenhetIdsForIdents(idents, grunnbokContext);
    }

    @Override
    public DokumentIdentTilDokumentIdMap findDokumentIdsForIdents(DokumentIdentList idents, GrunnbokContext grunnbokContext) throws ServiceException {
        return identService.findDokumentIdsForIdents(idents, grunnbokContext);
    }
}
