package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Beloep;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.ErklaeringOmSivilstand;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Pant;
import no.statkart.wsclient.grunnbok.innsending.domene.Person;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;

import java.util.List;

/**
 *
 */
public class PantBuilder {
   private List<ErklaeringOmSivilstand> erklaeringerOmSivilstand = Lists.newArrayList();
   private List<Person> pantsettere = Lists.newArrayList();
   private List<Beloep> beloep;
   private List<DelAvRegisterenhetsrett> hefterI = Lists.newArrayList();
   private List<Person> rettighetshavere = Lists.newArrayList();
   private List<Registerenhetsrett> realkobletTil = Lists.newArrayList();
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   private PantBuilder() {
   }

   public static PantBuilder aPant() {
      return new PantBuilder();
   }

   public PantBuilder withErklaeringerOmSivilstand(List<ErklaeringOmSivilstand> erklaeringerOmSivilstand) {
      this.erklaeringerOmSivilstand = erklaeringerOmSivilstand;
      return this;
   }

   public PantBuilder withPantsettere(List<Person> pantsettere) {
      this.pantsettere = pantsettere;
      return this;
   }

   public PantBuilder withBeloep(List<Beloep> beloep) {
      this.beloep = beloep;
      return this;
   }

   public PantBuilder withHefterI(List<DelAvRegisterenhetsrett> hefterI) {
      this.hefterI = hefterI;
      return this;
   }

   public PantBuilder withRettighetshavere(List<Person> rettighetshaverePerson) {
      this.rettighetshavere = rettighetshaverePerson;
      return this;
   }

   public PantBuilder withRealkobletTil(List<Registerenhetsrett> rettighetshavereRegisterenhetsrett) {
      this.realkobletTil = rettighetshavereRegisterenhetsrett;
      return this;
   }

   public PantBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public PantBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public PantBuilder withTekster(List<Tekst> tekster) {
      this.tekster = tekster;
      return this;
   }

   public PantBuilder but() {
      return aPant().withErklaeringerOmSivilstand(erklaeringerOmSivilstand).withPantsettere(pantsettere).withBeloep(beloep).withHefterI(hefterI).withRettighetshavere(rettighetshavere).withRealkobletTil(realkobletTil).withRettsstiftelsesreferanse(rettsstiftelsesreferanse).withRettsstiftelsestype(rettsstiftelsestype).withTekster(tekster);
   }

   public Pant build() {
      Pant pant = new Pant();
      pant.setErklaeringerOmSivilstand(erklaeringerOmSivilstand);
      pant.setPantsettere(pantsettere);
      pant.setBeloep(beloep);
      pant.setHefterI(hefterI);
      pant.setRettighetshavere(rettighetshavere);
      pant.setRealkobletTil(realkobletTil);
      pant.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      pant.setRettsstiftelsestype(rettsstiftelsestype);
      pant.setTekster(tekster);
      return pant;
   }
}
