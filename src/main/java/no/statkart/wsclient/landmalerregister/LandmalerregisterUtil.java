package no.statkart.wsclient.landmalerregister;

import com.google.common.base.Strings;
import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.ValidationException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Hjelpeklasser for søk mot AAL eller mock-implementasjon
 */
public class LandmalerregisterUtil {

    /**
     * Henter ut landmålere fra JSONObjectet som returneres fra AAL
     *
     * @param landmalerArray Listen av av treff fra webservice (rest)
     * @return Et set av den interne objekttypen LandmalerFraAAL
     */
    public static Set<LandmalerFraAAL> lagSetLandmalereFraAALFraJsonResponse(JSONArray landmalerArray) {
        Set<LandmalerFraAAL> landmalere = new HashSet<>();

        // opprett LandmalerDTO-objekter pr landmåler som returneres
        landmalerArray.toList().stream()
            .map(o -> new JSONObject((Map) o))
            .map(o -> new LandmalerFraAAL(
                Strings.padStart(String.valueOf(o.get("landmaalernummer")), 6, '0'), //TODO MAT-18144 Hack for at det skal funke pr nå, vil gjøres om til string
                o.getString("navn")
                )
            )
            .forEach(landmalere::add);

        return landmalere;
    }

    /**
     * Valider input og bygg parameter-string til URL
     *
     * @return Søkeparametere for request-strengen
     */
    public static String validateAndBuildUrlParameters(String landmalernummer, String fornavn, String etternavn) {
        // hvis alt er blankt
        if (landmalernummer == null && Stream.of(fornavn, etternavn).allMatch(s -> s == null || s.trim().isEmpty())) {
            throw new ImplementationException("Kun tomme parametre");
        }

        return buildUrlParameters(landmalernummer, fornavn, etternavn);
    }

    // bygg en url med de parameterene som er angitt
    private static String buildUrlParameters(String landmalernummer, String fornavn, String etternavn) {

        // sjekk hva som er fylt ut
        boolean landmalerUtfylt = landmalernummer != null;
        boolean fornavnUtfylt = fornavn != null && !fornavn.trim().isEmpty();
        boolean etternavnUtfylt = etternavn != null && !etternavn.trim().isEmpty();

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("?");

        // hvis landmåler er fylt ut
        if (landmalerUtfylt) {
            String urlLandmalernummerParameter = "landmaalernummer=";
            urlBuilder.append(urlLandmalernummerParameter);
            urlBuilder.append(landmalernummer);

            // hvis det skal være flere parametre i url
            if (fornavnUtfylt || etternavnUtfylt) {
                urlBuilder.append("&");
            }
        }

        // hvis det skal være en navneparameter
        if (fornavnUtfylt || etternavnUtfylt) {
            String urlNavnParameter = "navn=";
            urlBuilder.append(urlNavnParameter);
        }

        // hvis fornavn er fylt ut
        if (fornavnUtfylt) {
            urlBuilder.append(fornavn);
            urlBuilder.append("%20");
        }

        // hvis etternavn er fylt ut
        if (etternavnUtfylt) {
            urlBuilder.append(etternavn);
        }

        return urlBuilder.toString();
    }
}