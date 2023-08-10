package no.statkart.wsclient.grunnbok.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdentList;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v2.service.servicetyper.DokumentIdentTilDokumentIdMap;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.ident.DefaultIdentWS;
import no.statkart.wsclient.grunnbokv2.ident.IdentWS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class IdentIntegrationTest {
    IdentIntegrationTest.WSHelper ws;

    @Test
    public void findDokumentIdsForIdents_medGyldigDokumentIdent() throws Exception {
        DokumentIdentList dokumentIdentList = new DokumentIdentList();

        //Oppgi gyldig dokumentnr
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(2019, 517475L, "200");
        dokumentIdentList.getItem().add(dokumentIdent);

        //Finn dokumentId
        DokumentIdentTilDokumentIdMap dokumentIdsForIdents = ws.findDokumentIdsForIdents(dokumentIdentList);
        assertThat(dokumentIdsForIdents.getEntry())
            .extracting(DokumentIdentTilDokumentIdMap.Entry::getKey)
            .extracting(DokumentIdent::getDokumentaar, DokumentIdent::getDokumentnummer, DokumentIdent::getEmbetenummer)
            .containsExactly(tuple(2019, 517475L, "200"));

        for (DokumentIdentTilDokumentIdMap.Entry dokumentIdsForIdent : dokumentIdsForIdents.getEntry()) {
            assertThat(dokumentIdsForIdent.getValue())
                .isNotNull();
        }
    }

    @Test
    public void findDokumentIdsForIdents_medUgyldigDokumentIdent() throws Exception {
        DokumentIdentList dokumentIdentList = new DokumentIdentList();

        //Oppgi ugyldig dokumentnr
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(20194, 5174751L, "2001");
        dokumentIdentList.getItem().add(dokumentIdent);

        //Finn dokumentId. verdien skal være null
        DokumentIdentTilDokumentIdMap dokumentIdsForIdents = ws.findDokumentIdsForIdents(dokumentIdentList);
        for (DokumentIdentTilDokumentIdMap.Entry dokumentIdsForIdent : dokumentIdsForIdents.getEntry()) {
            assertThat(dokumentIdsForIdent.getValue())
                .isNull();
        }
    }

    /**
     * Implementasjon til grunnboka returnerer samme objekt kun en gang ved spørring av samme id
     */
    @Test
    public void findDokumentIdsForIdents_duplikate_ids_ignoreres() throws Exception {
        DokumentIdentList dokumentIdentList = new DokumentIdentList();

        dokumentIdentList.getItem().addAll(List.of(
            IdentWS.dokumentIdent(2019, 517475L, "200"),
            IdentWS.dokumentIdent(2019, 517475L, "200")));

        DokumentIdentTilDokumentIdMap dokumentIdsForIdents = ws.findDokumentIdsForIdents(dokumentIdentList);
        assertThat(dokumentIdsForIdents.getEntry())
            .extracting(DokumentIdentTilDokumentIdMap.Entry::getKey)
            .extracting(DokumentIdent::getDokumentaar, DokumentIdent::getDokumentnummer, DokumentIdent::getEmbetenummer)
            .containsExactly(tuple(2019, 517475L, "200"));
    }


    static class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();

        /**
         * @see IdentWS#findDokumentIdsForIdents(DokumentIdentList, GrunnbokContext)
         */
        public DokumentIdentTilDokumentIdMap findDokumentIdsForIdents(DokumentIdentList dokumentIdentList) throws ServiceException {
            IdentWS ws = new DefaultIdentWS(
                config.getGrunnbokMatFnUsername(),
                config.getGrunnbokMatFnPassword(),
                config.getIdentServiceServiceUrl());
            return ws.findDokumentIdsForIdents(dokumentIdentList, grunnbokHelper.context());
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
