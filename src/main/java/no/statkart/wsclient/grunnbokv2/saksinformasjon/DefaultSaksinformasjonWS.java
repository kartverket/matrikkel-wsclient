package no.statkart.wsclient.grunnbokv2.saksinformasjon;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.saksinformasjon.SaksinformasjonId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.saksinformasjon.SaksinformasjonService;
import no.kartverket.grunnbok.wsapi.v2.service.saksinformasjon.SaksinformasjonServiceWS;
import no.statkart.wsclient.WebServiceBuilder;

public class DefaultSaksinformasjonWS implements SaksinformasjonWS {
    private static SaksinformasjonServiceWS saksinformasjonServiceWS;

    private final SaksinformasjonService saksinformasjonService;

    public DefaultSaksinformasjonWS(String brukernavn, String passord, String endpointUrl) {
        if (saksinformasjonServiceWS == null) {
            synchronized (this) {
                if (saksinformasjonServiceWS == null) {
                    saksinformasjonServiceWS = new SaksinformasjonServiceWS();
                }
            }
        }

        saksinformasjonService = WebServiceBuilder.builderv2(saksinformasjonServiceWS.getSaksinformasjonServicePort(),
                SaksinformasjonService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
    }

    @Override
    public SaksinformasjonId findSaksinformasjonIdForInnsendingId(String innsendingId, GrunnbokContext grunnbokContext) throws ServiceException {
        return saksinformasjonService.findSaksinformasjonIdForInnsendingId(innsendingId, grunnbokContext);
    }
}
