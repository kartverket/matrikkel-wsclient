package no.statkart.wsclient.landmalerregister;

/**
 * Inneholder informasjon om en landmåler.
 */
public class LandmalerFraAAL {

    private final Long landmalernr;
    private final String navn;


    public LandmalerFraAAL(Long landmalernr, String navn) {
        this.landmalernr = landmalernr;
        this.navn = navn;
    }

    public Long getLandmalernr() {
        return landmalernr;
    }

    public String getNavn() {
        return navn;
    }
}
