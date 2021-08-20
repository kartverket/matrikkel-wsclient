package no.statkart.wsclient.landmalerregister;

import com.google.common.base.Strings;
import no.statkart.skif.exception.ImplementationException;
import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
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
    public static String validateAndBuildUrlParameters(String requestUrl, String landmalernummer, String fornavn, String etternavn) {
        // TODO: Lage en mer robust builder for URI med parametre
        // hvis alt er blankt
        if (landmalernummer == null && Stream.of(fornavn, etternavn).allMatch(s -> s == null || s.trim().isEmpty())) {
            throw new ImplementationException("Kun tomme parametre");
        }

        // sjekk hva som er fylt ut
        boolean landmalerUtfylt = landmalernummer != null;
        boolean fornavnUtfylt = fornavn != null && !fornavn.trim().isEmpty();
        boolean etternavnUtfylt = etternavn != null && !etternavn.trim().isEmpty();

        StringBuilder urlParamsBuilder = new StringBuilder();
        urlParamsBuilder.append("?");

        // hvis landmåler er fylt ut
        if (landmalerUtfylt) {
            String urlLandmalernummerParameter = "landmaalernummer=";
            urlParamsBuilder.append(urlLandmalernummerParameter);
            urlParamsBuilder.append(landmalernummer);

            // hvis det skal være flere parametre i url
            if (fornavnUtfylt || etternavnUtfylt) {
                urlParamsBuilder.append("&");
            }
        }

        // hvis det skal være en navneparameter
        if (fornavnUtfylt || etternavnUtfylt) {
            urlParamsBuilder.append("navn=");
        }

        // hvis fornavn er fylt ut
        if (fornavnUtfylt) {
            // hvis fornavn inneholder mellomrom
            fornavn = fornavn.replaceAll("\\s", "%20");
            urlParamsBuilder.append(fornavn);
            // hvis både fornavn og etternavn er fylt ut
            if (etternavnUtfylt) {
                urlParamsBuilder.append("%20");
            }
        }

        // hvis etternavn er fylt ut
        if (etternavnUtfylt) {
            // hvis etternavn inneholder mellomrom
            etternavn = etternavn.replaceAll("\\s", "%20");
            urlParamsBuilder.append(etternavn);
        }

        String requestUrlWithParams = requestUrl + urlParamsBuilder.toString();

        if (!UrlValidator.getInstance().isValid(requestUrlWithParams)) {
            throw new ImplementationException("Request-url til Landmålerregisteret er ikke gyldig");
        }

        return requestUrlWithParams;
    }
}