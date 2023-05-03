package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class StedsnavnInternMerknad extends StedsnavnEntityComponentWithHistory {

    private String tekst;
    private StedsnavnBobleId.StedsnavnMerknadstypeKodeId merknadstypeId;
    private List<String> fellesarkiv;

    public StedsnavnInternMerknad(Long id, LocalDateTime registreringsdato, String tekst, StedsnavnBobleId.StedsnavnMerknadstypeKodeId merknadstypeId, List<String> fellesarkiv) {
        super(id, registreringsdato);
        this.tekst = tekst;
        this.merknadstypeId = merknadstypeId;
        this.fellesarkiv = Objects.requireNonNull(fellesarkiv);
    }

    public String getTekst() {
        return tekst;
    }

    public StedsnavnBobleId.StedsnavnMerknadstypeKodeId getMerknadstypeId() {
        return merknadstypeId;
    }

    public List<String> getFellesarkiv() {
        return fellesarkiv;
    }
}
