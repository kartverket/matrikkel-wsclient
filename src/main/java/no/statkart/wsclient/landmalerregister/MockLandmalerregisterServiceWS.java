package no.statkart.wsclient.landmalerregister;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockLandmalerregisterServiceWS implements LandmalerregisterServiceWS {

    private final List<LandmalerFraAAL> landmalerDb;

    public MockLandmalerregisterServiceWS() {
        landmalerDb = Arrays.asList(
            new LandmalerFraAAL(101L, "Kari Nordmann", "01.01.2021"),
            new LandmalerFraAAL(102L, "Ola Nordmann", "02.01.2021"),
            new LandmalerFraAAL(103L, "Landmåler Landmålersen", "03.01.2021")
        );
    }

    @Override
    public Set<LandmalerFraAAL> findLandmalerWS(Long landmalernr, String fornavn, String etternavn) {
        Set<LandmalerFraAAL> sokeresultat = new HashSet<>();

        LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernr, fornavn, etternavn);

        // løp gjennom alle i "databasen"
        for (LandmalerFraAAL landmalerFraDb : landmalerDb) {

            // hent ut landmåler fra "db"
            // hvis landmålernummer ikke er null og ikke blankt
            if (landmalernr != null) {
                // hvis det ikke stemmer, gå videre
                if (!landmalerFraDb.getLandmalernr().equals(landmalernr)) {
                    continue;
                }
            }

            // hvis fornavn ikke er null og ikke er blankt
            if (fornavn != null && !fornavn.trim().isEmpty()) {
                // hvis det ikke stemmer, gå videre
                if (!landmalerFraDb.getNavn().contains(fornavn)) {
                    continue;
                }
            }

            // hvis etternavn ikke er null og ikke er blankt
            if (etternavn != null && !etternavn.trim().isEmpty()) {
                if (!landmalerFraDb.getNavn().contains(etternavn)) {
                    continue;
                }
            }

            // har man kommet seg hit, er det treff
            sokeresultat.add(
                new LandmalerFraAAL(
                    landmalerFraDb.getLandmalernr(),
                    landmalerFraDb.getNavn(),
                    landmalerFraDb.getAutorisasjonsdato()
                )
            );
        }

        return sokeresultat;
    }
}
