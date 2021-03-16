package no.statkart.wsclient.landmalerregister;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LandmalerregisterUtilTest {

    @Test
    public void testvalidateAndBuildUrlParameters() {
        String landmalernummer = "123456";
        String fornavn = "Test";
        String etternavn = "Testesen";

        String urlParams1 = LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernummer, null, null);
        assertThat(urlParams1).contains("?landmaalernummer="+landmalernummer);

        String urlParams2 = LandmalerregisterUtil.validateAndBuildUrlParameters(null, fornavn, null);
        assertThat(urlParams2).contains("?navn="+fornavn);

        String urlParams3 = LandmalerregisterUtil.validateAndBuildUrlParameters(null, fornavn, etternavn);
        assertThat(urlParams3).contains("?navn="+fornavn+"%20"+etternavn);

        String urlParams4 = LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernummer, fornavn, null);
        assertThat(urlParams4).contains("?landmaalernummer="+landmalernummer+"&navn="+fornavn);

        String urlParams5 = LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernummer, fornavn, etternavn);
        assertThat(urlParams5).contains("?landmaalernummer="+landmalernummer+"&navn="+fornavn+"%20"+etternavn);
    }
}
