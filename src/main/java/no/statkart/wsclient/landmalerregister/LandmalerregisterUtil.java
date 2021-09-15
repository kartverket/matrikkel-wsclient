package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.ValidationException;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Hjelpeklasser for søk mot AAL eller mock-implementasjon
 */
public class LandmalerregisterUtil {

    private LandmalerregisterUtil() {
        //Dette er en util-klasse, og skal ikke kunne initieres
    }

    public static final String URL_LANDMALERNUMMER_PARAMETER = "landmaalernummer";
    public static final String URL_NAVN_PARAMETER = "navn";

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
                o.getString(URL_LANDMALERNUMMER_PARAMETER),
                o.getString(URL_NAVN_PARAMETER)
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
        // hvis alt er blankt
        if (Stream.of(landmalernummer, fornavn, etternavn).allMatch(s -> s == null || s.trim().isEmpty())) {
            throw new ValidationException("Kun tomme parametre");
        }

        try {
            URIBuilder uriBuilder = new URIBuilder(requestUrl);

            if (landmalernummer != null) {
                uriBuilder.addParameter(URL_LANDMALERNUMMER_PARAMETER, landmalernummer.trim());
            }

            String navn = fornavn == null ? "" : fornavn.trim();
            navn = navn + (etternavn == null ? "" : " " + etternavn.trim());
            navn = navn.trim();
            if (!navn.isEmpty()) {
                uriBuilder.addParameter(URL_NAVN_PARAMETER, navn);
            }

            return uriBuilder.build().toURL().toString();
        } catch (IllegalArgumentException | URISyntaxException | MalformedURLException e) {
            throw new ImplementationException("Request-url til Landmålerregisteret er ikke gyldig", e);
        }
    }
}