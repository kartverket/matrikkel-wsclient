package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRett;
import no.statkart.wsclient.grunnbok.innsending.domene.RettsstiftelseReferanse;

import java.util.List;

/**
 *
 */
public class DelAvRettBuilder {
   private List<DelAvRegisterenhetsrett> begrensetTil = Lists.newArrayList();
   private RettsstiftelseReferanse rettsstiftelse;

   private DelAvRettBuilder() {
   }

   public static DelAvRettBuilder aDelAvRett() {
      return new DelAvRettBuilder();
   }

   public DelAvRettBuilder withBegrensetTil(List<DelAvRegisterenhetsrett> begrensetTil) {
      this.begrensetTil = begrensetTil;
      return this;
   }

   public DelAvRettBuilder withRettsstiftelse(RettsstiftelseReferanse rettsstiftelse) {
      this.rettsstiftelse = rettsstiftelse;
      return this;
   }

   public DelAvRettBuilder but() {
      return aDelAvRett().withBegrensetTil(begrensetTil).withRettsstiftelse(rettsstiftelse);
   }

   public DelAvRett build() {
      DelAvRett delAvRett = new DelAvRett();
      delAvRett.setBegrensetTil(begrensetTil);
      delAvRett.setRettsstiftelse(rettsstiftelse);
      return delAvRett;
   }
}
