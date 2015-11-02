package no.statkart.wsclient.grunnbok.innsending.domene;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public abstract class HeftelseIRegisterenhetsrett extends Heftelse {

   private List<DelAvRegisterenhetsrett> hefterI = Lists.newArrayList();

   public List<DelAvRegisterenhetsrett> getHefterI() {
      return hefterI;
   }

   public void setHefterI(List<DelAvRegisterenhetsrett> hefterI) {
      this.hefterI = hefterI;
   }
}
