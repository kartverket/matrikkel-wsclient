package no.statkart.wsclient.landmalerregister;

/**
 * Inneholder informasjon om en landmåler.
 */
public class LandmalerFraAAL {

    private final Long landmalernr;
    private final String navn;
    private final String autorisasjonsdato;

    public LandmalerFraAAL(Long landmalernr, String navn, String autorisasjonsdato) {
        this.landmalernr = landmalernr;
        this.navn = navn;
        this.autorisasjonsdato = autorisasjonsdato;
    }

    public Long getLandmalernr() {
        return landmalernr;
    }

    public String getNavn() {
        return navn;
    }

    public String getAutorisasjonsdato() {
        return autorisasjonsdato;
    }
}
