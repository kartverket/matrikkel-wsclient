package no.statkart.wsclient.grunnbok.innsending;

import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbokv2.innsending.DefaultInnsendingServiceWS;
import no.statkart.wsclient.grunnbokv2.innsending.InnsendingServiceWS;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnsendingIntegrationTest {
    private InnsendingServiceWS innsendingServiceWS;

    @Test
    public void testHentStatus() {
        Forsendelsesstatus forsendelsesstatus = innsendingServiceWS.hentStatus("841822389");
        assertThat(forsendelsesstatus).isNotNull();
        assertThat(forsendelsesstatus.getTinglysingsinformasjon()).isNotNull();
        assertThat(forsendelsesstatus.getTinglysingsinformasjon().getSignerteGrunnboksutskrifter())
            .isNotNull().hasSize(2);

    }

    @BeforeTest
    public void setUp() {
        final IntegrationTestProperties config = new IntegrationTestProperties();
        final String grunnbokUser = config.getGrunnbokTinglysingUsername();
        final String grunnbokPassword = config.getGrunnbokTinglysingPassword();
        if (innsendingServiceWS == null) {
            try {
                innsendingServiceWS = new DefaultInnsendingServiceWS(grunnbokUser, grunnbokPassword,
                    config.getGrunnbokInnsendingServiceUrl());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @AfterTest
    public void teardown() {
        innsendingServiceWS = null;
    }

}
