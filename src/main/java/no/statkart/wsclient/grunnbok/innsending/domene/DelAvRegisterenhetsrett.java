package no.statkart.wsclient.grunnbok.innsending.domene;


import java.util.List;

/**
 *
 */
public class DelAvRegisterenhetsrett {

   private Registerenhetsrett registerenhetsrett;
   private List<Registerenhetsrettsandel> begrensetTil;

   public List<Registerenhetsrettsandel> getBegrensetTil() {
      return begrensetTil;
   }

   public void setBegrensetTil(List<Registerenhetsrettsandel> begrensetTil) {
      this.begrensetTil = begrensetTil;
   }

   public Registerenhetsrett getRegisterenhetsrett() {
      return registerenhetsrett;
   }

   public void setRegisterenhetsrett(Registerenhetsrett registerenhetsrett) {
      this.registerenhetsrett = registerenhetsrett;
   }
}
