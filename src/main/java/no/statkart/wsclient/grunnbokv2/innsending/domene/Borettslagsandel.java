package no.statkart.wsclient.grunnbokv2.innsending.domene;

public class Borettslagsandel {

   private String borettslagnummer;
   private String borettslagnavn;
   private int andelsnummer;

   public int getAndelsnummer() {
      return andelsnummer;
   }

   public void setAndelsnummer(int andelsnummer) {
      this.andelsnummer = andelsnummer;
   }

   public String getBorettslagnavn() {
      return borettslagnavn;
   }

   public void setBorettslagnavn(String borettslagnavn) {
      this.borettslagnavn = borettslagnavn;
   }

   public String getBorettslagnummer() {
      return borettslagnummer;
   }

   public void setBorettslagnummer(String borettslagnummer) {
      this.borettslagnummer = borettslagnummer;
   }
}
