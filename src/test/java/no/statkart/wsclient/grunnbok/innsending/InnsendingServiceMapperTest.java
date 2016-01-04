package no.statkart.wsclient.grunnbok.innsending;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokument;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Foelgebrev;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Kode;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhetsendring;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Referanse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Rettsstiftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.UsignertMelding;
import no.statkart.wsclient.grunnbok.innsending.domene.Avvisningsinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.Begrunnelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokumentinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Kontrollresultat;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelsesinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.SDODokument;
import no.statkart.wsclient.grunnbok.innsending.domene.SignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbok.innsending.domene.Tinglysingsinformasjon;
import no.statkart.wsclient.grunnbok.innsending.testdatafactory.ForsendelseFactory;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static no.statkart.wsclient.grunnbok.innsending.testdatafactory.ForsendelseFactory.*;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.ForsendelsesstatusBuilder.*;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.ForsendelsesstatusBuilder.DOKUMENTREFERANSE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class InnsendingServiceMapperTest {

   public void mapFromForsendelseToWSStructure() {
      Forsendelse forsendelse = defaultForsendelse().build();
      no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse wsForsendelse = new InnsendingServiceMapper().mapForsendelse(forsendelse);

      assertEquals(wsForsendelse.getForsendelsesreferanse(), FORSENDELSESREFERANSE);

      UsignertMelding usignertMelding = wsForsendelse.getUsignertMelding();
      Foelgebrev foelgebrev = usignertMelding.getFoelgebrev();
      assertEquals(foelgebrev.getInnsendersIdentifikasjonsnummer(), FOELGE_BREV_INNSENDERS_IDENTIFIKASJONSNUMMER);

      List<Referanse> referanseList = foelgebrev.getDokumentrekkefoelge().getReferanse();
      assertEquals(referanseList.size(), 1);
      Referanse referanse = referanseList.get(0);
      assertEquals(referanse.getGjelderDokumentreferanse(), REFERANSE_GJELDER_DOKUMENTREFERANSE);
      assertEquals(referanse.getDigestAlgoritme(), REFERANSE_DIGEST_ALGORITME);
      assertEquals(referanse.getDigest(), REFERANSE_DIGEST);

      List<Dokument> dokumentList = usignertMelding.getDokumenter().getDokument();
      assertEquals(dokumentList.size(), 1);
      Dokument dokument = dokumentList.get(0);
      assertEquals(dokument.getDokumentreferanse(), ForsendelseFactory.DOKUMENTREFERANSE);

      List<JAXBElement<? extends Rettsstiftelse>> rettstiftelser = dokument.getRettsstiftelser().getEierskifteMatrikkelenhetOrOverdragelseAvFesterettOrEierskifteBorettslagsandel();
      assertEquals(rettstiftelser.size(), 1);
      assertMatrikkelenhetsendring((Matrikkelenhetsendring) rettstiftelser.get(0).getValue());
   }

   public void mapFromWSResponseToBehandlingsstatus() {
      no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelsesstatus wsForsendelsesstatus = defaultForsendelsesstatus().build();
      Forsendelsesstatus forsendelsesstatus = new InnsendingServiceMapper().mapForsendelsesstatus(wsForsendelsesstatus);

      assertEquals(forsendelsesstatus.getInnsendingId(), DEFAULT_INNSENDING_ID);
      assertEquals(forsendelsesstatus.getForsendelsesreferanse(), DEFAULT_FORSENDELSESREFERANSE);
      assertTrue(forsendelsesstatus.getRegistreringstidspunkt().isEqual(DEFAULT_REGISTRERINGS_TIDSPUNKT));
      assertEquals(forsendelsesstatus.getBehandlingsutfall(), DEFAULT_BEHANDLINGSUTFALL);
      assertEquals(forsendelsesstatus.getSaksstatus(), DEFAULT_SAKS_STATUS);

      assertTinglysingsinformasjon(forsendelsesstatus.getTinglysingsinformasjon());
      assertAvvisningsinformasjon(forsendelsesstatus.getAvvisningsinformasjon());
   }

   private static void assertTinglysingsinformasjon(Tinglysingsinformasjon tinglysingsinformasjon) {
      List<Dokumentinformasjon> dokumentinformasjonList = tinglysingsinformasjon.getDokumentinformasjon();
      assertEquals(dokumentinformasjonList.size(), 1);

      Dokumentinformasjon dokumentinformasjon = dokumentinformasjonList.get(0);
      assertEquals(dokumentinformasjon.getDokumentaar(), DOKUMENTAAR);
      assertEquals(dokumentinformasjon.getEmbetenummer(), EMBETENUMMER);
      assertEquals(dokumentinformasjon.getDokumentnummer(), DOKUMENTNUMMER);
      assertEquals(dokumentinformasjon.getDokumentreferanse(), DOKUMENTREFERANSE);
      List<Rettsstiftelsesinformasjon> rettsstiftelsesinformasjonList = dokumentinformasjon.getRettsstiftelsesinformasjon();
      assertEquals(rettsstiftelsesinformasjonList.size(), 1);

      Rettsstiftelsesinformasjon rettsstiftelsesinformasjon = rettsstiftelsesinformasjonList.get(0);
      assertEquals(rettsstiftelsesinformasjon.getRettsstiftelsesnummer(), RETTSSTIFTELSESNUMMER, 0);
      assertEquals(rettsstiftelsesinformasjon.getRettsstiftelsesreferanse(), RETTSSTIFTELSESREFERANSE);

      List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter = tinglysingsinformasjon.getSignerteGrunnboksutskrifter();
      assertEquals(signerteGrunnboksutskrifter.size(), 1);
      SignertGrunnboksutskrift signertGrunnboksutskrift = signerteGrunnboksutskrifter.get(0);

      Registerenhet gjelderFor = signertGrunnboksutskrift.getGjelderFor();
      Matrikkelenhet matrikkelenhet = gjelderFor.getMatrikkelenhet();
      assertEquals(matrikkelenhet.getBruksnummer(), BRUKSNUMMER);
      assertEquals(matrikkelenhet.getFestenummer(), FESTENUMMER);
      assertEquals(matrikkelenhet.getGaardsnummer(), GAARDSNUMMER);
      assertEquals(matrikkelenhet.getKommunenavn(), KOMMUNENAVN);
      assertEquals(matrikkelenhet.getKommunenummer(), KOMMUNENUMMER);
      assertEquals(matrikkelenhet.getSeksjonsnummer(), SEKSJONSNUMMER);

      SDODokument signertUtskrift = signertGrunnboksutskrift.getSignertUtskrift();
      assertEquals(signertUtskrift.getSignertDokument(), SIGNERT_DOKUMENT_BYTES);

      assertEquals(signertGrunnboksutskrift.getDokumentreferanse(), SIGNERT_GRUNNBOKSUTSKRIFT_DOKUMENTREFERANSE);
   }

   private static void assertAvvisningsinformasjon(Avvisningsinformasjon avvisningsinformasjon) {
      assertEquals(avvisningsinformasjon.getKontrollresultater().size(), 1);
      Kontrollresultat kontrollresultat = avvisningsinformasjon.getKontrollresultater().get(0);
      List<Begrunnelse> begrunnelser = kontrollresultat.getBegrunnelser();

      Begrunnelse begrunnelse = begrunnelser.get(0);
      assertEquals(begrunnelse.getKodeverdi(), BEGRUNNELSE_KODEVERDI);
      assertEquals(begrunnelse.getElementnavn(), BEGRUNNELSE_ELEMENTNAVN);
      assertEquals(begrunnelse.getTekst(), BEGRUNNELSE_TEKST);

      assertEquals(kontrollresultat.getDokumentindeks(), KONTROLL_RESULTAT_DOKUMENTINDEKS, 0);
      assertEquals(kontrollresultat.getKodeverdi(), KONTROLL_RESULTAT_KODEVERDI);
      assertEquals(kontrollresultat.getNavn(), KONTROLL_RESULTAT_NAVN);
      assertEquals(kontrollresultat.getRettsstiftelsesindeks(), KONTROLL_RESULTAT_RETTSSTIFTELSESINDEKS, 0);
      assertEquals(kontrollresultat.getUtfall(), KONTROLL_RESULTAT_UTFALL);
   }

   private static void assertMatrikkelenhetsendring(Matrikkelenhetsendring matrikkelenhetsendring) {
      assertEquals(matrikkelenhetsendring.getRettsstiftelsesreferanse(), MATRIKKELENHETSENDRING_RETTSSTIFTELSESREFERANSE);
      assertKode(matrikkelenhetsendring.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);

      List<no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhet> fraMatrikkelenheter = matrikkelenhetsendring.getFra().getMatrikkelenhet();
      assertEquals(fraMatrikkelenheter.size(), 1);
      assertMatrikkelenhet(fraMatrikkelenheter.get(0), GAARDSNUMMER_OSLO, SEKSJONSNUMMER_OSLO, BRUKSNUMMER_OSLO,
            FESTENUMMER_OSLO, KOMMUNENAVN_OSLO, KOMMUNENUMMER_OSLO);

      List<no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhet> tilMatrikkelenheter = matrikkelenhetsendring.getTil().getMatrikkelenhet();
      assertEquals(tilMatrikkelenheter.size(), 1);
      assertMatrikkelenhet(tilMatrikkelenheter.get(0), GAARDSNUMMER_OSLO, TIL_MATRIKKELENHET_SEKSJONSNUMMER, BRUKSNUMMER_OSLO,
            FESTENUMMER_OSLO, KOMMUNENAVN_OSLO, KOMMUNENUMMER_OSLO);

   }

   private static void assertMatrikkelenhet(no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhet matrikkelenhet, int gaardsnummer, int seksjonsnummer, int bruksnummer, int festenummer,
                                            String kommunenavn, String kommunenummer) {
      assertEquals(matrikkelenhet.getGaardsnummer(), gaardsnummer);
      assertEquals(matrikkelenhet.getSeksjonsnummer(), seksjonsnummer);
      assertEquals(matrikkelenhet.getBruksnummer(), bruksnummer);
      assertEquals(matrikkelenhet.getFestenummer(), festenummer);
      assertEquals(matrikkelenhet.getKommunenavn(), kommunenavn);
      assertEquals(matrikkelenhet.getKommunenummer(), kommunenummer);
   }

   private static void assertKode(Kode kode, String navn, String kodeverdi) {
      assertEquals(kode.getNavn(), navn);
      assertEquals(kode.getKodeverdi(), kodeverdi);
   }

}
