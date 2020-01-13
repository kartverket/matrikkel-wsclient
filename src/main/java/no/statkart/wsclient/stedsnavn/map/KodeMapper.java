package no.statkart.wsclient.stedsnavn.map;

import no.statkart.wsclient.DateHjelper;
import no.statkart.wsclient.stedsnavn.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class KodeMapper {
    private static final Logger logger = LoggerFactory.getLogger(KodeMapper.class);

    static Kode mapKode(no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.Kode kode) {
        if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode) kode;
            StedstatusKode stedstatusKode = new StedstatusKode(new StedsnavnBobleId.StedstatusKodeId(wsKode.getId().getValue()), kodelisteId(wsKode), wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            setKodeFelter(wsKode, stedstatusKode);
            return stedstatusKode;
        } else if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKode) kode;
            NavnestatusKode navnestatusKode = new NavnestatusKode(new StedsnavnBobleId.NavnestatusKodeId(wsKode.getId().getValue()), kodelisteId(wsKode), wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            setKodeFelter(wsKode, navnestatusKode);
            return navnestatusKode;
        } else if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.SpraakprioriteringKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.SpraakprioriteringKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.SpraakprioriteringKode) kode;
            SpraakprioriteringKode spraakprioriteringKode = new SpraakprioriteringKode(new StedsnavnBobleId.SpraakprioriteringKodeId(wsKode.getId().getValue()), kodelisteId(wsKode), wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            setKodeFelter(wsKode, spraakprioriteringKode);
            return spraakprioriteringKode;
        } else if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKode) kode;
            SpraakKode spraakKode = new SpraakKode(new StedsnavnBobleId.SpraakKodeId(wsKode.getId().getValue()), kodelisteId(wsKode), wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            setKodeFelter(wsKode, spraakKode);
            return spraakKode;
        } else if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKode) kode;
            SkrivemaatestatusKode spraakKode = new SkrivemaatestatusKode(new StedsnavnBobleId.SkrivemaatestatusKodeId(wsKode.getId().getValue()), kodelisteId(wsKode), wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            setKodeFelter(wsKode, spraakKode);
            return spraakKode;
        } else if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKode) kode;
            RekkefoelgeKode rekkefoelgeKode = new RekkefoelgeKode(new StedsnavnBobleId.RekkefoelgeKodeId(wsKode.getId().getValue()), kodelisteId(wsKode), wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            setKodeFelter(wsKode, rekkefoelgeKode);
            return rekkefoelgeKode;
        } else if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKode) kode;
            KasustypeKode rekkefoelgeKode = new KasustypeKode(new StedsnavnBobleId.KasustypeKodeId(wsKode.getId().getValue()), kodelisteId(wsKode), wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            setKodeFelter(wsKode, rekkefoelgeKode);
            return rekkefoelgeKode;
        } else {
            logger.warn("Ikke definert mapping for objekt av type: {}", kode.getClass());
            throw new RuntimeException(String.format("Ikke definert mapping for objekt av type: %s", kode.getClass()));
        }
    }

    private static void setKodeFelter(no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.Kode wsKode, Kode domeneKode) {
        if (wsKode.getGyldigTil() != null) {
            domeneKode.setGyldigTil(DateHjelper.dateTimeFromXMLGregorianCalendar(wsKode.getGyldigTil().getTimestamp()));
        }
        if (wsKode.getOppdateringsdato() != null) {
            domeneKode.setOppdateringsdato(DateHjelper.dateTimeFromXMLGregorianCalendar(wsKode.getOppdateringsdato().getTimestamp()));
        }
        domeneKode.setOppdatertAv(wsKode.getOppdatertAv());
    }

    private static StedsnavnBobleId.StedsnavnKodelisteId kodelisteId(no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.Kode wsKode) {
        return new StedsnavnBobleId.StedsnavnKodelisteId(wsKode.getKodelisteId().getValue());
    }

    private static LocalizedString toLocalizedString(no.statkart.stedsnavn.ssr.wsapi.v1.domain.LocalizedString navn) {
        Map<String, String> map = new HashMap<>();
        navn.getEntry().forEach(entry -> map.put(entry.getKey(), entry.getValue()));
        return new LocalizedString(map);
    }
}
