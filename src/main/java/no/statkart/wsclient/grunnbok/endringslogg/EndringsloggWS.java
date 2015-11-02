package no.statkart.wsclient.grunnbok.endringslogg;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.Domainklasse;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.EndringId;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.Endringer;
import no.kartverket.grunnbok.wsapi.v1.domain.endringslogg.ReturnerBobler;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;

/**
 *
 */
public interface EndringsloggWS {

   EndringId findSisteEndringId(GrunnbokContext grunnbokContext) throws ServiceException;

   Endringer findEndringer(EndringId id, Domainklasse domainklasse, String filter, ReturnerBobler returnerBobler, int maksAntall,
                           GrunnbokContext grunnbokContext) throws ServiceException;
}
