package no.statkart.wsclient.grunnbokv2.saksinformasjon;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.saksinformasjon.SaksinformasjonId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

public interface SaksinformasjonWS {
    SaksinformasjonId findSaksinformasjonIdForInnsendingId(String innsendingId,
                                                           GrunnbokContext grunnbokContext) throws ServiceException;
}
