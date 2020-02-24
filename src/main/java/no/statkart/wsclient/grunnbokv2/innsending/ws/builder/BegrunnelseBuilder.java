package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Begrunnelse;

public class BegrunnelseBuilder {
   protected String elementnavn;
   protected String kodeverdi;
   protected String tekst;
   protected String utfall;

   private BegrunnelseBuilder() {
   }

   public static BegrunnelseBuilder aBegrunnelse() {
      return new BegrunnelseBuilder();
   }

   public BegrunnelseBuilder withElementnavn(String elementnavn) {
      this.elementnavn = elementnavn;
      return this;
   }

   public BegrunnelseBuilder withKodeverdi(String kodeverdi) {
      this.kodeverdi = kodeverdi;
      return this;
   }

   public BegrunnelseBuilder withTekst(String tekst) {
      this.tekst = tekst;
      return this;
   }

   public BegrunnelseBuilder withUtfall(String utfall) {
      this.utfall = utfall;
      return this;
   }

   public BegrunnelseBuilder but() {
      return aBegrunnelse().withElementnavn(elementnavn).withKodeverdi(kodeverdi).withTekst(tekst);
   }

   public Begrunnelse build() {
      Begrunnelse begrunnelse = new Begrunnelse();
      begrunnelse.setElementnavn(elementnavn);
      begrunnelse.setKodeverdi(kodeverdi);
      begrunnelse.setTekst(tekst);
      begrunnelse.setUtfall(utfall);
      return begrunnelse;
   }
}
