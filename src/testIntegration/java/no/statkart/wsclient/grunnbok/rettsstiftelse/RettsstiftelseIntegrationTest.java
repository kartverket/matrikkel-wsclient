package no.statkart.wsclient.grunnbok.rettsstiftelse;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokBubbleObjectList;
import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdentList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.Rettsstiftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.RettsstiftelseId;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.RettsstiftelseIdList;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.heftelse.AnnenHeftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.register.rettsstiftelse.heftelse.HeftelseIRegisterenhetsrett;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.DokumentIdentTilDokumentIdMap;
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

import static org.assertj.core.api.Assertions.assertThat;

public class RettsstiftelseIntegrationTest {
    RettsstiftelseIntegrationTest.WSHelper ws;

    @Test
    public void findRettsstiftelserForDokument_returnerIdList() throws Exception {
        DokumentIdentList dokumentIdentList = new DokumentIdentList();

        //Oppgi dokumentnr
        DokumentIdent dokumentIdent = new DokumentIdent();
        dokumentIdent.setDokumentaar(2019);
        dokumentIdent.setDokumentnummer(517475);
        dokumentIdent.setEmbetenummer("200");
        dokumentIdentList.getItem().add(dokumentIdent);

        //Finn dokumentId
        DokumentIdentTilDokumentIdMap dokumentIdsForIdents = ws.findDokumentIdsForIdents(dokumentIdentList);
        for (DokumentIdentTilDokumentIdMap.Entry dokumentIdsForIdent : dokumentIdsForIdents.getEntry()) {
            assertThat(dokumentIdsForIdent.getValue())
                .isNotNull();
        }

        //Finn rettsstiftelser fra dokumentId
        DokumentId dokumentId = dokumentIdsForIdents.getEntry().get(0).getValue();
        RettsstiftelseIdList rettsstiftelserForDokument = ws.findRettsstiftelserForDokument(dokumentId);
        assertThat(rettsstiftelserForDokument).isNotNull();
        assertThat(rettsstiftelserForDokument.getItem())
            .flatExtracting(Object::getClass)
            .contains(RettsstiftelseId.class);
    }


    @Test
    public void findRettsstiftelserForDokument_returnerIdListMedRettsstiftelsesnr() throws Exception {
        DokumentIdentList dokumentIdentList = new DokumentIdentList();

        //Oppgi dokumentnr med rettsstiftelsesnr
        int rettsstiftelsenr = 3;
        DokumentIdent dokumentIdent = new DokumentIdent();
        dokumentIdent.setDokumentaar(2019);
        dokumentIdent.setDokumentnummer(517475);
        dokumentIdent.setEmbetenummer("200");
        dokumentIdentList.getItem().add(dokumentIdent);

        //Finn dokumentId
        DokumentIdentTilDokumentIdMap dokumentIdsForIdents = ws.findDokumentIdsForIdents(dokumentIdentList);

        //Finn rettsstiftelser fra dokumentId
        DokumentId dokumentId = dokumentIdsForIdents.getEntry().get(0).getValue();
        assertThat(dokumentId).isNotNull();

        RettsstiftelseIdList rettsstiftelseIdList = ws.findRettsstiftelserForDokument(dokumentId);
        assertThat(rettsstiftelseIdList).isNotNull();
        assertThat(rettsstiftelseIdList.getItem())
            .flatExtracting(Object::getClass)
            .contains(RettsstiftelseId.class);

        //Lag et boble-objekt av rettsstiftelseIdList
        GrunnbokBubbleObjectIdList grunnbokBubbleObjectIdRettsstiftelse = new GrunnbokBubbleObjectIdList();
        grunnbokBubbleObjectIdRettsstiftelse.getItem().addAll(rettsstiftelseIdList.getItem());
        GrunnbokBubbleObjectList grunnbokBubbleObjectList = ws.getObjects(grunnbokBubbleObjectIdRettsstiftelse);

        //Hent ut rettsstiftelse med det oppgitte rettsstiftelsesnr
        HeftelseIRegisterenhetsrett heftelseIRegisterenhetsrett = grunnbokBubbleObjectList.getItem().stream()
            .filter(AnnenHeftelse.class::isInstance) ////tillatteRettstypekoder er alle aktuelle subklasser av HeftelseIRegisterenhetsrett.
            .map(HeftelseIRegisterenhetsrett.class::cast)
            .filter((HeftelseIRegisterenhetsrett h) -> h.getRettsstiftelsesnummer() == rettsstiftelsenr)
            .findFirst()
            .orElse(null);
        assertThat(heftelseIRegisterenhetsrett).isNotNull();
        assertThat(heftelseIRegisterenhetsrett).extracting(Rettsstiftelse::getRettsstiftelsesnummer)
            .isEqualTo(rettsstiftelsenr);
    }

    class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();
        String grunnbokUser = config.getGrunnbokUser();
        String grunnbokPassword = config.getGrunnbokPassword();
        GrunnbokContext context = grunnbokHelper.context();

        /**
         * @see RettsstiftelseWS#findRettsstiftelserForDokument(DokumentId, GrunnbokContext)
         */
        public RettsstiftelseIdList findRettsstiftelserForDokument(DokumentId dokumentId) throws ServiceException {
            RettsstiftelseWS ws = new DefaultRettsstiftelseWS(grunnbokUser, grunnbokPassword, config.getRettsstiftelseServiceUrl());
            return ws.findRettsstiftelserForDokument(dokumentId, context);
        }

        /**
         * @see IdentWS#findDokumentIdsForIdents(DokumentIdentList, GrunnbokContext)
         */
        public DokumentIdentTilDokumentIdMap findDokumentIdsForIdents(DokumentIdentList dokumentIdentList) throws ServiceException {
            IdentWS ws = new DefaultIdentWS(grunnbokUser, grunnbokPassword, config.getIdentServiceServiceUrl());
            return ws.findDokumentIdsForIdents(dokumentIdentList, context);
        }

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
        ws = new RettsstiftelseIntegrationTest.WSHelper();
    }

    @AfterTest
    public void teardown() {
        ws = null;
    }
}
