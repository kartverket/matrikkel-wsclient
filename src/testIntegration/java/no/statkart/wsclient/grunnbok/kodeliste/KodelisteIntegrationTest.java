package no.statkart.wsclient.grunnbok.kodeliste;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.Timestamp;
import no.kartverket.grunnbok.wsapi.v2.domain.kodeliste.KodelisteTransfer;
import no.kartverket.grunnbok.wsapi.v2.domain.register.koder.RettsstiftelsestypeKode;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.kodeliste.DefaultKodelisteWS;
import no.statkart.wsclient.grunnbokv2.kodeliste.KodelisteWS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KodelisteIntegrationTest {

    WSHelper ws;

    @Test
    public void getKodelister_returnererTransfer() throws Exception {
        KodelisteTransfer kodelister = ws.getKodelister(ws.context.getSnapshotVersion());

        assertThat(kodelister).isNotNull();
        assertThat(kodelister.getBubbleObjects().getItem())
            .flatExtracting(Object::getClass)
            .contains(RettsstiftelsestypeKode.class);
    }

    @Test
    public void getKodelister_harKoderMedFelterUtfyllt() throws Exception {
        KodelisteTransfer kodelister = ws.getKodelister(ws.context.getSnapshotVersion());

        RettsstiftelsestypeKode rettsstiftelsestype = kodelister.getBubbleObjects().getItem().stream()
            .filter(RettsstiftelsestypeKode.class::isInstance)
            .map(RettsstiftelsestypeKode.class::cast)
            .filter(kode -> "OB_REF".equals(kode.getKodeverdi()))
            .findFirst().orElse(null);

        assertThat(rettsstiftelsestype).describedAs("Forventer å finne OB_REF").isNotNull();
        assertThat(rettsstiftelsestype.getNavn().getEntry().get(0).getValue()).isNotBlank();
        assertThat(rettsstiftelsestype.getRettstypeId().getValue()).isNotBlank().isEqualTo("25");
        assertThat(rettsstiftelsestype.getKodeverdi()).isNotBlank().isEqualTo("OB_REF");
    }

    class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();
        String grunnbokUser = config.getGrunnbokUser();
        String grunnbokPassword = config.getGrunnbokPassword();
        GrunnbokContext context = grunnbokHelper.context();

        /**
         * @see KodelisteWS#getKodelister
         */
        public KodelisteTransfer getKodelister(Timestamp timestamp) throws ServiceException {
            KodelisteWS ws = new DefaultKodelisteWS(grunnbokUser, grunnbokPassword, config.getGrunnbokKodelisteServiceUrl());
            return ws.getKodelister(timestamp, context);
        }

    }

    @BeforeTest
    public void setUp() {
        ws = new WSHelper();
    }

    @AfterTest
    public void teardown() {
        ws = null;
    }
}
