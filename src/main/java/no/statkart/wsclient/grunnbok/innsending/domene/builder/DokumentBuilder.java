package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.AnmerkningPaaPerson;
import no.statkart.wsclient.grunnbok.innsending.domene.AnnenHeftelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring;
import no.statkart.wsclient.grunnbok.innsending.domene.OverdragelseAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Pant;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Sletting;
import no.statkart.wsclient.grunnbok.innsending.domene.Tvangsforretning;

import java.util.List;

/**
 *
 */
public class DokumentBuilder {
   private String dokumentreferanse;
   private List<Rettsstiftelse> rettsstiftelser = Lists.newArrayList();

   private DokumentBuilder() {
   }

   public static DokumentBuilder aDokument() {
      return new DokumentBuilder();
   }

   public DokumentBuilder withDokumentreferanse(String dokumentreferanse) {
      this.dokumentreferanse = dokumentreferanse;
      return this;
   }

   public DokumentBuilder withRettsstiftelser(List<Rettsstiftelse> rettsstiftelser) {
      this.rettsstiftelser = rettsstiftelser;
      return this;
   }

   public DokumentBuilder withAnmerkningPaaPerson(AnmerkningPaaPerson rettsstiftelse) {
      rettsstiftelser.add(rettsstiftelse);
      return this;
   }

   public DokumentBuilder withMatrikkelenhetsendring(Matrikkelenhetsendring rettsstiftelse) {
      rettsstiftelser.add(rettsstiftelse);
      return this;
   }

   public DokumentBuilder withOverdragelseAvRegisterenhetsrett(OverdragelseAvRegisterenhetsrett overdragelse) {
      rettsstiftelser.add(overdragelse);
      return this;
   }

   public DokumentBuilder withPant(Pant pant) {
      rettsstiftelser.add(pant);
      return this;
   }

   public DokumentBuilder withAnnenHeftelse(AnnenHeftelse annenHeftelse) {
      rettsstiftelser.add(annenHeftelse);
      return this;
   }

   public DokumentBuilder withTvangsforretning(Tvangsforretning tvangsforretning) {
      rettsstiftelser.add(tvangsforretning);
      return this;
   }

   public DokumentBuilder withSletting(Sletting sletting) {
      rettsstiftelser.add(sletting);
      return this;
   }

   public DokumentBuilder but() {
      return aDokument().withDokumentreferanse(dokumentreferanse).withRettsstiftelser(rettsstiftelser);
   }

   public Dokument build() {
      Dokument dokument = new Dokument();
      dokument.setDokumentreferanse(dokumentreferanse);
      dokument.setRettsstiftelser(rettsstiftelser);
      return dokument;
   }


}
