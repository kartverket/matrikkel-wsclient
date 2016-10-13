package no.statkart.wsclient.grunnbokv2.innsending.domene;

/**
 *
 */
public class Begrunnelse {

   private String kodeverdi;
   private String tekst;
   private String elementnavn;
   private String utfall;

   public String getElementnavn() {
      return elementnavn;
   }

   public void setElementnavn(String elementnavn) {
      this.elementnavn = elementnavn;
   }

   public String getKodeverdi() {
      return kodeverdi;
   }

   public void setKodeverdi(String kodeverdi) {
      this.kodeverdi = kodeverdi;
   }

   public String getTekst() {
      return tekst;
   }

   public void setTekst(String tekst) {
      this.tekst = tekst;
   }

   public String getUtfall() {
      return utfall;
   }

   public void setUtfall(String utfall) {
      this.utfall = utfall;
   }
}
