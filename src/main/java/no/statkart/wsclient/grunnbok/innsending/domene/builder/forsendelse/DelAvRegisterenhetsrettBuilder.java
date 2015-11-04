package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrettsandel;

import java.util.List;

/**
 *
 */
public class DelAvRegisterenhetsrettBuilder {
   private List<Registerenhetsrettsandel> begrensetTil;
   private Registerenhetsrett registerenhetsrett;

   private DelAvRegisterenhetsrettBuilder() {
   }

   public static DelAvRegisterenhetsrettBuilder aDelAvRegisterenhetsrett() {
      return new DelAvRegisterenhetsrettBuilder();
   }

   public DelAvRegisterenhetsrettBuilder withBegrensetTil(List<Registerenhetsrettsandel> begrensetTil) {
      this.begrensetTil = begrensetTil;
      return this;
   }

   public DelAvRegisterenhetsrettBuilder withRegisterenhetsrett(Registerenhetsrett registerenhetsrett) {
      this.registerenhetsrett = registerenhetsrett;
      return this;
   }

   public DelAvRegisterenhetsrettBuilder but() {
      return aDelAvRegisterenhetsrett().withBegrensetTil(begrensetTil).withRegisterenhetsrett(registerenhetsrett);
   }

   public DelAvRegisterenhetsrett build() {
      DelAvRegisterenhetsrett delAvRegisterenhetsrett = new DelAvRegisterenhetsrett();
      delAvRegisterenhetsrett.setBegrensetTil(begrensetTil);
      delAvRegisterenhetsrett.setRegisterenhetsrett(registerenhetsrett);
      return delAvRegisterenhetsrett;
   }
}
