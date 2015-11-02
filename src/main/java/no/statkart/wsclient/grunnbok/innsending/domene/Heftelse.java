package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public abstract class Heftelse extends Rettsstiftelse {

   private List<Person> rettighetshavere = Lists.newArrayList();
   private List<Registerenhetsrett> realkobletTil = Lists.newArrayList();

   public List<Person> getRettighetshavere() {
      return rettighetshavere;
   }

   public void setRettighetshavere(List<Person> rettighetshavere) {
      this.rettighetshavere = rettighetshavere;
   }

   public List<Registerenhetsrett> getRealkobletTil() {
      return realkobletTil;
   }

   public void setRealkobletTil(List<Registerenhetsrett> realkobletTil) {
      this.realkobletTil = realkobletTil;
   }
}
