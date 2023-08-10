package no.statkart.wsclient.grunnbok.rettsstiftelse;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObject;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectId;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.Rettsstiftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.RettsstiftelseId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.heftelse.AnnenHeftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.heftelse.HeftelseIRegisterenhetsrett;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.ident.DefaultIdentWS;
import no.statkart.wsclient.grunnbokv2.ident.IdentWS;
import no.statkart.wsclient.grunnbokv2.rettsstiftelse.DefaultRettsstiftelseWS;
import no.statkart.wsclient.grunnbokv2.rettsstiftelse.RettsstiftelseWS;
import no.statkart.wsclient.grunnbokv2.store.DefaultStoreWS;
import no.statkart.wsclient.grunnbokv2.store.StoreWS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RettsstiftelseIntegrationTest {
    RettsstiftelseIntegrationTest.WSHelper ws;

    @Test
    public void findRettsstiftelserForDokument_returnerIdList() throws Exception {
        //Oppgi dokumentnr
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(2019, 517475L, "200");

        //Finn dokumentId
        DokumentId dokumentId = ws.findDokumentIdForIdent(dokumentIdent);
        assertThat(dokumentId).isNotNull();

        //Finn rettsstiftelser fra dokumentId
        List<RettsstiftelseId> rettsstiftelseIds = ws.findRettsstiftelserForDokument(dokumentId);
        assertThat(rettsstiftelseIds).doesNotContainNull().isNotEmpty();
    }


    @Test
    public void findRettsstiftelserForDokument_returnerIdListMedRettsstiftelsesnr() throws Exception {
        //Oppgi dokumentnr med rettsstiftelsesnr
        int rettsstiftelsenr = 3;
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(2019, 517475L, "200");

        //Finn dokumentId
        DokumentId dokumentId = ws.findDokumentIdForIdent(dokumentIdent);
        assertThat(dokumentId).isNotNull();

        List<RettsstiftelseId> rettsstiftelseIds = ws.findRettsstiftelserForDokument(dokumentId);
        assertThat(rettsstiftelseIds).doesNotContainNull().isNotEmpty();

        //Lag et boble-objekt av rettsstiftelseIdList
        List<GrunnbokBubbleObject> grunnbokBubbleObjectList = ws.getObjects(rettsstiftelseIds);

        //Hent ut rettsstiftelse med det oppgitte rettsstiftelsesnr
        HeftelseIRegisterenhetsrett heftelseIRegisterenhetsrett = grunnbokBubbleObjectList.stream()
            .filter(AnnenHeftelse.class::isInstance) ////tillatteRettstypekoder er alle aktuelle subklasser av HeftelseIRegisterenhetsrett.
            .map(HeftelseIRegisterenhetsrett.class::cast)
            .filter((HeftelseIRegisterenhetsrett h) -> h.getRettsstiftelsesnummer() == rettsstiftelsenr)
            .findFirst()
            .orElse(null);
        assertThat(heftelseIRegisterenhetsrett).isNotNull();
        assertThat(heftelseIRegisterenhetsrett).extracting(Rettsstiftelse::getRettsstiftelsesnummer)
            .isEqualTo(rettsstiftelsenr);
    }

    static class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();

        /**
         * @see RettsstiftelseWS#findRettsstiftelserForDokument(DokumentId, GrunnbokContext)
         */
        public List<RettsstiftelseId> findRettsstiftelserForDokument(DokumentId dokumentId) throws ServiceException {
            RettsstiftelseWS ws = new DefaultRettsstiftelseWS(
                config.getGrunnbokMatFnUsername(),
                config.getGrunnbokMatFnPassword(),
                config.getRettsstiftelseServiceUrl());
            return ws.findRettsstiftelserForDokument(dokumentId, grunnbokHelper.context());
        }

        /**
         * @see IdentWS#findDokumentIdsForIdents
         */
        public DokumentId findDokumentIdForIdent(DokumentIdent dokumentIdent) throws ServiceException {
            IdentWS ws = new DefaultIdentWS(config.getGrunnbokMatFnUsername(), config.getGrunnbokMatFnPassword(), config.getIdentServiceServiceUrl());
            return ws.findDokumentIdForIdent(dokumentIdent, grunnbokHelper.context());
        }

        /**
         * @see StoreWS#getObjects
         */
        public List<GrunnbokBubbleObject> getObjects(List<? extends GrunnbokBubbleObjectId> ids) throws ServiceException {
            StoreWS ws = new DefaultStoreWS(config.getGrunnbokMatFnUsername(), config.getGrunnbokMatFnPassword(), config.getGrunnbokStoreServiceUrl());
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
