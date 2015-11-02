package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import no.statkart.wsclient.grunnbok.innsending.domene.Beloep;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokumentavgiftsplikt;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;

/**
 *
 */
public class DokumentavgiftspliktBuilder {
   private Kode dokumentavgiftsaarsak;
   private Beloep dokumentavgiftsgrunnlag;

   private DokumentavgiftspliktBuilder() {
   }

   public static DokumentavgiftspliktBuilder aDokumentavgiftsplikt() {
      return new DokumentavgiftspliktBuilder();
   }

   public DokumentavgiftspliktBuilder withDokumentavgiftsaarsak(Kode dokumentavgiftsaarsak) {
      this.dokumentavgiftsaarsak = dokumentavgiftsaarsak;
      return this;
   }

   public DokumentavgiftspliktBuilder withDokumentavgiftsgrunnlag(Beloep dokumentavgiftsgrunnlag) {
      this.dokumentavgiftsgrunnlag = dokumentavgiftsgrunnlag;
      return this;
   }

   public DokumentavgiftspliktBuilder but() {
      return aDokumentavgiftsplikt().withDokumentavgiftsaarsak(dokumentavgiftsaarsak).withDokumentavgiftsgrunnlag(dokumentavgiftsgrunnlag);
   }

   public Dokumentavgiftsplikt build() {
      Dokumentavgiftsplikt dokumentavgiftsplikt = new Dokumentavgiftsplikt();
      dokumentavgiftsplikt.setDokumentavgiftsaarsak(dokumentavgiftsaarsak);
      dokumentavgiftsplikt.setDokumentavgiftsgrunnlag(dokumentavgiftsgrunnlag);
      return dokumentavgiftsplikt;
   }
}
