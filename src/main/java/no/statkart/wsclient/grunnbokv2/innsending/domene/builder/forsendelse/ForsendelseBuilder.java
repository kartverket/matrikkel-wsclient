package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Signatur;
import no.statkart.wsclient.grunnbokv2.innsending.domene.SignertMelding;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertMelding;

import java.util.List;

/**
 *
 */
public class ForsendelseBuilder {

   private List<Signatur> ikkeDigitaleSignaturer = Lists.newArrayList();
   private String forsendelsesreferanse;
   private SignertMelding signertMelding;
   private UsignertMelding usignertMelding;

   private ForsendelseBuilder() {
   }

   public static ForsendelseBuilder aForsendelse() {
      return new ForsendelseBuilder();
   }

   public ForsendelseBuilder withIkkeDigitaleSignaturer(List<Signatur> ikkeDigitaleSignaturer) {
      this.ikkeDigitaleSignaturer = ikkeDigitaleSignaturer;
      return this;
   }

   public ForsendelseBuilder withForsendelsesReferanse(String forsendelsesreferanse) {
      this.forsendelsesreferanse = forsendelsesreferanse;
      return this;
   }

   public ForsendelseBuilder withSignertMelding(SignertMelding signertMelding) {
      this.signertMelding = signertMelding;
      return this;
   }

   public ForsendelseBuilder withUsignertMelding(UsignertMelding usignertMelding) {
      this.usignertMelding = usignertMelding;
      return this;
   }

   public ForsendelseBuilder but() {
      return aForsendelse().withIkkeDigitaleSignaturer(ikkeDigitaleSignaturer).withForsendelsesReferanse(forsendelsesreferanse).withSignertMelding(signertMelding).withUsignertMelding(usignertMelding);
   }

   public Forsendelse build() {
      Forsendelse forsendelse = new Forsendelse();
      forsendelse.setIkkeDigitaleSignaturer(ikkeDigitaleSignaturer);
      forsendelse.setForsendelsesreferanse(forsendelsesreferanse);
      forsendelse.setSignertMelding(signertMelding);
      forsendelse.setUsignertMelding(usignertMelding);
      return forsendelse;
   }

}
