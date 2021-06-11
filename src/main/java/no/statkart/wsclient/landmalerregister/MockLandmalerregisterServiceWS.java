package no.statkart.wsclient.landmalerregister;

import com.google.common.base.Strings;
import no.statkart.skif.exception.OperationalException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

/**
 * Mock-implementasjon av LandmalerregisterService
 * Bruker en JSON-fil med noen som en "database" for søkesvar
 */
public class MockLandmalerregisterServiceWS implements LandmalerregisterServiceWS {

    LandmalerregisterMockDb landmalerregisterMockDb;
    String dummyUrl = "http://www.dummy.com/dummy";

    @Override
    public Set<LandmalerFraAAL> findLandmalerWS(String landmalernummer, String fornavn, String etternavn) {

        landmalerregisterMockDb = new LandmalerregisterMockDb();

        Set<LandmalerFraAAL> sokeresultat;

        LandmalerregisterUtil.validateAndBuildUrlParameters(dummyUrl, landmalernummer, fornavn, etternavn);

        JSONArray sokeTreffArray = landmalerregisterMockDb.sokEtterLandmaler(landmalernummer, fornavn, etternavn);
        sokeresultat = LandmalerregisterUtil.lagSetLandmalereFraAALFraJsonResponse(sokeTreffArray);

        return sokeresultat;
    }


    private static class LandmalerregisterMockDb {
        private final JSONArray landmalereArray;

        public LandmalerregisterMockDb() {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("landmalerregister/mockResponse.json");
            String jsonString = readFromStream(resourceAsStream);

            this.landmalereArray = new JSONObject(jsonString).getJSONArray("landmaalere");
        }

        public JSONArray sokEtterLandmaler(String landmalernummer, String fornavn, String etternavn) {
            JSONArray sokeresultatArray = new JSONArray();

            // søker gjennom jsonArrayen og vurderer om gjeldende jsonObjectet skal legges til i svarArray.
            landmalereArray.toList().stream()
                .map(o -> new JSONObject((Map) o))
                .forEach(o -> {
                    if (treffPaSok(o, landmalernummer, fornavn, etternavn)) {
                        sokeresultatArray.put(o);
                    }
                });

            return sokeresultatArray;
        }

        // sjekker om noen jsonObjectet svarer til søkekriteriene
        private boolean treffPaSok(JSONObject landmalerJson, String landmalernummer, String fornavn, String etternavn) {
            boolean treff = false;

            // hvis landmålernummer er fylt ut
            if (landmalernummer != null) {

                // gjør om til string, da AAL returnerer alle med f.eks. 1 i landmalernummer-feltet (212, 1, 123, 51 = true)
                String landmalernummerKey = "landmaalernummer";
                String landmalernummerFraJson = Strings.padStart(String.valueOf(landmalerJson.get(landmalernummerKey)), 6, '0'); //TODO MAT-18144 Hack for at det skal funke pr nå, vil gjøres om til string
                // sett treff til true
                if (landmalernummerFraJson.contains(landmalernummer)) {
                    treff = true;
                } else {
                    // hvis fylt ut, men ikke riktig = ikke treff
                    return false;
                }
            }

            String navnKey = "navn";
            String navn = landmalerJson.getString(navnKey);

            // hvis fornavn er fylt ut
            if (fornavn != null) {
                // navn inneholder fornavn (usikker på hva AAL egentlig gjør, men er ikke så farlig) - sett treff til true
                if(navn.contains(fornavn)) {
                    treff = true;
                } else {
                    // hvis fylt ut, men stemmer ikke = ikke treff
                    return false;
                }
            }

            // hvis etternavn er fylt ut
            if (etternavn != null) {
                // navn inneholder etternavn, sett treff = true
                if(navn.contains(etternavn)) {
                    treff = true;
                } else {
                    // hvis fylt ut, men ikke stemmer = ikke treff
                    return false;
                }
            }

            return treff;
        }

        // leser inn json-string fra fil
        private String readFromStream(InputStream inputStream) {
            StringBuilder jsonString = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                while ((line = br.readLine()) != null) {
                    jsonString.append(line);
                }

            } catch (IOException e) {
                throw new OperationalException("Innlesing fra landmalerregisterMockResponse.json feilet");
            }

            return jsonString.toString();
        }
    }
}

