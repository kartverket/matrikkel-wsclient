package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Pant extends HeftelseMedBeloep {

   private List<Person> pantsettere = Lists.newArrayList();
   private List<ErklaeringOmSivilstand> erklaeringerOmSivilstand = Lists.newArrayList();

   @Override
   public Rettsstiftelsestype getRettstiftelsestype() {
      return Rettsstiftelsestype.PANT;
   }

   public List<ErklaeringOmSivilstand> getErklaeringerOmSivilstand() {
      return erklaeringerOmSivilstand;
   }

   public void setErklaeringerOmSivilstand(List<ErklaeringOmSivilstand> erklaeringerOmSivilstand) {
      this.erklaeringerOmSivilstand = erklaeringerOmSivilstand;
   }

   public List<Person> getPantsettere() {
      return pantsettere;
   }

   public void setPantsettere(List<Person> pantsettere) {
      this.pantsettere = pantsettere;
   }
}
