package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.MatrikkelenhetFraTil;

/**
 *
 */
public class MatrikkelenhetFraTilBuilder {
   private Matrikkelenhet fra;
   private Matrikkelenhet til;

   private MatrikkelenhetFraTilBuilder() {
   }

   public static MatrikkelenhetFraTilBuilder aMatrikkelenhetFraTil() {
      return new MatrikkelenhetFraTilBuilder();
   }

   public MatrikkelenhetFraTilBuilder withFra(Matrikkelenhet fra) {
      this.fra = fra;
      return this;
   }

   public MatrikkelenhetFraTilBuilder withTil(Matrikkelenhet til) {
      this.til = til;
      return this;
   }

   public MatrikkelenhetFraTilBuilder but() {
      return aMatrikkelenhetFraTil().withFra(fra).withTil(til);
   }

   public MatrikkelenhetFraTil build() {
      MatrikkelenhetFraTil matrikkelenhetFraTil = new MatrikkelenhetFraTil();
      matrikkelenhetFraTil.setFra(fra);
      matrikkelenhetFraTil.setTil(til);
      return matrikkelenhetFraTil;
   }
}
