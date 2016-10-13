package no.statkart.wsclient.grunnbokv2.innsending.domene;

/**
 *
 */
public class Tekst {

   private Kode teksttype;
   private String fritekst;

   public String getFritekst() {
      return fritekst;
   }

   public void setFritekst(String fritekst) {
      this.fritekst = fritekst;
   }

   public Kode getTeksttype() {
      return teksttype;
   }

   public void setTeksttype(Kode teksttype) {
      this.teksttype = teksttype;
   }
}
