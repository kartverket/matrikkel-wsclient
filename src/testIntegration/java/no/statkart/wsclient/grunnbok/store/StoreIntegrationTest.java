package no.statkart.wsclient.grunnbok.store;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObject;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectId;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.Matrikkelenhet;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.MatrikkelenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.ident.IdentWS;
import no.statkart.wsclient.grunnbokv2.store.DefaultStoreWS;
import no.statkart.wsclient.grunnbokv2.store.StoreWS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreIntegrationTest {
    StoreIntegrationTest.WSHelper ws;

    @Test
    public void getObjects_returnerBobleObjekt() throws Exception {
        final MatrikkelenhetIdent matrikkelenhetIdent = IdentWS.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = ws.grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        //Hent ut bobla til matrikkelenhetId
        List<GrunnbokBubbleObject> objects = ws.getObjects(List.of(matrikkelenhetId));

        assertThat(objects)
            .flatExtracting(Object::getClass)
            .containsExactly(Matrikkelenhet.class);

        Matrikkelenhet matrikkelenhet = (Matrikkelenhet) objects.get(0);

        assertThat(matrikkelenhet).isNotNull();
        assertThat(matrikkelenhet.getGaardsnummer()).isEqualTo(2);
        assertThat(matrikkelenhet.getBruksnummer()).isEqualTo(1);
        assertThat(matrikkelenhet.getFestenummer()).isEqualTo(0);
        assertThat(matrikkelenhet.getSeksjonsnummer()).isEqualTo(0);
    }

    /**
     * Implementasjon til grunnboka returnerer samme objekt kun en gang ved spørring av samme id
     */
    @Test
    public void getObjects_duplikate_ids_ignoreres() throws Exception {
        final MatrikkelenhetIdent matrikkelenhetIdent = IdentWS.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhet1Id = ws.grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);
        final MatrikkelenhetId matrikkelenhet2Id = ws.grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        assertThat(ws.getObjects(List.of(matrikkelenhet1Id, matrikkelenhet2Id)))
            .hasSize(1);
    }


    static class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();

        /**
         * @see StoreWS#getObjects
         */
        public List<GrunnbokBubbleObject> getObjects(List<? extends GrunnbokBubbleObjectId> ids) throws ServiceException {
            StoreWS ws = new DefaultStoreWS(
                config.getGrunnbokMatFnUsername(),
                config.getGrunnbokMatFnPassword(),
                config.getGrunnbokStoreServiceUrl());
            return ws.getObjects(ids, grunnbokHelper.context());
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
