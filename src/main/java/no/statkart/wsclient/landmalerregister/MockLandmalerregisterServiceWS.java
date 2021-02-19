package no.statkart.wsclient.landmalerregister;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockLandmalerregisterServiceWS implements LandmalerregisterServiceWS {

    private final List<LandmalerFraAAL> landmalerDb;

    public MockLandmalerregisterServiceWS() {
        landmalerDb = List.of(
                new LandmalerFraAAL(101, "Kari Nordmann"),
                new LandmalerFraAAL(102, "Ola Nordmann"),
                new LandmalerFraAAL(103, "Landmåler Landmålersen")
            );
    }

    @Override
    public Set<LandmalerFraAAL> findLandmalerWS(String landmalernr, String fornavn, String etternavn) {
        Set<LandmalerFraAAL> sokeresultat = new HashSet<>();

        LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernr, fornavn, etternavn);

        // løp gjennom alle i "databasen"
        for (LandmalerFraAAL landmalerFraDb : landmalerDb) {

            // hent ut landmåler fra "db"
            // hvis landmålernummer ikke er null og ikke blankt
            if (landmalernr != null && !landmalernr.isBlank()) {
                // hvis det ikke stemmer, gå videre
                if (!landmalerFraDb.getLandmalernr().equals(Integer.valueOf(landmalernr))) {
                    continue;
                }
            }

            // hvis fornavn ikke er null og ikke er blankt
            if (fornavn != null && !fornavn.isBlank()) {
                // hvis det ikke stemmer, gå videre
                if (!landmalerFraDb.getNavn().contains(fornavn)) {
                    continue;
                }
            }

            // hvis etternavn ikke er null og ikke er blankt
            if (etternavn != null && !etternavn.isBlank()) {
                if (!landmalerFraDb.getNavn().contains(etternavn)) {
                    continue;
                }
            }

            // har man kommet seg hit, er det treff
            sokeresultat.add(
                new LandmalerFraAAL(
                    landmalerFraDb.getLandmalernr(),
                    landmalerFraDb.getNavn()
                )
            );
        }

        return sokeresultat;
    }
}
