package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public class Forsendelse {

   private String forsendelsesreferanse;
   private SignertMelding signertMelding;
   private UsignertMelding usignertMelding;
   private List<Signatur> ikkeDigitaleSignaturer = Lists.newArrayList();

   public void setIkkeDigitaleSignaturer(List<Signatur> ikkeDigitaleSignaturer) {
      this.ikkeDigitaleSignaturer = ikkeDigitaleSignaturer;
   }

   public List<Signatur> getIkkeDigitaleSignaturer() {
      return ikkeDigitaleSignaturer;
   }

   public String getForsendelsesreferanse() {
      return forsendelsesreferanse;
   }

   public void setForsendelsesreferanse(String forsendelsesreferanse) {
      this.forsendelsesreferanse = forsendelsesreferanse;
   }

   public SignertMelding getSignertMelding() {
      return signertMelding;
   }

   public void setSignertMelding(SignertMelding signertMelding) {
      this.signertMelding = signertMelding;
   }

   public UsignertMelding getUsignertMelding() {
      return usignertMelding;
   }

   public void setUsignertMelding(UsignertMelding usignertMelding) {
      this.usignertMelding = usignertMelding;
   }

}
