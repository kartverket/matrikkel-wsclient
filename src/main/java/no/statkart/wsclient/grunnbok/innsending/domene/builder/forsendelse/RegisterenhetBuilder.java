package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.Borettslagsandel;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhet;

/**
 *
 */
public class RegisterenhetBuilder {
   private Borettslagsandel borettslagsandel;
   private Matrikkelenhet matrikkelenhet;

   private RegisterenhetBuilder() {
   }

   public static RegisterenhetBuilder aRegisterenhet() {
      return new RegisterenhetBuilder();
   }

   public RegisterenhetBuilder withBorettslagsandel(Borettslagsandel borettslagsandel) {
      this.borettslagsandel = borettslagsandel;
      return this;
   }

   public RegisterenhetBuilder withMatrikkelenhet(Matrikkelenhet matrikkelenhet) {
      this.matrikkelenhet = matrikkelenhet;
      return this;
   }

   public RegisterenhetBuilder but() {
      return aRegisterenhet().withBorettslagsandel(borettslagsandel).withMatrikkelenhet(matrikkelenhet);
   }

   public Registerenhet build() {
      Registerenhet registerenhet = new Registerenhet();
      registerenhet.setBorettslagsandel(borettslagsandel);
      registerenhet.setMatrikkelenhet(matrikkelenhet);
      return registerenhet;
   }
}
