package no.statkart.wsclient.brreg.kontaktinformasjon;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class KontaktinformasjonClientTest {

    /**
     * Test som kjører kall mot BRREG med kjent verdi og forventer kjente svar tilbake.
     */
    @Test
    public void testKallMotBRREG(){

        KontaktinformasjonClient klient = new KontaktinformasjonClient(null);

        List<String> kontaktinformasjonForMatrikkelenhetId = klient.findKontaktinformasjonForMatrikkelenhetId(31874161L);
        Assertions.assertThat(kontaktinformasjonForMatrikkelenhetId).hasSameElementsAs(Lists.newArrayList("917256276","993265845", "994908782", "996761495"));

    }
}
