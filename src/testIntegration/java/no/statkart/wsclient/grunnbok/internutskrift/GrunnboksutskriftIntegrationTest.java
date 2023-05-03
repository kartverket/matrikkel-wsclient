package no.statkart.wsclient.grunnbok.internutskrift;

import no.kartverket.grunnbok.wsapi.v2.domain.grunnboksidenter.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v2.domain.register.registerenhet.MatrikkelenhetId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.skif.util.NullHostnameVerifier;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.internutskrift.DefaultGrunnboksutskriftInternWS;
import no.statkart.wsclient.grunnbokv2.internutskrift.GrunnboksutskriftInternWS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integrajonstest av WS signatur og retur verdier ifra Grunnboksystemet er som forventet [MAT-12778].
 */
public class GrunnboksutskriftIntegrationTest {

    private no.statkart.wsclient.grunnbokv2.internutskrift.GrunnboksutskriftInternWS grunnboksutskriftIntern;
    private GrunnbokHelper grunnbokHelper;


    /**
     * @see GrunnboksutskriftInternWS#grunnboksutskriftInfo
     */
    @Test
    public void grunnboksutskriftInfo() throws Exception {
        final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        final String grunnboksutskriftInfo = grunnboksutskriftIntern.grunnboksutskriftInfo(matrikkelenhetId, grunnbokHelper.context());
        assertThat(grunnboksutskriftInfo).describedAs("grunnboksutskrift som tekst")
            .isNotEmpty()
            .contains("            Data uthentet: ")
            .contains("            Oppdatert per: ")
            .contains("            Kommune: 0301 OSLO")
            .endsWith("            Gnr: 2 Bnr: 1");
    }


    /**
     * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftOverdragelser
     */
    @Test
    public void delAvGrunnboksutskriftOverdragelser() throws ServiceException {
        final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        final String overdragelserInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftOverdragelser(matrikkelenhetId, grunnbokHelper.context());
        assertThat(overdragelserInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("HJEMMELSOPPLYSNINGER")
            .contains("Rettighetshavere til eiendomsrett");

        assertThat(overdragelserInfo)
            .describedAs("har forventede data")
            .matches("(?s:.*)2002/64546-1/105\\s*HJEMMEL TIL EIENDOMSRETT(?s:.*)");
    }

    /**
     * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftHeftelser
     */
    @Test
    public void delAvGrunnboksutskriftPengeheftelser() throws ServiceException {
        final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        final String pengeheftelserInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftHeftelser(matrikkelenhetId, grunnbokHelper.context());
        assertThat(pengeheftelserInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("HEFTELSER");

        assertThat(pengeheftelserInfo)
            .describedAs("har forventede data")
            .matches("(?s).*" +
                "2007/784165-1/200\\s*BRUKSRETT" +
                "\\s*02\\.10\\.2007\\s*RETTIGHETSHAVER: KNR:0301 GNR:2 BNR:1152" +
                "\\s*Gjelder bruksrett til mur\\..*");
    }

    /**
     * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftRegisterenhetsendringer
     */
    @Test
    public void delAvGrunnboksutskriftRegisterenhetsendringer() throws ServiceException {
        final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        final String registerenhetsendringerInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftRegisterenhetsendringer(matrikkelenhetId, grunnbokHelper.context());
        assertThat(registerenhetsendringerInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("GRUNNDATA")
        ;

        assertThat(registerenhetsendringerInfo)
            .describedAs("har forventede data")
            .contains("" +
                "1860/900085-1/105         REGISTRERING AV GRUNN                                                 \r\n" +
                "            05.10.1860                DENNE MATRIKKELENHET OPPRETTET FRA: KNR:0301 GNR:2 BNR:4\r\n" +
                "\r\n" +
                "            1902/900353-1/105         SAMMENSLÅING                                                          \r\n" +
                "            21.02.1902                DENNE MATRIKKELENHET SAMMENSLÅTT MED: KNR:0301 GNR:2 BNR:2\r\n" +
                "                                      DENNE MATRIKKELENHET SAMMENSLÅTT MED: KNR:0301 GNR:2 BNR:3\r\n" +
                "\r\n" +
                "            1902/900039-1/105         REGISTRERING AV GRUNN                                                 \r\n" +
                "            04.03.1902                UTSKILT FRA DENNE MATRIKKELENHET: KNR:0301 GNR:2 BNR:143\r\n" +
                "\r\n" +
                "            1952/4613-1/105           REGISTRERING AV GRUNN                                                 \r\n" +
                "            19.04.1952                UTSKILT FRA DENNE MATRIKKELENHET: KNR:0301 GNR:2 BNR:667\r\n" +
                "\r\n" +
                "            1955/8690-1/105           REGISTRERING AV GRUNN                                                 \r\n" +
                "            29.06.1955                UTSKILT FRA DENNE MATRIKKELENHET: KNR:0301 GNR:2 BNR:705\r\n" +
                "\r\n" +
                "            1991/29463-1/105          MÅLEBREV                                                              \r\n" +
                "            13.06.1991");
    }

    /**
     * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftTidligereOverdragelser
     */
    @Test
    public void delAvGrunnboksutskriftTidligereOverdragelser() throws ServiceException {
        final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        final String overdragelserInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftTidligereOverdragelser(matrikkelenhetId, grunnbokHelper.context());
        assertThat(overdragelserInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("HJEMMELSOPPLYSNINGER")
            .contains("Rettighetshavere til eiendomsrett");

        String expectedString = "[\\S\\s]*" +
            "1948\\/911568-1\\/105         HJEMMEL TIL EIENDOMSRETT                                              \r\n" +
            "            09\\.11\\.1948                VEDERLAG: NOK 0\r\n" +
            "                                      KJØPER: DEN NORSKE STAT\r\n" +
            "                                      LØPENR: 1176505\r\n" +
            "\r\n" +
            "            1991\\/68981-1\\/105          HJEMMEL TIL EIENDOMSRETT                                              \r\n" +
            "            19\\.12\\.1991                VEDERLAG: NOK 1 800 000\r\n" +
            "                                      SELGER: DEN NORSKE STAT\r\n" +
            "                                      LØPENR: 1176505\r\n" +
            "                                      KJØPER: [a-zA-ZøæåØÆÅ\\-\\s]+                     IDEELL: 1\\/2\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "                                      KJØPER: [a-zA-ZøæåØÆÅ\\-\\s]+             IDEELL: 1\\/2\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "\r\n" +
            "            1993\\/15855-1\\/105          HJEMMEL TIL EIENDOMSRETT                                              \r\n" +
            "            15\\.04\\.1993                VEDERLAG: NOK 400 000\r\n" +
            "                                      SELGER: [a-zA-ZøæåØÆÅ\\-\\s]+                     IDEELL: 1\\/2\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "                                      SELGER: [a-zA-ZøæåØÆÅ\\-\\s]+             IDEELL: 1\\/2\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "                                      KJØPER: [a-zA-ZøæåØÆÅ\\-\\s]+\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "\r\n" +
            "            2001\\/32146-1\\/105          HJEMMEL TIL EIENDOMSRETT                                              \r\n" +
            "            11\\.06\\.2001                VEDERLAG: NOK 0\r\n" +
            "                                      SELGER: [a-zA-ZøæåØÆÅ\\-\\s]+\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "                                      KJØPER: [a-zA-ZøæåØÆÅ\\-\\s]+\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "\r\n" +
            "\r\n" +
            "            Påtegning til hjemmelsdokumenter\r\n" +
            "\r\n" +
            "            2001\\/34270-1\\/105       \\*\\* EKTEPAKT U\\/HJ\\.OVERGANG                                                \r\n" +
            "            19\\.06\\.2001                GJELDER: HJEMMEL TIL EIENDOMSRETT       2001\\/32146-1\\/105\r\n" +
            "                                      GJELDER: [a-zA-ZøæåØÆÅ\\-\\s]+\r\n" +
            "                                      F\\.NR: \\d+ \\d+\r\n" +
            "                                      Bestemmelser om særeie iflg\\. ektepakt";

        assertThat(ignoreUnicodeNoBreakSpace(overdragelserInfo))
            .describedAs("har forventede data")
            .matches(ignoreUnicodeNoBreakSpace(expectedString));
    }

    // Bruker blank streng for Unicode Character 'NO-BREAK SPACE' (U+00A0) som ligger i returverdi fra Grunnboka
    private String ignoreUnicodeNoBreakSpace(String string) {
        return string.replaceAll("\\p{Z}", "");
    }

    /**
     * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftTidligereHeftelser
     */
    @Test
    public void delAvGrunnboksutskriftTidligerePengeheftelser() throws ServiceException {
        final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
        final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

        final String pengeheftelserInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftTidligereHeftelser(matrikkelenhetId, grunnbokHelper.context());
        assertThat(pengeheftelserInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("HEFTELSER");

        String expectedString = "[\\S\\s]*" +
            "1991\\/68982-1\\/105          OBLIGASJON                                                            \r\n" +
            "            19\\.12\\.1991                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 2 000 000\r\n" +
            "                                      PANTHAVER: ELCON FINANS AS\r\n" +
            "                                      LØPENR: 1087075\r\n" +
            "\r\n" +
            "            1993\\/60191-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            26\\.11\\.1993                \r\n" +
            "\r\n" +
            "            1993\\/54205-1\\/105          OBLIGASJON                                                            \r\n" +
            "            02\\.11\\.1993                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 1 000 000\r\n" +
            "                                      PANTHAVER: DNB BOLIGKREDITT AS\r\n" +
            "                                      LØPENR: 1137964\r\n" +
            "\r\n" +
            "            1999\\/23787-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            12\\.05\\.1999                \r\n" +
            "\r\n" +
            "            1994\\/16171-1\\/105          OBLIGASJON                                                            \r\n" +
            "            24\\.03\\.1994                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 2 000 000\r\n" +
            "                                      PANTHAVER: DNB BOLIGKREDITT AS\r\n" +
            "                                      LØPENR: 1138135\r\n" +
            "\r\n" +
            "            1999\\/23785-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            12\\.05\\.1999                \r\n" +
            "\r\n" +
            "            1996\\/45584-1\\/105          OBLIGASJON                                                            \r\n" +
            "            20\\.08\\.1996                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 1 500 000\r\n" +
            "                                      PANTHAVER: FINANSBANKEN AS\r\n" +
            "                                      LØPENR: 1087067\r\n" +
            "\r\n" +
            "            2002\\/45980-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            11\\.07\\.2002                \r\n" +
            "\r\n" +
            "            1997\\/63322-1\\/105          OBLIGASJON                                                            \r\n" +
            "            13\\.10\\.1997                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 2 000 000\r\n" +
            "                                      PANTHAVER: FINANSBANKEN ASA\r\n" +
            "                                      LØPENR: 1158123\r\n" +
            "\r\n" +
            "            2002\\/45982-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            11\\.07\\.2002                \r\n" +
            "\r\n" +
            "            1999\\/19270-1\\/105          OBLIGASJON                                                            \r\n" +
            "            20\\.04\\.1999                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 1 300 000\r\n" +
            "                                      PANTHAVER: FINANSBANKEN AS\r\n" +
            "                                      LØPENR: 1087067\r\n" +
            "\r\n" +
            "            2002\\/45981-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            11\\.07\\.2002                \r\n" +
            "\r\n" +
            "            1999\\/73869-1\\/105          OBLIGASJON                                                            \r\n" +
            "            15\\.12\\.1999                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 3 000 000\r\n" +
            "                                      PANTHAVER: FINANSBANKEN AS\r\n" +
            "                                      LØPENR: 1087067\r\n" +
            "\r\n" +
            "            2002\\/45984-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            11\\.07\\.2002                \r\n" +
            "\r\n" +
            "            2001\\/32146-2\\/105          LIVSVARIG BORETT                                                      \r\n" +
            "            11\\.06\\.2001                STATUS: HISTORISK\r\n" +
            "                                      Rettighetshaver [a-zA-ZøæåØÆÅ\\-\\s]+, f\\. \\d+\\.\r\n" +
            "\r\n" +
            "            2002\\/41810-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            26\\.06\\.2002                \r\n" +
            "\r\n" +
            "            2001\\/34270-2\\/105          BRUKSRETT                                                             \r\n" +
            "            19\\.06\\.2001                STATUS: HISTORISK\r\n" +
            "                                      Rettighetshaver [a-zA-ZøæåØÆÅ\\-\\s]+\\.\r\n" +
            "\r\n" +
            "            2002\\/41810-2\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            26\\.06\\.2002                \r\n" +
            "\r\n" +
            "            2002\\/34688-1\\/105          OBLIGASJON                                                            \r\n" +
            "            30\\.05\\.2002                STATUS: HISTORISK\r\n" +
            "                                      BELØP: NOK 19 000 000\r\n" +
            "                                      PANTHAVER: M2 EIENDOMSMEGLING AS\r\n" +
            "                                      LØPENR: 1087099\r\n" +
            "\r\n" +
            "            2002\\/68833-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            21\\.10\\.2002                \r\n" +
            "\r\n" +
            "            2002\\/34688-2\\/105          URÅDIGHET                                                             \r\n" +
            "            30\\.05\\.2002                STATUS: HISTORISK\r\n" +
            "                                      Eiendommen kan ikke disponeres m\\.v\\. over uten samtykke\r\n" +
            "                                      fra M2 Eiendomsmegling AS\\.\r\n" +
            "\r\n" +
            "            2002\\/68833-1\\/105       \\*\\* SLETTING                                                              \r\n" +
            "            21\\.10\\.2002";
        assertThat(ignoreUnicodeNoBreakSpace(pengeheftelserInfo))
            .describedAs("har forventede data")
            .matches(ignoreUnicodeNoBreakSpace(expectedString));
    }

    @BeforeTest
    public void setUp() throws ServiceException {
        final IntegrationTestProperties config = new IntegrationTestProperties();
        final String grunnbokUser = config.getGrunnbokMatFnUsername();
        final String grunnbokPassword = config.getGrunnbokMatFnPassword();
        if (grunnboksutskriftIntern == null) {
            final NullHostnameVerifier hostnameVerifier = new NullHostnameVerifier();
            try {
                grunnboksutskriftIntern = new DefaultGrunnboksutskriftInternWS(grunnbokUser, grunnbokPassword, config.getGrunnbokGrunnboksutskriftInternServiceUrl());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        grunnbokHelper = new GrunnbokHelper();
    }

    @AfterTest
    public void teardown() throws ServiceException {
        grunnboksutskriftIntern = null;
        grunnbokHelper = null;
    }


}
