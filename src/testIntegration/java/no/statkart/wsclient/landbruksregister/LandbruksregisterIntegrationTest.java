package no.statkart.wsclient.landbruksregister;

import no.slf.lib.server.ws.WsEiendomDTO;
import no.statkart.wsclient.IntegrationTestProperties;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Integrasjonstest
 */
public class LandbruksregisterIntegrationTest {

    LandbruksregisterWS landbruksregister;


    @Test
    public void getEiendomKanKalles() {
        final WsEiendomDTO eiendomDTO = landbruksregister.getEiendom("0605", "262", "141", "", 0, 0, 0, 0, 1);
        Assertions.assertThat(eiendomDTO).isNotNull();
    }

    @Test
    public void getEiendomForIkkeLandbrukseiendom() {
        final int landbrukseiendom = 0;
        final int eiere = 0;
        final int bedrifter = 0;
        final int skonti = 0;
        final int grunneiendommer = 1;
        final WsEiendomDTO eiendomDTO = landbruksregister.getEiendom("0605", "262", "141", "", landbrukseiendom, eiere, bedrifter, skonti, grunneiendommer);
        Assertions.assertThat(eiendomDTO)
            .usingRecursiveComparison()
            .describedAs("Ikke landbrukseiendom returnerer ikke mye data")
            .isEqualTo(new WsEiendomDTO());
    }

    @Test
    public void getEiendomForLandbrukseiendom() {
        final int landbrukseiendom = 0;
        final int eiere = 0;
        final int bedrifter = 0;
        final int skonti = 0;
        final int grunneiendommer = 1;
        final WsEiendomDTO eiendomDTO = landbruksregister.getEiendom("0605", "78", "1", "", landbrukseiendom, eiere, bedrifter, skonti, grunneiendommer);
        Assertions.assertThat(eiendomDTO.getGrunneiendoms()).isNotEmpty();
    }


    @BeforeTest
    public void setUp() {
        final IntegrationTestProperties config = new IntegrationTestProperties();
        final String uername = config.getLandbruksregisterUsername();
        final String password = config.getLandbruksregisterPassword();
        if (landbruksregister == null) {
            try {
                landbruksregister = new DefaultLandbruksregisterWS(uername, password, config.getLandbruksregisterUrl());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @AfterTest
    public void teardown() {
        landbruksregister = null;
    }

}
