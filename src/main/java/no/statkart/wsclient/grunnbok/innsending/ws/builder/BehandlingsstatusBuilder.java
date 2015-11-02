package no.statkart.wsclient.grunnbok.innsending.ws.builder;

import com.google.common.collect.Lists;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Avvisningsinformasjon;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Behandlingsstatus;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tinglysingsinformasjon;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;

/**
 *
 */
public class BehandlingsstatusBuilder {
   public static final int DOKUMENTNUMMER = 22;
   public static final String DEFAULT_INNSENDING_ID = "1";
   public static final LocalDateTime DEFAULT_REGISTRERINGS_TIDSPUNKT = new LocalDateTime(2015, DateTimeConstants.OCTOBER, 16, 12, 5, 6, 178);
   public static final String DEFAULT_BEHANDLINGSUTFALL = "OK";
   public static final String DEFAULT_SAKS_STATUS = "Prosessert";
   public static final String EMBETENUMMER = "34";
   public static final int DOKUMENTAAR = 2015;
   public static final String DOKUMENTREFERANSE = "Referanse1";
   public static final int RETTSSTIFTELSESNUMMER = 235;
   public static final String RETTSSTIFTELSESREFERANSE = "Xyz";
   public static final byte[] SIGNERT_DOKUMENT_BYTES = "Signed,sealed,delivered".getBytes();
   public static final String KOMMUNENUMMER = "1301";
   public static final String KOMMUNENAVN = "OSLO";
   public static final int GAARDSNUMMER = 1;
   public static final int BRUKSNUMMER = 2;
   public static final int FESTENUMMER = 3;
   public static final int SEKSJONSNUMMER = 1;
   public static final String BEGRUNNELSE_KODEVERDI = "B03";
   public static final String BEGRUNNELSE_ELEMENTNAVN = "Areal";
   public static final String BEGRUNNELSE_TEKST = "Manglende areal.";
   public static final int KONTROLL_RESULTAT_DOKUMENTINDEKS = 1;
   public static final String KONTROLL_RESULTAT_KODEVERDI = "Kontrollert";
   public static final String KONTROLL_RESULTAT_NAVN = "Resultat 1";
   public static final int KONTROLL_RESULTAT_RETTSSTIFTELSESINDEKS = 2;
   public static final String KONTROLL_RESULTAT_UTFALL = "Ikke tinglyst";

   protected Avvisningsinformasjon avvisningsinformasjon;
   protected String innsendingId;
   protected LocalDateTime registreringstidspunkt;
   protected String behandlingsutfall;
   protected String saksstatus;
   protected Tinglysingsinformasjon tinglysingsinformasjon;

   private BehandlingsstatusBuilder() {
   }

   public static BehandlingsstatusBuilder aBehandlingsstatus() {
      return new BehandlingsstatusBuilder();
   }

   public BehandlingsstatusBuilder withAvvisningsinformasjon(Avvisningsinformasjon avvisningsinformasjon) {
      this.avvisningsinformasjon = avvisningsinformasjon;
      return this;
   }

   public BehandlingsstatusBuilder withInnsendingId(String innsendingId) {
      this.innsendingId = innsendingId;
      return this;
   }

   public BehandlingsstatusBuilder withRegistreringstidspunkt(LocalDateTime registreringstidspunkt) {
      this.registreringstidspunkt = registreringstidspunkt;
      return this;
   }

   public BehandlingsstatusBuilder withBehandlingsutfall(String behandlingsutfall) {
      this.behandlingsutfall = behandlingsutfall;
      return this;
   }

   public BehandlingsstatusBuilder withSaksstatus(String saksstatus) {
      this.saksstatus = saksstatus;
      return this;
   }

   public BehandlingsstatusBuilder withTinglysingsinformasjon(Tinglysingsinformasjon tinglysingsinformasjon) {
      this.tinglysingsinformasjon = tinglysingsinformasjon;
      return this;
   }

   public BehandlingsstatusBuilder but() {
      return aBehandlingsstatus().withAvvisningsinformasjon(avvisningsinformasjon).withInnsendingId(innsendingId).withRegistreringstidspunkt(registreringstidspunkt).withBehandlingsutfall(behandlingsutfall).withSaksstatus(saksstatus).withTinglysingsinformasjon(tinglysingsinformasjon);
   }

   public Behandlingsstatus build() {
      Behandlingsstatus behandlingsstatus = new Behandlingsstatus();
      behandlingsstatus.setAvvisningsinformasjon(avvisningsinformasjon);
      behandlingsstatus.setInnsendingId(innsendingId);
      behandlingsstatus.setRegistreringstidspunkt(registreringstidspunkt);
      behandlingsstatus.setBehandlingsutfall(behandlingsutfall);
      behandlingsstatus.setSaksstatus(saksstatus);
      behandlingsstatus.setTinglysingsinformasjon(tinglysingsinformasjon);
      return behandlingsstatus;
   }

   public static BehandlingsstatusBuilder defaultBehandlingsstatus() {
      return aBehandlingsstatus()
            .withInnsendingId(DEFAULT_INNSENDING_ID)
            .withRegistreringstidspunkt(DEFAULT_REGISTRERINGS_TIDSPUNKT)
            .withBehandlingsutfall(DEFAULT_BEHANDLINGSUTFALL)
            .withSaksstatus(DEFAULT_SAKS_STATUS)
            .withTinglysingsinformasjon(TinglysingsinformasjonBuilder.aTinglysingsinformasjon()
                  .withDokumentinformasjon(Lists.newArrayList(DokumentinformasjonBuilder.aDokumentinformasjon()
                        .withDokumentnummer(DOKUMENTNUMMER)
                        .withEmbetenummer(EMBETENUMMER)
                        .withDokumentaar(DOKUMENTAAR)
                        .withDokumentreferanse(DOKUMENTREFERANSE)
                        .withRettsstiftelsesinformasjonList(Lists.newArrayList(RettsstiftelsesinformasjonBuilder.aRettsstiftelsesinformasjon()
                              .withRettsstiftelsesnummer(RETTSSTIFTELSESNUMMER)
                              .withRettsstiftelsesreferanse(RETTSSTIFTELSESREFERANSE)
                              .build()))
                        .build()))
                  .withSignerteGrunnboksutskrifter(Lists.newArrayList(SignertGrunnboksutskriftBuilder.aSignertGrunnboksutskrift()
                        .withGjelderFor(RegisterenhetBuilder.aRegisterenhet()
                              .withMatrikkelenhet(MatrikkelenhetBuilder.aMatrikkelenhet()
                                    .withKommunenummer(KOMMUNENUMMER)
                                    .withKommunenavn(KOMMUNENAVN)
                                    .withGaardsnummer(GAARDSNUMMER)
                                    .withBruksnummer(BRUKSNUMMER)
                                    .withFestenummer(FESTENUMMER)
                                    .withSeksjonsnummer(SEKSJONSNUMMER)
                                    .build())
                              .build())
                        .withSignertUtskrift(SDODokumentBuilder.aSDODokument()
                              .withSignertDokument(SIGNERT_DOKUMENT_BYTES)
                              .build())
                        .build()))
                  .build())
            .withAvvisningsinformasjon(AvvisningsinformasjonBuilder.anAvvisningsinformasjon()
                  .withKontrollresultater(Lists.newArrayList(KontrollresultatBuilder.aKontrollresultat()
                        .withBegrunnelser(Lists.newArrayList(BegrunnelseBuilder.aBegrunnelse()
                              .withKodeverdi(BEGRUNNELSE_KODEVERDI)
                              .withElementnavn(BEGRUNNELSE_ELEMENTNAVN)
                              .withTekst(BEGRUNNELSE_TEKST)
                              .build()))
                        .withDokumentindeks(KONTROLL_RESULTAT_DOKUMENTINDEKS)
                        .withKodeverdi(KONTROLL_RESULTAT_KODEVERDI)
                        .withNavn(KONTROLL_RESULTAT_NAVN)
                        .withRettsstiftelsesindeks(KONTROLL_RESULTAT_RETTSSTIFTELSESINDEKS)
                        .withUtfall(KONTROLL_RESULTAT_UTFALL)
                        .build()))
                  .build());
   }
}
