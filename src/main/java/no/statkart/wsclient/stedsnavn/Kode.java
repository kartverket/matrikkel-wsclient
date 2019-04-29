package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;

public class Kode extends StedsnavnBoble {

    private LocalDateTime oppdateringsdato;
    private String oppdatertAv;
    private LocalDateTime gyldigTil;
    private StedsnavnBobleId.StedsnavnKodelisteId kodelisteId;
    private String kodeverdi;
    private LocalizedString navn;

    public Kode(StedsnavnBobleId id, StedsnavnBobleId.StedsnavnKodelisteId kodelisteId, String kodeverdi, LocalizedString navn) {
        super(id);
        this.kodelisteId = kodelisteId;
        this.kodeverdi = kodeverdi;
        this.navn = navn;
    }

    public StedsnavnBobleId.StedsnavnKodelisteId getKodelisteId() {
        return kodelisteId;
    }

    public String getKodeverdi() {
        return kodeverdi;
    }

    public LocalizedString getNavn() {
        return navn;
    }

    public LocalDateTime getOppdateringsdato() {
        return oppdateringsdato;
    }

    public void setOppdateringsdato(LocalDateTime oppdateringsdato) {
        this.oppdateringsdato = oppdateringsdato;
    }

    public String getOppdatertAv() {
        return oppdatertAv;
    }

    public void setOppdatertAv(String oppdatertAv) {
        this.oppdatertAv = oppdatertAv;
    }

    public LocalDateTime getGyldigTil() {
        return gyldigTil;
    }

    public void setGyldigTil(LocalDateTime gyldigTil) {
        this.gyldigTil = gyldigTil;
    }
}
