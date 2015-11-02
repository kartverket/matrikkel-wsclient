package no.statkart.wsclient.grunnbok.innsending.domene;


/**
 *
 */
public class Registerenhetsrettsandel {

   private long teller;
   private long nevner;
   private Person rettighetshaver;
   private Registerenhet realkobletTil;

   public long getNevner() {
      return nevner;
   }

   public void setNevner(long nevner) {
      this.nevner = nevner;
   }

   public Person getRettighetshaver() {
      return rettighetshaver;
   }

   public void setRettighetshaver(Person rettighetshaver) {
      this.rettighetshaver = rettighetshaver;
   }

   public Registerenhet getRealkobletTil() {
      return realkobletTil;
   }

   public void setRealkobletTil(Registerenhet realkobletTil) {
      this.realkobletTil = realkobletTil;
   }

   public long getTeller() {
      return teller;
   }

   public void setTeller(long teller) {
      this.teller = teller;
   }
}
