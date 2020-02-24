package no.statkart.wsclient.grunnbokv2.innsending.domene;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.equal;

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

   @Override
   public int hashCode() {
      return Objects.hashCode(kommunenummer, kommunenavn, gaardsnummer, bruksnummer, festenummer, seksjonsnummer);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null || obj.getClass() != getClass()) {
         return false;
      }
      Matrikkelenhet other = (Matrikkelenhet) obj;
      return equal(kommunenummer, other.getKommunenummer()) &&
            equal(kommunenavn, other.getKommunenavn()) &&
            equal(gaardsnummer, other.getGaardsnummer()) &&
            equal(bruksnummer, other.getBruksnummer()) &&
            equal(festenummer, other.getFestenummer()) &&
            equal(seksjonsnummer, other.getSeksjonsnummer());
   }
}
