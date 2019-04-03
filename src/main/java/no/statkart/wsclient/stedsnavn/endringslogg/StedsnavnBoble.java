package no.statkart.wsclient.stedsnavn.endringslogg;

public class StedsnavnBoble {

   private StedsnavnBobleId id;

   public StedsnavnBoble(StedsnavnBobleId id) {
      this.id = id;
   }

   public StedsnavnBobleId getId() {
      return id;
   }
}
