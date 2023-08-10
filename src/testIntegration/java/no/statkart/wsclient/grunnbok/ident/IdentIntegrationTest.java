package no.statkart.wsclient.grunnbok.ident;

import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.DokumentIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.register.dokument.DokumentId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.ident.DefaultIdentWS;
import no.statkart.wsclient.grunnbokv2.ident.IdentWS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class IdentIntegrationTest {
    IdentIntegrationTest.WSHelper ws;

    @Test
    public void findDokumentIdForIdent_medGyldigDokumentIdent() throws Exception {
        //Oppgi gyldig dokumentnr
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(2019, 517475L, "200");

        //Finn dokumentId
        assertThat(ws.findDokumentIdForIdent(dokumentIdent))
            .extracting(DokumentId::getValue)
            .isEqualTo("601040072");
    }

    @Test
    public void findDokumentIdForIdent_medUgyldigDokumentIdent() throws Exception {
        //Oppgi ugyldig dokumentnr
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(20194, 5174751L, "2001");

        //Finn dokumentId. verdien skal være null
        assertThat(ws.findDokumentIdForIdent(dokumentIdent))
                .isNull();
    }

    @Test
    public void findDokumentIdsForIdents_medGyldigDokumentIdent() throws Exception {
        //Oppgi gyldig dokumentnr
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(2019, 517475L, "200");

        //Finn dokumentId
        var dokumentIdsForIdents = ws.findDokumentIdsForIdents(List.of(dokumentIdent));
        assertThat(dokumentIdsForIdents.keySet())
            .extracting(DokumentIdent::getDokumentaar, DokumentIdent::getDokumentnummer, DokumentIdent::getEmbetenummer)
            .containsExactly(tuple(2019, 517475L, "200"));

        assertThat(dokumentIdsForIdents.values())
                .extracting(DokumentId::getValue)
                .containsExactly("601040072");
    }

    @Test
    public void findDokumentIdsForIdents_medUgyldigDokumentIdent() throws Exception {
        //Oppgi ugyldig dokumentnr
        DokumentIdent dokumentIdent = IdentWS.dokumentIdent(20194, 5174751L, "2001");

        //Finn dokumentId. verdien skal være null
        var dokumentIdsForIdents = ws.findDokumentIdsForIdents(List.of(dokumentIdent));
        assertThat(dokumentIdsForIdents.values()).containsNull();
    }

    /**
     * Implementasjon til grunnboka returnerer samme objekt kun en gang ved spørring av samme id
     */
    @Test
    public void findDokumentIdsForIdents_duplikate_ids_ignoreres() throws Exception {
        var dokumentIdentList = List.of(
            IdentWS.dokumentIdent(2019, 517475L, "200"),
            IdentWS.dokumentIdent(2019, 517475L, "200"));

        var dokumentIdsForIdents = ws.findDokumentIdsForIdents(dokumentIdentList);
        assertThat(dokumentIdsForIdents.keySet())
            .extracting(DokumentIdent::getDokumentaar, DokumentIdent::getDokumentnummer, DokumentIdent::getEmbetenummer)
            .containsExactly(tuple(2019, 517475L, "200"));
    }


    static class WSHelper {
        final GrunnbokHelper grunnbokHelper = new GrunnbokHelper();
        private final IntegrationTestProperties config = new IntegrationTestProperties();

        /**
         * @see IdentWS#findDokumentIdsForIdents
         */
        public Map<DokumentIdent, DokumentId> findDokumentIdsForIdents(List<DokumentIdent> dokumentIdentList) throws ServiceException {
            IdentWS ws = new DefaultIdentWS(
                config.getGrunnbokMatFnUsername(),
                config.getGrunnbokMatFnPassword(),
                config.getIdentServiceServiceUrl());
            return ws.findDokumentIdsForIdents(dokumentIdentList, grunnbokHelper.context());
        }

        /**
         * @see IdentWS#findDokumentIdForIdent
         */
        public DokumentId findDokumentIdForIdent(DokumentIdent dokumentIdent) throws ServiceException {
            IdentWS ws = new DefaultIdentWS(
                config.getGrunnbokMatFnUsername(),
                config.getGrunnbokMatFnPassword(),
                config.getIdentServiceServiceUrl());
            return ws.findDokumentIdForIdent(dokumentIdent, grunnbokHelper.context());
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
