package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.AnnenHeftelse;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Person;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;

import java.util.List;

/**
 *
 */
public class AnnenHeftelseBuilder {
   private List<DelAvRegisterenhetsrett> hefterI = Lists.newArrayList();
   private List<Person> rettighetshavere = Lists.newArrayList();
   private List<Registerenhetsrett> realkobletTil = Lists.newArrayList();
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   private AnnenHeftelseBuilder() {
   }

   public static AnnenHeftelseBuilder anAnnenHeftelse() {
      return new AnnenHeftelseBuilder();
   }

   public AnnenHeftelseBuilder withHefterI(List<DelAvRegisterenhetsrett> hefterI) {
      this.hefterI = hefterI;
      return this;
   }

   public AnnenHeftelseBuilder withRettighetshavere(List<Person> rettighetshaverePerson) {
      this.rettighetshavere = rettighetshaverePerson;
      return this;
   }

   public AnnenHeftelseBuilder withRealkobletTil(List<Registerenhetsrett> rettighetshavereRegisterenhetsrett) {
      this.realkobletTil = rettighetshavereRegisterenhetsrett;
      return this;
   }

   public AnnenHeftelseBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public AnnenHeftelseBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public AnnenHeftelseBuilder withTekster(List<Tekst> tekster) {
      this.tekster = tekster;
      return this;
   }

   public AnnenHeftelseBuilder but() {
      return anAnnenHeftelse().withHefterI(hefterI).withRettighetshavere(rettighetshavere).withRealkobletTil(realkobletTil).withRettsstiftelsesreferanse(rettsstiftelsesreferanse).withRettsstiftelsestype(rettsstiftelsestype).withTekster(tekster);
   }

   public AnnenHeftelse build() {
      AnnenHeftelse annenHeftelse = new AnnenHeftelse();
      annenHeftelse.setHefterI(hefterI);
      annenHeftelse.setRettighetshavere(rettighetshavere);
      annenHeftelse.setRealkobletTil(realkobletTil);
      annenHeftelse.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      annenHeftelse.setRettsstiftelsestype(rettsstiftelsestype);
      annenHeftelse.setTekster(tekster);
      return annenHeftelse;
   }
}
