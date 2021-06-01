package no.statkart.wsclient.grunnbokv2.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public class Forsendelse {

   private String forsendelsesreferanse;
   // MAT-18455 Flagget må være false inntil MAT-18459 henter utskrift fra link
   private final Boolean linkTilUtskrifter = false;
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

   public Boolean getLinkTilUtskrifter() {
        return linkTilUtskrifter;
   }

   public void setLinkTilUtskrifter(Boolean linkTilUtskrifter) {
   // Trenger setter for mapping, men flagget skal alltid være true
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
