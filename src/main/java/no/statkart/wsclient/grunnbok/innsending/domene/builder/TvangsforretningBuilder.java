package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.AvholdtTvangsforretning;
import no.statkart.wsclient.grunnbok.innsending.domene.Beloep;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Person;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;
import no.statkart.wsclient.grunnbok.innsending.domene.Tvangsforretning;

import java.util.List;

/**
 *
 */
public class TvangsforretningBuilder {
   private AvholdtTvangsforretning avholdtTvangsforretning;
   private List<Beloep> beloep;
   private List<DelAvRegisterenhetsrett> hefterI = Lists.newArrayList();
   private List<Person> rettighetshavere = Lists.newArrayList();
   private List<Registerenhetsrett> realkobletTil = Lists.newArrayList();
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   private TvangsforretningBuilder() {
   }

   public static TvangsforretningBuilder aTvangsforretning() {
      return new TvangsforretningBuilder();
   }

   public TvangsforretningBuilder withAvholdtTvangsforretning(AvholdtTvangsforretning avholdtTvangsforretning) {
      this.avholdtTvangsforretning = avholdtTvangsforretning;
      return this;
   }

   public TvangsforretningBuilder withBeloep(List<Beloep> beloep) {
      this.beloep = beloep;
      return this;
   }

   public TvangsforretningBuilder withHefterI(List<DelAvRegisterenhetsrett> hefterI) {
      this.hefterI = hefterI;
      return this;
   }

   public TvangsforretningBuilder withRettighetshavere(List<Person> rettighetshaverePerson) {
      this.rettighetshavere = rettighetshaverePerson;
      return this;
   }

   public TvangsforretningBuilder withRealkobletTil(List<Registerenhetsrett> rettighetshavereRegisterenhetsrett) {
      this.realkobletTil = rettighetshavereRegisterenhetsrett;
      return this;
   }

   public TvangsforretningBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public TvangsforretningBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public TvangsforretningBuilder withTekster(List<Tekst> tekster) {
      this.tekster = tekster;
      return this;
   }

   public TvangsforretningBuilder but() {
      return aTvangsforretning().withAvholdtTvangsforretning(avholdtTvangsforretning).withBeloep(beloep).withHefterI(hefterI).withRettighetshavere(rettighetshavere).withRealkobletTil(realkobletTil).withRettsstiftelsesreferanse(rettsstiftelsesreferanse).withRettsstiftelsestype(rettsstiftelsestype).withTekster(tekster);
   }

   public Tvangsforretning build() {
      Tvangsforretning tvangsforretning = new Tvangsforretning();
      tvangsforretning.setAvholdtTvangsforretning(avholdtTvangsforretning);
      tvangsforretning.setBeloep(beloep);
      tvangsforretning.setHefterI(hefterI);
      tvangsforretning.setRettighetshavere(rettighetshavere);
      tvangsforretning.setRealkobletTil(realkobletTil);
      tvangsforretning.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      tvangsforretning.setRettsstiftelsestype(rettsstiftelsestype);
      tvangsforretning.setTekster(tekster);
      return tvangsforretning;
   }
}
