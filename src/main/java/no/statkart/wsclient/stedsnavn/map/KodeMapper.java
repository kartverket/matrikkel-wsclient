package no.statkart.wsclient.stedsnavn.map;

import no.statkart.wsclient.stedsnavn.Kode;
import no.statkart.wsclient.stedsnavn.LocalizedString;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedstatusKode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class KodeMapper {

    private static final Logger logger = LoggerFactory.getLogger(KodeMapper.class);

    static Kode mapKode(no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.Kode kode) {
        if (kode instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode wsKode = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode) kode;

            StedstatusKode stedstatusKode = new StedstatusKode(new StedsnavnBobleId.StedstatusKodeId(wsKode.getId().getValue()), new StedsnavnBobleId.StedsnavnKodelisteId(wsKode.getKodelisteId().getValue()),
                    wsKode.getKodeverdi(), toLocalizedString(wsKode.getNavn()));
            stedstatusKode.setGyldigTil(wsKode.getGyldigTil().getTimestamp());
            stedstatusKode.setOppdateringsdato(wsKode.getOppdateringsdato().getTimestamp());
            stedstatusKode.setOppdatertAv(wsKode.getOppdatertAv());
            return stedstatusKode;
        } else {
            logger.info("Ikke definert mapping for objekt av type: {}", kode.getClass());
            return null;
        }
    }

    private static LocalizedString toLocalizedString(no.statkart.stedsnavn.ssr.wsapi.v1.domain.LocalizedString navn) {
        Map<String, String> map = new HashMap<>();
        navn.getEntry().forEach(entry -> map.put(entry.getKey(), entry.getValue()));
        return new LocalizedString(map);
    }
}
