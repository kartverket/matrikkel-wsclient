package no.statkart.wsclient.nibas;

import com.fasterxml.jackson.databind.ObjectMapper;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import no.kartverket.nibas.api.v1.api.V1FylkerApi;
import no.kartverket.nibas.api.v1.invoker.ApiClient;
import no.kartverket.nibas.api.v1.model.AdministrativEnhetNavn;
import no.kartverket.nibas.api.v1.model.FylkeResponse;
import okio.Buffer;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class NibasCharsetTest {
    class MockApiClient extends ApiClient {
        public MockApiClient(String url) {
            super();
            this.setHttpClientBuilder(createDefaultHttpClientBuilder());
            this.setObjectMapper(createDefaultObjectMapper());
            updateBaseUri(url);
        }
    }

    @Test
    public void skalBrukeUTF8() throws Exception {
        assertThat(Charset.defaultCharset()).isNotEqualTo(StandardCharsets.UTF_8);

        try (var mockserver = new MockWebServer()) {
            mockserver.start();
            var client = new V1FylkerApi(
                new MockApiClient(mockserver.url("").toString())
            );
            var mockResponse = new FylkeResponse();
            var administrativenhetnavnItem = new AdministrativEnhetNavn();
            administrativenhetnavnItem.setNavn("Test æøå");
            mockResponse.addAdministrativenhetnavnItem(administrativenhetnavnItem);

            mockserver.enqueue(mockResponse(200, mockResponse));

            var response = client.hentFylke("test", LocalDate.now());
            var navn = response.getAdministrativenhetnavn().get(0).getNavn();

            assertThat(navn).isEqualTo("Test æøå");
        }
    }

    private static MockResponse mockResponse(int statusCode, Object value) throws Exception {
        return new MockResponse.Builder()
            .code(statusCode)
            .body(new Buffer().write(new ObjectMapper().writeValueAsString(value).getBytes(StandardCharsets.UTF_8)))
            .build();
    }
}
