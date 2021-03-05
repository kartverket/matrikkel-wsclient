package no.statkart.wsclient.landmalerregister;

/**
 * Inneholder informasjon om en landmåler.
 */
public class LandmalerFraAAL {

    private final Long landmalernr;
    private final String navn;
    private final String autorisasjonsdato;
    private final boolean autorisert;


    public LandmalerFraAAL(Long landmalernr, String navn, String autorisasjonsdato, boolean autorisert) {
        this.landmalernr = landmalernr;
        this.navn = navn;
        this.autorisasjonsdato = autorisasjonsdato;
        this.autorisert = autorisert;
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

    public boolean getAutorisert() {
        return autorisert;
    }
}
