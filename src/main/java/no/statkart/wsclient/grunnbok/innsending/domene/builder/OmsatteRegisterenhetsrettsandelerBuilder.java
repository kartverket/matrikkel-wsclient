package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.OmsatteRegisterenhetsrettsandeler;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrettsandel;

import java.util.List;

/**
 *
 */
public class OmsatteRegisterenhetsrettsandelerBuilder {
   private List<Registerenhetsrettsandel> kjoepte = Lists.newArrayList();
   private List<Registerenhetsrettsandel> solgte = Lists.newArrayList();

   private OmsatteRegisterenhetsrettsandelerBuilder() {
   }

   public static OmsatteRegisterenhetsrettsandelerBuilder anOmsatteRegisterenhetsrettsandeler() {
      return new OmsatteRegisterenhetsrettsandelerBuilder();
   }

   public OmsatteRegisterenhetsrettsandelerBuilder withKjoepte(List<Registerenhetsrettsandel> kjoepte) {
      this.kjoepte = kjoepte;
      return this;
   }

   public OmsatteRegisterenhetsrettsandelerBuilder withSolgte(List<Registerenhetsrettsandel> solgte) {
      this.solgte = solgte;
      return this;
   }

   public OmsatteRegisterenhetsrettsandelerBuilder but() {
      return anOmsatteRegisterenhetsrettsandeler().withKjoepte(kjoepte).withSolgte(solgte);
   }

   public OmsatteRegisterenhetsrettsandeler build() {
      OmsatteRegisterenhetsrettsandeler omsatteRegisterenhetsrettsandeler = new OmsatteRegisterenhetsrettsandeler();
      omsatteRegisterenhetsrettsandeler.setKjoepte(kjoepte);
      omsatteRegisterenhetsrettsandeler.setSolgte(solgte);
      return omsatteRegisterenhetsrettsandeler;
   }
}
