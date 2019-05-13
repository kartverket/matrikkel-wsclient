package no.statkart.wsclient.stedsnavn;

public class SpraakKode extends Kode {
    public SpraakKode(StedsnavnBobleId.SpraakKodeId spraakKodeId, StedsnavnBobleId.StedsnavnKodelisteId kodelisteId, String kodeverdi, LocalizedString navn) {
        super(spraakKodeId, kodelisteId, kodeverdi, navn);
    }

}
