package no.statkart.wsclient.landmalerregister;

import no.statkart.wsclient.IntegrationTestProperties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LandmalerregisterIntegrationTest {

    LandmalerregisterServiceWS landmalerregisterServiceWS;

    @BeforeTest
    public void setUp() {
        final IntegrationTestProperties config = new IntegrationTestProperties();
        String landmalerregisterUrl = config.getLandmalerregisterUrl();

        if (landmalerregisterServiceWS == null) {
            landmalerregisterServiceWS = new DefaultLandmalerregisterServiceWS(landmalerregisterUrl, null, null);
        }
    }

    @AfterTest
    public void tearDown() {
        landmalerregisterServiceWS = null;
    }

    @Test
    public void testHentLandmalerMedLandmalernummer() {
        assertThatThrownBy(() -> landmalerregisterServiceWS.findLandmalerWS("000001", null, null))
            .isInstanceOf(LandmalerregisterSokException.class)
            .hasMessageContaining("Feil brukernavn/passord i kall til Landmalerregisteret.");
    }
}
