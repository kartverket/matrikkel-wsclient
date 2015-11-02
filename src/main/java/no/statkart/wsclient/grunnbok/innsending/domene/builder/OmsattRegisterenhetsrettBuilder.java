package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.OmsattRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.OmsatteRegisterenhetsrettsandeler;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrett;

/**
 *
 */
public class OmsattRegisterenhetsrettBuilder {
   private Kode boligtype;
   private Kode brukstype;
   private Registerenhetsrett registerenhetsrett;
   private OmsatteRegisterenhetsrettsandeler omsatteRegisterenhetsrettsandeler;

   private OmsattRegisterenhetsrettBuilder() {
   }

   public static OmsattRegisterenhetsrettBuilder anOmsattRegisterenhetsrett() {
      return new OmsattRegisterenhetsrettBuilder();
   }

   public OmsattRegisterenhetsrettBuilder withBoligtype(Kode boligtype) {
      this.boligtype = boligtype;
      return this;
   }

   public OmsattRegisterenhetsrettBuilder withBrukstype(Kode brukstype) {
      this.brukstype = brukstype;
      return this;
   }

   public OmsattRegisterenhetsrettBuilder withRegisterenhetsrett(Registerenhetsrett registerenhetsrett) {
      this.registerenhetsrett = registerenhetsrett;
      return this;
   }

   public OmsattRegisterenhetsrettBuilder withOmsatteRegisterenhetsrettsandeler(OmsatteRegisterenhetsrettsandeler omsatteRegisterenhetsrettsandeler) {
      this.omsatteRegisterenhetsrettsandeler = omsatteRegisterenhetsrettsandeler;
      return this;
   }

   public OmsattRegisterenhetsrettBuilder but() {
      return anOmsattRegisterenhetsrett().withBoligtype(boligtype).withBrukstype(brukstype).withRegisterenhetsrett(registerenhetsrett).withOmsatteRegisterenhetsrettsandeler(omsatteRegisterenhetsrettsandeler);
   }

   public OmsattRegisterenhetsrett build() {
      OmsattRegisterenhetsrett omsattRegisterenhetsrett = new OmsattRegisterenhetsrett();
      omsattRegisterenhetsrett.setBoligtype(boligtype);
      omsattRegisterenhetsrett.setBrukstype(brukstype);
      omsattRegisterenhetsrett.setRegisterenhetsrett(registerenhetsrett);
      omsattRegisterenhetsrett.setOmsatteRegisterenhetsrettsandeler(omsatteRegisterenhetsrettsandeler);
      return omsattRegisterenhetsrett;
   }
}
