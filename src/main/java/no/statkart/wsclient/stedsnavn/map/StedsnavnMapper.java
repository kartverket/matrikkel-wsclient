package no.statkart.wsclient.stedsnavn.map;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.NavnesakstatusHistorikkList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.NavnestatusHistorikkList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnInternMerknadList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnTilleggsopplysningList;
import no.statkart.wsclient.stedsnavn.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static no.statkart.wsclient.stedsnavn.map.Mapper.*;

class StedsnavnMapper {

    static Stedsnavn mapStedsnavn(no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn wsStedsnavn) {
        Stedsnavn stedsnavn = new Stedsnavn(toDomainBobleId(wsStedsnavn.getId()), DateHjelper.dateTimeFromXMLGregorianCalendar(wsStedsnavn.getRegistreringsdato().getTimestamp()));
        setFellesFelterForHistoriskBoble(wsStedsnavn, stedsnavn);

        stedsnavn.setStedsnavnnummer(wsStedsnavn.getStedsnavnnummer());
        stedsnavn.setStedId(new StedsnavnBobleId.StedId(wsStedsnavn.getStedId().getValue()));
        stedsnavn.setNavnestatusHistorikker(toDomeneStatusHistorikk(wsStedsnavn.getNavnestatusHistorikker()));
        stedsnavn.setNavnesakstatusHistorikker(toDomeneSaksStatusHistorikk(wsStedsnavn.getNavnesakstatusHistorikker()));
        stedsnavn.setPrimaerfunksjonId(new StedsnavnBobleId.StedsnavnId(wsStedsnavn.getPrimaerfunksjonId().getValue()));
        stedsnavn.setGruppetilhoerighetId(new StedsnavnBobleId.StedsnavnId(wsStedsnavn.getGruppetilhoerighetId().getValue()));
        stedsnavn.setEksonym(wsStedsnavn.isEksonym());
        stedsnavn.setSpraakId(new StedsnavnBobleId.SpraakKodeId(wsStedsnavn.getSpraakId().getValue()));
        stedsnavn.setOpphavsspraakId(new StedsnavnBobleId.SpraakKodeId(wsStedsnavn.getOpphavsspraakId().getValue()));
        stedsnavn.setHoeyesteSkrivemaatenummer(wsStedsnavn.getHoeyesteSkrivemaatenummer());
        stedsnavn.setTilleggsopplysninger(toDomeneTilleggsopplysninger(wsStedsnavn.getTilleggsopplysninger()));
        stedsnavn.setInterneMerknader(toDomeneInterneMerknader(wsStedsnavn.getInterneMerknader()));

        return stedsnavn;
    }

    private static List<StedsnavnInternMerknad> toDomeneInterneMerknader(StedsnavnInternMerknadList interneMerknader) {
        return interneMerknader.getItem().stream()
                .map(ws -> {
                    StedsnavnInternMerknad stedsnavnInternMerknad = new StedsnavnInternMerknad(ws.getId(), regDato(ws), ws.getTekst(),
                            new StedsnavnBobleId.StedsnavnMerknadstypeKodeId(ws.getMerknadstypeId().getValue()), ws.getFellesarkiv().getItem());
                    setFellesFelterForHistoriskKomponent(ws, stedsnavnInternMerknad);
                    return stedsnavnInternMerknad;
                })
                .collect(Collectors.toList());
    }

    private static List<StedsnavnTilleggsopplysning> toDomeneTilleggsopplysninger(StedsnavnTilleggsopplysningList tilleggsopplysninger) {
        return tilleggsopplysninger.getItem().stream()
                .map(ws -> {
                    StedsnavnTilleggsopplysning domene = new StedsnavnTilleggsopplysning(ws.getId(), regDato(ws), ws.getTekst(),
                            new StedsnavnBobleId.StedsnavnTilleggsopplysningstypeKodeId(ws.getTilleggsopplysningstypeId().getValue()),
                            ws.getEksterneOpplysninger().getItem());
                    setFellesFelterForHistoriskKomponent(ws, domene);
                    return domene;
                })
                .collect(toList());
    }

    private static List<NavnesakstatusHistorikk> toDomeneSaksStatusHistorikk(NavnesakstatusHistorikkList navnesakstatusHistorikker) {
        return navnesakstatusHistorikker.getItem().stream()
                .map(nsh -> new NavnesakstatusHistorikk(DateHjelper.dateFromXMLGregorianCalendar(nsh.getFraDato().getDate()), new StedsnavnBobleId.NavnesakstatusKodeId(nsh.getNavnesakstatusId().getValue())))
                .collect(Collectors.toList());
    }

    private static List<NavnestatusHistorikk> toDomeneStatusHistorikk(NavnestatusHistorikkList navnestatusHistorikker) {
        return navnestatusHistorikker.getItem().stream()
                .map(nsh -> new NavnestatusHistorikk(DateHjelper.dateFromXMLGregorianCalendar(nsh.getFraDato().getDate()), new StedsnavnBobleId.NavnestatusKodeId(nsh.getNavnestatusId().getValue())))
                .collect(Collectors.toList());
    }

}
