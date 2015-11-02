package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class DelAvRett {

   private RettsstiftelseReferanse rettsstiftelse;
   private List<DelAvRegisterenhetsrett> begrensetTil = Lists.newArrayList();

   public List<DelAvRegisterenhetsrett> getBegrensetTil() {
      return begrensetTil;
   }

   public void setBegrensetTil(List<DelAvRegisterenhetsrett> begrensetTil) {
      this.begrensetTil = begrensetTil;
   }

   public RettsstiftelseReferanse getRettsstiftelse() {
      return rettsstiftelse;
   }

   public void setRettsstiftelse(RettsstiftelseReferanse rettsstiftelse) {
      this.rettsstiftelse = rettsstiftelse;
   }
}
