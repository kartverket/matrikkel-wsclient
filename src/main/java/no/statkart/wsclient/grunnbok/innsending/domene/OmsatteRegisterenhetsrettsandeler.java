package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class OmsatteRegisterenhetsrettsandeler {

   private List<Registerenhetsrettsandel> kjoepte = Lists.newArrayList();
   private List<Registerenhetsrettsandel> solgte = Lists.newArrayList();

   public List<Registerenhetsrettsandel> getKjoepte() {
      return kjoepte;
   }

   public void setKjoepte(List<Registerenhetsrettsandel> kjoepte) {
      this.kjoepte = kjoepte;
   }

   public List<Registerenhetsrettsandel> getSolgte() {
      return solgte;
   }

   public void setSolgte(List<Registerenhetsrettsandel> solgte) {
      this.solgte = solgte;
   }
}
