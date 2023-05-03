package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Stedsnavn extends StedsnavnBobleMedHistorie {

    private int stedsnavnnummer;
    private StedsnavnBobleId.StedId stedId;
    private List<NavnestatusHistorikk> navnestatusHistorikker = new ArrayList<>();
    private List<NavnesakstatusHistorikk> navnesakstatusHistorikker = new ArrayList<>();
    private StedsnavnBobleId.StedsnavnId primaerfunksjonId;
    private StedsnavnBobleId.StedsnavnId gruppetilhoerighetId;
    private boolean eksonym;
    private StedsnavnBobleId.SpraakKodeId spraakId;
    private StedsnavnBobleId.SpraakKodeId opphavsspraakId;
    private int hoeyesteSkrivemaatenummer;
    private List<StedsnavnTilleggsopplysning> tilleggsopplysninger = new ArrayList<>();
    private List<StedsnavnInternMerknad> interneMerknader = new ArrayList<>();

    public Stedsnavn(StedsnavnBobleId id, LocalDateTime registreringsdato) {
        super(id, registreringsdato);
    }

    public int getStedsnavnnummer() {
        return stedsnavnnummer;
    }

    public void setStedsnavnnummer(int stedsnavnnummer) {
        this.stedsnavnnummer = stedsnavnnummer;
    }

    public StedsnavnBobleId.StedId getStedId() {
        return stedId;
    }

    public void setStedId(StedsnavnBobleId.StedId stedId) {
        this.stedId = stedId;
    }

    public List<NavnestatusHistorikk> getNavnestatusHistorikker() {
        return navnestatusHistorikker;
    }

    public void setNavnestatusHistorikker(List<NavnestatusHistorikk> navnestatusHistorikker) {
        this.navnestatusHistorikker = navnestatusHistorikker;
    }

    public List<NavnesakstatusHistorikk> getNavnesakstatusHistorikker() {
        return navnesakstatusHistorikker;
    }

    public void setNavnesakstatusHistorikker(List<NavnesakstatusHistorikk> navnesakstatusHistorikker) {
        this.navnesakstatusHistorikker = navnesakstatusHistorikker;
    }

    public StedsnavnBobleId.StedsnavnId getPrimaerfunksjonId() {
        return primaerfunksjonId;
    }

    public void setPrimaerfunksjonId(StedsnavnBobleId.StedsnavnId primaerfunksjonId) {
        this.primaerfunksjonId = primaerfunksjonId;
    }

    public StedsnavnBobleId.StedsnavnId getGruppetilhoerighetId() {
        return gruppetilhoerighetId;
    }

    public void setGruppetilhoerighetId(StedsnavnBobleId.StedsnavnId gruppetilhoerighetId) {
        this.gruppetilhoerighetId = gruppetilhoerighetId;
    }

    public boolean isEksonym() {
        return eksonym;
    }

    public void setEksonym(boolean eksonym) {
        this.eksonym = eksonym;
    }

    public StedsnavnBobleId.SpraakKodeId getSpraakId() {
        return spraakId;
    }

    public void setSpraakId(StedsnavnBobleId.SpraakKodeId spraakId) {
        this.spraakId = spraakId;
    }

    public StedsnavnBobleId.SpraakKodeId getOpphavsspraakId() {
        return opphavsspraakId;
    }

    public void setOpphavsspraakId(StedsnavnBobleId.SpraakKodeId opphavsspraakId) {
        this.opphavsspraakId = opphavsspraakId;
    }

    public int getHoeyesteSkrivemaatenummer() {
        return hoeyesteSkrivemaatenummer;
    }

    public void setHoeyesteSkrivemaatenummer(int hoeyesteSkrivemaatenummer) {
        this.hoeyesteSkrivemaatenummer = hoeyesteSkrivemaatenummer;
    }

    public List<StedsnavnTilleggsopplysning> getTilleggsopplysninger() {
        return tilleggsopplysninger;
    }

    public void setTilleggsopplysninger(List<StedsnavnTilleggsopplysning> tilleggsopplysninger) {
        this.tilleggsopplysninger = tilleggsopplysninger;
    }

    public List<StedsnavnInternMerknad> getInterneMerknader() {
        return interneMerknader;
    }

    public void setInterneMerknader(List<StedsnavnInternMerknad> interneMerknader) {
        this.interneMerknader = interneMerknader;
    }
}
