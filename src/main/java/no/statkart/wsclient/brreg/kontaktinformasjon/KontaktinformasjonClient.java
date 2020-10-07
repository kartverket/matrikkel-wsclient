package no.statkart.wsclient.brreg.kontaktinformasjon;

import com.google.common.collect.ImmutableList;
import com.google.common.io.CharStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
    Logger logger = LoggerFactory.getLogger(KontaktinformasjonClient.class);

    private final String endpointURL;

    /**
     * Konstruktør for klienten. Hvis parameteren er null vil vi gå mot testsystem for Brønnøysunds tjenester
     */
    public KontaktinformasjonClient(String endpointURL) {
        if (endpointURL != null) {
            this.endpointURL = endpointURL + "?matrikkelenhetid=";
        } else {
            this.endpointURL = "https://data.ppe.brreg.no:443/enhetsregisteret/api/matrikkelenhet?matrikkelenhetid=";
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

        String response = null;
        HttpURLConnection conn = null;
        try {
            //Setter opp tilkobling til enhetsregisteret
            URL url = new URL(endpointURL + matrikkelenhetId);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            conn.setReadTimeout(5000);

            //Gjør kall til tjenesten
            try (InputStream is = conn.getInputStream()) {
                response = CharStreams.toString(new InputStreamReader(is, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            String error = null;
            if (conn != null) {
                try (InputStream errorStream = conn.getErrorStream()) {
                    if(errorStream != null) {
                        error = CharStreams.toString(new InputStreamReader(errorStream, StandardCharsets.UTF_8));
                    }
                } catch (IOException ioException) {
                    //Feil ved feil! Vi ønsker da å kaste opprinnelig feil og ikke denne, så kastet ikke denne videre.
                    logger.error("Feil oppstod ved uthenting av errorstream etter å ha fått en ioexception. ", ioException);
                }
            }
            if (conn == null || error != null) {
                throw new RuntimeException("Feil ved oppkobling til " + endpointURL + ", " + error, e);
            }
        }

        //Henter så ut organisasjonsnummer fra responsen. Er da interessert i alle key-value par som inneholder "orgnr"
        List<String> orgnr = new ArrayList<>();
        if (response != null) {
            String[] split = response.split(",");
            for (String s : split) {
                if (s.contains("orgnr")) {
                    String[] split1 = s.split(":");
                    String nr = split1[1];
                    nr = nr.replaceAll("\"", "");
                    orgnr.add(nr);
                }
            }
        }
        return ImmutableList.sortedCopyOf(orgnr);
    }
}
