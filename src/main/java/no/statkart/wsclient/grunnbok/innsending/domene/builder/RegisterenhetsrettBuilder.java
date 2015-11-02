package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrett;

/**
 *
 */
public class RegisterenhetsrettBuilder {
   private Registerenhet registerenhet;
   private Kode registerenhetsrettstype;

   private RegisterenhetsrettBuilder() {
   }

   public static RegisterenhetsrettBuilder aRegisterenhetsrett() {
      return new RegisterenhetsrettBuilder();
   }

   public RegisterenhetsrettBuilder withRegisterenhet(Registerenhet registerenhet) {
      this.registerenhet = registerenhet;
      return this;
   }

   public RegisterenhetsrettBuilder withRegisterenhetsrettstype(Kode registerenhetsrettstype) {
      this.registerenhetsrettstype = registerenhetsrettstype;
      return this;
   }

   public RegisterenhetsrettBuilder but() {
      return aRegisterenhetsrett().withRegisterenhet(registerenhet).withRegisterenhetsrettstype(registerenhetsrettstype);
   }

   public Registerenhetsrett build() {
      Registerenhetsrett registerenhetsrett = new Registerenhetsrett();
      registerenhetsrett.setRegisterenhet(registerenhet);
      registerenhetsrett.setRegisterenhetsrettstype(registerenhetsrettstype);
      return registerenhetsrett;
   }
}
