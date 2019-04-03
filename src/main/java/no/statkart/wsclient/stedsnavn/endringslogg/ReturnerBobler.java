package no.statkart.wsclient.stedsnavn.endringslogg;

public enum ReturnerBobler {

   ALDRI("Aldri"),
   ALLTID("Alltid"),
   VED_SLUTT("VedSlutt");
   private final String kodeverdi;

   ReturnerBobler(String kodeverdi) {
      this.kodeverdi = kodeverdi;
   }

   public String getKodeverdi() {
      return kodeverdi;
   }
}
