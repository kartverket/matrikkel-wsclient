package no.statkart.wsclient.grunnbok.innsending.domene;

/**
 *
 */
public class Matrikkelenhet {

   private String kommunenummer;
   private String kommunenavn;
   private int gaardsnummer;
   private int bruksnummer;
   private int festenummer;
   private int seksjonsnummer;

   public int getBruksnummer() {
      return bruksnummer;
   }

   public void setBruksnummer(int bruksnummer) {
      this.bruksnummer = bruksnummer;
   }

   public int getFestenummer() {
      return festenummer;
   }

   public void setFestenummer(int festenummer) {
      this.festenummer = festenummer;
   }

   public int getGaardsnummer() {
      return gaardsnummer;
   }

   public void setGaardsnummer(int gaardsnummer) {
      this.gaardsnummer = gaardsnummer;
   }

   public String getKommunenavn() {
      return kommunenavn;
   }

   public void setKommunenavn(String kommunenavn) {
      this.kommunenavn = kommunenavn;
   }

   public String getKommunenummer() {
      return kommunenummer;
   }

   public void setKommunenummer(String kommunenummer) {
      this.kommunenummer = kommunenummer;
   }

   public int getSeksjonsnummer() {
      return seksjonsnummer;
   }

   public void setSeksjonsnummer(int seksjonsnummer) {
      this.seksjonsnummer = seksjonsnummer;
   }
}
