package no.statkart.wsclient.grunnbok.innsending.domene;

/**
 *
 */
public abstract class Anmerkning extends Rettsstiftelse {

   private String saksnummer;

   public String getSaksnummer() {
      return saksnummer;
   }

   public void setSaksnummer(String saksnummer) {
      this.saksnummer = saksnummer;
   }
}
