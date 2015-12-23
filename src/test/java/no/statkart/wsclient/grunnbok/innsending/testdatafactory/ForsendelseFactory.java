package no.statkart.wsclient.grunnbok.innsending.testdatafactory;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.*;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class ForsendelseFactory {

   public static final long DEFAULT_BELOEPSVERDI = 100L;
   public static final String PANT_RETTSSTIFTELSESREFERANSE = "234567";
   public static final long REGISTERENHETSRETTSANDEL_DEFAULT_NEVNER = 4;
   public static final String ANNEN_HEFTELSE_RETTSSTIFTELSESREFERANSE = "2322323";
   public static final LocalDate OCTOBER_15 = new LocalDate(2015, DateTimeConstants.OCTOBER, 15);
   public static final LocalTime TIME_OF_DAY = new LocalTime(12, 23, 2, 678);
   public static final String PROSESS_FULLMEKTIG_NAVN = "Fabian";
   public static final String SAKSOEKER_NAVN = "Jens";
   public static final String SAKSOEKT_NAVN = "Ulrik";
   public static final String TVANGSFORRETNING_RETTSSTIFTELSESREFERANSE = "2938383";
   public static final String SLETTING_RETTSSTIFTELSESREFERANSE = "668878";
   public static final int RETTSSTIFTELSESNUMMER = 4567;
   public static final String RETTSSTIFTELSE_RETTSTIFTELSESTYPE_KODEVERDI = "Rettstiftelse1";
   public static final long RETTSSTIFTELSE_DOKUMENTNUMMER = 2356L;
   public static final int RETTSSTIFTELSE_DOKUMENTAAR = 2015;
   public static final String RETTSSTIFTELSE_EMBETENUMMER = "44";

   public static final String FORSENDELSESREFERANSE = "12AZd";
   public static final byte[] SIGNERT_MELDING_DOKUMENT = "AByteArray".getBytes();
   public static final byte[] SIGNERT_MELDING_FOLGE_BREV = "folgebrev".getBytes();
   public static final String GJELDER_DOKUMENTREFERANSE = "23a";
   public static final String PERSONIDENTIFIKASJONSNUMMER = "0102010233443";

   public static final String FOELGE_BREV_INNSENDERS_IDENTIFIKASJONSNUMMER = "244432";
   public static final String REFERANSE_GJELDER_DOKUMENTREFERANSE = "3232";
   public static final String REFERANSE_DIGEST_ALGORITME = "digestAlgoritme";
   public static final byte[] REFERANSE_DIGEST = "folgebrevDigest".getBytes();

   public static final String DOKUMENTREFERANSE = "25";
   public static final String ANMERKNING_PAA_PERSON_RETTSTIFTELSESREFERANSE = "rettstiftelsesreferanse";
   public static final String ANMERKNING_PAA_PERSON_SAKSNUMMER = "987";
   public static final String ANMERKET_PERSON_IDENTIFIKASJONSNUMMER = "0202";
   public static final String ANMERKET_PERSON_NAVN = "Jens Stoltenberg";
   public static final String BOSTYRER_IDENTIFIKASJONSNUMMER = "0203";
   public static final String BOSTYRER_NAVN = "Truls Svendsen";
   public static final String KONKURS_BO_IDENTIFIKASJONSNUMMER = "0204";
   public static final String KONKURS_BO_NAVN = "Trine Skei Grande";
   public static final String DEFAULT_KODE_NAVN = "type";
   public static final String DEFAULT_KODEVERDI = "23";
   public static final String FRITEKST = "joda";
   public static final String TEKST_TYPE_NAVN = "Tekst1";
   public static final String TEKST_TYPE_KODEVERDI = "Oransje";

   public static final String MATRIKKELENHETSENDRING_RETTSSTIFTELSESREFERANSE = "2323";

   public static final int GAARDSNUMMER_OSLO = 1;
   public static final int SEKSJONSNUMMER_OSLO = 1;
   public static final int BRUKSNUMMER_OSLO = 1;
   public static final int FESTENUMMER_OSLO = 1;
   public static final String KOMMUNENAVN_OSLO = "OSLO";
   public static final String KOMMUNENUMMER_OSLO = "1301";

   public static final int TIL_MATRIKKELENHET_SEKSJONSNUMMER = 2;

   public static final String OVERDRAGELSE_RETTSSTIFTELSESREFERANSE = "232323";

   public static final boolean KREVER_SAMTYKKE = true;
   public static final boolean UTLYST_TIL_SALG_PAA_DET_FRIE_MARKED = true;
   public static final String VEDERLAG_BELOEPSTEKST = "kroner";
   public static final long VEDERLAG_BELOEPSVERDI = 560000L;
   public static final String VEDERLAG_VALUTAKODE_NAVN = "KR";
   public static final String DOKUMENTAVGIFTSAARSAK_KODEVERDI = "19";
   public static final String DOKUMENT_AVGIFTSGRUNNLAG_BELOEPSTEKST = "svenske kroner";
   public static final long DOKUMENT_AVGIFTSGRUNNLAG_BELOEPSVERDI = 200L;
   public static final String DOKUMENT_AVGIFTSGRUNNLAG_KODEVERDI = "SEK";
   public static final String OMSETNING_OMSETNINGSTYPE_NAVN = "Kontant";
   public static final String BOLIG_TYPE_KODEVERDI = "Leilighet";
   public static final String BRUKSTYPE_KODEVERDI = "Fritid";

   public static final long KJOPT_ANDEL_TELLER = 1L;
   public static final long KJOPT_ANDEL_NEVNER = 7L;
   public static final int KJOPT_ANDEL_BORETTSLAGS_ANDELSNUMMER = 13;
   public static final String KJOPT_ANDEL_BORETTSLAGSNAVN = "Tertitten";
   public static final String KJOPT_ANDEL_BORETTSLAGNUMMER = "1345";

   public static final long SOLGT_ANDEL_TELLER = 2L;
   public static final long SOLGT_ANDEL_NEVNER = 5L;
   public static final int SOLGT_ANDEL_BORETTSLAGSANDELSNUMMER = 17;
   public static final String SOLGT_ANDEL_BORETTSLAGSNAVN = "Tertitten2";
   public static final String SOLGT_ANDEL_BORETTSLAGNUMMER = "2154";

   public static final String REGISTERENHETSRETTSTYPE_KODEVERDI = "Lovlig";

   public static final String DEFAULT_IDENTIFIKASJONSNUMMER = "0200";
   public static final String DEFAULT_PERSON_NAVN = "Jim Jensen";
   public static final int MATRIKKELENHET_FRA_TIL_FRA_BRUKSNUMMER = 17;
   public static final String MATRIKKELENHET_FRA_TIL_TIL_KOMMUNENUMMER = "0456";

   public static ForsendelseBuilder defaultForsendelse() {

      Tekst defaultTekst = TekstBuilder.aTekst()
            .withFritekst(FRITEKST)
            .withTeksttype(KodeBuilder.aKode()
                  .withNavn(TEKST_TYPE_NAVN).withKodeverdi(TEKST_TYPE_KODEVERDI)
                  .build())
            .build();
      KodeBuilder defaultKode = KodeBuilder.aKode()
            .withNavn(DEFAULT_KODE_NAVN)
            .withKodeverdi(DEFAULT_KODEVERDI);

      MatrikkelenhetBuilder matrikkelenhetIOslo = MatrikkelenhetBuilder.aMatrikkelenhet()
            .withGaardsnummer(GAARDSNUMMER_OSLO)
            .withSeksjonsnummer(SEKSJONSNUMMER_OSLO)
            .withBruksnummer(BRUKSNUMMER_OSLO)
            .withFestenummer(FESTENUMMER_OSLO)
            .withKommunenavn(KOMMUNENAVN_OSLO)
            .withKommunenummer(KOMMUNENUMMER_OSLO);


      return ForsendelseBuilder.aForsendelse()
            .withForsendelsesReferanse(FORSENDELSESREFERANSE)
            .withSignertMelding(SignertMeldingBuilder.aSignertMelding()
                  .withDokumenter(Lists.newArrayList(SDODokumentBuilder.aSDODokument()
                        .withSignertDokument(SIGNERT_MELDING_DOKUMENT)
                        .build()))
                  .withFoelgebrev(SDODokumentBuilder.aSDODokument()
                        .withSignertDokument(SIGNERT_MELDING_FOLGE_BREV)
                        .build())
                  .build())
            .withIkkeDigitaleSignaturer(Lists.newArrayList(SignaturBuilder.aSignatur()
                  .withGjelderDokumentreferanse(GJELDER_DOKUMENTREFERANSE)
                  .withPersonidentifikasjonsnummer(PERSONIDENTIFIKASJONSNUMMER)
                  .build()))
            .withUsignertMelding(UsignertMeldingBuilder.anUsignertMelding()
                  .withFoelgebrev(FoelgebrevBuilder.aFoelgebrev()
                        .withInnsendersIdentifikasjonsnummer(FOELGE_BREV_INNSENDERS_IDENTIFIKASJONSNUMMER)
                        .withDokumentrekkefoelge(Lists.newArrayList(
                              ReferanseBuilder.aReferanse()
                                    .withGjelderDokumentreferanse(REFERANSE_GJELDER_DOKUMENTREFERANSE)
                                    .withDigestAlgoritme(REFERANSE_DIGEST_ALGORITME)
                                    .withDigest(REFERANSE_DIGEST)
                                    .build()))
                        .build())
                  .withDokumenter(Lists.newArrayList(DokumentBuilder.aDokument()
                        .withDokumentreferanse(DOKUMENTREFERANSE)
                        .withMatrikkelenhetsendring(MatrikkelenhetsendringBuilder.aFradeling()
                              .withRettsstiftelsesreferanse(MATRIKKELENHETSENDRING_RETTSSTIFTELSESREFERANSE)
                              .withRettsstiftelsestype(defaultKode.build())
                              .withTekster(Lists.newArrayList(defaultTekst))
                              .withFra(Lists.newArrayList(matrikkelenhetIOslo.build()))
                              .withTil(Lists.newArrayList(matrikkelenhetIOslo.but().withSeksjonsnummer(TIL_MATRIKKELENHET_SEKSJONSNUMMER).build()))
                              .build())
                        .build())
                  )
                  .build());
   }

   public static Forsendelse fradelingForsendelse() {

      return ForsendelseBuilder.aForsendelse()
            .withForsendelsesReferanse(FORSENDELSESREFERANSE)
            .withUsignertMelding(UsignertMeldingBuilder.anUsignertMelding()
                  .withFoelgebrev(FoelgebrevBuilder.aFoelgebrev()
                        .withInnsendersIdentifikasjonsnummer("12313") //rekvirent for tinglysing
                        .withDokumentrekkefoelge(Lists.newArrayList(ReferanseBuilder.aReferanse().withGjelderDokumentreferanse("1").build()))
                        .build())
                  .withDokumenter(Lists.newArrayList((DokumentBuilder.aDokument()
                        .withMatrikkelenhetsendring(MatrikkelenhetsendringBuilder.aFradeling()
                              .withTil(Lists.newArrayList(
                                    MatrikkelenhetFactory.createMatrikkelenhet(KOMMUNENUMMER_OSLO, KOMMUNENAVN_OSLO, 2, 90)
                              ))
                              .withFra(Lists.newArrayList(
                                    MatrikkelenhetFactory.createMatrikkelenhet(KOMMUNENUMMER_OSLO, KOMMUNENAVN_OSLO, 2, 91)
                              ))
                              .withRekvirenterAvForretning(Lists.newArrayList(PersonBuilder.aPerson().withIdentifikasjonsnummer("12345678910").withNavn("navn").build()))
                              .build())
                        .build())))
                  .build())
            .build();
   }
}
