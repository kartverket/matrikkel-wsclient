package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Kode;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Matrikkelenhetsendring;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Person;

import java.util.List;

/**
 *
 */
public class MatrikkelenhetsendringBuilder {
   private List<Matrikkelenhet> fra = Lists.newArrayList();
   private List<Matrikkelenhet> til = Lists.newArrayList();
   private List<Person> rekvirenterAvForretning = Lists.newArrayList();
   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;

   private Matrikkelenhetsendring.TypeMatrikkelenhetsendring typeMatrikkelenhetsendring;

   private MatrikkelenhetsendringBuilder() {
   }

   public static MatrikkelenhetsendringBuilder aFradeling() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.FRADELING);
      matrikkelenhetsendringBuilder.withRettsstiftelsesreferanse("1"); //ved fradeling vil vi kun ha en rettstiftelsereferanse og kan dermed hardkode denne verdien.
      matrikkelenhetsendringBuilder.withRettsstiftelsestype(KodeBuilder.aKode().withKodeverdi("FR_REG").withNavn("REGISTRERING AV GRUNN").build());
      return matrikkelenhetsendringBuilder;
   }

   public static MatrikkelenhetsendringBuilder aSammenslaaingAvMatrikkelenheter() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.SAMMENSLAAING_AV_MATRIKKELENHETER);
      matrikkelenhetsendringBuilder.withRettsstiftelsesreferanse("1");
      matrikkelenhetsendringBuilder.withRettsstiftelsestype(KodeBuilder.aKode().withKodeverdi("SA_SAM").withNavn("SAMMENSLÅING").build());
      return matrikkelenhetsendringBuilder;
   }

   public static MatrikkelenhetsendringBuilder anOverfoeringFraTidligereFestenummer() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.FESTENUMMER_GITT_BRUKSNUMMER);
      matrikkelenhetsendringBuilder.withRettsstiftelsesreferanse("1");
      matrikkelenhetsendringBuilder.withRettsstiftelsestype(KodeBuilder.aKode().withKodeverdi("FB_FGB").withNavn("FESTENUMMER GITT BRUKSNUMMER").build());
      return matrikkelenhetsendringBuilder;
   }

   public static MatrikkelenhetsendringBuilder aOpprettFestegrunn() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.OPPRETT_FESTEGRUNN);
      matrikkelenhetsendringBuilder.withRettsstiftelsesreferanse("1"); //ved fradeling vil vi kun ha en rettstiftelsereferanse og kan dermed hardkode denne verdien.
      matrikkelenhetsendringBuilder.withRettsstiftelsestype(KodeBuilder.aKode().withKodeverdi("FR_RFE").withNavn("REGISTRERING AV FESTENR.").build());
      return matrikkelenhetsendringBuilder;
   }


   public static MatrikkelenhetsendringBuilder aOmnummererMatrikkelenhet() {
      MatrikkelenhetsendringBuilder matrikkelenhetsendringBuilder = new MatrikkelenhetsendringBuilder();
      matrikkelenhetsendringBuilder.ofType(Matrikkelenhetsendring.TypeMatrikkelenhetsendring.OMNUMMERER_MATRIKKELENHETER);
      matrikkelenhetsendringBuilder.withRettsstiftelsesreferanse("1"); //ved fradeling vil vi kun ha en rettstiftelsereferanse og kan dermed hardkode denne verdien.
      matrikkelenhetsendringBuilder.withRettsstiftelsestype(KodeBuilder.aKode().withKodeverdi("OM_OMN").withNavn("OMNUMMERERING").build());
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

   public MatrikkelenhetsendringBuilder withRekvirenterAvForretning(List<Person> rekvirenterAvForretning) {
      this.rekvirenterAvForretning = rekvirenterAvForretning;
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

   public Matrikkelenhetsendring build() {
      if(typeMatrikkelenhetsendring==null) {
         throw new RuntimeException("typeMatrikkelenhetsendring is required");
      }
      Matrikkelenhetsendring matrikkelenhetsendring = new Matrikkelenhetsendring(typeMatrikkelenhetsendring);
      matrikkelenhetsendring.setFra(fra);
      matrikkelenhetsendring.setTil(til);
      matrikkelenhetsendring.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      matrikkelenhetsendring.setRettsstiftelsestype(rettsstiftelsestype);
      matrikkelenhetsendring.setRekvirenterAvForretning(rekvirenterAvForretning);
      return matrikkelenhetsendring;
   }
}
