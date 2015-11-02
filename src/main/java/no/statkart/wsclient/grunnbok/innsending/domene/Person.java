package no.statkart.wsclient.grunnbok.innsending.domene;


public class Person {

   private String navn;
   private String identifikasjonsnummer;

   public String getIdentifikasjonsnummer() {
      return identifikasjonsnummer;
   }

   public void setIdentifikasjonsnummer(String identifikasjonsnummer) {
      this.identifikasjonsnummer = identifikasjonsnummer;
   }

   public String getNavn() {
      return navn;
   }

   public void setNavn(String navn) {
      this.navn = navn;
   }
}
