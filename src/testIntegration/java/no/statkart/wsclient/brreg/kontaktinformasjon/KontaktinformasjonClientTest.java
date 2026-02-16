package no.statkart.wsclient.brreg.kontaktinformasjon;

import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class KontaktinformasjonClientTest {
    /**
     * Test som kjører kall mot BRREG med kjent verdi og forventer kjente svar tilbake.
     */
    @Test
    public void testKallMotBRREG() throws Exception {
        try (var mockHttpServer = new MockWebServer()) {
            mockHttpServer.start();
            KontaktinformasjonClient klient = new KontaktinformasjonClient(mockHttpServer.url("").toString());
            mockHttpServer.enqueue(
                new MockResponse.Builder()
                    .code(200)
                    .body("[{\"orgnr\":917256276, \"komnr\":123}, {\"orgnr\":993265845}, {\"orgnr\":994908782}, {\"orgnr\":996761495}]")
                    .build()
            );

            List<String> result = klient.findKontaktinformasjonForMatrikkelenhetId(31874161L);

            var request = mockHttpServer.takeRequest();
            assertThat(request.getRequestLine()).contains("matrikkelenhetid=31874161");
            assertThat(result)
                .containsOnly("917256276", "993265845", "994908782", "996761495");
        }

    }

    @Test
    public void testKallMotBRREGGirTomListeVed404() throws Exception {
        try (var mockHttpServer = new MockWebServer()) {
            mockHttpServer.start();
            mockHttpServer.enqueue(
                new MockResponse.Builder()
                    .code(404)
                    .build()
            );
            KontaktinformasjonClient klient = new KontaktinformasjonClient(mockHttpServer.url("").toString());

            assertThat(klient.findKontaktinformasjonForMatrikkelenhetId(123L)).isEmpty();
        }
    }
}
