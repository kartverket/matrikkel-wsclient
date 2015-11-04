package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.Person;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrettsandel;

/**
 *
 */
public class RegisterenhetsrettsandelBuilder {
   private long nevner;
   private long teller;
   private Person rettighetshaver;
   private Registerenhet realkobletTil;

   private RegisterenhetsrettsandelBuilder() {
   }

   public static RegisterenhetsrettsandelBuilder aRegisterenhetsrettsandel() {
      return new RegisterenhetsrettsandelBuilder();
   }

   public RegisterenhetsrettsandelBuilder withNevner(long nevner) {
      this.nevner = nevner;
      return this;
   }

   public RegisterenhetsrettsandelBuilder withTeller(long teller) {
      this.teller = teller;
      return this;
   }

   public RegisterenhetsrettsandelBuilder withRettighetshaver(Person rettighetshaverPerson) {
      this.rettighetshaver = rettighetshaverPerson;
      return this;
   }

   public RegisterenhetsrettsandelBuilder withRealkobletTil(Registerenhet rettighetshaverRegisterenhet) {
      this.realkobletTil = rettighetshaverRegisterenhet;
      return this;
   }

   public RegisterenhetsrettsandelBuilder but() {
      return aRegisterenhetsrettsandel().withNevner(nevner).withTeller(teller).withRettighetshaver(rettighetshaver).withRealkobletTil(realkobletTil);
   }

   public Registerenhetsrettsandel build() {
      Registerenhetsrettsandel registerenhetsrettsandel = new Registerenhetsrettsandel();
      registerenhetsrettsandel.setNevner(nevner);
      registerenhetsrettsandel.setTeller(teller);
      registerenhetsrettsandel.setRettighetshaver(rettighetshaver);
      registerenhetsrettsandel.setRealkobletTil(realkobletTil);
      return registerenhetsrettsandel;
   }
}
