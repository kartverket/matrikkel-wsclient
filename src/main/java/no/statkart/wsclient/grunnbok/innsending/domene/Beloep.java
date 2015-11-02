package no.statkart.wsclient.grunnbok.innsending.domene;

/**
 *
 */
public class Beloep {

   private long beloepsverdi;
   private Kode valutakode;
   private String beloepstekst;

   public String getBeloepstekst() {
      return beloepstekst;
   }

   public void setBeloepstekst(String beloepstekst) {
      this.beloepstekst = beloepstekst;
   }

   public long getBeloepsverdi() {
      return beloepsverdi;
   }

   public void setBeloepsverdi(long beloepsverdi) {
      this.beloepsverdi = beloepsverdi;
   }

   public Kode getValutakode() {
      return valutakode;
   }

   public void setValutakode(Kode valutakode) {
      this.valutakode = valutakode;
   }
}
