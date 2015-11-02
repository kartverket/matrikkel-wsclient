package no.statkart.wsclient.grunnbok.innsending.domene;


public class Registerenhetsrett {

   private Kode registerenhetsrettstype;
   private Registerenhet registerenhet;

   public Registerenhet getRegisterenhet() {
      return registerenhet;
   }

   public void setRegisterenhet(Registerenhet registerenhet) {
      this.registerenhet = registerenhet;
   }

   public Kode getRegisterenhetsrettstype() {
      return registerenhetsrettstype;
   }

   public void setRegisterenhetsrettstype(Kode registerenhetsrettstype) {
      this.registerenhetsrettstype = registerenhetsrettstype;
   }
}
