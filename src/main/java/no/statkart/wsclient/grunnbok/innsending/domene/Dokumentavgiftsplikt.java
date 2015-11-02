package no.statkart.wsclient.grunnbok.innsending.domene;


/**
 *
 */
public class Dokumentavgiftsplikt {

   private Beloep dokumentavgiftsgrunnlag;
   private Kode dokumentavgiftsaarsak;

   public Kode getDokumentavgiftsaarsak() {
      return dokumentavgiftsaarsak;
   }

   public void setDokumentavgiftsaarsak(Kode dokumentavgiftsaarsak) {
      this.dokumentavgiftsaarsak = dokumentavgiftsaarsak;
   }

   public Beloep getDokumentavgiftsgrunnlag() {
      return dokumentavgiftsgrunnlag;
   }

   public void setDokumentavgiftsgrunnlag(Beloep dokumentavgiftsgrunnlag) {
      this.dokumentavgiftsgrunnlag = dokumentavgiftsgrunnlag;
   }
}
