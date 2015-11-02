package no.statkart.wsclient.grunnbok.innsending.ws.builder;


import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Borettslagsandel;

/**
 *
 */
public class BorettslagsandelBuilder {
   private int andelsnummer;
   private String borettslagnummer;
   private String borettslagnavn;

   private BorettslagsandelBuilder() {
   }

   public static BorettslagsandelBuilder aBorettslagsandel() {
      return new BorettslagsandelBuilder();
   }

   public BorettslagsandelBuilder withAndelsnummer(int andelsnummer) {
      this.andelsnummer = andelsnummer;
      return this;
   }

   public BorettslagsandelBuilder withBorettslagnummer(String borettslagnummer) {
      this.borettslagnummer = borettslagnummer;
      return this;
   }

   public BorettslagsandelBuilder withBorettslagnavn(String borettslagnavn) {
      this.borettslagnavn = borettslagnavn;
      return this;
   }

   public BorettslagsandelBuilder but() {
      return aBorettslagsandel().withAndelsnummer(andelsnummer).withBorettslagnummer(borettslagnummer).withBorettslagnavn(borettslagnavn);
   }

   public Borettslagsandel build() {
      Borettslagsandel borettslagsandel = new Borettslagsandel();
      borettslagsandel.setAndelsnummer(andelsnummer);
      borettslagsandel.setBorettslagnummer(borettslagnummer);
      borettslagsandel.setBorettslagnavn(borettslagnavn);
      return borettslagsandel;
   }
}
