package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.ErklaeringOmSivilstand;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Omsetning;
import no.statkart.wsclient.grunnbok.innsending.domene.OverdragelseAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;

import java.util.List;

/**
 *
 */
public class OverdragelseAvRegisterenhetsrettBuilder {
   private List<ErklaeringOmSivilstand> erklaeringerOmSivilstand = Lists.newArrayList();
   private Omsetning omsetning;
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   private OverdragelseAvRegisterenhetsrett.TypeOverdragelseAvRegisterenhetsrett typeOverdragelseAvRegisterenhetsrett;

   private OverdragelseAvRegisterenhetsrettBuilder() {
   }

   public static OverdragelseAvRegisterenhetsrettBuilder anEierskifteMatrikkelenhet() {
      OverdragelseAvRegisterenhetsrettBuilder builder = new OverdragelseAvRegisterenhetsrettBuilder();
      builder.ofType(OverdragelseAvRegisterenhetsrett.TypeOverdragelseAvRegisterenhetsrett.EIERSKIFTE_MATRIKKELENHET);
      return builder;
   }

   public static OverdragelseAvRegisterenhetsrettBuilder anOverdragelseAvFesterett() {
      OverdragelseAvRegisterenhetsrettBuilder builder = new OverdragelseAvRegisterenhetsrettBuilder();
      builder.ofType(OverdragelseAvRegisterenhetsrett.TypeOverdragelseAvRegisterenhetsrett.OVERDRAGELSE_AV_FESTERETT);
      return builder;
   }

   public static OverdragelseAvRegisterenhetsrettBuilder anEierskifteBorettslagsandel() {
      OverdragelseAvRegisterenhetsrettBuilder builder = new OverdragelseAvRegisterenhetsrettBuilder();
      builder.ofType(OverdragelseAvRegisterenhetsrett.TypeOverdragelseAvRegisterenhetsrett.EIERSKIFTE_BORETTSLAGSANDEL);
      return builder;
   }

   public OverdragelseAvRegisterenhetsrettBuilder ofType(OverdragelseAvRegisterenhetsrett.TypeOverdragelseAvRegisterenhetsrett typeOverdragelseAvRegisterenhetsrett) {
      this.typeOverdragelseAvRegisterenhetsrett = typeOverdragelseAvRegisterenhetsrett;
      return this;
   }

   public OverdragelseAvRegisterenhetsrettBuilder withErklaeringerOmSivilstand(List<ErklaeringOmSivilstand> erklaeringerOmSivilstand) {
      this.erklaeringerOmSivilstand = erklaeringerOmSivilstand;
      return this;
   }

   public OverdragelseAvRegisterenhetsrettBuilder withOmsetning(Omsetning omsetning) {
      this.omsetning = omsetning;
      return this;
   }

   public OverdragelseAvRegisterenhetsrettBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public OverdragelseAvRegisterenhetsrettBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public OverdragelseAvRegisterenhetsrettBuilder withTekster(List<Tekst> tekster) {
      this.tekster = tekster;
      return this;
   }

   public OverdragelseAvRegisterenhetsrettBuilder but() {
      return anEierskifteMatrikkelenhet().withErklaeringerOmSivilstand(erklaeringerOmSivilstand)
            .withOmsetning(omsetning).withRettsstiftelsesreferanse(rettsstiftelsesreferanse)
            .withRettsstiftelsestype(rettsstiftelsestype).withTekster(tekster).ofType(typeOverdragelseAvRegisterenhetsrett);
   }

   public OverdragelseAvRegisterenhetsrett build() {
      if( typeOverdragelseAvRegisterenhetsrett == null ) {
         throw new RuntimeException("type må være satt");
      }
      OverdragelseAvRegisterenhetsrett overdragelseAvRegisterenhetsrett = new OverdragelseAvRegisterenhetsrett(typeOverdragelseAvRegisterenhetsrett);
      overdragelseAvRegisterenhetsrett.setErklaeringerOmSivilstand(erklaeringerOmSivilstand);
      overdragelseAvRegisterenhetsrett.setOmsetning(omsetning);
      overdragelseAvRegisterenhetsrett.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      overdragelseAvRegisterenhetsrett.setRettsstiftelsestype(rettsstiftelsestype);
      overdragelseAvRegisterenhetsrett.setTekster(tekster);
      return overdragelseAvRegisterenhetsrett;
   }
}
