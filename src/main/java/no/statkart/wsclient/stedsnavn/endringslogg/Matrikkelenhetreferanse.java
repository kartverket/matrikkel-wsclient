package no.statkart.wsclient.stedsnavn.endringslogg;

import java.time.LocalDateTime;

public class Matrikkelenhetreferanse extends Matrikkelreferanse {

   private String kommunenummer;
   private int gaardsnummer;
   private int bruksnummer;
   private int festenummer;
   private int seksjonsnummer;

   public Matrikkelenhetreferanse(LocalDateTime registreringsdato, String kommunenummer) {
      super(registreringsdato);
      this.kommunenummer = kommunenummer;
   }

   public String getKommunenummer() {
      return kommunenummer;
   }

   public int getGaardsnummer() {
      return gaardsnummer;
   }

   public void setGaardsnummer(int gaardsnummer) {
      this.gaardsnummer = gaardsnummer;
   }

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

   public int getSeksjonsnummer() {
      return seksjonsnummer;
   }

   public void setSeksjonsnummer(int seksjonsnummer) {
      this.seksjonsnummer = seksjonsnummer;
   }
}
