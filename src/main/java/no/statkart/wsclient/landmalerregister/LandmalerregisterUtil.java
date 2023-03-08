package no.statkart.wsclient.landmalerregister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.ValidationException;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Hjelpeklasser for søk mot AAL eller mock-implementasjon
 */
public class LandmalerregisterUtil {

    private LandmalerregisterUtil() {
        //Dette er en util-klasse, og skal ikke kunne initieres
    }

    public static final String URL_LANDMALERE_ARRAY = "landmaalere";
    public static final String URL_LANDMALERNUMMER_PARAMETER = "landmaalernummer";
    public static final String URL_NAVN_PARAMETER = "navn";

    /**
     * Henter ut landmålere fra JSON-listen som returneres fra AAL
     *
     * @param responseJson JSON-respons fra AAL
     * @return Et set av den interne objekttypen LandmalerFraAAL
     */
    public static Set<LandmalerFraAAL> lagSetLandmalereFraAALFraJsonResponse(String responseJson) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            final ArrayNode arrayNode = (ArrayNode) mapper.readTree(responseJson).get(URL_LANDMALERE_ARRAY);
            // opprett LandmalerDTO-objekter pr landmåler som returneres
            return Set.of(mapper.readValue(arrayNode.toString(), LandmalerFraAAL[].class));
        } catch (JsonProcessingException e) {
            throw new ImplementationException("Noe gikk galt med prosessering av landmåler-JSON", e);
        }
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