package no.statkart.wsclient.grunnbok.innsending.testdatafactory;

import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.MatrikkelenhetBuilder;

public class MatrikkelenhetFactory {

   public static final int GAARDSNUMMER_OSLO = 1;
   public static final int SEKSJONSNUMMER_OSLO = 1;
   public static final int BRUKSNUMMER_OSLO = 1;
   public static final int FESTENUMMER_OSLO = 1;
   public static final String KOMMUNENAVN_OSLO = "OSLO";
   public static final String KOMMUNENUMMER_OSLO = "1301";

   public static final int TIL_MATRIKKELENHET_SEKSJONSNUMMER = 2;

   public static final int MATRIKKELENHET_FRA_TIL_FRA_BRUKSNUMMER = 17;
   public static final String MATRIKKELENHET_FRA_TIL_TIL_KOMMUNENUMMER = "0456";

   public static Matrikkelenhet createMatrikkelenhetIOslo() {
      return MatrikkelenhetBuilder.aMatrikkelenhet()
            .withGaardsnummer(GAARDSNUMMER_OSLO)
            .withBruksnummer(BRUKSNUMMER_OSLO)
            .withFestenummer(FESTENUMMER_OSLO)
            .withSeksjonsnummer(SEKSJONSNUMMER_OSLO)
            .withKommunenavn(KOMMUNENAVN_OSLO)
            .withKommunenummer(KOMMUNENUMMER_OSLO).build();

   }
   
   public static Matrikkelenhet createMatrikkelenhetIOsloWithSeksjonsnr() {
      return MatrikkelenhetBuilder.aMatrikkelenhet()
            .withGaardsnummer(GAARDSNUMMER_OSLO)
            .withBruksnummer(BRUKSNUMMER_OSLO)
            .withFestenummer(FESTENUMMER_OSLO)
            .withSeksjonsnummer(TIL_MATRIKKELENHET_SEKSJONSNUMMER)
            .withKommunenavn(KOMMUNENAVN_OSLO)
            .withKommunenummer(KOMMUNENUMMER_OSLO).build();
   }


   public static Matrikkelenhet createMatrikkelenhet(String kommunenummer, String kommunenavn, int gardsnummer, int bruksnummer) {
      return MatrikkelenhetBuilder.aMatrikkelenhet()
            .withKommunenavn(kommunenavn)
            .withKommunenummer(kommunenummer)
            .withGaardsnummer(gardsnummer)
            .withBruksnummer(bruksnummer)
            .build();
   }
}
