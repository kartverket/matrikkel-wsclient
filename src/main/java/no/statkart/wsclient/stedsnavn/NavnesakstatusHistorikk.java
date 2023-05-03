package no.statkart.wsclient.stedsnavn;


import java.time.LocalDate;

public class NavnesakstatusHistorikk {

    private LocalDate fraDato;
    private StedsnavnBobleId.NavnesakstatusKodeId navnesakstatusId;

    public NavnesakstatusHistorikk(LocalDate fraDato, StedsnavnBobleId.NavnesakstatusKodeId navnesakstatusId) {
        this.fraDato = fraDato;
        this.navnesakstatusId = navnesakstatusId;
    }

    public LocalDate getFraDato() {
        return fraDato;
    }

    public StedsnavnBobleId.NavnesakstatusKodeId getNavnesakstatusId() {
        return navnesakstatusId;
    }
}
