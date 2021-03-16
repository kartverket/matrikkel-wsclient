package no.statkart.wsclient.landmalerregister;

/**
 * Inneholder informasjon om en landmåler.
 */
public class LandmalerFraAAL {

    private final String landmalernummer;
    private final String navn;


    public LandmalerFraAAL(String landmalernummer, String navn) {
        this.landmalernummer = landmalernummer;
        this.navn = navn;
    }

    public String getLandmalernummer() {
        return landmalernummer;
    }

    public String getNavn() {
        return navn;
    }
}
