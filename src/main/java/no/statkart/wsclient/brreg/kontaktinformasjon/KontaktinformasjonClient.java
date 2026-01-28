package no.statkart.wsclient.brreg.kontaktinformasjon;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasse for å gjøre kall mot enhetsregisteret sitt api for kontaktinformasjon for seksjonssameier. Alle seksjonssameier
 * med ni eller flere seksjoner er påkrevd registrert i foretaksregisteret, mens det er friviligg for mindre sameier.
 * <p>
 * En veldig enkel implementasjon som benytter seg av URL-er og parser returstrengen for hånd.
 * <p>
 * Format på response er som følger:
 * <p>
 * [
 * {
 * "orgnr": "000000000",
 * "kommnr": "3411",
 * "gaardsnr": "527",
 * "bruksnr": "1",
 * "festenr": null,
 * "matrikkelenhetid": "31874161",
 * "rekkefolge": "1"
 * },
 * {
 * "orgnr": "000000000",
 * "kommnr": "3411",
 * "gaardsnr": "527",
 * "bruksnr": "1",
 * "festenr": null,
 * "matrikkelenhetid": "31874161",
 * "rekkefolge": "1"
 * }
 * ]
 * <p>
 * OBS! Respons fra tjenesten er ikke pretty-printet slik, den er uten linjeskift.
 * <p>
 * Se <a href="https://data.brreg.no/enhetsregisteret/api/docs/index.html">https://data.brreg.no/enhetsregisteret/api/docs/index.html</a> for dokumentasjon av Brønnøysunds tjenester.
 */
public class KontaktinformasjonClient {
    public static final Duration READ_TIMEOUT = Duration.ofSeconds(5);
    Logger logger = LoggerFactory.getLogger(KontaktinformasjonClient.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String endpointURL;

    /**
     * Konstruktør for klienten. Hvis parameteren er null vil vi gå mot testsystem for Brønnøysunds tjenester
     */
    public KontaktinformasjonClient(String endpointURL) {
        if (endpointURL != null) {
            this.endpointURL = endpointURL + "?matrikkelenhetid=";
        } else {
            this.endpointURL = "https://data.brreg.no:443/enhetsregisteret/api/matrikkelenhet?matrikkelenhetid=";
        }
    }

    /**
     * Tjeneste for å kalle på Brønnøysunds enhetsregister-tjenester for å hente ut kontaktinformasjon for et
     * seksjonssameie. Input til kallet er den matrikkelens id for den seksjonerte matrikkelenheten.
     * <p>
     * Se <a href="https://data.brreg.no/enhetsregisteret/api/docs/index.html">https://data.brreg.no/enhetsregisteret/api/docs/index.html</a> for dokumentasjon av Brønnøysunds tjenester.
     *
     * @param matrikkelenhetId Matrikkelenhet vi vil søke etter
     * @return Liste av organisasjonsnummer som er kontaktinformasjon for angitt matrikkelenhet. Tom liste hvis ingenting
     * er registrert
     */
    public List<String> findKontaktinformasjonForMatrikkelenhetId(Long matrikkelenhetId) {
        try {
            var request = HttpRequest.newBuilder()
                .uri(URI.create(this.endpointURL + matrikkelenhetId))
                .timeout(READ_TIMEOUT)
                .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            // APIet svarer med 404 dersom ressursen man etterspør ikke finnes. For at det ikke skal feile svarer vi med tom liste.
            if (response.statusCode() == 404) {
                logger.warn("404 mot BRREG ved oppslag på kontaktinformasjon for matrikkelenhet id {}", matrikkelenhetId);
                return Collections.emptyList();
            } else if (response.statusCode() >= 400) {
                var error = CharStreams.toString(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
                logger.error("{} mot BRREG for matrikkelenhet id {}; {}", response.statusCode(), matrikkelenhetId, error);
                throw new RuntimeException(error);
            }

            List<OrgNr> orgNrs = objectMapper.readValue(response.body(), new TypeReference<>() {});
            return orgNrs.stream()
                .map(it -> it.orgnr)
                .sorted()
                .collect(Collectors.toList());

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class OrgNr {
        public String orgnr;
    }
}
