package no.statkart.wsclient.landmalerregister;

import no.statkart.wsclient.IntegrationTestProperties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void testHentLandmalerMedLandmalernummer() {
        Set<LandmalerFraAAL> landmalereWS = landmalerregisterServiceWS.findLandmalerWS("1", null, null);
        assertThat(landmalereWS).isNotEmpty();
    }
}
