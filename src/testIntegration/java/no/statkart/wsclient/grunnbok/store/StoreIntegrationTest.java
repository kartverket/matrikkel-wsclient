package no.statkart.wsclient.grunnbok.store;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectList;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.Matrikkelenhet;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.MatrikkelenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.store.DefaultStoreWS;
import no.statkart.wsclient.grunnbokv2.store.StoreWS;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreIntegrationTest {
    StoreIntegrationTest.WSHelper ws;

    @Test
    public void getObjects_returnerBobleObjekt() throws Exception {
        GrunnbokBubbleObjectIdList grunnbokBubbleObjectIdList = new GrunnbokBubbleObjectIdList();
        final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = ws.grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        //Hent ut bobla til matrikkelenhetId
        grunnbokBubbleObjectIdList.getItem().add(matrikkelenhetId);
        GrunnbokBubbleObjectList objects = ws.getObjects(grunnbokBubbleObjectIdList);

        assertThat(objects.getItem())
            .isNotNull()
            .flatExtracting(Object::getClass)
            .contains(Matrikkelenhet.class);

        Matrikkelenhet matrikkelenhet = objects.getItem().stream()
            .map(Matrikkelenhet.class::cast)
            .findFirst().orElse(null);

        assertThat(matrikkelenhet).isNotNull();
        Assert.assertEquals(matrikkelenhetIdent.getGaardsnummer(), matrikkelenhet.getGaardsnummer());
        Assert.assertEquals(matrikkelenhetIdent.getBruksnummer(), matrikkelenhet.getBruksnummer());
    }

    class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();
        String grunnbokUser = config.getGrunnbokMatFnUsername();
        String grunnbokPassword = config.getGrunnbokMatFnPassword();
        GrunnbokContext context = grunnbokHelper.context();

        /**
         * @see StoreWS#getObjects(GrunnbokBubbleObjectIdList, GrunnbokContext)
         */
        public GrunnbokBubbleObjectList getObjects(GrunnbokBubbleObjectIdList ids) throws ServiceException {
            StoreWS ws = new DefaultStoreWS(grunnbokUser, grunnbokPassword, config.getGrunnbokStoreServiceUrl());
            return ws.getObjects(ids, context);
        }

    }

    @BeforeTest
    public void setUp() {
        ws = new StoreIntegrationTest.WSHelper();
    }

    @AfterTest
    public void teardown() {
        ws = null;
    }
}
