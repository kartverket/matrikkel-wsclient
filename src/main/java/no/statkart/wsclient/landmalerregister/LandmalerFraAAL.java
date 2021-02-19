package no.statkart.wsclient.landmalerregister;

/**
 * Inneholder informasjon om en landmåler.
 */
public class LandmalerFraAAL {

    private final Integer landmalernr;
    private final String navn;

    public LandmalerFraAAL(Integer landmalernr, String navn) {
        this.landmalernr = landmalernr;
        this.navn = navn;
    }

    public Integer getLandmalernr() {
        return landmalernr;
    }

    public String getNavn() {
        return navn;
    }
}
