package no.statkart.wsclient.grunnbok.innsending.domene;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public abstract class Rettsendring extends Rettsstiftelse {

   private List<DelAvRett> endrer = Lists.newArrayList();

   public List<DelAvRett> getEndrer() {
      return endrer;
   }

   public void setEndrer(List<DelAvRett> endrer) {
      this.endrer = endrer;
   }
}
