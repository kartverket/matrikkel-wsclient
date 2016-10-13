package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Kode;

/**
 *
 */
public class KodeBuilder {
   private String kodeverdi;
   private String navn;

   private KodeBuilder() {
   }

   public static KodeBuilder aKode() {
      return new KodeBuilder();
   }

   public KodeBuilder withKodeverdi(String kodeverdi) {
      this.kodeverdi = kodeverdi;
      return this;
   }

   public KodeBuilder withNavn(String navn) {
      this.navn = navn;
      return this;
   }

   public KodeBuilder but() {
      return aKode().withKodeverdi(kodeverdi).withNavn(navn);
   }

   public Kode build() {
      Kode kode = new Kode();
      kode.setKodeverdi(kodeverdi);
      kode.setNavn(navn);
      return kode;
   }
}
