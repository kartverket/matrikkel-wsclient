package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ImplementationException;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LandmalerregisterUtilTest {

    String requestUrl = "http://www.dummy.com/dummy";

    @Test
    public void testvalidateAndBuildUrlParameters() {
        String landmalernummer = "123456";
        String fornavn = "Test";
        String etternavn = "Testesen";

        String urlParams1 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, landmalernummer, null, null);
        assertThat(urlParams1).contains("?landmaalernummer="+landmalernummer);

        String urlParams2 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, fornavn, null);
        assertThat(urlParams2).contains("?navn="+fornavn);

        String urlParams3 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, fornavn, etternavn);
        assertThat(urlParams3).contains("?navn="+fornavn+"%20"+etternavn);

        String urlParams4 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, landmalernummer, fornavn, null);
        assertThat(urlParams4).contains("?landmaalernummer="+landmalernummer+"&navn="+fornavn);

        String urlParams5 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, landmalernummer, fornavn, etternavn);
        assertThat(urlParams5).contains("?landmaalernummer="+landmalernummer+"&navn="+fornavn+"%20"+etternavn);
    }

    @Test
    public void testAtDobbeltnavnFormateresRiktig() {
        String fornavn = "Land Landmåler";
        String etternavn = "Landmåleren Landmålersen";

        String urlParameters = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, fornavn, etternavn);
        assertThat(urlParameters).contains(fornavn.replaceAll("\\s", "%20"));
        assertThat(urlParameters).contains(etternavn.replaceAll("\\s", "%20"));
        assertThat(urlParameters).doesNotContain(" ");
    }

    @Test
    public void testAtSpesielleTegnKasterFeil() {
        assertThatThrownBy(() -> LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, null, "\\"))
            .isInstanceOf(ImplementationException.class)
            .hasMessageContaining("Request-url til Landmålerregisteret er ikke gyldig");
    }
}
