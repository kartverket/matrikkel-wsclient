package no.statkart.wsclient.grunnbokv2.innsending.testdatafactory;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.MatrikkelenhetBuilder;

public class MatrikkelenhetFactory {

   public static Matrikkelenhet createMatrikkelenhet(String kommunenummer, String kommunenavn, int gardsnummer, int bruksnummer) {
      return MatrikkelenhetBuilder.aMatrikkelenhet()
            .withKommunenavn(kommunenavn)
            .withKommunenummer(kommunenummer)
            .withGaardsnummer(gardsnummer)
            .withBruksnummer(bruksnummer)
            .build();
   }
}
