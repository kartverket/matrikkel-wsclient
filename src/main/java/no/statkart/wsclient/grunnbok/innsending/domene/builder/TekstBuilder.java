package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;

/**
 *
 */
public class TekstBuilder {
   private String fritekst;
   private Kode teksttype;

   private TekstBuilder() {
   }

   public static TekstBuilder aTekst() {
      return new TekstBuilder();
   }

   public TekstBuilder withFritekst(String fritekst) {
      this.fritekst = fritekst;
      return this;
   }

   public TekstBuilder withTeksttype(Kode teksttype) {
      this.teksttype = teksttype;
      return this;
   }

   public TekstBuilder but() {
      return aTekst().withFritekst(fritekst).withTeksttype(teksttype);
   }

   public Tekst build() {
      Tekst tekst = new Tekst();
      tekst.setFritekst(fritekst);
      tekst.setTeksttype(teksttype);
      return tekst;
   }
}
