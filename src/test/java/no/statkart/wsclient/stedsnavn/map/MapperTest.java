package no.statkart.wsclient.stedsnavn.map;

import com.google.common.collect.ImmutableMap;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.LocalizedString;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.*;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.BokId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.DokumentasjonList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.KartforekomstInternMerknadList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.KartproduktId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.kilde.LokaleInnsamlinger;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.koder.DokumenttypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.koder.KartforekomstMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.DelAvSamlevedtakId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.Kartforekomst;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.KlageId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.VedtakId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endring;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringer;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.StedsnavnKodelisteId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.LandKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.VedtaksmyndighetKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.kommune.KommuneId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.kommune.KommuneIdList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.posisjon.PosisjonId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.KasusForSkrivemaateList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaateId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaateInternMerknadList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaatestatusHistorikkList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaateMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedInternMerknad;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.*;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.*;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.NavnesakstatusHistorikk;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.NavnestatusHistorikk;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.*;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnesakstatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.StedsnavnMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.StedsnavnTilleggsopplysningstypeKodeId;
import no.statkart.wsclient.stedsnavn.Matrikkelreferanse;
import no.statkart.wsclient.stedsnavn.NavneobjekttypeHistorikk;
import no.statkart.wsclient.stedsnavn.Sortering;
import no.statkart.wsclient.stedsnavn.SpraakprioriteringKode;
import no.statkart.wsclient.stedsnavn.Sted;
import no.statkart.wsclient.stedsnavn.StedTilleggsopplysning;
import no.statkart.wsclient.stedsnavn.Stedsnavn;
import no.statkart.wsclient.stedsnavn.StedsnavnEntityComponentWithHistory;
import no.statkart.wsclient.stedsnavn.StedsnavnInternMerknad;
import no.statkart.wsclient.stedsnavn.StedsnavnTilleggsopplysning;
import no.statkart.wsclient.stedsnavn.StedstatusHistorikk;
import no.statkart.wsclient.stedsnavn.StedstatusKode;
import no.statkart.wsclient.stedsnavn.VedtaksmyndighetHistorikk;
import no.statkart.wsclient.stedsnavn.Vegreferanse;
import no.statkart.wsclient.stedsnavn.*;
import no.statkart.wsclient.stedsnavn.endringslogg.EndringerRespons;
import no.statkart.wsclient.stedsnavn.endringslogg.Endringstype;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MapperTest {

    private static final LocalDateTime NOW = LocalDateTime.of(2019, Month.APRIL, 10, 12, 9, 18, 510_000_000);
    private static final LocalDate TODAY = LocalDate.of(2019, Month.APRIL, 23);
    private static final String AVSLUTTET_AV = "Jens";
    private static final String OPPDATERT_AV = "ulf";
    private static final String ID = "123597";

    public void toWsCtx() {
        no.statkart.wsclient.stedsnavn.StedsnavnContext stedsnavnContext = new no.statkart.wsclient.stedsnavn.StedsnavnContext("no");
        StedsnavnContext wsCtx = Mapper.toWsCtx(stedsnavnContext);

        assertThat(wsCtx.getSystemVersion()).isEqualTo("2.6");
        assertThat(wsCtx.getClientIdentification()).isEqualTo("1");
        assertThat(wsCtx.getLocale()).isEqualTo("no");
        assertThat(wsCtx.getSnapshotVersion().getTimestamp().toString()).isEqualTo("9999-01-01T00:00:00.000+01:00");
    }

    public void toWsBobleIds() {
        List<StedsnavnBobleId> ids = new ArrayList<>();
        ids.add(new StedsnavnBobleId.BokId("0"));
        ids.add(new StedsnavnBobleId.DelAvSamlevedtakId("1"));
        ids.add(new StedsnavnBobleId.DokumenttypeKodeId("2"));
        ids.add(new StedsnavnBobleId.KartforekomstMerknadstypeKodeId("3"));
        ids.add(new StedsnavnBobleId.KartproduktId("4"));
        ids.add(new StedsnavnBobleId.KasustypeKodeId("5"));
        ids.add(new StedsnavnBobleId.KlageId("6"));
        ids.add(new StedsnavnBobleId.KommuneId("7"));
        ids.add(new StedsnavnBobleId.LandKodeId("8"));
        ids.add(new StedsnavnBobleId.NavneobjekttypeKodeId("9"));
        ids.add(new StedsnavnBobleId.NavnesakstatusKodeId("10"));
        ids.add(new StedsnavnBobleId.NavnestatusKodeId("11"));
        ids.add(new StedsnavnBobleId.PosisjonId("12"));
        ids.add(new StedsnavnBobleId.RekkefoelgeKodeId("13"));
        ids.add(new StedsnavnBobleId.SkrivemaateId("14"));
        ids.add(new StedsnavnBobleId.SkrivemaateMerknadstypeKodeId("15"));
        ids.add(new StedsnavnBobleId.SkrivemaatestatusKodeId("16"));
        ids.add(new StedsnavnBobleId.Sortering1KodeId("17"));
        ids.add(new StedsnavnBobleId.Sortering2KodeId("18"));
        ids.add(new StedsnavnBobleId.SpraakKodeId("19"));
        ids.add(new StedsnavnBobleId.SpraakprioriteringKodeId("20"));
        ids.add(new StedsnavnBobleId.StedId("21"));
        ids.add(new StedsnavnBobleId.StedMerknadstypeKodeId("22"));
        ids.add(new StedsnavnBobleId.StedsnavnId("23"));
        ids.add(new StedsnavnBobleId.StedsnavnMerknadstypeKodeId("24"));
        ids.add(new StedsnavnBobleId.StedsnavnTilleggsopplysningstypeKodeId("25"));
        ids.add(new StedsnavnBobleId.StedstatusKodeId("26"));
        ids.add(new StedsnavnBobleId.StedTilleggsopplysningstypeKodeId("27"));
        ids.add(new StedsnavnBobleId.VedtakId("28"));
        ids.add(new StedsnavnBobleId.VedtaksmyndighetKodeId("29"));
        ids.add(new StedsnavnBobleId.StedsnavnKodelisteId("30"));

        StedsnavnBubbleObjectIdList wsBobleIdList = Mapper.toWsBobleIds(ids);

        List<StedsnavnBubbleObjectId> wsBobleIds = wsBobleIdList.getItem();
        assertWsId(wsBobleIds, 0, BokId.class);
        assertWsId(wsBobleIds, 1, DelAvSamlevedtakId.class);
        assertWsId(wsBobleIds, 2, DokumenttypeKodeId.class);
        assertWsId(wsBobleIds, 3, KartforekomstMerknadstypeKodeId.class);
        assertWsId(wsBobleIds, 4, KartproduktId.class);
        assertWsId(wsBobleIds, 5, KasustypeKodeId.class);
        assertWsId(wsBobleIds, 6, KlageId.class);
        assertWsId(wsBobleIds, 7, KommuneId.class);
        assertWsId(wsBobleIds, 8, LandKodeId.class);
        assertWsId(wsBobleIds, 9, NavneobjekttypeKodeId.class);
        assertWsId(wsBobleIds, 10, NavnesakstatusKodeId.class);
        assertWsId(wsBobleIds, 11, NavnestatusKodeId.class);
        assertWsId(wsBobleIds, 12, PosisjonId.class);
        assertWsId(wsBobleIds, 13, RekkefoelgeKodeId.class);
        assertWsId(wsBobleIds, 14, SkrivemaateId.class);
        assertWsId(wsBobleIds, 15, SkrivemaateMerknadstypeKodeId.class);
        assertWsId(wsBobleIds, 16, SkrivemaatestatusKodeId.class);
        assertWsId(wsBobleIds, 17, Sortering1KodeId.class);
        assertWsId(wsBobleIds, 18, Sortering2KodeId.class);
        assertWsId(wsBobleIds, 19, SpraakKodeId.class);
        assertWsId(wsBobleIds, 20, SpraakprioriteringKodeId.class);
        assertWsId(wsBobleIds, 21, StedId.class);
        assertWsId(wsBobleIds, 22, StedMerknadstypeKodeId.class);
        assertWsId(wsBobleIds, 23, StedsnavnId.class);
        assertWsId(wsBobleIds, 24, StedsnavnMerknadstypeKodeId.class);
        assertWsId(wsBobleIds, 25, StedsnavnTilleggsopplysningstypeKodeId.class);
        assertWsId(wsBobleIds, 26, StedstatusKodeId.class);
        assertWsId(wsBobleIds, 27, StedTilleggsopplysningstypeKodeId.class);
        assertWsId(wsBobleIds, 28, VedtakId.class);
        assertWsId(wsBobleIds, 29, VedtaksmyndighetKodeId.class);
        assertWsId(wsBobleIds, 30, StedsnavnKodelisteId.class);
    }

    private void assertWsId(List<StedsnavnBubbleObjectId> wsBobleIds, int index, Class<? extends StedsnavnBubbleObjectId> clazz) {
        assertThat(wsBobleIds.get(index)).isInstanceOf(clazz);
        assertThat(wsBobleIds.get(index).getValue()).isEqualTo(String.valueOf(index));
    }

    public void mapEndringerRespons() {
        Endringer endringer = new Endringer();

        EndringId sisteEndringId = new EndringId();
        sisteEndringId.setValue("123");
        endringer.setSisteEndringIdProsessert(sisteEndringId);

        EndringList endringList = new EndringList();
        Endring endring = new Endring();
        EndringId wsEndringId = new EndringId();
        wsEndringId.setValue("456");
        endring.setId(wsEndringId);
        StedsnavnId stedsnavnId = new StedsnavnId();
        stedsnavnId.setValue("12345");
        endring.setEndretBubbleId(stedsnavnId);
        endring.setEndringstidspunkt(timestamp());
        endring.setEndringstype(no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringstype.OPPDATERING);

        endringList.getItem().add(endring);
        endringer.setEndringList(endringList);

        endringer.setAlleEndringerFunnet(true);

        EndringerRespons endringerRespons = Mapper.mapEndringerRespons(endringer);

        StedsnavnBobleId.EndringId domainEndringId = new StedsnavnBobleId.EndringId("123");
        assertThat(endringerRespons.getSisteEndringIdProsessert().getValue()).isEqualTo(domainEndringId.getValue());

        List<no.statkart.wsclient.stedsnavn.endringslogg.Endring> domainEndringer = endringerRespons.getEndringList();
        assertThat(domainEndringer.size()).isEqualTo(1);
        no.statkart.wsclient.stedsnavn.endringslogg.Endring domainEndring = domainEndringer.get(0);

        StedsnavnBobleId id = domainEndring.getId();
        assertThat(id.getTypeId()).isEqualTo(StedsnavnBobleId.TypeId.ENDRING);
        assertThat(id.getValue()).isEqualTo("456");

        StedsnavnBobleId endretBubbleId = domainEndring.getEndretBubbleId();
        assertThat(endretBubbleId).isInstanceOf(StedsnavnBobleId.StedsnavnId.class);
        assertThat(endretBubbleId.getValue()).isEqualTo("12345");

        assertThat(domainEndring.getEndringstidspunkt()).isEqualTo(NOW);

        assertThat(domainEndring.getEndringstype()).isEqualTo(Endringstype.OPPDATERING);

        assertThat(endringerRespons.isAlleEndringerFunnet()).isTrue();
    }

    public void toDomainObjects() {
        StedsnavnBubbleObjectList objectList = new StedsnavnBubbleObjectList();
        //1. Sted
        List<StedsnavnBubbleObject> wsBubbleObjects = objectList.getItem();
        wsBubbleObjects.add(createWsSted());
        //2. Stedsnavn
        wsBubbleObjects.add(createWsStedsnavn());
        //3. Skrivemate
        wsBubbleObjects.add(createWsSkrivemaate());

        List<StedsnavnBoble> bobler = Mapper.toDomainObjects(objectList);
        assertThat(bobler.size()).isEqualTo(3);

        Sted sted = assertBobleAvType(bobler, 0, Sted.class);
        assertSted(sted);

        Stedsnavn stedsnavn = assertBobleAvType(bobler, 1, Stedsnavn.class);
        assertStedsnavn(stedsnavn);

        Skrivemaate skrivemaate = assertBobleAvType(bobler, 2, Skrivemaate.class);
        assertSkrivemaate(skrivemaate);
    }

    public void toDomainObject_StedstatusKode() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode wsKode = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.StedstatusKode();
        StedstatusKodeId id = new StedstatusKodeId();
        setFelterForWsKode(wsKode, id);

        StedsnavnBoble domeneKode = Mapper.toDomainObject(wsKode);
        assertKodeType(domeneKode, StedstatusKode.class);
    }

    public void toDomainObject_NavnestatusKode() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKode wsKode = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKode();
        NavnestatusKodeId id = new NavnestatusKodeId();
        setFelterForWsKode(wsKode, id);

        StedsnavnBoble domeneKode = Mapper.toDomainObject(wsKode);

        assertKodeType(domeneKode, NavnestatusKode.class);
    }

    public void toDomainObject_SpraakprioriteringKode() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.SpraakprioriteringKode wsKode = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.SpraakprioriteringKode();
        SpraakprioriteringKodeId id = new SpraakprioriteringKodeId();
        setFelterForWsKode(wsKode, id);

        StedsnavnBoble domeneKode = Mapper.toDomainObject(wsKode);

        assertKodeType(domeneKode, SpraakprioriteringKode.class);
    }

    public void toDomainObject_SpraakKode() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKode wsKode = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKode();
        SpraakKodeId id = new SpraakKodeId();
        setFelterForWsKode(wsKode, id);

        StedsnavnBoble domeneKode = Mapper.toDomainObject(wsKode);

        assertKodeType(domeneKode, SpraakKode.class);
    }

    public void toDomainObject_SkrivemaatestatusKode() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKode wsKode = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKode();
        SkrivemaatestatusKodeId id = new SkrivemaatestatusKodeId();
        setFelterForWsKode(wsKode, id);

        StedsnavnBoble domeneKode = Mapper.toDomainObject(wsKode);

        assertKodeType(domeneKode, SkrivemaatestatusKode.class);
    }

    public void toDomainObject_RekkefoelgeKode() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKode wsKode = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKode();
        RekkefoelgeKodeId id = new RekkefoelgeKodeId();
        setFelterForWsKode(wsKode, id);
        StedsnavnBoble domeneKode = Mapper.toDomainObject(wsKode);
        assertKodeType(domeneKode, RekkefoelgeKode.class);
    }

    public void toDomainObject_KasustypeKode() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKode wsKode = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKode();
        KasustypeKodeId id = new KasustypeKodeId();
        setFelterForWsKode(wsKode, id);
        StedsnavnBoble domeneKode = Mapper.toDomainObject(wsKode);
        assertKodeType(domeneKode, KasustypeKode.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends Kode> void assertKodeType(StedsnavnBoble domeneKode, Class<T> kodeClazz) {
        assertThat(domeneKode).isNotNull();
        assertThat(domeneKode).isInstanceOf(kodeClazz);
        T kode = (T) domeneKode;
        assertThat(kode.getOppdateringsdato()).isEqualTo(NOW);
        assertThat(kode.getOppdatertAv()).isEqualTo(OPPDATERT_AV);
        assertThat(kode.getGyldigTil()).isEqualTo(NOW);
        assertThat(kode.getKodelisteId().getValue()).isEqualTo("2");
        assertThat(kode.getNavn().getKeyAndValues()).isEqualTo(ImmutableMap.of("key", "value"));
    }

    private void setFelterForWsKode(no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.Kode wsKode, no.statkart.stedsnavn.ssr.wsapi.v1.domain.kodeliste.KodeId id) {
        id.setValue("1");
        wsKode.setId(id);
        wsKode.setOppdateringsdato(timestamp());
        wsKode.setOppdatertAv(OPPDATERT_AV);
        wsKode.setGyldigTil(timestamp());
        StedsnavnKodelisteId kodelisteId = new StedsnavnKodelisteId();
        kodelisteId.setValue("2");
        wsKode.setKodelisteId(kodelisteId);
        wsKode.setKodeverdi("STATUS");
        LocalizedString localizedString = new LocalizedString();
        LocalizedString.Entry entry = new LocalizedString.Entry();
        entry.setKey("key");
        entry.setValue("value");
        localizedString.getEntry().add(entry);
        wsKode.setNavn(localizedString);
    }

    private void assertSkrivemaate(Skrivemaate skrivemaate) {
        assertFellesFelterForHistoriskBoble(skrivemaate);

        assertThat(skrivemaate.getSkrivemaatenummer()).isEqualTo(234);
        id_ok(skrivemaate.getStedsnavnId());
        id_ok(skrivemaate.getNormertFraId());
        id_ok(skrivemaate.getRekkefoelgeId());

        List<KasusForSkrivemaate> kasuser = skrivemaate.getKasuser();
        assertThat(kasuser.size()).isEqualTo(1);
        KasusForSkrivemaate kfs = kasuser.get(0);
        assertFellesFelterForHistoriskKomponent(kfs);
        id_ok(kfs.getKasusTilKjernenavnId());
        assertThat(kfs.getKjernenavn()).isEqualTo("kjernenavn");
        assertThat(kfs.getVariasjonstillegg()).isEqualTo("varTillegg");
        assertThat(kfs.getFunksjonstillegg()).isEqualTo("funkTillegg");

        assertThat(skrivemaate.getKortnavn()).isEqualTo("kortnavn");

        List<SkrivemaatestatusHistorikk> historikker = skrivemaate.getSkrivemaatestatusHistorikker();
        assertThat(historikker.size()).isEqualTo(1);
        SkrivemaatestatusHistorikk hist = historikker.get(0);
        assertFellesFelterForHistoriskKomponent(hist);
        assertThat(hist.getFraDato()).isEqualTo(TODAY);
        id_ok(hist.getSkrivemaatestatusId());
        assertThat(hist.isPrioritertSkrivemaate()).isTrue();

        List<SkrivemaateInternMerknad> interneMerknader = skrivemaate.getInterneMerknader();
        assertThat(interneMerknader.size()).isEqualTo(1);
        SkrivemaateInternMerknad stedInternMerknad = interneMerknader.get(0);
        assertFellesFelterForHistoriskKomponent(stedInternMerknad);
        assertThat(stedInternMerknad.getTekst()).isEqualTo("noe tekst");
        assertThat(stedInternMerknad.getFellesarkiv().size()).isEqualTo(1);
        assertThat(stedInternMerknad.getFellesarkiv().iterator().next()).isEqualTo("Fellesarkiv1");
        id_ok(stedInternMerknad.getMerknadstypeId());

        List<Dokumentasjon> dokumentasjonListe = skrivemaate.getDokumentasjon();
        assertThat(dokumentasjonListe.size()).isEqualTo(2);

        Dokumentasjon dokumentasjon = dokumentasjonListe.get(0);
        assertThat(dokumentasjon).isInstanceOf(Dokumentasjon.LokaleInnsamlinger.class);
        Dokumentasjon.LokaleInnsamlinger lokaleInnsamlinger = (Dokumentasjon.LokaleInnsamlinger) dokumentasjon;
        assertFellesFelterForHistoriskKomponent(lokaleInnsamlinger);
        assertThat(lokaleInnsamlinger.getKildedato()).isEqualTo(TODAY);
        assertThat(lokaleInnsamlinger.getInnsamler()).isEqualTo("innsamler");
        assertThat(lokaleInnsamlinger.getBeskrivelse()).isEqualTo("Dokumentasjonsbeskrivelse.");

        Dokumentasjon dokumentasjon2 = dokumentasjonListe.get(1);
        assertThat(dokumentasjon2).isInstanceOf(Dokumentasjon.Kartforekomst.class);
        Dokumentasjon.Kartforekomst kartforekomst = (Dokumentasjon.Kartforekomst) dokumentasjon2;
        assertFellesFelterForHistoriskKomponent(kartforekomst);
        assertThat(kartforekomst.getPosisjon()).isEqualTo("Pos1");
        id_ok(kartforekomst.getKartproduktId());
        List<KartforekomstInternMerknad> kartforekomstInterneMerknader = kartforekomst.getInterneMerknader();
        assertThat(kartforekomstInterneMerknader.size()).isEqualTo(1);
        KartforekomstInternMerknad kartforekomstInternMerknad = kartforekomstInterneMerknader.get(0);
        assertFellesFelterForHistoriskKomponent(kartforekomstInternMerknad);
        assertThat(kartforekomstInternMerknad.getTekst()).isEqualTo("noe tekst");
        assertThat(kartforekomstInternMerknad.getFellesarkiv().size()).isEqualTo(1);
        assertThat(kartforekomstInternMerknad.getFellesarkiv().iterator().next()).isEqualTo("Fellesarkiv1");
        id_ok(kartforekomstInternMerknad.getMerknadstypeId());
    }


    private void assertStedsnavn(Stedsnavn stedsnavn) {
        assertFellesFelterForHistoriskBoble(stedsnavn);

        assertThat(stedsnavn.getStedsnavnnummer()).isEqualTo(50);
        id_ok(stedsnavn.getStedId());

        assertThat(stedsnavn.getNavnestatusHistorikker().size()).isEqualTo(1);
        no.statkart.wsclient.stedsnavn.NavnestatusHistorikk historikk = stedsnavn.getNavnestatusHistorikker().get(0);
        assertThat(historikk.getFraDato()).isEqualTo(TODAY);
        id_ok(historikk.getNavnestatusId());

        assertThat(stedsnavn.getNavnesakstatusHistorikker().size()).isEqualTo(1);
        no.statkart.wsclient.stedsnavn.NavnesakstatusHistorikk historikk2 = stedsnavn.getNavnesakstatusHistorikker().get(0);
        assertThat(historikk2.getFraDato()).isEqualTo(TODAY);
        id_ok(historikk2.getNavnesakstatusId());

        id_ok(stedsnavn.getPrimaerfunksjonId());
        id_ok(stedsnavn.getGruppetilhoerighetId());

        assertThat(stedsnavn.isEksonym()).isTrue();

        id_ok(stedsnavn.getSpraakId());
        id_ok(stedsnavn.getOpphavsspraakId());

        assertThat(stedsnavn.getHoeyesteSkrivemaatenummer()).isEqualTo(3);

        List<StedsnavnTilleggsopplysning> tilleggsopplysninger = stedsnavn.getTilleggsopplysninger();
        assertThat(tilleggsopplysninger.size()).isEqualTo(1);
        StedsnavnTilleggsopplysning tilleggsopplysning = tilleggsopplysninger.get(0);
        assertFellesFelterForHistoriskKomponent(tilleggsopplysning);
        assertThat(tilleggsopplysning.getTekst()).isEqualTo("tekst");
        assertThat(tilleggsopplysning.getEksterneOpplysninger().size()).isEqualTo(1);
        assertThat(tilleggsopplysning.getEksterneOpplysninger().get(0)).isEqualTo("eksternOpplysning1");
        id_ok(tilleggsopplysning.getTilleggsopplysningstypeId());

        List<StedsnavnInternMerknad> interneMerknader = stedsnavn.getInterneMerknader();
        assertThat(interneMerknader.size()).isEqualTo(1);
        StedsnavnInternMerknad stedInternMerknad = interneMerknader.get(0);
        assertFellesFelterForHistoriskKomponent(stedInternMerknad);
        assertThat(stedInternMerknad.getTekst()).isEqualTo("noe tekst");
        assertThat(stedInternMerknad.getFellesarkiv().size()).isEqualTo(1);
        assertThat(stedInternMerknad.getFellesarkiv().iterator().next()).isEqualTo("Fellesarkiv1");
        id_ok(stedInternMerknad.getMerknadstypeId());
    }

    private void assertSted(Sted sted) {
        assertFellesFelterForHistoriskBoble(sted);

        assertThat(sted.getStedsnummer()).isEqualTo(56L);

        assertThat(sted.getKommunerIds().size()).isEqualTo(1);
        StedsnavnBobleId.KommuneId kommuneId = sted.getKommunerIds().get(0);
        id_ok(kommuneId);

        id_ok(sted.getLandId());

        assertThat(sted.getNavneobjekttypeHistorikker().size()).isEqualTo(1);
        NavneobjekttypeHistorikk navneobjekttypeHistorikk = sted.getNavneobjekttypeHistorikker().get(0);
        assertThat(navneobjekttypeHistorikk.getFraDato()).isEqualTo(TODAY);
        id_ok(navneobjekttypeHistorikk.getNavneobjekttypeKodeId());

        List<StedstatusHistorikk> stedstatusHistorikker = sted.getStedstatusHistorikker();
        assertThat(stedstatusHistorikker.size()).isEqualTo(1);
        StedstatusHistorikk stedstatusHistorikk = stedstatusHistorikker.get(0);
        assertThat(stedstatusHistorikk.getFraDato()).isEqualTo(TODAY);
        id_ok(stedstatusHistorikk.getStedstatusId());

        List<VedtaksmyndighetHistorikk> vedtaksmyndighetHistorikker = sted.getVedtaksmyndighetHistorikker();
        assertThat(vedtaksmyndighetHistorikker.size()).isEqualTo(1);
        VedtaksmyndighetHistorikk vedtaksmyndighetHistorikk = vedtaksmyndighetHistorikker.get(0);
        assertThat(vedtaksmyndighetHistorikk.getFraDato()).isEqualTo(TODAY);
        id_ok(vedtaksmyndighetHistorikk.getVedtaksmyndighetId());

        id_ok(sted.getPosisjonId());

        id_ok(sted.getSpraakprioriteringId());

        Sortering sortering = sted.getSortering();
        id_ok(sortering.getSortering1KodeId());
        id_ok(sortering.getSortering2KodeId());

        assertThat(sted.getHoeyesteStedsnavnnummer()).isEqualTo(2);

        List<StedTilleggsopplysning> tilleggsopplysninger = sted.getTilleggsopplysninger();
        assertThat(tilleggsopplysninger.size()).isEqualTo(1);
        StedTilleggsopplysning tilleggsopplysning = tilleggsopplysninger.get(0);
        assertFellesFelterForHistoriskKomponent(tilleggsopplysning);
        assertThat(tilleggsopplysning.getTekst()).isEqualTo("tekst");
        assertThat(tilleggsopplysning.getEksterneOpplysninger().size()).isEqualTo(1);
        assertThat(tilleggsopplysning.getEksterneOpplysninger().get(0)).isEqualTo("eksternOpplysning1");
        id_ok(tilleggsopplysning.getTilleggsopplysningstypeId());

        List<no.statkart.wsclient.stedsnavn.StedInternMerknad> interneMerknader = sted.getInterneMerknader();
        assertThat(interneMerknader.size()).isEqualTo(1);
        no.statkart.wsclient.stedsnavn.StedInternMerknad stedInternMerknad = interneMerknader.get(0);
        assertFellesFelterForHistoriskKomponent(stedInternMerknad);
        assertThat(stedInternMerknad.getTekst()).isEqualTo("noe tekst");
        assertThat(stedInternMerknad.getFellesarkiv().size()).isEqualTo(1);
        assertThat(stedInternMerknad.getFellesarkiv().iterator().next()).isEqualTo("Fellesarkiv1");
        id_ok(stedInternMerknad.getMerknadstypeId());

        Matrikkelreferanse matrikkelreferanse = sted.getMatrikkelreferanse();
        assertThat(matrikkelreferanse).isInstanceOf(Vegreferanse.class);
        Vegreferanse vegreferanse = (Vegreferanse) matrikkelreferanse;
        assertThat(vegreferanse.getMatrikkelId()).isEqualTo(1L);
        assertThat(vegreferanse.getOppdateringsdato()).isEqualTo(NOW);
        assertThat(vegreferanse.getOppdatertAv()).isEqualTo(OPPDATERT_AV);
        assertThat(vegreferanse.getSluttdato()).isEqualTo(NOW);
        assertThat(vegreferanse.getAvsluttetAv()).isEqualTo(AVSLUTTET_AV);
        assertThat(vegreferanse.getRegistreringsdato()).isEqualTo(NOW);
        assertThat(vegreferanse.getAdressekode()).isEqualTo(11);
        assertThat(vegreferanse.getKommunenummer()).isEqualTo("0301");
    }

    private void id_ok(StedsnavnBobleId stedsnavnBobleId) {
        assertThat(stedsnavnBobleId.getValue()).isEqualTo(ID);
    }

    private void assertFellesFelterForHistoriskKomponent(StedsnavnEntityComponentWithHistory historiskKomponent) {
        assertThat(historiskKomponent.getId()).isEqualTo(1L);
        assertThat(historiskKomponent.getOppdateringsdato()).isEqualTo(NOW);
        assertThat(historiskKomponent.getOppdatertAv()).isEqualTo(OPPDATERT_AV);
        assertThat(historiskKomponent.getSluttdato()).isEqualTo(NOW);
        assertThat(historiskKomponent.getAvsluttetAv()).isEqualTo(AVSLUTTET_AV);
        assertThat(historiskKomponent.getRegistreringsdato()).isEqualTo(NOW);
    }

    private void assertFellesFelterForHistoriskBoble(StedsnavnBobleMedHistorie historiskBoble) {
        assertThat(historiskBoble.getOppdateringsdato()).isEqualTo(NOW);
        assertThat(historiskBoble.getOppdatertAv()).isEqualTo(OPPDATERT_AV);
        assertThat(historiskBoble.getSluttdato()).isEqualTo(NOW);
        assertThat(historiskBoble.getAvsluttetAv()).isEqualTo(AVSLUTTET_AV);
        assertThat(historiskBoble.getRegistreringsdato()).isEqualTo(NOW);
    }

    @SuppressWarnings("unchecked")
    private <T extends StedsnavnBoble> T assertBobleAvType(List<StedsnavnBoble> bobler, int index, Class<T> clazz) {
        StedsnavnBoble boble = bobler.get(index);
        assertThat(boble).isInstanceOf(clazz);
        return (T) boble;
    }


    private no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate createWsSkrivemaate() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate sm = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate();
        setFellesFelterForHistoriskBoble(sm);
        sm.setId(skrivemaateId());
        sm.setSkrivemaatenummer(234);
        sm.setStedsnavnId(stedsnavnId());
        sm.setNormertFraId(skrivemaateId());
        sm.setRekkefoelgeId(rekkefoelgeId());
        sm.setKasuser(kasuser());
        sm.setKortnavn("kortnavn");
        sm.setSkrivemaatestatusHistorikker(skrivemaateStatusHistorikker());
        sm.setInterneMerknader(skrivemaateInterneMerknader());
        sm.setDokumentasjon(dokumentasjonList());

        return sm;
    }

    private DokumentasjonList dokumentasjonList() {
        DokumentasjonList dokumentasjonList = new DokumentasjonList();

        LokaleInnsamlinger dok = new LokaleInnsamlinger();
        setFellesFelterForHistoriskKomponent(dok);
        dok.setKildedato(localDate());
        dok.setInnsamler("innsamler");
        dok.setBeskrivelse("Dokumentasjonsbeskrivelse.");

        Kartforekomst kartforekomst = new Kartforekomst();
        setFellesFelterForHistoriskKomponent(kartforekomst);
        kartforekomst.setPosisjon("Pos1");
        KartproduktId kartproduktId = new KartproduktId();
        kartproduktId.setValue(ID);
        kartforekomst.setKartproduktId(kartproduktId);
        kartforekomst.setInterneMerknader(kartforekomstInterneMerknader());

        List<no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.Dokumentasjon> dokumentasjonListItem = dokumentasjonList.getItem();
        dokumentasjonListItem.add(dok);
        dokumentasjonListItem.add(kartforekomst);
        return dokumentasjonList;
    }

    private KartforekomstInternMerknadList kartforekomstInterneMerknader() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.KartforekomstInternMerknad merknad = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.KartforekomstInternMerknad();
        setFellesFelterForHistoriskKomponent(merknad);
        StringList fellesarkiv = new StringList();
        fellesarkiv.getItem().add("Fellesarkiv1");
        merknad.setFellesarkiv(fellesarkiv);
        KartforekomstMerknadstypeKodeId stedMerknadstypeKodeId = new KartforekomstMerknadstypeKodeId();
        stedMerknadstypeKodeId.setValue(ID);
        merknad.setMerknadstypeId(stedMerknadstypeKodeId);
        merknad.setTekst("noe tekst");

        KartforekomstInternMerknadList merknadList = new KartforekomstInternMerknadList();
        merknadList.getItem().add(merknad);
        return merknadList;
    }

    private SkrivemaateInternMerknadList skrivemaateInterneMerknader() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaateInternMerknad merknad = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaateInternMerknad();
        setFellesFelterForHistoriskKomponent(merknad);
        StringList fellesarkiv = new StringList();
        fellesarkiv.getItem().add("Fellesarkiv1");
        merknad.setFellesarkiv(fellesarkiv);
        SkrivemaateMerknadstypeKodeId stedMerknadstypeKodeId = new SkrivemaateMerknadstypeKodeId();
        stedMerknadstypeKodeId.setValue(ID);
        merknad.setMerknadstypeId(stedMerknadstypeKodeId);
        merknad.setTekst("noe tekst");

        SkrivemaateInternMerknadList merknadList = new SkrivemaateInternMerknadList();
        merknadList.getItem().add(merknad);
        return merknadList;
    }

    private SkrivemaatestatusHistorikkList skrivemaateStatusHistorikker() {
        SkrivemaatestatusHistorikkList list = new SkrivemaatestatusHistorikkList();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaatestatusHistorikk hist = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaatestatusHistorikk();
        setFellesFelterForHistoriskKomponent(hist);

        hist.setFraDato(localDate());
        SkrivemaatestatusKodeId id = new SkrivemaatestatusKodeId();
        id.setValue(ID);
        hist.setSkrivemaatestatusId(id);
        hist.setPrioritertSkrivemaate(true);

        list.getItem().add(hist);

        return list;
    }

    private KasusForSkrivemaateList kasuser() {
        KasusForSkrivemaateList kasuser = new KasusForSkrivemaateList();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.KasusForSkrivemaate kasus = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.KasusForSkrivemaate();
        setFellesFelterForHistoriskKomponent(kasus);

        KasustypeKodeId id = new KasustypeKodeId();
        id.setValue(ID);
        kasus.setKasusTilKjernenavnId(id);
        kasus.setKjernenavn("kjernenavn");
        kasus.setVariasjonstillegg("varTillegg");
        kasus.setFunksjonstillegg("funkTillegg");

        kasuser.getItem().add(kasus);

        return kasuser;
    }

    private no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn createWsStedsnavn() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn stedsnavn = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn();
        setFellesFelterForHistoriskBoble(stedsnavn);
        stedsnavn.setId(stedsnavnId());

        stedsnavn.setStedsnavnnummer(50);
        stedsnavn.setStedId(stedId());

        NavnestatusHistorikkList navnestatusHistorikkList = new NavnestatusHistorikkList();
        NavnestatusHistorikk navnestatusHistorikk = new NavnestatusHistorikk();
        navnestatusHistorikk.setFraDato(localDate());
        NavnestatusKodeId id = new NavnestatusKodeId();
        id.setValue(ID);
        navnestatusHistorikk.setNavnestatusId(id);
        navnestatusHistorikkList.getItem().add(navnestatusHistorikk);
        stedsnavn.setNavnestatusHistorikker(navnestatusHistorikkList);

        NavnesakstatusHistorikkList historikkList = new NavnesakstatusHistorikkList();
        NavnesakstatusHistorikk historikk = new NavnesakstatusHistorikk();
        historikk.setFraDato(localDate());
        NavnesakstatusKodeId id2 = new NavnesakstatusKodeId();
        id2.setValue(ID);
        historikk.setNavnesakstatusId(id2);
        historikkList.getItem().add(historikk);
        stedsnavn.setNavnesakstatusHistorikker(historikkList);

        stedsnavn.setPrimaerfunksjonId(stedsnavnId());
        stedsnavn.setGruppetilhoerighetId(stedsnavnId());

        stedsnavn.setEksonym(true);

        stedsnavn.setSpraakId(spraakKodeId());
        stedsnavn.setOpphavsspraakId(spraakKodeId());

        stedsnavn.setHoeyesteSkrivemaatenummer(3);

        stedsnavn.setTilleggsopplysninger(stedsnavnTilleggsopplysninger());

        stedsnavn.setInterneMerknader(stedsnavnInterneMerknader());

        return stedsnavn;
    }

    private no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted createWsSted() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted sted = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted();
        setFellesFelterForHistoriskBoble(sted);
        sted.setId(stedId());

        sted.setStedsnummer(56L);

        KommuneIdList kommunerIds = new KommuneIdList();
        KommuneId kommuneId = new KommuneId();
        kommuneId.setValue(ID);
        kommunerIds.getItem().add(kommuneId);
        sted.setKommunerIds(kommunerIds);

        LandKodeId landId = new LandKodeId();
        landId.setValue(ID);
        sted.setLandId(landId);

        NavneobjekttypeHistorikkList noth = new NavneobjekttypeHistorikkList();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.NavneobjekttypeHistorikk navneobjekttypeHistorikk = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.NavneobjekttypeHistorikk();
        navneobjekttypeHistorikk.setFraDato(localDate());
        NavneobjekttypeKodeId navneobjekttypeKodeId = new NavneobjekttypeKodeId();
        navneobjekttypeKodeId.setValue(ID);
        navneobjekttypeHistorikk.setNavneobjekttypeKodeId(navneobjekttypeKodeId);
        noth.getItem().add(navneobjekttypeHistorikk);
        sted.setNavneobjekttypeHistorikker(noth);

        StedstatusHistorikkList stedstatusHistorikkList = new StedstatusHistorikkList();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedstatusHistorikk historikk = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedstatusHistorikk();
        historikk.setFraDato(localDate());
        StedstatusKodeId id1 = new StedstatusKodeId();
        id1.setValue(ID);
        historikk.setStedstatusId(id1);
        stedstatusHistorikkList.getItem().add(historikk);
        sted.setStedstatusHistorikker(stedstatusHistorikkList);

        VedtaksmyndighetHistorikkList value = new VedtaksmyndighetHistorikkList();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.VedtaksmyndighetHistorikk vedtaksmyndighetHistorikk = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.VedtaksmyndighetHistorikk();
        vedtaksmyndighetHistorikk.setFraDato(localDate());
        VedtaksmyndighetKodeId id2 = new VedtaksmyndighetKodeId();
        id2.setValue(ID);
        vedtaksmyndighetHistorikk.setVedtaksmyndighetId(id2);
        value.getItem().add(vedtaksmyndighetHistorikk);
        sted.setVedtaksmyndighetHistorikker(value);

        PosisjonId id3 = new PosisjonId();
        id3.setValue(ID);
        sted.setPosisjonId(id3);

        SpraakprioriteringKodeId id4 = new SpraakprioriteringKodeId();
        id4.setValue(ID);
        sted.setSpraakprioriteringId(id4);

        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sortering sortering = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sortering();
        Sortering1KodeId sortering1KodeId = new Sortering1KodeId();
        sortering1KodeId.setValue(ID);
        sortering.setSortering1KodeId(sortering1KodeId);
        Sortering2KodeId sortering2KodeId = new Sortering2KodeId();
        sortering2KodeId.setValue(ID);
        sortering.setSortering2KodeId(sortering2KodeId);
        sted.setSortering(sortering);

        sted.setTilleggsopplysninger(stedTilleggsopplysninger());

        sted.setHoeyesteStedsnavnnummer(2);

        sted.setInterneMerknader(stedInterneMerknader());

        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Vegreferanse vegreferanse = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Vegreferanse();
        vegreferanse.setAdressekode(11);
        vegreferanse.setKommunenummer("0301");
        vegreferanse.setMatrikkelId(1);
        vegreferanse.setOppdateringsdato(timestamp());
        vegreferanse.setOppdatertAv(OPPDATERT_AV);
        vegreferanse.setSluttdato(timestamp());
        vegreferanse.setAvsluttetAv(AVSLUTTET_AV);
        vegreferanse.setRegistreringsdato(timestamp());
        sted.setMatrikkelreferanse(vegreferanse);

        return sted;
    }

    private StedInternMerknadList stedInterneMerknader() {
        StedInternMerknad merknad = new StedInternMerknad();
        setFellesFelterForHistoriskKomponent(merknad);
        StringList fellesarkiv = new StringList();
        fellesarkiv.getItem().add("Fellesarkiv1");
        merknad.setFellesarkiv(fellesarkiv);
        StedMerknadstypeKodeId stedMerknadstypeKodeId = new StedMerknadstypeKodeId();
        stedMerknadstypeKodeId.setValue(ID);
        merknad.setMerknadstypeId(stedMerknadstypeKodeId);
        merknad.setTekst("noe tekst");

        StedInternMerknadList stedInternMerknadList = new StedInternMerknadList();
        stedInternMerknadList.getItem().add(merknad);
        return stedInternMerknadList;
    }

    private StedsnavnInternMerknadList stedsnavnInterneMerknader() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnInternMerknad merknad = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnInternMerknad();
        setFellesFelterForHistoriskKomponent(merknad);
        StringList fellesarkiv = new StringList();
        fellesarkiv.getItem().add("Fellesarkiv1");
        merknad.setFellesarkiv(fellesarkiv);
        StedsnavnMerknadstypeKodeId stedMerknadstypeKodeId = new StedsnavnMerknadstypeKodeId();
        stedMerknadstypeKodeId.setValue(ID);
        merknad.setMerknadstypeId(stedMerknadstypeKodeId);
        merknad.setTekst("noe tekst");

        StedsnavnInternMerknadList stedInternMerknadList = new StedsnavnInternMerknadList();
        stedInternMerknadList.getItem().add(merknad);
        return stedInternMerknadList;
    }

    private StedTilleggsopplysningList stedTilleggsopplysninger() {
        StedTilleggsopplysningList tilleggsopplysninger = new StedTilleggsopplysningList();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedTilleggsopplysning tilleggsopplysning = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedTilleggsopplysning();
        setFellesFelterForHistoriskKomponent(tilleggsopplysning);
        tilleggsopplysning.setTekst("tekst");
        UrlList urlList = new UrlList();
        urlList.getItem().add("eksternOpplysning1");
        tilleggsopplysning.setEksterneOpplysninger(urlList);
        StedTilleggsopplysningstypeKodeId id5 = new StedTilleggsopplysningstypeKodeId();
        id5.setValue(ID);
        tilleggsopplysning.setTilleggsopplysningstypeId(id5);
        tilleggsopplysninger.getItem().add(tilleggsopplysning);
        return tilleggsopplysninger;
    }

    private StedsnavnTilleggsopplysningList stedsnavnTilleggsopplysninger() {
        StedsnavnTilleggsopplysningList tilleggsopplysninger = new StedsnavnTilleggsopplysningList();
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnTilleggsopplysning tilleggsopplysning = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnTilleggsopplysning();
        setFellesFelterForHistoriskKomponent(tilleggsopplysning);
        tilleggsopplysning.setTekst("tekst");
        UrlList urlList = new UrlList();
        urlList.getItem().add("eksternOpplysning1");
        tilleggsopplysning.setEksterneOpplysninger(urlList);
        StedsnavnTilleggsopplysningstypeKodeId id5 = new StedsnavnTilleggsopplysningstypeKodeId();
        id5.setValue(ID);
        tilleggsopplysning.setTilleggsopplysningstypeId(id5);
        tilleggsopplysninger.getItem().add(tilleggsopplysning);
        return tilleggsopplysninger;
    }

    private StedId stedId() {
        StedId id = new StedId();
        id.setValue(ID);
        return id;
    }

    private StedsnavnId stedsnavnId() {
        StedsnavnId id = new StedsnavnId();
        id.setValue(ID);
        return id;
    }

    private SpraakKodeId spraakKodeId() {
        SpraakKodeId spraakKodeId = new SpraakKodeId();
        spraakKodeId.setValue(ID);
        return spraakKodeId;
    }

    private SkrivemaateId skrivemaateId() {
        SkrivemaateId skrivemaateId = new SkrivemaateId();
        skrivemaateId.setValue(ID);
        return skrivemaateId;
    }

    private RekkefoelgeKodeId rekkefoelgeId() {
        RekkefoelgeKodeId rekkefoelgeKodeId = new RekkefoelgeKodeId();
        rekkefoelgeKodeId.setValue(ID);
        return rekkefoelgeKodeId;
    }

    private no.statkart.stedsnavn.ssr.wsapi.v1.domain.LocalDate localDate() {
        no.statkart.stedsnavn.ssr.wsapi.v1.domain.LocalDate localDate = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.LocalDate();
        localDate.setDate(DateHjelper.fromLocalDate(TODAY));
        return localDate;
    }

    private void setFellesFelterForHistoriskKomponent(no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnEntityComponentWithHistory historiskKomponent) {
        historiskKomponent.setId(1L);
        historiskKomponent.setOppdateringsdato(timestamp());
        historiskKomponent.setOppdatertAv(OPPDATERT_AV);
        historiskKomponent.setSluttdato(timestamp());
        historiskKomponent.setAvsluttetAv(AVSLUTTET_AV);
        historiskKomponent.setRegistreringsdato(timestamp());
    }

    private void setFellesFelterForHistoriskBoble(StedsnavnBubbleObjectWithHistory bubbleObject) {
        bubbleObject.setAvsluttetAv(AVSLUTTET_AV);
        bubbleObject.setSluttdato(timestamp());
        bubbleObject.setRegistreringsdato(timestamp());
        bubbleObject.setOppdateringsdato(timestamp());
        bubbleObject.setOppdatertAv(OPPDATERT_AV);
    }

    private Timestamp timestamp() {
        Timestamp timestamp = new Timestamp();
        timestamp.setTimestamp(DateHjelper.fromLocalDateTime(NOW));
        return timestamp;
    }

}
