package no.statkart.wsclient.stedsnavn;

public class NavnestatusKode extends Kode {

    public NavnestatusKode(StedsnavnBobleId.NavnestatusKodeId navnestatusKodeId, StedsnavnBobleId.StedsnavnKodelisteId kodelisteId, String kodeverdi, LocalizedString navn) {
        super(navnestatusKodeId, kodelisteId, kodeverdi, navn);
    }

}
