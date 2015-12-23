package no.statkart.wsclient.grunnbok.innsending.testdatafactory;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.*;

public class ForsendelseFactory {


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
