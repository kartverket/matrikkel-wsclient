package no.statkart.wsclient.grunnbok.innsending.domene;


public class AnmerkningPaaPerson extends Anmerkning {

   private Person anmerketPerson;
   private Person konkursbo;
   private Person bostyrer;

   @Override
   public Rettsstiftelsestype getRettstiftelsestype() {
      return Rettsstiftelsestype.ANMERKNING_PAA_PERSON;
   }

   public Person getAnmerketPerson() {
      return anmerketPerson;
   }

   public void setAnmerketPerson(Person anmerketPerson) {
      this.anmerketPerson = anmerketPerson;
   }

   public Person getBostyrer() {
      return bostyrer;
   }

   public void setBostyrer(Person bostyrer) {
      this.bostyrer = bostyrer;
   }

   public Person getKonkursbo() {
      return konkursbo;
   }

   public void setKonkursbo(Person konkursbo) {
      this.konkursbo = konkursbo;
   }
}
