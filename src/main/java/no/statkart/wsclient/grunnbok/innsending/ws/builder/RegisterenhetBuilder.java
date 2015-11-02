package no.statkart.wsclient.grunnbok.innsending.ws.builder;


import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Borettslagsandel;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhet;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhet;

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
