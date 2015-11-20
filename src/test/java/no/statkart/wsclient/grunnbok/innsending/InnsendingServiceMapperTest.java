package no.statkart.wsclient.grunnbok.innsending;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.AnmerkningPaaPerson;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.AnnenHeftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.AvholdtTvangsforretning;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Beloep;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Borettslagsandel;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.DelAvRegisterenhetsrett;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.DelAvRett;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokument;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokumentavgiftsplikt;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.ErklaeringOmSivilstand;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Foelgebrev;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Kode;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.MatrikkelenhetFraTil;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhetsendring;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.OmsattRegisterenhetsrett;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.OmsatteRegisterenhetsrettsandeler;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Omsetning;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.OverdragelseAvRegisterenhetsrett;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Pant;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Person;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Referanse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhetsrett;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhetsrettsandel;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Rettsstiftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.RettsstiftelseReferanse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Signatur;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.*;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SignertMelding;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Sletting;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Tekst;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Tvangsforretning;
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
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.ForsendelseBuilder;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.ForsendelseBuilder.*;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.ForsendelsesstatusBuilder.*;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.ForsendelsesstatusBuilder.DOKUMENTREFERANSE;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.ForsendelsesstatusBuilder.RETTSSTIFTELSESNUMMER;
import static org.testng.Assert.*;

@Test
public class InnsendingServiceMapperTest {

   public void mapFromForsendelseToWSStructure() {
      Forsendelse forsendelse = defaultForsendelse().build();
      no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse wsForsendelse = new InnsendingServiceMapper().mapForsendelse(forsendelse);

      assertEquals(wsForsendelse.getForsendelsesreferanse(), FORSENDELSESREFERANSE);

      assertSignertMelding(wsForsendelse);
      assertIkkeDigitaleSignaturer(wsForsendelse);

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
      assertEquals(dokument.getDokumentreferanse(), ForsendelseBuilder.DOKUMENTREFERANSE);

      List<JAXBElement<? extends Rettsstiftelse>> rettstiftelser = dokument.getRettsstiftelser().getEierskifteMatrikkelenhetOrOverdragelseAvFesterettOrEierskifteBorettslagsandel();
      assertEquals(rettstiftelser.size(), 7);
      assertAnmerkningPaaPerson((AnmerkningPaaPerson) rettstiftelser.get(0).getValue());
      assertMatrikkelenhetsendring((Matrikkelenhetsendring) rettstiftelser.get(1).getValue());
      assertOverdragelseAvRegisterenhetsrett((OverdragelseAvRegisterenhetsrett) rettstiftelser.get(2).getValue());
      assertPant((Pant) rettstiftelser.get(3).getValue());
      assertAnnenHeftelse((AnnenHeftelse) rettstiftelser.get(4).getValue());
      assertTvangsforretning((Tvangsforretning) rettstiftelser.get(5).getValue());
      assertSletting((Sletting) rettstiftelser.get(6).getValue());
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

   private static void assertSletting(Sletting sletting) {
      assertEquals(sletting.getRettsstiftelsesreferanse(), SLETTING_RETTSSTIFTELSESREFERANSE);

      List<DelAvRett> delAvRettList = sletting.getEndrer().getDelAvRett();
      assertEquals(delAvRettList.size(), 1);
      DelAvRett delAvRett = delAvRettList.get(0);

      List<DelAvRegisterenhetsrett> delAvRegisterenhetsrettList = delAvRett.getBegrensetTil().getDelAvRegisterenhetsrett();
      assertEquals(delAvRegisterenhetsrettList.size(), 1);

      RettsstiftelseReferanse rettsstiftelse = delAvRett.getRettsstiftelse();
      assertEquals(rettsstiftelse.getDokumentaar(), RETTSSTIFTELSE_DOKUMENTAAR);
      assertEquals(rettsstiftelse.getDokumentnummer(), RETTSSTIFTELSE_DOKUMENTNUMMER);
      assertEquals(rettsstiftelse.getEmbetenummer(), RETTSSTIFTELSE_EMBETENUMMER);
      assertEquals(rettsstiftelse.getRettsstiftelsesnummer(), ForsendelseBuilder.RETTSSTIFTELSESNUMMER);
      assertEquals(rettsstiftelse.getRettsstiftelsestype().getKodeverdi(), RETTSSTIFTELSE_RETTSTIFTELSESTYPE_KODEVERDI);
   }

   private static void assertTvangsforretning(Tvangsforretning tvangsforretning) {
      assertEquals(tvangsforretning.getRettsstiftelsesreferanse(), TVANGSFORRETNING_RETTSSTIFTELSESREFERANSE);

      AvholdtTvangsforretning avholdtTvangsforretning = tvangsforretning.getAvholdtTvangsforretning();
      assertTrue(avholdtTvangsforretning.getAvholdtDato().isEqual(OCTOBER_15));
      assertTrue(avholdtTvangsforretning.getAvholdtKlokkeslett().isEqual(TIME_OF_DAY));
      assertEquals(avholdtTvangsforretning.getProsessfullmektiger().getPerson().get(0).getNavn(), PROSESS_FULLMEKTIG_NAVN);
      assertEquals(avholdtTvangsforretning.getSaksoekere().getPerson().get(0).getNavn(), SAKSOEKER_NAVN);
      assertEquals(avholdtTvangsforretning.getSaksoekte().getPerson().get(0).getNavn(), SAKSOEKT_NAVN);
   }

   private static void assertAnnenHeftelse(AnnenHeftelse annenHeftelse) {
      assertEquals(annenHeftelse.getRettsstiftelsesreferanse(), ANNEN_HEFTELSE_RETTSSTIFTELSESREFERANSE);
   }

   private static void assertPant(Pant pant) {
      assertEquals(pant.getRettsstiftelsesreferanse(), PANT_RETTSSTIFTELSESREFERANSE);
      assertKode(pant.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);
      assertEquals(pant.getBeloep().getBeloep().get(0).getBeloepsverdi(), DEFAULT_BELOEPSVERDI);

      assertEquals(pant.getErklaeringerOmSivilstand().getErklaeringOmSivilstand().get(0).getEktefellePartner().getNavn(), DEFAULT_PERSON_NAVN);

      List<DelAvRegisterenhetsrett> delAvRegisterenhetsrettList = pant.getHefterI().getDelAvRegisterenhetsrett();
      DelAvRegisterenhetsrett delAvRegisterenhetsrett = delAvRegisterenhetsrettList.get(0);
      List<Registerenhetsrettsandel> registerenhetsrettsandelList = delAvRegisterenhetsrett.getBegrensetTil().getRegisterenhetsrettsandel();
      assertEquals(registerenhetsrettsandelList.get(0).getNevner(), REGISTERENHETSRETTSANDEL_DEFAULT_NEVNER);

      List<Registerenhetsrett> registerenhetsrettList = pant.getRealkobletTil().getRegisterenhetsrett();
      assertEquals(registerenhetsrettList.size(), 1);
      Registerenhetsrett registerenhetsrett = registerenhetsrettList.get(0);
      assertEquals(registerenhetsrett.getRegisterenhetsrettstype().getKodeverdi(), REGISTERENHETSRETTSTYPE_KODEVERDI);
   }

   private static void assertOverdragelseAvRegisterenhetsrett(OverdragelseAvRegisterenhetsrett overdragelse) {
      assertEquals(overdragelse.getRettsstiftelsesreferanse(), OVERDRAGELSE_RETTSSTIFTELSESREFERANSE);
      assertKode(overdragelse.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);
      List<Tekst> tekstList = overdragelse.getTekster().getTekst();
      assertEquals(tekstList.size(), 1);
      assertTekst(tekstList.get(0), FRITEKST, TEKST_TYPE_NAVN, TEKST_TYPE_KODEVERDI);

      List<ErklaeringOmSivilstand> erklaeringOmSivilstandList = overdragelse.getErklaeringerOmSivilstand().getErklaeringOmSivilstand();
      assertEquals(erklaeringOmSivilstandList.size(), 1);
      ErklaeringOmSivilstand erklaeringOmSivilstand = erklaeringOmSivilstandList.get(0);
      assertPerson(erklaeringOmSivilstand.getEktefellePartner(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      assertPerson(erklaeringOmSivilstand.getErklaeringFor(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      assertEquals(erklaeringOmSivilstand.isKreverSamtykke(), KREVER_SAMTYKKE);

      Omsetning omsetning = overdragelse.getOmsetning();
      assertEquals(omsetning.isUtlystTilSalgPaaDetFrieMarked(), Boolean.valueOf(UTLYST_TIL_SALG_PAA_DET_FRIE_MARKED));

      Kode omsetningstype = omsetning.getOmsetningstype();
      assertEquals(omsetningstype.getNavn(), OMSETNING_OMSETNINGSTYPE_NAVN);

      Beloep vederlag = omsetning.getVederlag();
      assertEquals(vederlag.getBeloepstekst(), VEDERLAG_BELOEPSTEKST);
      assertEquals(vederlag.getBeloepsverdi(), VEDERLAG_BELOEPSVERDI);
      Kode valutakode = vederlag.getValutakode();
      assertEquals(valutakode.getNavn(), VEDERLAG_VALUTAKODE_NAVN);
      assertEquals(valutakode.getKodeverdi(), DEFAULT_KODEVERDI);

      Dokumentavgiftsplikt dokumentavgiftsplikt = omsetning.getDokumentavgiftsplikt();
      Kode dokumentavgiftsaarsak = dokumentavgiftsplikt.getDokumentavgiftsaarsak();
      assertEquals(dokumentavgiftsaarsak.getKodeverdi(), DOKUMENTAVGIFTSAARSAK_KODEVERDI);
      Beloep dokumentavgiftsgrunnlag = dokumentavgiftsplikt.getDokumentavgiftsgrunnlag();
      assertEquals(dokumentavgiftsgrunnlag.getBeloepstekst(), DOKUMENT_AVGIFTSGRUNNLAG_BELOEPSTEKST);
      assertEquals(dokumentavgiftsgrunnlag.getBeloepsverdi(), DOKUMENT_AVGIFTSGRUNNLAG_BELOEPSVERDI);
      assertEquals(dokumentavgiftsgrunnlag.getValutakode().getKodeverdi(), DOKUMENT_AVGIFTSGRUNNLAG_KODEVERDI);

      List<OmsattRegisterenhetsrett> omsattRegisterenhetsrettList = omsetning.getOmsatteRegisterenhetsretter().getOmsattRegisterenhetsrett();
      assertEquals(omsattRegisterenhetsrettList.size(), 1);
      OmsattRegisterenhetsrett omsattRegisterenhetsrett = omsattRegisterenhetsrettList.get(0);
      Kode boligtype = omsattRegisterenhetsrett.getBoligtype();
      assertEquals(boligtype.getKodeverdi(), BOLIG_TYPE_KODEVERDI);

      Kode brukstype = omsattRegisterenhetsrett.getBrukstype();
      assertEquals(brukstype.getKodeverdi(), BRUKSTYPE_KODEVERDI);

      OmsatteRegisterenhetsrettsandeler omsatteRegisterenhetsrettsandeler = omsattRegisterenhetsrett.getOmsatteRegisterenhetsrettsandeler();
      List<Registerenhetsrettsandel> kjopteAndeler = omsatteRegisterenhetsrettsandeler.getKjoepte().getRegisterenhetsrettsandel();
      assertEquals(kjopteAndeler.size(), 1);
      Registerenhetsrettsandel kjoptAndel = kjopteAndeler.get(0);
      assertEquals(kjoptAndel.getTeller(), KJOPT_ANDEL_TELLER);
      assertEquals(kjoptAndel.getNevner(), KJOPT_ANDEL_NEVNER);
      assertPerson(kjoptAndel.getRettighetshaver(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhet rettighetshaverRegisterenhet = kjoptAndel.getRealkobletTil();
      Borettslagsandel borettslagsandel = rettighetshaverRegisterenhet.getBorettslagsandel();
      assertEquals(borettslagsandel.getAndelsnummer(), KJOPT_ANDEL_BORETTSLAGS_ANDELSNUMMER);
      assertEquals(borettslagsandel.getBorettslagnavn(), KJOPT_ANDEL_BORETTSLAGSNAVN);
      assertEquals(borettslagsandel.getBorettslagnummer(), KJOPT_ANDEL_BORETTSLAGNUMMER);

      assertNull(rettighetshaverRegisterenhet.getMatrikkelenhet());

      List<Registerenhetsrettsandel> solgte = omsatteRegisterenhetsrettsandeler.getSolgte().getRegisterenhetsrettsandel();
      assertEquals(solgte.size(), 1);
      Registerenhetsrettsandel solgtAndel = solgte.get(0);
      assertEquals(solgtAndel.getTeller(), SOLGT_ANDEL_TELLER);
      assertEquals(solgtAndel.getNevner(), SOLGT_ANDEL_NEVNER);
      assertPerson(solgtAndel.getRettighetshaver(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhet solgtAndelRegisterenhet = solgtAndel.getRealkobletTil();
      Borettslagsandel solgtAndelBorettslagsandel = solgtAndelRegisterenhet.getBorettslagsandel();
      assertEquals(solgtAndelBorettslagsandel.getAndelsnummer(), SOLGT_ANDEL_BORETTSLAGSANDELSNUMMER);
      assertEquals(solgtAndelBorettslagsandel.getBorettslagnavn(), SOLGT_ANDEL_BORETTSLAGSNAVN);
      assertEquals(solgtAndelBorettslagsandel.getBorettslagnummer(), SOLGT_ANDEL_BORETTSLAGNUMMER);

      assertNull(solgtAndelRegisterenhet.getMatrikkelenhet());

      Registerenhetsrett registerenhetsrett = omsattRegisterenhetsrett.getRegisterenhetsrett();
      Kode registerenhetsrettstype = registerenhetsrett.getRegisterenhetsrettstype();
      assertEquals(registerenhetsrettstype.getKodeverdi(), REGISTERENHETSRETTSTYPE_KODEVERDI);
   }

   private static void assertMatrikkelenhetsendring(Matrikkelenhetsendring matrikkelenhetsendring) {
      assertEquals(matrikkelenhetsendring.getRettsstiftelsesreferanse(), MATRIKKELENHETSENDRING_RETTSSTIFTELSESREFERANSE);
      assertKode(matrikkelenhetsendring.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);
      List<Tekst> tekstList = matrikkelenhetsendring.getTekster().getTekst();
      assertEquals(tekstList.size(), 1);
      assertTekst(tekstList.get(0), FRITEKST, TEKST_TYPE_NAVN, TEKST_TYPE_KODEVERDI);

      List<no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhet> fraMatrikkelenheter = matrikkelenhetsendring.getFra().getMatrikkelenhet();
      assertEquals(fraMatrikkelenheter.size(), 1);
      assertMatrikkelenhet(fraMatrikkelenheter.get(0), GAARDSNUMMER_OSLO, SEKSJONSNUMMER_OSLO, BRUKSNUMMER_OSLO,
            FESTENUMMER_OSLO, KOMMUNENAVN_OSLO, KOMMUNENUMMER_OSLO);

      List<no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhet> tilMatrikkelenheter = matrikkelenhetsendring.getTil().getMatrikkelenhet();
      assertEquals(tilMatrikkelenheter.size(), 1);
      assertMatrikkelenhet(tilMatrikkelenheter.get(0), GAARDSNUMMER_OSLO, TIL_MATRIKKELENHET_SEKSJONSNUMMER, BRUKSNUMMER_OSLO,
            FESTENUMMER_OSLO, KOMMUNENAVN_OSLO, KOMMUNENUMMER_OSLO);

      List<MatrikkelenhetFraTil> omnummereringsListe = matrikkelenhetsendring.getOmnummereringAvUnderliggende().getMatrikkelenhetFraTil();
      assertEquals(omnummereringsListe.size(), 1);
      MatrikkelenhetFraTil matrikkelenhetFraTil = omnummereringsListe.get(0);
      assertEquals(matrikkelenhetFraTil.getFra().getBruksnummer(), MATRIKKELENHET_FRA_TIL_FRA_BRUKSNUMMER);
      assertEquals(matrikkelenhetFraTil.getTil().getKommunenummer(), MATRIKKELENHET_FRA_TIL_TIL_KOMMUNENUMMER);
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

   private static void assertAnmerkningPaaPerson(AnmerkningPaaPerson anmerkningPaaPerson) {
      assertEquals(anmerkningPaaPerson.getRettsstiftelsesreferanse(), ANMERKNING_PAA_PERSON_RETTSTIFTELSESREFERANSE);
      assertEquals(anmerkningPaaPerson.getSaksnummer(), ANMERKNING_PAA_PERSON_SAKSNUMMER);

      assertPerson(anmerkningPaaPerson.getAnmerketPerson(), ANMERKET_PERSON_IDENTIFIKASJONSNUMMER, ANMERKET_PERSON_NAVN);
      assertPerson(anmerkningPaaPerson.getBostyrer(), BOSTYRER_IDENTIFIKASJONSNUMMER, BOSTYRER_NAVN);
      assertPerson(anmerkningPaaPerson.getKonkursbo(), KONKURS_BO_IDENTIFIKASJONSNUMMER, KONKURS_BO_NAVN);

      assertKode(anmerkningPaaPerson.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);

      List<Tekst> tekstList = anmerkningPaaPerson.getTekster().getTekst();
      assertEquals(tekstList.size(), 1);
      assertTekst(tekstList.get(0), FRITEKST, TEKST_TYPE_NAVN, TEKST_TYPE_KODEVERDI);
   }

   private static void assertTekst(Tekst tekst, String fritekst, String tekstTypeNavn, String tekstTypeKodeverdi) {
      assertEquals(tekst.getFritekst(), fritekst);
      Kode teksttype = tekst.getTeksttype();
      assertEquals(teksttype.getNavn(), tekstTypeNavn);
      assertEquals(teksttype.getKodeverdi(), tekstTypeKodeverdi);
   }

   private static void assertKode(Kode kode, String navn, String kodeverdi) {
      assertEquals(kode.getNavn(), navn);
      assertEquals(kode.getKodeverdi(), kodeverdi);
   }

   private static void assertIkkeDigitaleSignaturer(no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse wsForsendelse) {
      SignaturList ikkeDigitaleSignaturer = wsForsendelse.getIkkeDigitaleSignaturer();
      List<Signatur> signaturList = ikkeDigitaleSignaturer.getSignatur();
      assertEquals(signaturList.size(), 1);
      Signatur signatur = signaturList.get(0);
      assertEquals(signatur.getGjelderDokumentreferanse(), GJELDER_DOKUMENTREFERANSE);
      assertEquals(signatur.getPersonidentifikasjonsnummer(), PERSONIDENTIFIKASJONSNUMMER);
   }

   private static void assertSignertMelding(no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse wsForsendelse) {
      SignertMelding signertMelding = wsForsendelse.getSignertMelding();
      List<no.kartverket.grunnbok.wsapi.v2.domain.innsending.SDODokument> sdoDokument = signertMelding.getDokumenter().getSDODokument();
      assertEquals(sdoDokument.size(), 1);
      assertEquals(sdoDokument.get(0).getSignertDokument(), SIGNERT_MELDING_DOKUMENT);
      assertEquals(signertMelding.getFoelgebrev().getSignertDokument(), SIGNERT_MELDING_FOLGE_BREV);
   }

   private static void assertPerson(Person person, String identifikasjonsnummer, String navn) {
      assertEquals(person.getIdentifikasjonsnummer(), identifikasjonsnummer);
      assertEquals(person.getNavn(), navn);
   }

}
