package no.statkart.wsclient.grunnbokv2.endringslogg;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.Domainklasse;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.EndringId;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.Endringer;
import no.kartverket.grunnbok.wsapi.v2.domain.endringslogg.ReturnerBobler;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;

public interface EndringsloggWS {

    EndringId findSisteEndringId(GrunnbokContext grunnbokContext) throws ServiceException;

    Endringer findEndringer(EndringId id, Domainklasse domainklasse, String filter, ReturnerBobler returnerBobler, int maksAntall,
                            GrunnbokContext grunnbokContext) throws ServiceException;
}
