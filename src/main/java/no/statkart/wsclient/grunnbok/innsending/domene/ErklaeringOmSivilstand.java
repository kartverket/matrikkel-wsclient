package no.statkart.wsclient.grunnbok.innsending.domene;

/**
 *
 */
public class ErklaeringOmSivilstand {

   private Person erklaeringFor;
   private boolean kreverSamtykke;
   private Person ektefellePartner;

   public Person getEktefellePartner() {
      return ektefellePartner;
   }

   public void setEktefellePartner(Person ektefellePartner) {
      this.ektefellePartner = ektefellePartner;
   }

   public Person getErklaeringFor() {
      return erklaeringFor;
   }

   public void setErklaeringFor(Person erklaeringFor) {
      this.erklaeringFor = erklaeringFor;
   }

   public boolean isKreverSamtykke() {
      return kreverSamtykke;
   }

   public void setKreverSamtykke(boolean kreverSamtykke) {
      this.kreverSamtykke = kreverSamtykke;
   }
}
