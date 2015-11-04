package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.Beloep;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;

/**
 *
 */
public class BeloepBuilder {
   private String beloepstekst;
   private long beloepsverdi;
   private Kode valutakode;

   private BeloepBuilder() {
   }

   public static BeloepBuilder aBeloep() {
      return new BeloepBuilder();
   }

   public BeloepBuilder withBeloepstekst(String beloepstekst) {
      this.beloepstekst = beloepstekst;
      return this;
   }

   public BeloepBuilder withBeloepsverdi(long beloepsverdi) {
      this.beloepsverdi = beloepsverdi;
      return this;
   }

   public BeloepBuilder withValutakode(Kode valutakode) {
      this.valutakode = valutakode;
      return this;
   }

   public BeloepBuilder but() {
      return aBeloep().withBeloepstekst(beloepstekst).withBeloepsverdi(beloepsverdi).withValutakode(valutakode);
   }

   public Beloep build() {
      Beloep beloep = new Beloep();
      beloep.setBeloepstekst(beloepstekst);
      beloep.setBeloepsverdi(beloepsverdi);
      beloep.setValutakode(valutakode);
      return beloep;
   }
}
