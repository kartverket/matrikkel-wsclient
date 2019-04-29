package no.statkart.wsclient.stedsnavn;

public class StedstatusKode extends Kode {

    public StedstatusKode(StedsnavnBobleId.StedstatusKodeId stedstatusKodeId, StedsnavnBobleId.StedsnavnKodelisteId kodelisteId, String kodeverdi, LocalizedString navn) {
        super(stedstatusKodeId, kodelisteId, kodeverdi, navn);
    }
}
