package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Skrivemaate extends StedsnavnBobleMedHistorie {

    private int skrivemaatenummer;
    private StedsnavnBobleId.StedsnavnId stedsnavnId;
    private StedsnavnBobleId.SkrivemaateId normertFraId;
    private StedsnavnBobleId.RekkefoelgeKodeId rekkefoelgeId;
    private List<KasusForSkrivemaate> kasuser = new ArrayList<>();
    private String kortnavn;
    private List<SkrivemaatestatusHistorikk> skrivemaatestatusHistorikker = new ArrayList<>();
    private List<SkrivemaateInternMerknad> interneMerknader = new ArrayList<>();
    private List<Dokumentasjon> dokumentasjon = new ArrayList<>();

    public Skrivemaate(StedsnavnBobleId id, LocalDateTime registreringsdato) {
        super(id, registreringsdato);
    }

    public int getSkrivemaatenummer() {
        return skrivemaatenummer;
    }

    public void setSkrivemaatenummer(int skrivemaatenummer) {
        this.skrivemaatenummer = skrivemaatenummer;
    }

    public StedsnavnBobleId.StedsnavnId getStedsnavnId() {
        return stedsnavnId;
    }

    public void setStedsnavnId(StedsnavnBobleId.StedsnavnId stedsnavnId) {
        this.stedsnavnId = stedsnavnId;
    }

    public StedsnavnBobleId.SkrivemaateId getNormertFraId() {
        return normertFraId;
    }

    public void setNormertFraId(StedsnavnBobleId.SkrivemaateId normertFraId) {
        this.normertFraId = normertFraId;
    }

    public StedsnavnBobleId.RekkefoelgeKodeId getRekkefoelgeId() {
        return rekkefoelgeId;
    }

    public void setRekkefoelgeId(StedsnavnBobleId.RekkefoelgeKodeId rekkefoelgeId) {
        this.rekkefoelgeId = rekkefoelgeId;
    }

    public List<KasusForSkrivemaate> getKasuser() {
        return kasuser;
    }

    public void setKasuser(List<KasusForSkrivemaate> kasuser) {
        this.kasuser = kasuser;
    }

    public String getKortnavn() {
        return kortnavn;
    }

    public void setKortnavn(String kortnavn) {
        this.kortnavn = kortnavn;
    }

    public List<SkrivemaatestatusHistorikk> getSkrivemaatestatusHistorikker() {
        return skrivemaatestatusHistorikker;
    }

    public void setSkrivemaatestatusHistorikker(List<SkrivemaatestatusHistorikk> skrivemaatestatusHistorikker) {
        this.skrivemaatestatusHistorikker = skrivemaatestatusHistorikker;
    }

    public List<SkrivemaateInternMerknad> getInterneMerknader() {
        return interneMerknader;
    }

    public void setInterneMerknader(List<SkrivemaateInternMerknad> interneMerknader) {
        this.interneMerknader = interneMerknader;
    }

    public List<Dokumentasjon> getDokumentasjon() {
        return dokumentasjon;
    }

    public void setDokumentasjon(List<Dokumentasjon> dokumentasjon) {
        this.dokumentasjon = dokumentasjon;
    }
}
