package no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus;


import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;

/**
 * Fluent opprettelse av {@link Matrikkelenhet}
 */
public class MatrikkelenhetBuilder {
   private String kommunenummer;
   private String kommunenavn;
   private int gaardsnummer;
   private int bruksnummer;
   private int festenummer;
   private int seksjonsnummer;

   private MatrikkelenhetBuilder() {
   }

   public static MatrikkelenhetBuilder aMatrikkelenhet() {
      return new MatrikkelenhetBuilder();
   }

   public MatrikkelenhetBuilder withKommunenummer(String kommunenummer) {
      this.kommunenummer = kommunenummer;
      return this;
   }

   public MatrikkelenhetBuilder withKommunenavn(String kommunenavn) {
      this.kommunenavn = kommunenavn;
      return this;
   }

   public MatrikkelenhetBuilder withGaardsnummer(int gaardsnummer) {
      this.gaardsnummer = gaardsnummer;
      return this;
   }

   public MatrikkelenhetBuilder withBruksnummer(int bruksnummer) {
      this.bruksnummer = bruksnummer;
      return this;
   }

   public MatrikkelenhetBuilder withFestenummer(int festenummer) {
      this.festenummer = festenummer;
      return this;
   }

   public MatrikkelenhetBuilder withSeksjonsnummer(int seksjonsnummer) {
      this.seksjonsnummer = seksjonsnummer;
      return this;
   }

   public MatrikkelenhetBuilder but() {
      return aMatrikkelenhet()
            .withBruksnummer(bruksnummer)
            .withKommunenummer(kommunenummer)
            .withKommunenavn(kommunenavn)
            .withGaardsnummer(gaardsnummer)
            .withFestenummer(festenummer)
            .withSeksjonsnummer(seksjonsnummer)
            ;
   }

   public Matrikkelenhet build() {
      Matrikkelenhet matrikkelenhet = new Matrikkelenhet();
      matrikkelenhet.setKommunenummer(kommunenummer);
      matrikkelenhet.setKommunenavn(kommunenavn);
      matrikkelenhet.setGaardsnummer(gaardsnummer);
      matrikkelenhet.setBruksnummer(bruksnummer);
      matrikkelenhet.setFestenummer(festenummer);
      matrikkelenhet.setSeksjonsnummer(seksjonsnummer);
      return matrikkelenhet;
   }
}
