package no.statkart.wsclient.grunnbokv2.innsending;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.DokumentBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.ForsendelseBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.UsignertMeldingBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.testdatafactory.ForsendelseFactory;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXParseException;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNotNull;

/**
 * Test av {@link DefaultInnsendingServiceWS} som mapper
 */
@Test
public class DefaultInnsendingServiceWSIT {

    private static final String ADDRESS_TO_LOCAL_STUB_SERVICE = "http://localhost:9000/services/InnsendingService";

    private static Endpoint endpoint;
    private static InnsendingServiceWS innsendingService;

    @BeforeTest
    public static void setUp() {
        endpoint = Endpoint.publish(ADDRESS_TO_LOCAL_STUB_SERVICE, new InnsendingServiceStub());
        innsendingService = new DefaultInnsendingServiceWS("grunnbokdevEkstern", "", ADDRESS_TO_LOCAL_STUB_SERVICE, true);
    }

    @AfterTest
    public static void tearDown() {
        endpoint.stop();
    }

    @Test
    public void tinglysMelding() {
        Forsendelsesstatus forsendelsesstatus = innsendingService.sendTilTinglysing(ForsendelseFactory.defaultForsendelse().build());
        assertNotNull(forsendelsesstatus);
    }

    @Test
    public void tinglysFradelingForretning() {
        Forsendelse forsendelse = ForsendelseFactory.fradelingForsendelse();
        Forsendelsesstatus forsendelsesstatus = innsendingService.sendTilTinglysing(forsendelse);
        assertNotNull(forsendelsesstatus);
    }

    @Test
    public void invalidForsendelse() {
        final Dokument dokumentWithoutDokumentReferanse = DokumentBuilder.aDokument().withDokumentreferanse(null).build();
        final ForsendelseBuilder invalidForsendelse = ForsendelseFactory.defaultForsendelse()
            .but()
            .withUsignertMelding(UsignertMeldingBuilder.anUsignertMelding()
                .withDokumenter(List.of(dokumentWithoutDokumentReferanse))
                .build());

        Assertions.assertThatThrownBy(() -> innsendingService.sendTilTinglysing(invalidForsendelse.build())).isInstanceOf(WebServiceException.class)
            .hasCauseInstanceOf(SAXParseException.class);
    }

    @Test
    public void hentStatusPakkerUtGrunnboksutskrift() {
        final String innsendingId = "2";
        final Forsendelsesstatus forsendelsesstatus = innsendingService.hentStatus(innsendingId);

        assertThat(forsendelsesstatus.getBehandlingsutfall()).isEqualTo("TINGLYST");
        assertThat(forsendelsesstatus.getTinglysingsinformasjon().getSignerteGrunnboksutskrifter()).isEmpty();

        assertThat(forsendelsesstatus.getTinglysingsinformasjon().getGrunnboksutskrifter()).isNotEmpty();
        for (UsignertGrunnboksutskrift usignertGrunnboksutskrift : forsendelsesstatus.getTinglysingsinformasjon().getGrunnboksutskrifter()) {
            assertThat(usignertGrunnboksutskrift.getUtskrift()).isNotNull();
            assertThat(usignertGrunnboksutskrift.getUtskrift().getUsignertDokument()).isNotNull();
        }
    }
}
