package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sted extends StedsnavnBobleMedHistorie {

    private long stedsnummer;
    private List<StedsnavnBobleId.KommuneId> kommunerIds = new ArrayList<>();
    private StedsnavnBobleId.LandKodeId landId;
    private List<NavneobjekttypeHistorikk> navneobjekttypeHistorikker = new ArrayList<>();
    private List<StedstatusHistorikk> stedstatusHistorikker = new ArrayList<>();
    private List<VedtaksmyndighetHistorikk> vedtaksmyndighetHistorikker = new ArrayList<>();
    private StedsnavnBobleId.PosisjonId posisjonId;
    private StedsnavnBobleId.SpraakprioriteringKodeId spraakprioriteringId;
    private Sortering sortering;
    private int hoeyesteStedsnavnnummer;
    private List<StedTilleggsopplysning> tilleggsopplysninger = new ArrayList<>();
    private List<StedInternMerknad> interneMerknader = new ArrayList<>();
    private Matrikkelreferanse matrikkelreferanse;

    public Sted(StedsnavnBobleId id, LocalDateTime registreringsdato) {
        super(id, registreringsdato);
    }

    public long getStedsnummer() {
        return stedsnummer;
    }

    public void setStedsnummer(long stedsnummer) {
        this.stedsnummer = stedsnummer;
    }

    public List<StedsnavnBobleId.KommuneId> getKommunerIds() {
        return kommunerIds;
    }

    public void setKommunerIds(List<StedsnavnBobleId.KommuneId> kommunerIds) {
        this.kommunerIds = kommunerIds;
    }

    public StedsnavnBobleId.LandKodeId getLandId() {
        return landId;
    }

    public void setLandId(StedsnavnBobleId.LandKodeId landId) {
        this.landId = landId;
    }

    public List<NavneobjekttypeHistorikk> getNavneobjekttypeHistorikker() {
        return navneobjekttypeHistorikker;
    }

    public void setNavneobjekttypeHistorikker(List<NavneobjekttypeHistorikk> navneobjekttypeHistorikker) {
        this.navneobjekttypeHistorikker = navneobjekttypeHistorikker;
    }

    public List<StedstatusHistorikk> getStedstatusHistorikker() {
        return stedstatusHistorikker;
    }

    public void setStedstatusHistorikker(List<StedstatusHistorikk> stedstatusHistorikker) {
        this.stedstatusHistorikker = stedstatusHistorikker;
    }

    public List<VedtaksmyndighetHistorikk> getVedtaksmyndighetHistorikker() {
        return vedtaksmyndighetHistorikker;
    }

    public void setVedtaksmyndighetHistorikker(List<VedtaksmyndighetHistorikk> vedtaksmyndighetHistorikker) {
        this.vedtaksmyndighetHistorikker = vedtaksmyndighetHistorikker;
    }

    public StedsnavnBobleId.PosisjonId getPosisjonId() {
        return posisjonId;
    }

    public void setPosisjonId(StedsnavnBobleId.PosisjonId posisjonId) {
        this.posisjonId = posisjonId;
    }

    public StedsnavnBobleId.SpraakprioriteringKodeId getSpraakprioriteringId() {
        return spraakprioriteringId;
    }

    public void setSpraakprioriteringId(StedsnavnBobleId.SpraakprioriteringKodeId spraakprioriteringId) {
        this.spraakprioriteringId = spraakprioriteringId;
    }

    public Sortering getSortering() {
        return sortering;
    }

    public void setSortering(Sortering sortering) {
        this.sortering = sortering;
    }

    public int getHoeyesteStedsnavnnummer() {
        return hoeyesteStedsnavnnummer;
    }

    public void setHoeyesteStedsnavnnummer(int hoeyesteStedsnavnnummer) {
        this.hoeyesteStedsnavnnummer = hoeyesteStedsnavnnummer;
    }

    public List<StedTilleggsopplysning> getTilleggsopplysninger() {
        return tilleggsopplysninger;
    }

    public void setTilleggsopplysninger(List<StedTilleggsopplysning> tilleggsopplysninger) {
        this.tilleggsopplysninger = tilleggsopplysninger;
    }

    public List<StedInternMerknad> getInterneMerknader() {
        return interneMerknader;
    }

    public void setInterneMerknader(List<StedInternMerknad> interneMerknader) {
        this.interneMerknader = interneMerknader;
    }

    public Matrikkelreferanse getMatrikkelreferanse() {
        return matrikkelreferanse;
    }

    public void setMatrikkelreferanse(Matrikkelreferanse matrikkelreferanse) {
        this.matrikkelreferanse = matrikkelreferanse;
    }
}
