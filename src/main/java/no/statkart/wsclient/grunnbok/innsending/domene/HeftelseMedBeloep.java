package no.statkart.wsclient.grunnbok.innsending.domene;


import java.util.List;

/**
 *
 */
public abstract class HeftelseMedBeloep extends HeftelseIRegisterenhetsrett {

   private List<Beloep> beloep;

   public List<Beloep> getBeloep() {
      return beloep;
   }

   public void setBeloep(List<Beloep> beloep) {
      this.beloep = beloep;
   }
}
