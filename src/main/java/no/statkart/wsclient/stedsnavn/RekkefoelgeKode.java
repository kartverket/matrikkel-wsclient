package no.statkart.wsclient.stedsnavn;

public class RekkefoelgeKode extends Kode {

    public RekkefoelgeKode(StedsnavnBobleId.RekkefoelgeKodeId kodeId, StedsnavnBobleId.StedsnavnKodelisteId kodelisteId, String kodeverdi, LocalizedString navn) {
        super(kodeId, kodelisteId, kodeverdi, navn);
    }

}
