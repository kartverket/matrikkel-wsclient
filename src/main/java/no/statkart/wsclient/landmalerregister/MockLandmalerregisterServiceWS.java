package no.statkart.wsclient.landmalerregister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.OperationalException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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

        String sokeTreffArray = landmalerregisterMockDb.sokEtterLandmaler(landmalernummer, fornavn, etternavn);
        sokeresultat = LandmalerregisterUtil.lagSetLandmalereFraAALFraJsonResponse(sokeTreffArray);

        return sokeresultat;
    }


    private static class LandmalerregisterMockDb {
        private final ArrayNode landmaalereArray;

        public LandmalerregisterMockDb() {
            InputStream resourceAsStream =
                getClass().getClassLoader().getResourceAsStream("landmalerregister/mockResponse.json");
            String jsonString = readFromStream(resourceAsStream);
            final ObjectMapper mapper = new ObjectMapper();

            try {
                this.landmaalereArray = (ArrayNode) mapper.readTree(jsonString).get(LandmalerregisterUtil.URL_LANDMALERE_ARRAY);
            } catch (JsonProcessingException e) {
                throw new ImplementationException("Noe gikk galt under prosessering av JSON" , e);
            }
        }

        public String sokEtterLandmaler(String landmaalernummer, String fornavn, String etternavn) {
            StringBuilder sokeresultat = new StringBuilder("{\"");
            sokeresultat.append(LandmalerregisterUtil.URL_LANDMALERE_ARRAY);
            sokeresultat.append("\":[");

            List<String> resultat = new ArrayList<>();
            // søker gjennom arrayNoden og vurderer om gjeldende node skal legges til i svarArray.
            landmaalereArray.forEach(node -> {
                    if (treffPaSok(node, landmaalernummer, fornavn, etternavn)) {
                        resultat.add(node.toString());
                    }
                });
            sokeresultat.append(String.join(",", resultat));
            sokeresultat.append("]}");
            return sokeresultat.toString();
        }

        // sjekker om noen jsonObjectet svarer til søkekriteriene
        private boolean treffPaSok(JsonNode landmalerJson, String landmalernummer, String fornavn, String etternavn) {
            boolean treff = false;

            // hvis landmålernummer er fylt ut
            if (landmalernummer != null) {

                // gjør om til string, da AAL returnerer alle med f.eks. 1 i landmalernummer-feltet (212, 1, 123, 51 = true)
                String landmalernummerFraJson = landmalerJson.get(LandmalerregisterUtil.URL_LANDMALERNUMMER_PARAMETER).textValue();
                // sett treff til true
                if (landmalernummerFraJson.contains(landmalernummer)) {
                    treff = true;
                } else {
                    // hvis fylt ut, men ikke riktig = ikke treff
                    return false;
                }
            }

            String navn = landmalerJson.get(LandmalerregisterUtil.URL_NAVN_PARAMETER).textValue();

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

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

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

