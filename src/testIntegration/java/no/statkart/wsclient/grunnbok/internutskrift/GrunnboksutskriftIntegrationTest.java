package no.statkart.wsclient.grunnbok.internutskrift;

import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.MatrikkelenhetId;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.statkart.skif.util.NullHostnameVerifier;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integrajonstest av WS signatur og retur verdier ifra Grunnboksystemet er som forventet [MAT-12778].
 */
public class GrunnboksutskriftIntegrationTest {

   private GrunnboksutskriftInternWS grunnboksutskriftIntern;
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
            .contains("            Registrert til og med: ")
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
            .contains("Hjemmelshavere");

      assertThat(overdragelserInfo).describedAs("har forventede data").contains(new StringBuilder()
            .append("                64546 02/10/2002      HJEMMEL TIL GRUNN\r\n")
            .append("                                      VEDERLAG: 19.000.000\r\n")
            .append("                                      EMBASSY OF IRELAND\r\n")
            .append("                                      LØPENR: 6107411\r\n")
            .append("                                      SKJØTE (UTLAND DIPL/KONS)")
      );
   }

   /**
    * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftPengeheftelser
    */
   @Test
   public void delAvGrunnboksutskriftPengeheftelser() throws ServiceException {
      final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
      final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

      final String pengeheftelserInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftPengeheftelser(matrikkelenhetId, grunnbokHelper.context());
      assertThat(pengeheftelserInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("PENGEHEFTELSER");

      assertThat(pengeheftelserInfo).describedAs("har forventede data").contains(new StringBuilder()
            .append("               784165 02/10/2007      BRUKSRETT\r\n")
            .append("                                      RETTIGHETSHAVER: KNR:0301 GNR:2 BNR:1152\r\n")
            .append("                                      Gjelder bruksrett til mur.")
      );
   }

   /**
    * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftServitutter
    */
   @Test
   public void delAvGrunnboksutskriftServitutter() throws ServiceException {
      final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
      final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

      final String servitutterInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftServitutter(matrikkelenhetId, grunnbokHelper.context());
      assertThat(servitutterInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("SERVITUTTER")
            .contains("IKKE OVERFØRTE DOKUMENTER:")
            .contains("  DEN MANUELLE GRUNNBOKEN HAR DOKUMENTER SOM ANTAS KUN Å HA HISTORISK BETYDNING,")
            .contains("  ELLER SOM ER TINGLYST VEDRØRENDE MATRIKKELENHETENS GRENSER OG AREAL.")

            .contains("  FOR SERVITUTTER ELDRE ENN FRADELINGSDATO OG EVENTUELLE AREALOVERFØRINGER")
            .contains("  SOM KAN HA BETYDNING FOR DENNE MATRIKKELENHET HENVISES TIL HOVEDBRUKET/AVGIVEREIENDOMMEN.")
            .contains("  FOR FESTENUMMER GJELDER HENVISNINGEN SERVITUTTER ELDRE ENN FESTEKONTRAKTEN.")
      ;

      assertThat(servitutterInfo).describedAs("har forventede data").contains(new StringBuilder()

            .append("               924218 17/12/1860      BEST. OM BÅT/BRYGGEPLASS\r\n")
            .append("                                      Bestemmelse om vannrett\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("                                      RETT TIL BÅTHAVN OVERFØRT TIL BNR 143\r\n")
            .append("                                      IFLG. SKJØTE TGL. 21/3-1902.\r\n")
            .append("\r\n")
            .append("               919437 16/12/1864      ERKLÆRING/AVTALE\r\n")
            .append("                                      Bestemmelse om veg og bro - vedlikehold.\r\n")
            .append("                                      GJELDER DENNE REGISTERENHETEN MED FLERE\r\n")
            .append("\r\n")
            .append("               920051 11/07/1871      BESTEMMELSE OM VANNRETT\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("                                      IFLG SKJØTE PÅ LØPENR 338A.\r\n")
            .append("\r\n")
            .append("               920050 18/08/1871      BESTEMMELSE OM VEG\r\n")
            .append("                                      MÅ IKKE BENYTTES TIL FABRIKK ELLER UTSALGSSTED.\r\n")
            .append("\r\n")
            .append("               919904 20/09/1872      BESTEMMELSE OM VEG\r\n")
            .append("                                      Bestemmelse om gjerdeplikt\r\n")
            .append("\r\n")
            .append("               900241 20/04/1906      BESTEMMELSE OM VANNRETT\r\n")
            .append("                                      Bestemmelse om vann/kloakkledning - VEDTAGELSE AV\r\n")
            .append("                                      REGLEMENTET\r\n")
            .append("                                      Med flere bestemmelser\r\n")
            .append("\r\n")
            .append("                12458 22/09/1953      BEST. OM VANN/KLOAKKLEDN.\r\n")
            .append("\r\n")
            .append("                15778 25/11/1953      BESTEMMELSE OM VEG\r\n")
            .append("                                      BESTEMMELSE OM KLOAKKLEDNING\r\n")
            .append("                                      Bestemmelse om gjerdeplikt\r\n")
            .append("\r\n")
            .append("                 5947 02/05/1955      BEST. OM VANN/KLOAKKLEDN.\r\n")
            .append("                                      VEDTAGELSE AV REGLEMENTET\r\n")
            .append("\r\n")
            .append("                15956 21/12/1957      BESTEMMELSE OM VEG\r\n")
            .append("                                      Bestemmelse om gjerdeplikt\r\n")
            .append("\r\n")
            .append("                  980 08/01/1998      ERKLÆRING/AVTALE\r\n")
            .append("                                      RETTIGHETSHAVER: KNR:0301 GNR:2 BNR:1146\r\n")
            .append("                                      Rett til vederlagsfri tilkobling av stikkledning\r\n")
            .append("                                      Kan ikke slettes uten samtykke fra Oslo vann- og avløpsverk")
      );
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

      assertThat(registerenhetsendringerInfo).describedAs("har forventede data").contains(new StringBuilder()
            .append("               900085 05/10/1860      REGISTRERING AV GRUNN\r\n")
            .append("                                      DENNE MATRIKKELENHET OPPRETTET FRA: KNR:0301 GNR:2 BNR:4\r\n")
            .append("\r\n")
            .append("               900353 21/02/1902      SAMMENSLÅING\r\n")
            .append("                                      DENNE MATRIKKELENHET SAMMENSLÅTT MED: KNR:0301 GNR:2 BNR:2\r\n")
            .append("                                      DENNE MATRIKKELENHET SAMMENSLÅTT MED: KNR:0301 GNR:2 BNR:3\r\n")
            .append("\r\n")
            .append("               900039 04/03/1902      REGISTRERING AV GRUNN\r\n")
            .append("                                      UTSKILT FRA DENNE MATRIKKELENHET: KNR:0301 GNR:2 BNR:143\r\n")
            .append("\r\n")
            .append("                 4613 19/04/1952      REGISTRERING AV GRUNN\r\n")
            .append("                                      UTSKILT FRA DENNE MATRIKKELENHET: KNR:0301 GNR:2 BNR:667\r\n")
            .append("\r\n")
            .append("                 8690 29/06/1955      REGISTRERING AV GRUNN\r\n")
            .append("                                      UTSKILT FRA DENNE MATRIKKELENHET: KNR:0301 GNR:2 BNR:705\r\n")
            .append("\r\n")
            .append("                29463 13/06/1991      MÅLEBREV")
      );
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
            .contains("Hjemmelshavere");

      assertThat(overdragelserInfo).describedAs("har forventede data").contains(new StringBuilder()
            .append("               911568 09/11/1948      HJEMMEL TIL GRUNN\r\n")
            .append("                                      VEDERLAG: 0\r\n")
            .append("                                      KJØPER: DEN NORSKE STAT\r\n")
            .append("                                      LØPENR: 1176505\r\n")
            .append("\r\n")
            .append("                68981 19/12/1991      HJEMMEL TIL GRUNN\r\n")
            .append("                                      VEDERLAG: 1.800.000\r\n")
            .append("                                      SELGER: DEN NORSKE STAT\r\n")
            .append("                                      LØPENR: 1176505\r\n")
            .append("                                      KJØPER: ***REMOVED***                     IDEELL: 1/2\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("                                      KJØPER: RØSTAD-TOLLEFSEN HELEN               \r\n")
            .append("                                      KATHRINE                                     IDEELL: 1/2\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("\r\n")
            .append("                15855 15/04/1993      HJEMMEL TIL GRUNN\r\n")
            .append("                                      VEDERLAG: 400.000\r\n")
            .append("                                      SELGER: ***REMOVED***                     IDEELL: 1/2\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("                                      SELGER: RØSTAD-TOLLEFSEN HELEN               \r\n")
            .append("                                      KATHRINE                                     IDEELL: 1/2\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("                                      KJØPER: ***REMOVED***\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("\r\n")
            .append("                32146 11/06/2001      HJEMMEL TIL GRUNN\r\n")
            .append("                                      VEDERLAG: 0\r\n")
            .append("                                      SELGER: ***REMOVED***\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("                                      KJØPER: ***REMOVED***\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("\r\n")
            .append("\r\n")
            .append("            Påtegning til hjemmelsdokumenter\r\n")
            .append("                34270 19/06/2001   ** EKTEPAKT U/HJ.OVERGANG\r\n")
            .append("                                      GJELDER: HJEMMEL TIL GRUNN 32146/2001\r\n")
            .append("                                      GJELDER: ***REMOVED***\r\n")
            .append("                                      F.NR: ***REMOVED***\r\n")
            .append("                                      Bestemmelser om særeie iflg. ektepakt")
      );
   }

   /**
    * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftTidligerePengeheftelser
    */
   @Test
   public void delAvGrunnboksutskriftTidligerePengeheftelser() throws ServiceException {
      final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 1);
      final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

      final String pengeheftelserInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftTidligerePengeheftelser(matrikkelenhetId, grunnbokHelper.context());
      assertThat(pengeheftelserInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("PENGEHEFTELSER");

      assertThat(pengeheftelserInfo).describedAs("har forventede data").contains(new StringBuilder()
            .append("                                      LØPENR: 1087075\r\n")
            .append("\r\n")
            .append("                60191 26/11/1993   ** SLETTING\r\n")
            .append("\r\n")
            .append("                54205 02/11/1993      OBLIGASJON\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      BELØP: NOK 1.000.000\r\n")
            .append("                                      PANTHAVER: DNB BOLIGKREDITT AS\r\n")
            .append("                                      LØPENR: 1137964\r\n")
            .append("\r\n")
            .append("                23787 12/05/1999   ** SLETTING\r\n")
            .append("\r\n")
            .append("                16171 24/03/1994      OBLIGASJON\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      BELØP: NOK 2.000.000\r\n")
            .append("                                      PANTHAVER: DNB BOLIGKREDITT AS\r\n")
            .append("                                      LØPENR: 1138135\r\n")
            .append("\r\n")
            .append("                23785 12/05/1999   ** SLETTING\r\n")
            .append("\r\n")
            .append("                45584 20/08/1996      OBLIGASJON\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      BELØP: NOK 1.500.000\r\n")
            .append("                                      PANTHAVER: FINANSBANKEN AS\r\n")
            .append("                                      LØPENR: 1087067\r\n")
            .append("\r\n")
            .append("                45980 11/07/2002   ** SLETTING\r\n")
            .append("\r\n")
            .append("                63322 13/10/1997      OBLIGASJON\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      BELØP: NOK 2.000.000\r\n")
            .append("                                      PANTHAVER: FINANSBANKEN ASA\r\n")
            .append("                                      LØPENR: 1158123\r\n")
            .append("\r\n")
            .append("                45982 11/07/2002   ** SLETTING\r\n")
            .append("\r\n")
            .append("                19270 20/04/1999      OBLIGASJON\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      BELØP: NOK 1.300.000\r\n")
            .append("                                      PANTHAVER: FINANSBANKEN AS\r\n")
            .append("                                      LØPENR: 1087067\r\n")
            .append("\r\n")
            .append("                45981 11/07/2002   ** SLETTING\r\n")
            .append("\r\n")
            .append("                73869 15/12/1999      OBLIGASJON\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      BELØP: NOK 3.000.000\r\n")
            .append("                                      PANTHAVER: FINANSBANKEN AS\r\n")
            .append("                                      LØPENR: 1087067\r\n")
            .append("\r\n")
            .append("                45984 11/07/2002   ** SLETTING\r\n")
            .append("\r\n")
            .append("                32146 11/06/2001      LIVSVARIG BORETT\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      Rettighetshaver ***REMOVED***, f. ***REMOVED***.\r\n")
            .append("\r\n")
            .append("                41810 26/06/2002   ** SLETTING\r\n")
            .append("\r\n")
            .append("                34270 19/06/2001      BRUKSRETT\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      Rettighetshaver ***REMOVED***.\r\n")
            .append("\r\n")
            .append("                41810 26/06/2002   ** SLETTING\r\n")
            .append("\r\n")
            .append("                34688 30/05/2002      OBLIGASJON\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      BELØP: NOK 19.000.000\r\n")
            .append("                                      PANTHAVER: M2 EIENDOMSMEGLING AS\r\n")
            .append("                                      LØPENR: 1087099\r\n")
            .append("\r\n")
            .append("                68833 21/10/2002   ** SLETTING\r\n")
            .append("\r\n")
            .append("                34688 30/05/2002      URÅDIGHET\r\n")
            .append("                                      STATUS: HISTORISK\r\n")
            .append("                                      Eiendommen kan ikke disponeres m.v. over uten samtykke\r\n")
            .append("                                      fra M2 Eiendomsmegling AS.\r\n")
      );
   }

   /**
    * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftTidligereServitutter
    */
   @Test
   public void delAvGrunnboksutskriftTidligereServitutter() throws ServiceException {
      final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 4);
      final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

      final String servitutterInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftTidligereServitutter(matrikkelenhetId, grunnbokHelper.context());
      assertThat(servitutterInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("SERVITUTTER")
      ;

      assertThat(servitutterInfo).describedAs("har forventede data").contains(new StringBuilder()
            .append("            Servitutter i grunn:\r\n")
            .append("                    \r\n")
            .append("               911569 17/12/1860      BEST. OM BÅT/BRYGGEPLASS\r\n")
            .append("\r\n")
            .append("               911570 17/12/1860      BEST. OM BÅT/BRYGGEPLASS\r\n")
            .append("                                      RETTIGHETSHAVER: KNR:0301 GNR:2 BNR:1\r\n")
            .append("                                      Bestemmelse om båt/bryggeplass\r\n")
            .append("                                      Bestemmelse om vannrett\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("\r\n")
            .append("               924219 17/12/1860      RETTIGHETER IFLG. SKJØTE\r\n")
            .append("                                      Bestemmelse om båt/bryggeplass\r\n")
            .append("                                      Bestemmelse om vannrett\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("\r\n")
            .append("               900100 04/12/1863      ERKLÆRING/AVTALE\r\n")
            .append("                                      SKJØTE PÅ BNR 6 M/FORSKJELLIGE FORPLIKTELSER\r\n")
            .append("\r\n")
            .append("               919438 18/03/1864      ERKLÆRING/AVTALE\r\n")
            .append("                                      IFLG SKJØTE PÅ BNR 8\r\n")
            .append("                                      BESTEMMELSER OM STRAND\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("\r\n")
            .append("               922663 08/01/1869      ERKLÆRING/AVTALE\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("                                      Bestemmelse om båt/bryggeplass\r\n")
            .append("                                      IFLG SKJØTE PÅ BNR 20\r\n")
            .append("\r\n")
            .append("               920052 18/08/1871      RETTIGHETER IFLG. SKJØTE\r\n")
            .append("                                      Bestemmelse om båt/bryggeplass\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("\r\n")
            .append("               919905 02/02/1872      RETTIGHETER IFLG. SKJØTE\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("                                      Bestemmelse om båt/bryggeplass\r\n")
            .append("                                      IFLG SKJØTE PÅ BNR 34\r\n")
            .append("\r\n")
            .append("               916716 17/10/1873      RETTIGHETER IFLG. SKJØTE\r\n")
            .append("                                      Bestemmelse om båt/bryggeplass\r\n")
            .append("                                      IFLG SKJØTE PÅ BNR 44\r\n")
            .append("\r\n")
            .append("               924217 16/07/1880      BESTEMMELSE OM VEG\r\n")
            .append("                                      Bestemmelse om gjerdeplikt, vedlikehold\r\n")
            .append("\r\n")
            .append("               900955 23/08/1929      BEST. OM VANN/KLOAKKLEDN.\r\n")
            .append("\r\n")
            .append("               900956 23/08/1929      ERKLÆRING/AVTALE\r\n")
            .append("                                      BESTEMMELSE OM KLOAKKLEDNING\r\n")
            .append("                                      RETIGHETSHAVER: BNR 22 OG 33\r\n")
            .append("\r\n")
            .append("               901019 05/06/1931      BESTEMMELSE OM BEBYGGELSE\r\n")
            .append("\r\n")
            .append("               901023 11/08/1933      BEST. OM VANN/KLOAKKLEDN.\r\n")
            .append("                                      RETTIGHETSHAVER: KNR:0301 GNR:2 BNR:486\r\n")
            .append("                                      RETTIGHETSHAVER: KNR:0301 GNR:2 BNR:486 SNR:1\r\n")
            .append("                                      RETTIGHETSHAVER: KNR:0301 GNR:2 BNR:486 SNR:2\r\n")
            .append("                                      over parsell 22.\r\n")
            .append("\r\n")
            .append("               901024 18/08/1933      RETTIGHETER IFLG. SKJØTE\r\n")
            .append("                                      Bestemmelse om bebyggelse\r\n")
            .append("                                      Bestemmelse om gjerdeplikt\r\n")
            .append("                                      Bestemmelse om vannledning\r\n")
            .append("                                      BESTEMMELSE OM KLOAKKLEDNING\r\n")
            .append("                                      Bestemmelse om veg\r\n")
            .append("                                      Bestemmelse om deleforbud\r\n")
            .append("                                      RETT FOR BNR 486 VEDR. VANN/KLOAKKLEDN.\r\n")
            .append("\r\n")
            .append("               909125 08/03/1935      BESTEMMELSE OM VEG\r\n")
            .append("                                      Bestemmelse om gjerdeplikt\r\n")
            .append("                                      BESTEMMELSE OM KLOAKKLEDNING\r\n")
            .append("\r\n")
            .append("                11437 25/09/1952      ERKLÆRING/AVTALE\r\n")
            .append("                                      FELLESERKL I ANL. KOMMUNAL OVERTAGELSE AV LOVISENBERGVN\r\n")
            .append("\r\n")
            .append("                 4209 05/04/1957      ERKLÆRING/AVTALE\r\n")
            .append("                                      BESTEMMELSE OM KLOAKKLEDNING\r\n")
            .append("\r\n")
            .append("               876362 17/11/2009      ERKLÆRING/AVTALE\r\n")
            .append("                                      Midlertidig dispensasjon fra bygningsloven\r\n")
            .append("                                      Lovisenlund godkjennes inntil videre i sin nåværende bredde.\r\n")
            .append("                                      Plikt til fremtidig vederlagsfri avståelse av veigrunn til Oslo kommune.")
      );
   }

   /**
    * @see GrunnboksutskriftInternWS#delAvGrunnboksutskriftTidligereRegisterenhetsendringer
    */
   @Test
   public void delAvGrunnboksutskriftTidligereRegisterenhetsendringer() throws ServiceException {
      final MatrikkelenhetIdent matrikkelenhetIdent = GrunnbokHelper.matrikkelenhetIdent("0301", 2, 2);
      final MatrikkelenhetId matrikkelenhetId = grunnbokHelper.findMatrikkelenhetId(matrikkelenhetIdent);

      final String registerenhetsendringerInfo = grunnboksutskriftIntern.delAvGrunnboksutskriftTidligereRegisterenhetsendringer(matrikkelenhetId, grunnbokHelper.context());
      assertThat(registerenhetsendringerInfo).describedAs("overskrift")
            .isNotEmpty()
            .contains("GRUNNDATA")
      ;

      assertThat(registerenhetsendringerInfo).describedAs("har forventede data").contains(new StringBuilder()
            .append("               900111 15/09/1876      REGISTRERING AV GRUNN\r\n")
            .append("                                      DENNE MATRIKKELENHET OPPRETTET FRA: KNR:0301 GNR:2 BNR:5\r\n")
            .append("\r\n")
            .append("               900353 21/02/1902      SAMMENSLÅING\r\n")
            .append("                                      DENNE MATRIKKELENHET SAMMENSLÅTT MED: KNR:0301 GNR:2 BNR:1")
      );
   }


   @BeforeTest
   public void setUp() throws ServiceException {
      final IntegrationTestProperties config = new IntegrationTestProperties();
      final String grunnbokUser = config.getGrunnbokUser();
      final String grunnbokPassword = config.getGrunnbokPassword();
      if (grunnboksutskriftIntern == null) {
         final NullHostnameVerifier hostnameVerifier = new NullHostnameVerifier();
         try {
            grunnboksutskriftIntern = new DefaultGrunnboksutskriftInternWS(grunnbokUser, grunnbokPassword, config.getGrunnbokGrunnboksutskriftInternServiceUrl(), hostnameVerifier);
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
