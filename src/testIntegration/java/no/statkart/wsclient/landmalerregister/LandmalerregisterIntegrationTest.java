package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ImplementationException;
import no.statkart.wsclient.IntegrationTestProperties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

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
    public void testHentLandmalerMedLandmalernr() {
        Set<LandmalerFraAAL> landmalereWS = landmalerregisterServiceWS.findLandmalerWS(1L, null, null);
        assertThat(landmalereWS).isNotEmpty();
    }

    @Test
    public void testSokLandmalerMedTommeParametre() {
        assertThatThrownBy(() -> landmalerregisterServiceWS.findLandmalerWS(null, null, null))
            .isInstanceOf(ImplementationException.class)
            .hasMessage("Kun tomme parametre");

        assertThatThrownBy(() -> landmalerregisterServiceWS.findLandmalerWS(null, "", "   "))
            .isInstanceOf(ImplementationException.class)
            .hasMessage("Kun tomme parametre");
    }
}
