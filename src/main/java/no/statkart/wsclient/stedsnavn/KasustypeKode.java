package no.statkart.wsclient.stedsnavn;

public class KasustypeKode extends Kode {

    public KasustypeKode(StedsnavnBobleId.KasustypeKodeId kodeId, StedsnavnBobleId.StedsnavnKodelisteId kodelisteId, String kodeverdi, LocalizedString navn) {
        super(kodeId, kodelisteId, kodeverdi, navn);
    }

}
