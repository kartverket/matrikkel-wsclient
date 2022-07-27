package no.statkart.wsclient.grunnbokv2.innsending.domene;

import java.util.ArrayList;
import java.util.List;

public class Matrikkelenhetsendring extends Registerenhetsendring {

   private List<Matrikkelenhet> fra = new ArrayList<>();
    private List<Matrikkelenhet> til = new ArrayList<>();
    private List<MatrikkelenhetFraTil> omnummereringAvUnderliggende = new ArrayList<>();
    private List<Person> rekvirenterAvForretning = new ArrayList<>();

    public List<MatrikkelenhetFraTil> getOmnummereringAvUnderliggende() {
      return omnummereringAvUnderliggende;
   }

   public void setOmnummereringAvUnderliggende(List<MatrikkelenhetFraTil> omnummereringAvUnderliggende) {
      this.omnummereringAvUnderliggende = omnummereringAvUnderliggende;
   }

   private TypeMatrikkelenhetsendring typeMatrikkelenhetsendring;

   public enum TypeMatrikkelenhetsendring {
      FRADELING, SAMMENSLAAING_AV_MATRIKKELENHETER, FESTENUMMER_GITT_BRUKSNUMMER
      , OPPRETT_FESTEGRUNN             // for EtablerFesteForretning
      , OMNUMMERER_MATRIKKELENHETER      // for OmnummereringForretning
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

   public List<Person> getRekvirenterAvForretning() {
      return rekvirenterAvForretning;
   }

   public void setRekvirenterAvForretning(List<Person> rekvirenterAvForretning) {
      this.rekvirenterAvForretning = rekvirenterAvForretning;
   }
}
