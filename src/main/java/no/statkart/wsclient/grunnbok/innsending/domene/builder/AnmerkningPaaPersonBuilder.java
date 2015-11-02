package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.AnmerkningPaaPerson;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Person;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;

import java.util.List;

/**
 *
 */
public class AnmerkningPaaPersonBuilder {
   private Person anmerketPerson;
   private Person konkursbo;
   private Person bostyrer;
   private String saksnummer;
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   private AnmerkningPaaPersonBuilder() {
   }

   public static AnmerkningPaaPersonBuilder anAnmerkningPaaPerson() {
      return new AnmerkningPaaPersonBuilder();
   }

   public AnmerkningPaaPersonBuilder withAnmerketPerson(Person anmerketPerson) {
      this.anmerketPerson = anmerketPerson;
      return this;
   }

   public AnmerkningPaaPersonBuilder withKonkursbo(Person konkursbo) {
      this.konkursbo = konkursbo;
      return this;
   }

   public AnmerkningPaaPersonBuilder withBostyrer(Person bostyrer) {
      this.bostyrer = bostyrer;
      return this;
   }

   public AnmerkningPaaPersonBuilder withSaksnummer(String saksnummer) {
      this.saksnummer = saksnummer;
      return this;
   }

   public AnmerkningPaaPersonBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public AnmerkningPaaPersonBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public AnmerkningPaaPersonBuilder withTekster(List<Tekst> tekster) {
      this.tekster = tekster;
      return this;
   }

   public AnmerkningPaaPersonBuilder but() {
      return anAnmerkningPaaPerson().withAnmerketPerson(anmerketPerson).withKonkursbo(konkursbo).withBostyrer(bostyrer).withSaksnummer(saksnummer).withRettsstiftelsesreferanse(rettsstiftelsesreferanse).withRettsstiftelsestype(rettsstiftelsestype).withTekster(tekster);
   }

   public AnmerkningPaaPerson build() {
      AnmerkningPaaPerson anmerkningPaaPerson = new AnmerkningPaaPerson();
      anmerkningPaaPerson.setAnmerketPerson(anmerketPerson);
      anmerkningPaaPerson.setKonkursbo(konkursbo);
      anmerkningPaaPerson.setBostyrer(bostyrer);
      anmerkningPaaPerson.setSaksnummer(saksnummer);
      anmerkningPaaPerson.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      anmerkningPaaPerson.setRettsstiftelsestype(rettsstiftelsestype);
      anmerkningPaaPerson.setTekster(tekster);
      return anmerkningPaaPerson;
   }
}
