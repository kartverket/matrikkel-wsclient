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

import static org.assertj.core.api.Assertions.assertThat;

public class IdentIntegrationTest {
    IdentIntegrationTest.WSHelper ws;
    DokumentIdentList dokumentIdentList;

    @Test
    public void findDokumentIdsForIdents_medGyldigDokumentIdent() throws Exception {
        dokumentIdentList = new DokumentIdentList();

        //Oppgi gyldig dokumentnr
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
    }

    @Test
    public void findDokumentIdsForIdents_medUgyldigDokumentIdent() throws Exception {
        dokumentIdentList = new DokumentIdentList();

        //Oppgi ugyldig dokumentnr
        DokumentIdent dokumentIdent = new DokumentIdent();
        dokumentIdent.setDokumentaar(20194);
        dokumentIdent.setDokumentnummer(5174751);
        dokumentIdent.setEmbetenummer("2001");
        dokumentIdentList.getItem().add(dokumentIdent);

        //Finn dokumentId. verdien skal være null
        DokumentIdentTilDokumentIdMap dokumentIdsForIdents = ws.findDokumentIdsForIdents(dokumentIdentList);
        for (DokumentIdentTilDokumentIdMap.Entry dokumentIdsForIdent : dokumentIdsForIdents.getEntry()) {
            assertThat(dokumentIdsForIdent.getValue())
                .isNull();
        }
    }

    class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();
        String grunnbokUser = config.getGrunnbokMatFnUsername();
        String grunnbokPassword = config.getGrunnbokMatFnPassword();
        GrunnbokContext context = grunnbokHelper.context();

        /**
         * @see IdentWS#findDokumentIdsForIdents(DokumentIdentList, GrunnbokContext)
         */
        public DokumentIdentTilDokumentIdMap findDokumentIdsForIdents(DokumentIdentList dokumentIdentList) throws ServiceException {
            IdentWS ws = new DefaultIdentWS(grunnbokUser, grunnbokPassword, config.getIdentServiceServiceUrl());
            return ws.findDokumentIdsForIdents(dokumentIdentList, context);
        }
    }

    @BeforeTest
    public void setUp() {
        ws = new IdentIntegrationTest.WSHelper();
    }

    @AfterTest
    public void teardown() {
        ws = null;
    }
}
