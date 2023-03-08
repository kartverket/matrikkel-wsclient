package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ValidationException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LandmalerregisterServiceMockTest {

    LandmalerregisterServiceWS landmalerregisterServiceWS;

    @BeforeTest
    public void setUp() {
        if (landmalerregisterServiceWS == null) {
            landmalerregisterServiceWS = new MockLandmalerregisterServiceWS();
        }
    }

    @Test
    public void testAntallSifferLandmalernummer() {
        Set<LandmalerFraAAL> landmalere = landmalerregisterServiceWS.findLandmalerWS("1", null, null);
        assertThat(
            landmalere.stream().allMatch(l -> l.getLandmalernummer().length() == 6)
        ).isTrue();
    }

    @Test
    public void testService1() {
        Set<LandmalerFraAAL> landmalere = landmalerregisterServiceWS.findLandmalerWS("1", null, null);
        assertThat(landmalere).hasSize(6);
    }

    @Test
    public void testService2() {
        Set<LandmalerFraAAL> landmalere = landmalerregisterServiceWS.findLandmalerWS(null, "Kari", null);
        assertThat(landmalere).hasSize(1);
    }

    @Test
    public void testService3() {
        Set<LandmalerFraAAL> landmalere = landmalerregisterServiceWS.findLandmalerWS(null, null, "Nordmann");
        assertThat(landmalere).hasSize(2);
    }

    @Test
    public void testService4() {
        assertThatThrownBy(() -> landmalerregisterServiceWS.findLandmalerWS(null, null, null))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Kun tomme parametre");
    }
}