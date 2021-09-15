package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.ValidationException;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LandmalerregisterUtilTest {

    String requestUrl = "http://www.dummy.com/dummy";

    @Test
    public void testIngenParametre() {
        assertThatThrownBy(() -> LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, null, null))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("tomme parametre");
    }

    @Test
    public void testBuildUrlParameters() {
        String urlParams1 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, "123456", null, null);
        assertThat(urlParams1).contains("?landmaalernummer=123456");

        String urlParams2 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, "Test", null);
        assertThat(urlParams2).contains("?navn=Test");

        String urlParams3 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, "Test", "Testesen");
        assertThat(urlParams3).contains("?navn=Test+Testesen");

        String urlParams4 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, "123456", "Test", null);
        assertThat(urlParams4).contains("?landmaalernummer=123456&navn=Test");

        String urlParams5 = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, "123456", "Test", "Testesen");
        assertThat(urlParams5).contains("?landmaalernummer=123456&navn=Test+Testesen");
    }

    @Test
    public void testAtDobbeltnavnFormateresRiktig() {
        String urlParameters = LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, "Land Land", "Landeren Landersen");
        assertThat(urlParameters).contains("Land+Land").contains("Landeren+Landersen").doesNotContain(" ");
    }

    @Test
    public void testAtUgyldigUrlGirFeil() {
        assertThatThrownBy(() -> LandmalerregisterUtil.validateAndBuildUrlParameters("requestUrl", null, null, "\\"))
            .isInstanceOf(ImplementationException.class)
            .hasMessageContaining("Request-url til Landmålerregisteret er ikke gyldig");
    }

    @Test
    public void testSpesialtegn() {
        assertThatCode(() -> LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, "Ærlend", "Måler")).doesNotThrowAnyException();
        assertThatCode(() -> LandmalerregisterUtil.validateAndBuildUrlParameters(requestUrl, null, "Knût", "Lämm")).doesNotThrowAnyException();
    }

}
