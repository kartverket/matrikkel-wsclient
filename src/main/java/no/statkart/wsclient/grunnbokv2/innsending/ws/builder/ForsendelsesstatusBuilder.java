package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Behandlingsinformasjon;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelsesstatus;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Tinglysingsinformasjon;
import no.statkart.wsclient.grunnbokv2.innsending.InnsendingServiceWSStub;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 *
 */
public class ForsendelsesstatusBuilder {
   public static final int DOKUMENTNUMMER = 22;
   public static final String DEFAULT_INNSENDING_ID = "1";
   public static final String DEFAULT_FORSENDELSESREFERANSE = "16UNO";
   public static final LocalDateTime DEFAULT_REGISTRERINGS_TIDSPUNKT = new LocalDateTime(2015, DateTimeConstants.OCTOBER, 16, 12, 5, 6, 178);
   public static final String DEFAULT_BEHANDLINGSUTFALL = "TINGLYST";
   public static final String DEFAULT_SAKS_STATUS = "Prosessert";
   public static final String EMBETENUMMER = "34";
   public static final int DOKUMENTAAR = 2015;
   public static final String DOKUMENTREFERANSE = "Referanse1";
   public static final int RETTSSTIFTELSESNUMMER = 235;
   public static final String RETTSSTIFTELSESREFERANSE = "Xyz";
   public static final byte[] SIGNERT_DOKUMENT_BYTES = psedudoSDO();
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
   public static final List<String> SIGNERT_GRUNNBOKSUTSKRIFT_DOKUMENTREFERANSE = Collections.singletonList("235A");
   public static final String BEGRUNNELSE_UTFALL = "UAVKLART";

   protected Behandlingsinformasjon behandlingsinformasjon;
   protected String innsendingId;
   private String forsendelsesreferanse;
   protected LocalDateTime registreringstidspunkt;
   protected String behandlingsutfall;
   protected String saksstatus;
   protected Tinglysingsinformasjon tinglysingsinformasjon;

   private ForsendelsesstatusBuilder() {
   }

   public static ForsendelsesstatusBuilder aBehandlingsstatus() {
      return new ForsendelsesstatusBuilder();
   }

   public ForsendelsesstatusBuilder withBehandlingsinformasjon(Behandlingsinformasjon behandlingsinformasjon) {
      this.behandlingsinformasjon = behandlingsinformasjon;
      return this;
   }

   public ForsendelsesstatusBuilder withInnsendingId(String innsendingId) {
      this.innsendingId = innsendingId;
      return this;
   }

   public ForsendelsesstatusBuilder withForsendelsesreferanse(String forsendelsesreferanse) {
      this.forsendelsesreferanse = forsendelsesreferanse;
      return this;
   }

   public ForsendelsesstatusBuilder withRegistreringstidspunkt(LocalDateTime registreringstidspunkt) {
      this.registreringstidspunkt = registreringstidspunkt;
      return this;
   }

   public ForsendelsesstatusBuilder withBehandlingsutfall(String behandlingsutfall) {
      this.behandlingsutfall = behandlingsutfall;
      return this;
   }

   public ForsendelsesstatusBuilder withSaksstatus(String saksstatus) {
      this.saksstatus = saksstatus;
      return this;
   }

   public ForsendelsesstatusBuilder withTinglysingsinformasjon(Tinglysingsinformasjon tinglysingsinformasjon) {
      this.tinglysingsinformasjon = tinglysingsinformasjon;
      return this;
   }

   public ForsendelsesstatusBuilder but() {
      return aBehandlingsstatus().withBehandlingsinformasjon(behandlingsinformasjon).withInnsendingId(innsendingId)
            .withForsendelsesreferanse(forsendelsesreferanse)
            .withRegistreringstidspunkt(registreringstidspunkt).withBehandlingsutfall(behandlingsutfall)
            .withSaksstatus(saksstatus).withTinglysingsinformasjon(tinglysingsinformasjon);
   }

   public Forsendelsesstatus build() {
      Forsendelsesstatus forsendelsesstatus = new Forsendelsesstatus();
      forsendelsesstatus.setBehandlingsinformasjon(behandlingsinformasjon);
      forsendelsesstatus.setInnsendingId(innsendingId);
      forsendelsesstatus.setForsendelsesreferanse(forsendelsesreferanse);
      forsendelsesstatus.setRegistreringstidspunkt(java.time.LocalDateTime.ofInstant(registreringstidspunkt.toDate().toInstant(), TimeZone.getDefault().toZoneId()));
      forsendelsesstatus.setBehandlingsutfall(behandlingsutfall);
      forsendelsesstatus.setSaksstatus(saksstatus);
      forsendelsesstatus.setTinglysingsinformasjon(tinglysingsinformasjon);
      return forsendelsesstatus;
   }

   public static ForsendelsesstatusBuilder defaultForsendelsesstatus() {
      return aBehandlingsstatus()
            .withInnsendingId(DEFAULT_INNSENDING_ID)
            .withForsendelsesreferanse(DEFAULT_FORSENDELSESREFERANSE)
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
                        .withPaavirkerRegisterenheter(Lists.newArrayList(RegisterenhetBuilder.aRegisterenhet()
                              .withMatrikkelenhet(MatrikkelenhetBuilder.aMatrikkelenhet()
                                    .withKommunenummer("0233")
                                    .withGaardsnummer(12)
                                    .withBruksnummer(13)
                                    .build())
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
                        .withDokumentreferanser(SIGNERT_GRUNNBOKSUTSKRIFT_DOKUMENTREFERANSE)
                        .build()))
                  .build())
            .withBehandlingsinformasjon(BehandlingsinformasjonBuilder.anAvvisningsinformasjon()
                  .withKontrollresultater(Lists.newArrayList(KontrollresultatBuilder.aKontrollresultat()
                        .withBegrunnelser(Lists.newArrayList(BegrunnelseBuilder.aBegrunnelse()
                              .withKodeverdi(BEGRUNNELSE_KODEVERDI)
                              .withElementnavn(BEGRUNNELSE_ELEMENTNAVN)
                              .withTekst(BEGRUNNELSE_TEKST)
                              .withUtfall(BEGRUNNELSE_UTFALL)
                              .build()))
                        .withDokumentindeks(KONTROLL_RESULTAT_DOKUMENTINDEKS)
                        .withKodeverdi(KONTROLL_RESULTAT_KODEVERDI)
                        .withNavn(KONTROLL_RESULTAT_NAVN)
                        .withRettsstiftelsesindeks(KONTROLL_RESULTAT_RETTSSTIFTELSESINDEKS)
                        .withUtfall(KONTROLL_RESULTAT_UTFALL)
                        .build()))
                  .build());
   }

   /**
    * @return Base64 encodet SDO
    */
   static byte[] psedudoSDO() {
      try {
         final byte[] bytes = ByteStreams.toByteArray(InnsendingServiceWSStub.class.getClassLoader().getResourceAsStream("sdo/eksempel-SDOv1.0.xml"));
         return bytes;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
