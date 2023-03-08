package no.statkart.wsclient.landmalerregister;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Inneholder informasjon om en landmåler.
 */
public class LandmalerFraAAL {

    private final String landmalernummer;
    private final String navn;


    public LandmalerFraAAL(@JsonProperty(LandmalerregisterUtil.URL_LANDMALERNUMMER_PARAMETER) String landmaalernummer,
                           @JsonProperty(LandmalerregisterUtil.URL_NAVN_PARAMETER) String navn) {
        this.landmalernummer = landmaalernummer;
        this.navn = navn;
    }

    public String getLandmalernummer() {
        return landmalernummer;
    }

    public String getNavn() {
        return navn;
    }
}
