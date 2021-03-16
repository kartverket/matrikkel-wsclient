package no.statkart.wsclient.landmalerregister;


import java.util.Set;

/**
 * Interface for tjeneste mot Landmålerregisteret (AAL)
 */
public interface LandmalerregisterServiceWS {

    /**
     * Søker etter landmåler basert på input.
     *
     * @return Et set av landmålere. Kan være tomt.
     */
    Set<LandmalerFraAAL> findLandmalerWS(String landmalernummer, String fornavn, String etternavn);
}