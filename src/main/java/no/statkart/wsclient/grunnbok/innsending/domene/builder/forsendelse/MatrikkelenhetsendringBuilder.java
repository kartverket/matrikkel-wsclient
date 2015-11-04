package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.MatrikkelenhetFraTil;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;

import java.util.List;

/**
 *
 */
public class MatrikkelenhetsendringBuilder {
   private List<Matrikkelenhet> fra = Lists.newArrayList();
   private List<Matrikkelenhet> til = Lists.newArrayList();
   private List<MatrikkelenhetFraTil> omnummereringAvUnderliggende = Lists.newArrayList();
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   private Matrikkelenhetsendring.TypeMatrikkelenhetsendring typeMatrikkelenhetsendring;

   private MatrikkelenhetsendringBuilder() {
   }

   public static MatrikkelenhetsendringBuilder aFradeling() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.FRADELING);
      return matrikkelenhetsendringBuilder;
   }

   public static MatrikkelenhetsendringBuilder aSammenslaaingAvMatrikkelenheter() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.SAMMENSLAAING_AV_MATRIKKELENHETER);
      return matrikkelenhetsendringBuilder;
   }

   public static MatrikkelenhetsendringBuilder anOverfoeringFraTidligereFestenummer() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.OVERFOERING_FRA_TIDLIGERE_FESTENUMMER);
      return matrikkelenhetsendringBuilder;
   }

   public MatrikkelenhetsendringBuilder withFra(List<Matrikkelenhet> fra) {
      this.fra = fra;
      return this;
   }

   public MatrikkelenhetsendringBuilder withTil(List<Matrikkelenhet> til) {
      this.til = til;
      return this;
   }

   public MatrikkelenhetsendringBuilder withOmnummereringAvUnderliggende(List<MatrikkelenhetFraTil> omnummereringAvUnderliggende) {
      this.omnummereringAvUnderliggende = omnummereringAvUnderliggende;
      return this;
   }

   public MatrikkelenhetsendringBuilder ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring typeMatrikkelenhetsendring) {
      this.typeMatrikkelenhetsendring = typeMatrikkelenhetsendring;
      return this;
   }

   public MatrikkelenhetsendringBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public MatrikkelenhetsendringBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public MatrikkelenhetsendringBuilder withTekster(List<Tekst> tekster) {
      this.tekster = tekster;
      return this;
   }

   public MatrikkelenhetsendringBuilder but() {
      return aFradeling().withFra(fra).withTil(til).withRettsstiftelsesreferanse(rettsstiftelsesreferanse).withRettsstiftelsestype(rettsstiftelsestype).withTekster(tekster).ofType(typeMatrikkelenhetsendring);
   }

   public Matrikkelenhetsendring build() {
      if(typeMatrikkelenhetsendring==null) {
         throw new RuntimeException("typeMatrikkelenhetsendring is required");
      }
      Matrikkelenhetsendring matrikkelenhetsendring = new Matrikkelenhetsendring(typeMatrikkelenhetsendring);
      matrikkelenhetsendring.setFra(fra);
      matrikkelenhetsendring.setTil(til);
      matrikkelenhetsendring.setOmnummereringAvUnderliggende(omnummereringAvUnderliggende);
      matrikkelenhetsendring.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      matrikkelenhetsendring.setRettsstiftelsestype(rettsstiftelsestype);
      matrikkelenhetsendring.setTekster(tekster);
      return matrikkelenhetsendring;
   }
}
