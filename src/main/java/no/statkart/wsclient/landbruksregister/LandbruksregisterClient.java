package no.statkart.wsclient.landbruksregister;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.UriBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class LandbruksregisterClient {
    public static final Duration READ_TIMEOUT = Duration.ofSeconds(5);

    private final Logger logger = LoggerFactory.getLogger(LandbruksregisterClient.class);
    private final String endpointURL;
    private final Supplier<String> tokenProvider;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public LandbruksregisterClient(
        String endpointURL,
        Supplier<String> tokenProvider
    ) {
        this.endpointURL = endpointURL;
        this.tokenProvider = tokenProvider;
    }

    public EiendomDTO getEiendom(
        String kommunenr,
        int gaardsnr,
        int bruksnr,
        int festenr
    ) {
        try {
            var uri = UriBuilder.fromUri(this.endpointURL)
                .path("/api/v1/external/landbrukseiendom/matrikkel")
                .queryParam("kommunenr", kommunenr)
                .queryParam("gaardsnr", gaardsnr)
                .queryParam("bruksnr", bruksnr)
                .queryParam("festenr", festenr)
                .build();

            var request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + tokenProvider.get())
                .timeout(READ_TIMEOUT)
                .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            var statuCode = response.statusCode();

            if (statuCode != 200) {
                ErrorDTO error = objectMapper.readValue(response.body(), new TypeReference<>() {
                });

                if (statuCode == 404) {
                    // Velkjent 404 som skyldes at det ikke er en landbrukseiendom,
                    // så vi returnerer bare en tom liste
                    return EiendomDTO.create(
                        kommunenr,
                        gaardsnr,
                        bruksnr,
                        festenr,
                        Collections.emptyList()
                    );
                }

                var errorDTOMsg = objectMapper.writeValueAsString(error);
                var errorMsg = "%d mot LDIR for matrikkelenhet id %s-%d/%d/%d; %s".formatted(response.statusCode(), kommunenr, gaardsnr, bruksnr, festenr, errorDTOMsg);
                logger.error(errorMsg);
                throw new RuntimeException(errorMsg);
            }

            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class EiendomDTO {
        public String kommunenr;
        public Integer gaardsnr;
        public Integer bruksnr;
        public Integer festenr;
        public Boolean aktiv;
        public List<GrunneiendomDTO> tilknyttetGrunneiendoms;

        public static EiendomDTO create(
            String kommunenr,
            int gaardsnr,
            int bruksnr,
            int festnr,
            List<LandbruksregisterClient.GrunneiendomDTO> tilknyttetGrunneiendoms
        ) {
            var eiendom = new EiendomDTO();
            eiendom.kommunenr = kommunenr;
            eiendom.gaardsnr = gaardsnr;
            eiendom.bruksnr = bruksnr;
            eiendom.festenr = festnr;
            eiendom.tilknyttetGrunneiendoms = tilknyttetGrunneiendoms;
            return eiendom;
        }
    }

    public static class GrunneiendomDTO {
        public String kommunenr;
        public Integer gaardsnr;
        public Integer bruksnr;
        public Integer festenr;

        public static GrunneiendomDTO create(
            String kommunenr,
            int gaardsnr,
            int bruksnr,
            int festnr
        ) {
            var eiendom = new GrunneiendomDTO();
            eiendom.kommunenr = kommunenr;
            eiendom.gaardsnr = gaardsnr;
            eiendom.bruksnr = bruksnr;
            eiendom.festenr = festnr;
            return eiendom;
        }
    }

    public static class ErrorDTO {
        public String errorId;
        public String errorCode;
        public String errorTitle;
        public String errorMessage;

        public static ErrorDTO create(
            String errorId,
            String errorCode,
            String errorTitle,
            String errorMessage
        ) {
            var error = new ErrorDTO();
            error.errorId = errorId;
            error.errorCode = errorCode;
            error.errorTitle = errorTitle;
            error.errorMessage = errorMessage;
            return error;
        }
    }
}
