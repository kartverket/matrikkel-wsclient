package no.statkart.wsclient.landbruksregister;

import com.fasterxml.jackson.databind.ObjectMapper;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import no.statkart.wsclient.landbruksregister.LandbruksregisterClient.EiendomDTO;
import no.statkart.wsclient.landbruksregister.LandbruksregisterClient.GrunneiendomDTO;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class LandbruksregisterClientTest {
    @Test
    public void testKallMotLDIR() throws Exception {
        try (var mockserver = new MockWebServer()) {
            mockserver.start();
            var klient = new LandbruksregisterClient(mockserver.url("").toString());
            var responseDTO = EiendomDTO.create(
                "1231", 123, 123, 0, List.of(
                    GrunneiendomDTO.create("1231", 123, 124, 0),
                    GrunneiendomDTO.create("1231", 123, 125, 0)
                )
            );
            mockserver.enqueue(mockResponse(200, responseDTO));


            var result = klient.getEiendom("1231", 123, 123, 123);
            var request = mockserver.takeRequest();
            assertThat(request.getRequestLine())
                .contains("kommunenr=1231")
                .contains("gaardsnr=123")
                .contains("bruksnr=123")
                .contains("festenr=123");

            assertThat(result.tilknyttetGrunneiendoms).hasSize(2);
        }
    }

    @Test
    public void testkjent404ReturnererTomList() throws Exception {
        try (var mockserver = new MockWebServer()) {
            mockserver.start();
            var klient = new LandbruksregisterClient(mockserver.url("").toString());
            var responseDTO = errorDTO("error.notfound.landbrukseiendom.notfound");
            mockserver.enqueue(mockResponse(404, responseDTO));


            var result = klient.getEiendom("1231", 123, 123, 123);
            var request = mockserver.takeRequest();
            assertThat(request.getRequestLine())
                .contains("kommunenr=1231")
                .contains("gaardsnr=123")
                .contains("bruksnr=123")
                .contains("festenr=123");

            assertThat(result.tilknyttetGrunneiendoms).hasSize(0);
        }
    }

    @Test
    public void testukjentFeilKasterFeil() throws Exception {
        try (var mockserver = new MockWebServer()) {
            mockserver.start();
            var klient = new LandbruksregisterClient(mockserver.url("").toString());
            var responseDTO = errorDTO("error.forbidden");
            mockserver.enqueue(mockResponse(403, responseDTO));


            assertThatThrownBy(() -> {
                klient.getEiendom("1231", 123, 456, 789);
            })
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("403 mot LDIR for matrikkelenhet id 1231-123/456/789")
                .hasMessageContaining("error.forbidden");

        }
    }

    private static LandbruksregisterClient.ErrorDTO errorDTO(String code) {
        return LandbruksregisterClient.ErrorDTO.create("", code, "", "");
    }

    private static MockResponse mockResponse(int statusCode, Object value) throws Exception {
        return new MockResponse.Builder()
            .code(statusCode)
            .body(new ObjectMapper().writeValueAsString(value))
            .build();
    }
}
