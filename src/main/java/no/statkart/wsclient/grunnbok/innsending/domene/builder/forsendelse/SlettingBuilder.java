package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRett;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Sletting;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;

import java.util.List;

/**
 *
 */
public class SlettingBuilder {
   private List<DelAvRett> endrer = Lists.newArrayList();
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   private SlettingBuilder() {
   }

   public static SlettingBuilder aSletting() {
      return new SlettingBuilder();
   }

   public SlettingBuilder withEndrer(List<DelAvRett> endrer) {
      this.endrer = endrer;
      return this;
   }

   public SlettingBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public SlettingBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public SlettingBuilder withTekster(List<Tekst> tekster) {
      this.tekster = tekster;
      return this;
   }

   public SlettingBuilder but() {
      return aSletting().withEndrer(endrer).withRettsstiftelsesreferanse(rettsstiftelsesreferanse).withRettsstiftelsestype(rettsstiftelsestype).withTekster(tekster);
   }

   public Sletting build() {
      Sletting sletting = new Sletting();
      sletting.setEndrer(endrer);
      sletting.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      sletting.setRettsstiftelsestype(rettsstiftelsestype);
      sletting.setTekster(tekster);
      return sletting;
   }
}
