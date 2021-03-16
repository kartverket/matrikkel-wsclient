package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ImplementationException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LandmalerregisterServiceWSTest {

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
        assertThat(landmalere.size()).isEqualTo(6);
    }

    @Test
    public void testService2() {
        Set<LandmalerFraAAL> landmalere = landmalerregisterServiceWS.findLandmalerWS(null, "Kari", null);
        assertThat(landmalere.size()).isEqualTo(1);
    }

    @Test
    public void testService3() {
        Set<LandmalerFraAAL> landmalere = landmalerregisterServiceWS.findLandmalerWS(null, null, "Nordmann");
        assertThat(landmalere.size()).isEqualTo(2);
    }

    @Test
    public void testService4() {
        assertThatThrownBy(() -> landmalerregisterServiceWS.findLandmalerWS(null, null, null))
            .isInstanceOf(ImplementationException.class)
            .hasMessageContaining("Kun tomme parametre");
    }
}