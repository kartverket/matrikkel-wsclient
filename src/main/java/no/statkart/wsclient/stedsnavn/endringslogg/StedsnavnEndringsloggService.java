package no.statkart.wsclient.stedsnavn.endringslogg;

import java.util.List;

import static no.statkart.wsclient.stedsnavn.endringslogg.StedsnavnBobleId.EndringId;

public interface StedsnavnEndringsloggService {

   Kontroll calcEndringskontroll(EndringId id, Domainklasse domainklasse, String filter, int maksAntall, StedsnavnContext stedsnavnContext);

   StedsnavnBobleId findSisteEndringId(StedsnavnContext stedsnavnContext);

   Kontroll calcObjektkontrollForList(List<StedsnavnBobleId> ids, Domainklasse domainklasse, StedsnavnContext stedsnavnContext);

   EndringerRespons findEndringer(EndringId id, Domainklasse domainklasse, String filter, ReturnerBobler returnerBobler, int maksAntall, StedsnavnContext stedsnavnContext);

}
