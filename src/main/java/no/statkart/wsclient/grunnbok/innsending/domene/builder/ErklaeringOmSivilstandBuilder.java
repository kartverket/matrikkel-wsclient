package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import no.statkart.wsclient.grunnbok.innsending.domene.ErklaeringOmSivilstand;
import no.statkart.wsclient.grunnbok.innsending.domene.Person;

/**
 *
 */
public class ErklaeringOmSivilstandBuilder {
   private Person ektefellePartner;
   private Person erklaeringFor;
   private boolean kreverSamtykke;

   private ErklaeringOmSivilstandBuilder() {
   }

   public static ErklaeringOmSivilstandBuilder anErklaeringOmSivilstand() {
      return new ErklaeringOmSivilstandBuilder();
   }

   public ErklaeringOmSivilstandBuilder withEktefellePartner(Person ektefellePartner) {
      this.ektefellePartner = ektefellePartner;
      return this;
   }

   public ErklaeringOmSivilstandBuilder withErklaeringFor(Person erklaeringFor) {
      this.erklaeringFor = erklaeringFor;
      return this;
   }

   public ErklaeringOmSivilstandBuilder withKreverSamtykke(boolean kreverSamtykke) {
      this.kreverSamtykke = kreverSamtykke;
      return this;
   }

   public ErklaeringOmSivilstandBuilder but() {
      return anErklaeringOmSivilstand().withEktefellePartner(ektefellePartner).withErklaeringFor(erklaeringFor).withKreverSamtykke(kreverSamtykke);
   }

   public ErklaeringOmSivilstand build() {
      ErklaeringOmSivilstand erklaeringOmSivilstand = new ErklaeringOmSivilstand();
      erklaeringOmSivilstand.setEktefellePartner(ektefellePartner);
      erklaeringOmSivilstand.setErklaeringFor(erklaeringFor);
      erklaeringOmSivilstand.setKreverSamtykke(kreverSamtykke);
      return erklaeringOmSivilstand;
   }
}
