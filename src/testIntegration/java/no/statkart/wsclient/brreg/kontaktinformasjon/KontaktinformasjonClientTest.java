package no.statkart.wsclient.brreg.kontaktinformasjon;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class KontaktinformasjonClientTest {

    /**
     * Test som kjører kall mot BRREG med kjent verdi og forventer kjente svar tilbake.
     */
    @Test
    public void testKallMotBRREG() {
        KontaktinformasjonClient klient = new KontaktinformasjonClient(null);

        assertThat(klient.findKontaktinformasjonForMatrikkelenhetId(31874161L))
            .containsOnly("917256276", "993265845", "994908782", "996761495");
    }
}
