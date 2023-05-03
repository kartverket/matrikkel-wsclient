package no.statkart.wsclient.stedsnavn.map;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnBubbleObject;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnBubbleObjectId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnBubbleObjectIdList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnBubbleObjectList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnBubbleObjectWithHistory;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.Timestamp;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.BokId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.KartproduktId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.koder.DokumenttypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.koder.KartforekomstMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.DelAvSamlevedtakId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.KlageId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.VedtakId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringer;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.StedsnavnKodelisteId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.LandKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.VedtaksmyndighetKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.kommune.KommuneId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.posisjon.PosisjonId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaateId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaateMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.NavneobjekttypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.Sortering1KodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.Sortering2KodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.SpraakprioriteringKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedTilleggsopplysningstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnesakstatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.StedsnavnMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.StedsnavnTilleggsopplysningstypeKodeId;
import no.statkart.wsclient.DateHjelper;
import no.statkart.wsclient.stedsnavn.KanSetteHistoriskeFelter;
import no.statkart.wsclient.stedsnavn.StedsnavnBoble;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleMedHistorie;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;
import no.statkart.wsclient.stedsnavn.StedsnavnEntityComponentWithHistory;
import no.statkart.wsclient.stedsnavn.endringslogg.Domainklasse;
import no.statkart.wsclient.stedsnavn.endringslogg.Endring;
import no.statkart.wsclient.stedsnavn.endringslogg.EndringerRespons;
import no.statkart.wsclient.stedsnavn.endringslogg.Endringstype;
import no.statkart.wsclient.stedsnavn.endringslogg.ReturnerBobler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.BOK;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.DEL_AV_SAMLE;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.DOKUMENT_TYPE;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.KART;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.KART_PRODUKT;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.KASUS;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.KLAGE;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.KOMMUNE;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.LAND;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.NAVNE_OBJEKT;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.NAVNE_SAK;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.NAVNE_STATUS;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.POSISJON;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.REKKEFOELGE;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.SKRIVEMAATE;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.SKRIVEMAATE_MERKNAD;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.SKRIVEMAATE_STATUS;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.SORT_1;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.SORT_2;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.SPRAAK;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.SPRAAK_PRIO;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STED;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STEDSNAVN;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STEDSNAVN_KODELISTE;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STEDSNAVN_MERKNAD;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STEDSNAVN_TILLEGG;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STED_MERKNAD;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STED_STATUS;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.STED_TILLEGG;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.VEDTAK;
import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.VEDTAKS_MYND;
import static no.statkart.wsclient.stedsnavn.map.KodeMapper.mapKode;
import static no.statkart.wsclient.stedsnavn.map.SkrivemaateMapper.mapSkrivemaate;
import static no.statkart.wsclient.stedsnavn.map.StedMapper.mapSted;
import static no.statkart.wsclient.stedsnavn.map.StedsnavnMapper.mapStedsnavn;

public class Mapper {

    private static final Logger logger = LoggerFactory.getLogger(Mapper.class);

    private static final Map<StedsnavnBobleId.TypeId, Class<? extends StedsnavnBubbleObjectId>> typeIdTilWsIdMap = Map.ofEntries(
        Map.entry(BOK, BokId.class)
        , Map.entry(DEL_AV_SAMLE, DelAvSamlevedtakId.class)
        , Map.entry(DOKUMENT_TYPE, DokumenttypeKodeId.class)
        , Map.entry(KART, KartforekomstMerknadstypeKodeId.class)
        , Map.entry(KART_PRODUKT, KartproduktId.class)
        , Map.entry(KASUS, KasustypeKodeId.class)
        , Map.entry(KLAGE, KlageId.class)
        , Map.entry(KOMMUNE, KommuneId.class)
        , Map.entry(LAND, LandKodeId.class)
        , Map.entry(NAVNE_OBJEKT, NavneobjekttypeKodeId.class)
        , Map.entry(NAVNE_SAK, NavnesakstatusKodeId.class)
        , Map.entry(NAVNE_STATUS, NavnestatusKodeId.class)
        , Map.entry(POSISJON, PosisjonId.class)
        , Map.entry(REKKEFOELGE, RekkefoelgeKodeId.class)
        , Map.entry(SKRIVEMAATE, SkrivemaateId.class)
        , Map.entry(SKRIVEMAATE_MERKNAD, SkrivemaateMerknadstypeKodeId.class)
        , Map.entry(SKRIVEMAATE_STATUS, SkrivemaatestatusKodeId.class)
        , Map.entry(SORT_1, Sortering1KodeId.class)
        , Map.entry(SORT_2, Sortering2KodeId.class)
        , Map.entry(SPRAAK, SpraakKodeId.class)
        , Map.entry(SPRAAK_PRIO, SpraakprioriteringKodeId.class)
        , Map.entry(STED, StedId.class)
        , Map.entry(STED_MERKNAD, StedMerknadstypeKodeId.class)
        , Map.entry(STEDSNAVN, StedsnavnId.class)
        , Map.entry(STEDSNAVN_MERKNAD, StedsnavnMerknadstypeKodeId.class)
        , Map.entry(STEDSNAVN_TILLEGG, StedsnavnTilleggsopplysningstypeKodeId.class)
        , Map.entry(STED_STATUS, StedstatusKodeId.class)
        , Map.entry(STED_TILLEGG, StedTilleggsopplysningstypeKodeId.class)
        , Map.entry(VEDTAK, VedtakId.class)
        , Map.entry(VEDTAKS_MYND, VedtaksmyndighetKodeId.class)
        , Map.entry(STEDSNAVN_KODELISTE, StedsnavnKodelisteId.class)
    );

    public static EndringerRespons mapEndringerRespons(Endringer endringer) {
        EndringerRespons respons = new EndringerRespons(
            toDomainEndringer(endringer.getEndringList()),
            toDomainEndringId(endringer.getSisteEndringIdProsessert()));
        respons.setAlleEndringerFunnet(endringer.isAlleEndringerFunnet());

        return respons;
    }

    public static no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext toWsCtx(StedsnavnContext stedsnavnContext) {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext wsStedsnavnContext = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext();

        wsStedsnavnContext.setSystemVersion("1.8");
        wsStedsnavnContext.setClientIdentification("1");
        wsStedsnavnContext.setLocale(stedsnavnContext.getLocale());

        Timestamp snapshotVersion = new Timestamp();
        snapshotVersion.setTimestamp(DateHjelper.xmlGregorianCalendarForCurrentSnapshotVersion());
        wsStedsnavnContext.setSnapshotVersion(snapshotVersion);

        return wsStedsnavnContext;
    }

    public static no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Domainklasse toWsDomainklasse(Domainklasse domainklasse) {
        return no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Domainklasse.fromValue(domainklasse.getKodeverdi());
    }

    public static StedsnavnBubbleObjectIdList toWsBobleIds(List<StedsnavnBobleId> ids) {
        StedsnavnBubbleObjectIdList stedsnavnBubbleObjectIdList = new StedsnavnBubbleObjectIdList();
        stedsnavnBubbleObjectIdList.getItem().addAll(ids.stream()
            .map(Mapper::toWsBobleId)
            .collect(Collectors.toList()));
        return stedsnavnBubbleObjectIdList;
    }

    public static StedsnavnBubbleObjectId toWsBobleId(StedsnavnBobleId stedsnavnBobleId) {
        try {
            Class<? extends StedsnavnBubbleObjectId> wsIdClass = typeIdTilWsIdMap.get(stedsnavnBobleId.getTypeId());
            StedsnavnBubbleObjectId bobleId = wsIdClass.getDeclaredConstructor().newInstance();
            bobleId.setValue(stedsnavnBobleId.getValue());
            return bobleId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId toWsEndringId(StedsnavnBobleId.EndringId id) {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId wsId = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId();
        wsId.setValue(id.getValue());
        return wsId;
    }

    public static no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.ReturnerBobler toWsReturnerBobler(ReturnerBobler returnerBobler) {
        return no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.ReturnerBobler.fromValue(returnerBobler.getKodeverdi());
    }

    public static List<StedsnavnBoble> toDomainObjects(StedsnavnBubbleObjectList wsBubbleObjects) {
        return wsBubbleObjects.getItem().stream()
            .map(Mapper::toDomainObject)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public static StedsnavnBoble toDomainObject(StedsnavnBubbleObject stedsnavnBubbleObject) {
        if (stedsnavnBubbleObject instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted) {
            return mapSted((no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted) stedsnavnBubbleObject);
        } else if (stedsnavnBubbleObject instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn) {
            return mapStedsnavn((no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn) stedsnavnBubbleObject);
        } else if (stedsnavnBubbleObject instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate) {
            return mapSkrivemaate((no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate) stedsnavnBubbleObject);
        } else if (stedsnavnBubbleObject instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.Kode) {
            return mapKode((no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.Kode) stedsnavnBubbleObject);
        } else {
            logger.info("Ikke definert mapping for objekt av type: {}", stedsnavnBubbleObject.getClass());
            return null;
        }
    }

    static void setFellesFelterForHistoriskBoble(StedsnavnBubbleObjectWithHistory wsObject, StedsnavnBobleMedHistorie domeneBoble) {
        setHistoriskeFelter(domeneBoble, wsObject.getOppdateringsdato(), wsObject.getOppdatertAv(), wsObject.getSluttdato(), wsObject.getAvsluttetAv());
    }

    static void setFellesFelterForHistoriskKomponent(no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnEntityComponentWithHistory ws, StedsnavnEntityComponentWithHistory domeneKomponent) {
        setHistoriskeFelter(domeneKomponent, ws.getOppdateringsdato(), ws.getOppdatertAv(), ws.getSluttdato(), ws.getAvsluttetAv());
    }

    private static void setHistoriskeFelter(KanSetteHistoriskeFelter domeneType, Timestamp oppdateringsdato, String oppdatertAv, Timestamp sluttdato, String avsluttetAv) {
        domeneType.setOppdateringsdato(DateHjelper.dateTimeFromXMLGregorianCalendar(oppdateringsdato.getTimestamp()));
        domeneType.setOppdatertAv(oppdatertAv);
        if (sluttdato != null) {
            domeneType.setSluttdato(DateHjelper.dateTimeFromXMLGregorianCalendar(sluttdato.getTimestamp()));
        }
        domeneType.setAvsluttetAv(avsluttetAv);
    }

    static LocalDateTime regDato(no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnEntityComponentWithHistory ws) {
        return DateHjelper.dateTimeFromXMLGregorianCalendar(ws.getRegistreringsdato().getTimestamp());
    }

    public static boolean bobleErStedStedsnavnEllerSkrivemaate(StedsnavnBubbleObjectId wsBubbleId) {
        return wsBubbleId instanceof SkrivemaateId || wsBubbleId instanceof StedsnavnId || wsBubbleId instanceof StedId;
    }

    static StedsnavnBobleId toDomainBobleId(StedsnavnBubbleObjectId wsBubbleId) {
        String value = wsBubbleId.getValue();
        StedsnavnBobleId bobleId;

        if (wsBubbleId instanceof BokId) {
            bobleId = new StedsnavnBobleId.BokId(value);
        } else if (wsBubbleId instanceof DelAvSamlevedtakId) {
            bobleId = new StedsnavnBobleId.DelAvSamlevedtakId(value);
        } else if (wsBubbleId instanceof DokumenttypeKodeId) {
            bobleId = new StedsnavnBobleId.DokumenttypeKodeId(value);
        } else if (wsBubbleId instanceof KartforekomstMerknadstypeKodeId) {
            bobleId = new StedsnavnBobleId.KartforekomstMerknadstypeKodeId(value);
        } else if (wsBubbleId instanceof KartproduktId) {
            bobleId = new StedsnavnBobleId.KartproduktId(value);
        } else if (wsBubbleId instanceof KasustypeKodeId) {
            bobleId = new StedsnavnBobleId.KasustypeKodeId(value);
        } else if (wsBubbleId instanceof KlageId) {
            bobleId = new StedsnavnBobleId.KlageId(value);
        } else if (wsBubbleId instanceof KommuneId) {
            bobleId = new StedsnavnBobleId.KommuneId(value);
        } else if (wsBubbleId instanceof LandKodeId) {
            bobleId = new StedsnavnBobleId.LandKodeId(value);
        } else if (wsBubbleId instanceof NavneobjekttypeKodeId) {
            bobleId = new StedsnavnBobleId.NavneobjekttypeKodeId(value);
        } else if (wsBubbleId instanceof NavnesakstatusKodeId) {
            bobleId = new StedsnavnBobleId.NavnesakstatusKodeId(value);
        } else if (wsBubbleId instanceof NavnestatusKodeId) {
            bobleId = new StedsnavnBobleId.NavnestatusKodeId(value);
        } else if (wsBubbleId instanceof PosisjonId) {
            bobleId = new StedsnavnBobleId.PosisjonId(value);
        } else if (wsBubbleId instanceof RekkefoelgeKodeId) {
            bobleId = new StedsnavnBobleId.RekkefoelgeKodeId(value);
        } else if (wsBubbleId instanceof SkrivemaateId) {
            bobleId = new StedsnavnBobleId.SkrivemaateId(value);
        } else if (wsBubbleId instanceof SkrivemaateMerknadstypeKodeId) {
            bobleId = new StedsnavnBobleId.SkrivemaateMerknadstypeKodeId(value);
        } else if (wsBubbleId instanceof SkrivemaatestatusKodeId) {
            bobleId = new StedsnavnBobleId.SkrivemaatestatusKodeId(value);
        } else if (wsBubbleId instanceof Sortering1KodeId) {
            bobleId = new StedsnavnBobleId.Sortering1KodeId(value);
        } else if (wsBubbleId instanceof Sortering2KodeId) {
            bobleId = new StedsnavnBobleId.Sortering2KodeId(value);
        } else if (wsBubbleId instanceof SpraakKodeId) {
            bobleId = new StedsnavnBobleId.SpraakKodeId(value);
        } else if (wsBubbleId instanceof SpraakprioriteringKodeId) {
            bobleId = new StedsnavnBobleId.SpraakprioriteringKodeId(value);
        } else if (wsBubbleId instanceof StedId) {
            bobleId = new StedsnavnBobleId.StedId(value);
        } else if (wsBubbleId instanceof StedMerknadstypeKodeId) {
            bobleId = new StedsnavnBobleId.StedMerknadstypeKodeId(value);
        } else if (wsBubbleId instanceof StedsnavnId) {
            bobleId = new StedsnavnBobleId.StedsnavnId(value);
        } else if (wsBubbleId instanceof StedsnavnMerknadstypeKodeId) {
            bobleId = new StedsnavnBobleId.StedsnavnMerknadstypeKodeId(value);
        } else if (wsBubbleId instanceof StedsnavnTilleggsopplysningstypeKodeId) {
            bobleId = new StedsnavnBobleId.StedsnavnTilleggsopplysningstypeKodeId(value);
        } else if (wsBubbleId instanceof StedstatusKodeId) {
            bobleId = new StedsnavnBobleId.StedstatusKodeId(value);
        } else if (wsBubbleId instanceof StedTilleggsopplysningstypeKodeId) {
            bobleId = new StedsnavnBobleId.StedTilleggsopplysningstypeKodeId(value);
        } else if (wsBubbleId instanceof VedtakId) {
            bobleId = new StedsnavnBobleId.VedtakId(value);
        } else if (wsBubbleId instanceof VedtaksmyndighetKodeId) {
            bobleId = new StedsnavnBobleId.VedtaksmyndighetKodeId(value);
        } else if (wsBubbleId instanceof StedsnavnKodelisteId) {
            bobleId = new StedsnavnBobleId.StedsnavnKodelisteId(value);
        } else {
            throw new RuntimeException(String.format("Ikke mapping for: %s", wsBubbleId.getClass()));
        }

        return bobleId;
    }

    private static List<Endring> toDomainEndringer(EndringList endringList) {
        return endringList.getItem()
            .stream()
            .map(Mapper::toDomainEndring)
            .collect(Collectors.toList());
    }

    private static Endring toDomainEndring(no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endring wsEndring) {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId wsEndringId = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId) wsEndring.getId();
        StedsnavnBubbleObjectId endretBubbleId = wsEndring.getEndretBubbleId();
        Timestamp endringstidspunkt = wsEndring.getEndringstidspunkt();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringstype endringstype = wsEndring.getEndringstype();

        return new Endring(toDomainEndringId(wsEndringId), DateHjelper.dateTimeFromXMLGregorianCalendar(endringstidspunkt.getTimestamp()), Endringstype.fromValue(endringstype.value()), toDomainBobleId(endretBubbleId));
    }

    private static StedsnavnBobleId.EndringId toDomainEndringId(no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId id) {
        return new StedsnavnBobleId.EndringId(id.getValue());
    }
}
