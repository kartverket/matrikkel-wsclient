package no.statkart.wsclient.grunnbok.innsending.domene;


/**
 *
 */
public class OmsattRegisterenhetsrett {

   private Kode boligtype;
   private Kode brukstype;
   private Registerenhetsrett registerenhetsrett;
   private OmsatteRegisterenhetsrettsandeler omsatteRegisterenhetsrettsandeler;

   public Kode getBoligtype() {
      return boligtype;
   }

   public void setBoligtype(Kode boligtype) {
      this.boligtype = boligtype;
   }

   public Kode getBrukstype() {
      return brukstype;
   }

   public void setBrukstype(Kode brukstype) {
      this.brukstype = brukstype;
   }

   public OmsatteRegisterenhetsrettsandeler getOmsatteRegisterenhetsrettsandeler() {
      return omsatteRegisterenhetsrettsandeler;
   }

   public void setOmsatteRegisterenhetsrettsandeler(OmsatteRegisterenhetsrettsandeler omsatteRegisterenhetsrettsandeler) {
      this.omsatteRegisterenhetsrettsandeler = omsatteRegisterenhetsrettsandeler;
   }

   public Registerenhetsrett getRegisterenhetsrett() {
      return registerenhetsrett;
   }

   public void setRegisterenhetsrett(Registerenhetsrett registerenhetsrett) {
      this.registerenhetsrett = registerenhetsrett;
   }
}
