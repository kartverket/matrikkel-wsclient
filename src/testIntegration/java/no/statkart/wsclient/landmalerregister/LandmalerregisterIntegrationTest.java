package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ValidationException;
import no.statkart.wsclient.IntegrationTestProperties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LandmalerregisterIntegrationTest {

    LandmalerregisterServiceWS landmalerregisterServiceWS;

    @BeforeTest
    public void setUp() {
        final IntegrationTestProperties config = new IntegrationTestProperties();
        String landmalerregisterUrl = config.getLandmalerregisterUrl();
        if (landmalerregisterServiceWS == null) {
            landmalerregisterServiceWS = new DefaultLandmalerregisterServiceWS(landmalerregisterUrl);
        }
    }

    @AfterTest
    public void tearDown() { landmalerregisterServiceWS = null; }

    @Test
    public void testHentLandmalerFraAAL() {
        assertThatCode(() -> landmalerregisterServiceWS.findLandmalerWS(null, "Landmåler", null)).doesNotThrowAnyException();
    }

    @Test
    public void testSokLandmalerMedTommeParametre() {
        assertThatThrownBy(() -> landmalerregisterServiceWS.findLandmalerWS(null, null, null))
            .isInstanceOf(ValidationException.class)
            .hasMessage("Søk i Landmålerregister inneholder bare tomme felter.");

        assertThatThrownBy(() -> landmalerregisterServiceWS.findLandmalerWS(" ", "", "   "))
            .isInstanceOf(ValidationException.class)
            .hasMessage("Søk i Landmålerregister inneholder bare tomme felter.");
    }
}
