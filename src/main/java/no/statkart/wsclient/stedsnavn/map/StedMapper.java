package no.statkart.wsclient.stedsnavn.map;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.Timestamp;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.kommune.KommuneIdList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.NavneobjekttypeHistorikkList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedInternMerknadList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedTilleggsopplysningList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedstatusHistorikkList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.VedtaksmyndighetHistorikkList;
import no.statkart.wsclient.DateHjelper;
import no.statkart.wsclient.stedsnavn.Matrikkelenhetreferanse;
import no.statkart.wsclient.stedsnavn.Matrikkelreferanse;
import no.statkart.wsclient.stedsnavn.NavneobjekttypeHistorikk;
import no.statkart.wsclient.stedsnavn.Sortering;
import no.statkart.wsclient.stedsnavn.Sted;
import no.statkart.wsclient.stedsnavn.StedInternMerknad;
import no.statkart.wsclient.stedsnavn.StedTilleggsopplysning;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedstatusHistorikk;
import no.statkart.wsclient.stedsnavn.VedtaksmyndighetHistorikk;
import no.statkart.wsclient.stedsnavn.Vegreferanse;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static no.statkart.wsclient.stedsnavn.map.Mapper.regDato;
import static no.statkart.wsclient.stedsnavn.map.Mapper.setFellesFelterForHistoriskBoble;
import static no.statkart.wsclient.stedsnavn.map.Mapper.setFellesFelterForHistoriskKomponent;
import static no.statkart.wsclient.stedsnavn.map.Mapper.toDomainBobleId;

class StedMapper {

    static Sted mapSted(no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted wsSted) {
        Sted sted = new Sted(toDomainBobleId(wsSted.getId()), DateHjelper.dateTimeFromXMLGregorianCalendar(wsSted.getRegistreringsdato().getTimestamp()));
        setFellesFelterForHistoriskBoble(wsSted, sted);

        sted.setStedsnummer(wsSted.getStedsnummer());
        sted.setKommunerIds(toDomeneKommuner(wsSted.getKommunerIds()));
        if (wsSted.getLandId() != null) {
            sted.setLandId(new StedsnavnBobleId.LandKodeId(wsSted.getLandId().getValue()));
        }
        sted.setNavneobjekttypeHistorikker(toDomeneNavneObjekttypeHistorikk(wsSted.getNavneobjekttypeHistorikker()));
        sted.setStedstatusHistorikker(toDomeneStedstatusHistorikk(wsSted.getStedstatusHistorikker()));
        sted.setVedtaksmyndighetHistorikker(toDomeneVedtaksmyndighetHistorikk(wsSted.getVedtaksmyndighetHistorikker()));
        if (wsSted.getPosisjonId() != null) {
            sted.setPosisjonId(new StedsnavnBobleId.PosisjonId(wsSted.getPosisjonId().getValue()));
        }
        if (wsSted.getSpraakprioriteringId() != null) {
            sted.setSpraakprioriteringId(new StedsnavnBobleId.SpraakprioriteringKodeId(wsSted.getSpraakprioriteringId().getValue()));
        }
        sted.setSortering(toDomeneSortering(wsSted.getSortering()));
        sted.setHoeyesteStedsnavnnummer(wsSted.getHoeyesteStedsnavnnummer());
        sted.setTilleggsopplysninger(toDomeneTilleggsopplysninger(wsSted.getTilleggsopplysninger()));
        sted.setInterneMerknader(toDomeneInterneMerknader(wsSted.getInterneMerknader()));

        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Matrikkelreferanse matrikkelreferanse = wsSted.getMatrikkelreferanse();
        if (matrikkelreferanse != null) {
            sted.setMatrikkelreferanse(toDomeneMatrikkelreferanse(matrikkelreferanse));
        }

        return sted;
    }

    private static Matrikkelreferanse toDomeneMatrikkelreferanse(no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Matrikkelreferanse ws) {
        if (ws instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Vegreferanse) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Vegreferanse wsVegRef = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Vegreferanse) ws;
            Vegreferanse vegreferanse = new Vegreferanse(DateHjelper.dateTimeFromXMLGregorianCalendar(wsVegRef.getRegistreringsdato().getTimestamp()), wsVegRef.getKommunenummer());
            setMatrikkelReferanseFelter(ws, vegreferanse);

            vegreferanse.setAdressekode(wsVegRef.getAdressekode());
            return vegreferanse;
        } else if (ws instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Matrikkelenhetreferanse) {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Matrikkelenhetreferanse wsMatrikkelenhetRef = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Matrikkelenhetreferanse) ws;
            Matrikkelenhetreferanse matrikkelenhetreferanse = new Matrikkelenhetreferanse(DateHjelper.dateTimeFromXMLGregorianCalendar(wsMatrikkelenhetRef.getRegistreringsdato().getTimestamp()), wsMatrikkelenhetRef.getKommunenummer());
            setMatrikkelReferanseFelter(ws, matrikkelenhetreferanse);

            matrikkelenhetreferanse.setGaardsnummer(wsMatrikkelenhetRef.getGaardsnummer());
            matrikkelenhetreferanse.setBruksnummer(wsMatrikkelenhetRef.getBruksnummer());
            matrikkelenhetreferanse.setFestenummer(wsMatrikkelenhetRef.getFestenummer());
            matrikkelenhetreferanse.setSeksjonsnummer(wsMatrikkelenhetRef.getSeksjonsnummer());
            return matrikkelenhetreferanse;
        } else {
            throw new RuntimeException("Ukjent subtype: " + ws.getClass());
        }
    }

    private static void setMatrikkelReferanseFelter(no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Matrikkelreferanse ws, Matrikkelreferanse matrikkelreferanse) {
        matrikkelreferanse.setMatrikkelId(ws.getMatrikkelId());
        Timestamp oppdateringsdato = ws.getOppdateringsdato();
        if (oppdateringsdato != null) {
            matrikkelreferanse.setOppdateringsdato(DateHjelper.dateTimeFromXMLGregorianCalendar(oppdateringsdato.getTimestamp()));
        }
        matrikkelreferanse.setOppdatertAv(ws.getOppdatertAv());
        Timestamp sluttdato = ws.getSluttdato();
        if (sluttdato != null) {
            matrikkelreferanse.setSluttdato(DateHjelper.dateTimeFromXMLGregorianCalendar(sluttdato.getTimestamp()));
        }
        matrikkelreferanse.setAvsluttetAv(ws.getAvsluttetAv());
    }

    private static List<StedInternMerknad> toDomeneInterneMerknader(StedInternMerknadList interneMerknader) {
        return interneMerknader.getItem().stream()
            .map(ws -> {
                StedInternMerknad stedInternMerknad = new StedInternMerknad(ws.getId(), regDato(ws), ws.getTekst(),
                    new StedsnavnBobleId.StedMerknadstypeKodeId(ws.getMerknadstypeId().getValue()), ws.getFellesarkiv().getItem());
                setFellesFelterForHistoriskKomponent(ws, stedInternMerknad);
                return stedInternMerknad;
            })
            .collect(Collectors.toList());
    }

    private static List<StedTilleggsopplysning> toDomeneTilleggsopplysninger(StedTilleggsopplysningList tilleggsopplysninger) {
        return tilleggsopplysninger.getItem().stream()
            .map(ws -> {
                StedTilleggsopplysning domene = new StedTilleggsopplysning(ws.getId(), regDato(ws), ws.getTekst(),
                    ws.getEksterneOpplysninger().getItem(), new StedsnavnBobleId.StedTilleggsopplysningstypeKodeId(ws.getTilleggsopplysningstypeId().getValue()));
                setFellesFelterForHistoriskKomponent(ws, domene);
                return domene;
            })
            .collect(toList());
    }

    private static Sortering toDomeneSortering(no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sortering sortering) {
        return new Sortering(
            new StedsnavnBobleId.Sortering1KodeId(sortering.getSortering1KodeId().getValue()),
            new StedsnavnBobleId.Sortering2KodeId(sortering.getSortering2KodeId().getValue()));
    }

    private static List<VedtaksmyndighetHistorikk> toDomeneVedtaksmyndighetHistorikk(VedtaksmyndighetHistorikkList vedtaksmyndighetHistorikker) {
        return vedtaksmyndighetHistorikker.getItem().stream()
            .map(ws -> new VedtaksmyndighetHistorikk(ws.getFraDato() != null ? DateHjelper.dateFromXMLGregorianCalendar(ws.getFraDato().getDate()) : null,
                new StedsnavnBobleId.VedtaksmyndighetKodeId(ws.getVedtaksmyndighetId().getValue())))
            .collect(toList());
    }

    private static List<StedstatusHistorikk> toDomeneStedstatusHistorikk(StedstatusHistorikkList stedstatusHistorikker) {
        return stedstatusHistorikker.getItem().stream()
            .map(ws -> new StedstatusHistorikk(ws.getFraDato() != null ? DateHjelper.dateFromXMLGregorianCalendar(ws.getFraDato().getDate()) : null,
                new StedsnavnBobleId.StedstatusKodeId(ws.getStedstatusId().getValue())))
            .collect(toList());
    }

    private static List<NavneobjekttypeHistorikk> toDomeneNavneObjekttypeHistorikk(NavneobjekttypeHistorikkList navneobjekttypeHistorikker) {
        return navneobjekttypeHistorikker.getItem().stream()
            .map(ws -> new NavneobjekttypeHistorikk(ws.getFraDato() != null ? DateHjelper.dateFromXMLGregorianCalendar(ws.getFraDato().getDate()) : null,
                new StedsnavnBobleId.NavneobjekttypeKodeId(ws.getNavneobjekttypeKodeId().getValue())))
            .collect(toList());
    }

    private static List<StedsnavnBobleId.KommuneId> toDomeneKommuner(KommuneIdList kommunerIds) {
        return kommunerIds.getItem().stream()
            .map(kommuneId -> new StedsnavnBobleId.KommuneId(kommuneId.getValue()))
            .collect(toList());
    }


}
