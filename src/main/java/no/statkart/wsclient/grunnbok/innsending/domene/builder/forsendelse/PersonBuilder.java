package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.Person;

/**
 *
 */
public class PersonBuilder {
   private String identifikasjonsnummer;
   private String navn;

   private PersonBuilder() {
   }

   public static PersonBuilder aPerson() {
      return new PersonBuilder();
   }

   public PersonBuilder withIdentifikasjonsnummer(String identifikasjonsnummer) {
      this.identifikasjonsnummer = identifikasjonsnummer;
      return this;
   }

   public PersonBuilder withNavn(String navn) {
      this.navn = navn;
      return this;
   }

   public PersonBuilder but() {
      return aPerson().withIdentifikasjonsnummer(identifikasjonsnummer).withNavn(navn);
   }

   public Person build() {
      Person person = new Person();
      person.setIdentifikasjonsnummer(identifikasjonsnummer);
      person.setNavn(navn);
      return person;
   }
}
