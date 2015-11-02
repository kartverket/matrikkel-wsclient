package no.statkart.wsclient.grunnbok.innsending.domene;

import com.google.common.collect.Lists;

import java.util.List;

public class Matrikkelenhetsendring extends Registerenhetsendring {

   private List<Matrikkelenhet> fra = Lists.newArrayList();
   private List<Matrikkelenhet> til = Lists.newArrayList();
   private List<MatrikkelenhetFraTil> omnummereringAvUnderliggende = Lists.newArrayList();

   public List<MatrikkelenhetFraTil> getOmnummereringAvUnderliggende() {
      return omnummereringAvUnderliggende;
   }

   public void setOmnummereringAvUnderliggende(List<MatrikkelenhetFraTil> omnummereringAvUnderliggende) {
      this.omnummereringAvUnderliggende = omnummereringAvUnderliggende;
   }

   private TypeMatrikkelenhetsendring typeMatrikkelenhetsendring;

   public enum TypeMatrikkelenhetsendring {
      FRADELING, SAMMENSLAAING_AV_MATRIKKELENHETER, OVERFOERING_FRA_TIDLIGERE_FESTENUMMER
   }

   public Matrikkelenhetsendring(TypeMatrikkelenhetsendring typeMatrikkelenhetsendring) {
      this.typeMatrikkelenhetsendring = typeMatrikkelenhetsendring;
   }

   @Override
   public Rettsstiftelsestype getRettstiftelsestype() {
      return Rettsstiftelsestype.MATRIKKELENHETSENDRING;
   }

   public TypeMatrikkelenhetsendring getTypeMatrikkelenhetsendring() {
      return typeMatrikkelenhetsendring;
   }

   public void setTypeMatrikkelenhetsendring(TypeMatrikkelenhetsendring typeMatrikkelenhetsendring) {
      this.typeMatrikkelenhetsendring = typeMatrikkelenhetsendring;
   }

   public List<Matrikkelenhet> getFra() {
      return fra;
   }

   public void setFra(List<Matrikkelenhet> fra) {
      this.fra = fra;
   }

   public List<Matrikkelenhet> getTil() {
      return til;
   }

   public void setTil(List<Matrikkelenhet> til) {
      this.til = til;
   }
}
