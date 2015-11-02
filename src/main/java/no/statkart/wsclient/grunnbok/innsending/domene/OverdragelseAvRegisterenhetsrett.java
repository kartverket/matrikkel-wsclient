package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class OverdragelseAvRegisterenhetsrett extends Rettsstiftelse {

   private Omsetning omsetning;
   private List<ErklaeringOmSivilstand> erklaeringerOmSivilstand = Lists.newArrayList();

   private TypeOverdragelseAvRegisterenhetsrett typeOverdragelseAvRegisterenhetsrett;

   public enum TypeOverdragelseAvRegisterenhetsrett {
      EIERSKIFTE_MATRIKKELENHET, OVERDRAGELSE_AV_FESTERETT, EIERSKIFTE_BORETTSLAGSANDEL
   }

   public OverdragelseAvRegisterenhetsrett(TypeOverdragelseAvRegisterenhetsrett typeOverdragelseAvRegisterenhetsrett) {
      this.typeOverdragelseAvRegisterenhetsrett = typeOverdragelseAvRegisterenhetsrett;
   }

   public TypeOverdragelseAvRegisterenhetsrett getTypeOverdragelseAvRegisterenhetsrett() {
      return typeOverdragelseAvRegisterenhetsrett;
   }

   public void setTypeOverdragelseAvRegisterenhetsrett(TypeOverdragelseAvRegisterenhetsrett typeOverdragelseAvRegisterenhetsrett) {
      this.typeOverdragelseAvRegisterenhetsrett = typeOverdragelseAvRegisterenhetsrett;
   }

   public List<ErklaeringOmSivilstand> getErklaeringerOmSivilstand() {
      return erklaeringerOmSivilstand;
   }

   public void setErklaeringerOmSivilstand(List<ErklaeringOmSivilstand> erklaeringerOmSivilstand) {
      this.erklaeringerOmSivilstand = erklaeringerOmSivilstand;
   }

   public Omsetning getOmsetning() {
      return omsetning;
   }

   public void setOmsetning(Omsetning omsetning) {
      this.omsetning = omsetning;
   }

   @Override
   public Rettsstiftelsestype getRettstiftelsestype() {
      return Rettsstiftelsestype.OVERDRAGELSE_AV_REGISTERENHETSRETT;
   }
}
