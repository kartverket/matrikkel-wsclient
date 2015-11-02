package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public class Forsendelse {

   private String innsendingId;
   private String innsendersReferanse;
   private SignertMelding signertMelding;
   private UsignertMelding usignertMelding;
   private List<Signatur> ikkeDigitaleSignaturer = Lists.newArrayList();

   public void setIkkeDigitaleSignaturer(List<Signatur> ikkeDigitaleSignaturer) {
      this.ikkeDigitaleSignaturer = ikkeDigitaleSignaturer;
   }

   public List<Signatur> getIkkeDigitaleSignaturer() {
      return ikkeDigitaleSignaturer;
   }

   public String getInnsendersReferanse() {
      return innsendersReferanse;
   }

   public void setInnsendersReferanse(String innsendersReferanse) {
      this.innsendersReferanse = innsendersReferanse;
   }

   public String getInnsendingId() {
      return innsendingId;
   }

   public void setInnsendingId(String innsendingId) {
      this.innsendingId = innsendingId;
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
