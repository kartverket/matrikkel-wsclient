package no.statkart.wsclient.grunnbok.innsending;

import no.kartverket.grunnbok.wsapi.internal.domain.innsending.MatrikkelenhetFraTil;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.SignaturList;
import no.statkart.wsclient.grunnbok.innsending.domene.Avvisningsinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.Begrunnelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Behandlingsstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokumentinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Kontrollresultat;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelsesinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.SDODokument;
import no.statkart.wsclient.grunnbok.innsending.domene.SignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbok.innsending.domene.Tinglysingsinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.ForsendelseBuilder;
import no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static no.statkart.wsclient.grunnbok.innsending.domene.builder.ForsendelseBuilder.*;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.BEGRUNNELSE_ELEMENTNAVN;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.BEGRUNNELSE_KODEVERDI;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.BEGRUNNELSE_TEKST;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.BRUKSNUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.DEFAULT_BEHANDLINGSUTFALL;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.DEFAULT_INNSENDING_ID;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.DEFAULT_REGISTRERINGS_TIDSPUNKT;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.DEFAULT_SAKS_STATUS;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.DOKUMENTAAR;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.DOKUMENTNUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.DOKUMENTREFERANSE;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.EMBETENUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.FESTENUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.GAARDSNUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.KOMMUNENAVN;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.KOMMUNENUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.KONTROLL_RESULTAT_DOKUMENTINDEKS;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.KONTROLL_RESULTAT_KODEVERDI;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.KONTROLL_RESULTAT_NAVN;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.KONTROLL_RESULTAT_RETTSSTIFTELSESINDEKS;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.KONTROLL_RESULTAT_UTFALL;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.RETTSSTIFTELSESNUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.RETTSSTIFTELSESREFERANSE;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.SEKSJONSNUMMER;
import static no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder.SIGNERT_DOKUMENT_BYTES;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

@Test
public class InnsendingServiceMapperTest {

   public void mapFromForsendelseToWSStructure() {
      Forsendelse forsendelse = ForsendelseBuilder.defaultForsendelse().build();
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Forsendelse wsForsendelse = new InnsendingServiceMapper().mapForsendelse(forsendelse);

      assertEquals(wsForsendelse.getInnsendingId(), INNSENDING_ID);
      assertEquals(wsForsendelse.getInnsendersReferanse(), INNSENDERS_REFERANSE);

      assertSignertMelding(wsForsendelse);
      assertIkkeDigitaleSignaturer(wsForsendelse);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.UsignertMelding usignertMelding = wsForsendelse.getUsignertMelding();
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Foelgebrev foelgebrev = usignertMelding.getFoelgebrev();
      assertEquals(foelgebrev.getInnsendersIdentifikasjonsnummer(), FOELGE_BREV_INNSENDERS_IDENTIFIKASJONSNUMMER);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Referanse> referanseList = foelgebrev.getDokumentrekkefoelge().getReferanse();
      assertEquals(referanseList.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Referanse referanse = referanseList.get(0);
      assertEquals(referanse.getGjelderDokumentreferanse(), REFERANSE_GJELDER_DOKUMENTREFERANSE);
      assertEquals(referanse.getDigestAlgoritme(), REFERANSE_DIGEST_ALGORITME);
      assertEquals(referanse.getDigest(), REFERANSE_DIGEST);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokument> dokumentList = usignertMelding.getDokumenter().getDokument();
      assertEquals(dokumentList.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokument dokument = dokumentList.get(0);
      assertEquals(dokument.getDokumentreferanse(), ForsendelseBuilder.DOKUMENTREFERANSE);

      List<JAXBElement<? extends no.kartverket.grunnbok.wsapi.internal.domain.innsending.Rettsstiftelse>> rettstiftelser = dokument.getRettsstiftelser().getEierskifteMatrikkelenhetOrOverdragelseAvFesterettOrEierskifteBorettslagsandel();
      assertEquals(rettstiftelser.size(), 7);
      assertAnmerkningPaaPerson((no.kartverket.grunnbok.wsapi.internal.domain.innsending.AnmerkningPaaPerson) rettstiftelser.get(0).getValue());
      assertMatrikkelenhetsendring((no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhetsendring) rettstiftelser.get(1).getValue());
      assertOverdragelseAvRegisterenhetsrett((no.kartverket.grunnbok.wsapi.internal.domain.innsending.OverdragelseAvRegisterenhetsrett) rettstiftelser.get(2).getValue());
      assertPant((no.kartverket.grunnbok.wsapi.internal.domain.innsending.Pant) rettstiftelser.get(3).getValue());
      assertAnnenHeftelse((no.kartverket.grunnbok.wsapi.internal.domain.innsending.AnnenHeftelse) rettstiftelser.get(4).getValue());
      assertTvangsforretning((no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tvangsforretning) rettstiftelser.get(5).getValue());
      assertSletting((no.kartverket.grunnbok.wsapi.internal.domain.innsending.Sletting) rettstiftelser.get(6).getValue());
   }

   public void mapFromWSResponseToBehandlingsstatus() {
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Behandlingsstatus wsBehandlingsstatus = BehandlingsstatusBuilder.defaultBehandlingsstatus().build();
      Behandlingsstatus behandlingsstatus = new InnsendingServiceMapper().mapBehandlingsstatus(wsBehandlingsstatus);

      assertEquals(behandlingsstatus.getInnsendingId(), DEFAULT_INNSENDING_ID);
      assertTrue(behandlingsstatus.getRegistreringstidspunkt().isEqual(DEFAULT_REGISTRERINGS_TIDSPUNKT));
      assertEquals(behandlingsstatus.getBehandlingsutfall(), DEFAULT_BEHANDLINGSUTFALL);
      assertEquals(behandlingsstatus.getSaksstatus(), DEFAULT_SAKS_STATUS);

      assertTinglysingsinformasjon(behandlingsstatus.getTinglysingsinformasjon());
      assertAvvisningsinformasjon(behandlingsstatus.getAvvisningsinformasjon());
   }

   private void assertTinglysingsinformasjon(Tinglysingsinformasjon tinglysingsinformasjon) {
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
   }

   private void assertAvvisningsinformasjon(Avvisningsinformasjon avvisningsinformasjon) {
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

   private void assertSletting(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Sletting sletting) {
      assertEquals(sletting.getRettsstiftelsesreferanse(), SLETTING_RETTSSTIFTELSESREFERANSE);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRett> delAvRettList = sletting.getEndrer().getDelAvRett();
      assertEquals(delAvRettList.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRett delAvRett = delAvRettList.get(0);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRegisterenhetsrett> delAvRegisterenhetsrettList = delAvRett.getBegrensetTil().getDelAvRegisterenhetsrett();
      assertEquals(delAvRegisterenhetsrettList.size(), 1);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.RettsstiftelseReferanse rettsstiftelse = delAvRett.getRettsstiftelse();
      assertEquals(rettsstiftelse.getDokumentaar(), RETTSSTIFTELSE_DOKUMENTAAR);
      assertEquals(rettsstiftelse.getDokumentnummer(), RETTSSTIFTELSE_DOKUMENTNUMMER);
      assertEquals(rettsstiftelse.getEmbetenummer(), RETTSSTIFTELSE_EMBETENUMMER);
      assertEquals(rettsstiftelse.getRettsstiftelsesnummer(), ForsendelseBuilder.RETTSSTIFTELSESNUMMER);
      assertEquals(rettsstiftelse.getRettsstiftelsestype().getKodeverdi(), RETTSSTIFTELSE_RETTSTIFTELSESTYPE_KODEVERDI);
   }

   private void assertTvangsforretning(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tvangsforretning tvangsforretning) {
      assertEquals(tvangsforretning.getRettsstiftelsesreferanse(), TVANGSFORRETNING_RETTSSTIFTELSESREFERANSE);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.AvholdtTvangsforretning avholdtTvangsforretning = tvangsforretning.getAvholdtTvangsforretning();
      assertTrue(avholdtTvangsforretning.getAvholdtDato().isEqual(OCTOBER_15));
      assertTrue(avholdtTvangsforretning.getAvholdtKlokkeslett().isEqual(TIME_OF_DAY));
      assertEquals(avholdtTvangsforretning.getProsessfullmektiger().getPerson().get(0).getNavn(), PROSESS_FULLMEKTIG_NAVN);
      assertEquals(avholdtTvangsforretning.getSaksoekere().getPerson().get(0).getNavn(), SAKSOEKER_NAVN);
      assertEquals(avholdtTvangsforretning.getSaksoekte().getPerson().get(0).getNavn(), SAKSOEKT_NAVN);
   }

   private void assertAnnenHeftelse(no.kartverket.grunnbok.wsapi.internal.domain.innsending.AnnenHeftelse annenHeftelse) {
      assertEquals(annenHeftelse.getRettsstiftelsesreferanse(), ANNEN_HEFTELSE_RETTSSTIFTELSESREFERANSE);
   }

   private void assertPant(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Pant pant) {
      assertEquals(pant.getRettsstiftelsesreferanse(), PANT_RETTSSTIFTELSESREFERANSE);
      assertKode(pant.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);
      assertEquals(pant.getBeloep().getBeloep().get(0).getBeloepsverdi(), DEFAULT_BELOEPSVERDI);

      assertEquals(pant.getErklaeringerOmSivilstand().getErklaeringOmSivilstand().get(0).getEktefellePartner().getNavn(), DEFAULT_PERSON_NAVN);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRegisterenhetsrett> delAvRegisterenhetsrettList = pant.getHefterI().getDelAvRegisterenhetsrett();
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRegisterenhetsrett delAvRegisterenhetsrett = delAvRegisterenhetsrettList.get(0);
      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrettsandel> registerenhetsrettsandelList = delAvRegisterenhetsrett.getBegrensetTil().getRegisterenhetsrettsandel();
      assertEquals(registerenhetsrettsandelList.get(0).getNevner(), REGISTERENHETSRETTSANDEL_DEFAULT_NEVNER);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrett> registerenhetsrettList = pant.getRealkobletTil().getRegisterenhetsrett();
      assertEquals(registerenhetsrettList.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrett registerenhetsrett = registerenhetsrettList.get(0);
      assertEquals(registerenhetsrett.getRegisterenhetsrettstype().getKodeverdi(), REGISTERENHETSRETTSTYPE_KODEVERDI);
   }

   private void assertOverdragelseAvRegisterenhetsrett(no.kartverket.grunnbok.wsapi.internal.domain.innsending.OverdragelseAvRegisterenhetsrett overdragelse) {
      assertEquals(overdragelse.getRettsstiftelsesreferanse(), OVERDRAGELSE_RETTSSTIFTELSESREFERANSE);
      assertKode(overdragelse.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);
      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tekst> tekstList = overdragelse.getTekster().getTekst();
      assertEquals(tekstList.size(), 1);
      assertTekst(tekstList.get(0), FRITEKST, TEKST_TYPE_NAVN, TEKST_TYPE_KODEVERDI);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.ErklaeringOmSivilstand> erklaeringOmSivilstandList = overdragelse.getErklaeringerOmSivilstand().getErklaeringOmSivilstand();
      assertEquals(erklaeringOmSivilstandList.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.ErklaeringOmSivilstand erklaeringOmSivilstand = erklaeringOmSivilstandList.get(0);
      assertPerson(erklaeringOmSivilstand.getEktefellePartner(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      assertPerson(erklaeringOmSivilstand.getErklaeringFor(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      assertEquals(erklaeringOmSivilstand.isKreverSamtykke(), KREVER_SAMTYKKE);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Omsetning omsetning = overdragelse.getOmsetning();
      assertEquals(omsetning.isUtlystTilSalgPaaDetFrieMarked(), Boolean.valueOf(UTLYST_TIL_SALG_PAA_DET_FRIE_MARKED));

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode omsetningstype = omsetning.getOmsetningstype();
      assertEquals(omsetningstype.getNavn(), OMSETNING_OMSETNINGSTYPE_NAVN);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Beloep vederlag = omsetning.getVederlag();
      assertEquals(vederlag.getBeloepstekst(), VEDERLAG_BELOEPSTEKST);
      assertEquals(vederlag.getBeloepsverdi(), VEDERLAG_BELOEPSVERDI);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode valutakode = vederlag.getValutakode();
      assertEquals(valutakode.getNavn(), VEDERLAG_VALUTAKODE_NAVN);
      assertEquals(valutakode.getKodeverdi(), DEFAULT_KODEVERDI);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokumentavgiftsplikt dokumentavgiftsplikt = omsetning.getDokumentavgiftsplikt();
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode dokumentavgiftsaarsak = dokumentavgiftsplikt.getDokumentavgiftsaarsak();
      assertEquals(dokumentavgiftsaarsak.getKodeverdi(), DOKUMENTAVGIFTSAARSAK_KODEVERDI);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Beloep dokumentavgiftsgrunnlag = dokumentavgiftsplikt.getDokumentavgiftsgrunnlag();
      assertEquals(dokumentavgiftsgrunnlag.getBeloepstekst(), DOKUMENT_AVGIFTSGRUNNLAG_BELOEPSTEKST);
      assertEquals(dokumentavgiftsgrunnlag.getBeloepsverdi(), DOKUMENT_AVGIFTSGRUNNLAG_BELOEPSVERDI);
      assertEquals(dokumentavgiftsgrunnlag.getValutakode().getKodeverdi(), DOKUMENT_AVGIFTSGRUNNLAG_KODEVERDI);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.OmsattRegisterenhetsrett> omsattRegisterenhetsrettList = omsetning.getOmsatteRegisterenhetsretter().getOmsattRegisterenhetsrett();
      assertEquals(omsattRegisterenhetsrettList.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.OmsattRegisterenhetsrett omsattRegisterenhetsrett = omsattRegisterenhetsrettList.get(0);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode boligtype = omsattRegisterenhetsrett.getBoligtype();
      assertEquals(boligtype.getKodeverdi(), BOLIG_TYPE_KODEVERDI);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode brukstype = omsattRegisterenhetsrett.getBrukstype();
      assertEquals(brukstype.getKodeverdi(), BRUKSTYPE_KODEVERDI);

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.OmsatteRegisterenhetsrettsandeler omsatteRegisterenhetsrettsandeler = omsattRegisterenhetsrett.getOmsatteRegisterenhetsrettsandeler();
      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrettsandel> kjopteAndeler = omsatteRegisterenhetsrettsandeler.getKjoepte().getRegisterenhetsrettsandel();
      assertEquals(kjopteAndeler.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrettsandel kjoptAndel = kjopteAndeler.get(0);
      assertEquals(kjoptAndel.getTeller(), KJOPT_ANDEL_TELLER);
      assertEquals(kjoptAndel.getNevner(), KJOPT_ANDEL_NEVNER);
      assertPerson(kjoptAndel.getRettighetshaver(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhet rettighetshaverRegisterenhet = kjoptAndel.getRealkobletTil();
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Borettslagsandel borettslagsandel = rettighetshaverRegisterenhet.getBorettslagsandel();
      assertEquals(borettslagsandel.getAndelsnummer(), KJOPT_ANDEL_BORETTSLAGS_ANDELSNUMMER);
      assertEquals(borettslagsandel.getBorettslagnavn(), KJOPT_ANDEL_BORETTSLAGSNAVN);
      assertEquals(borettslagsandel.getBorettslagnummer(), KJOPT_ANDEL_BORETTSLAGNUMMER);

      assertNull(rettighetshaverRegisterenhet.getMatrikkelenhet());

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrettsandel> solgte = omsatteRegisterenhetsrettsandeler.getSolgte().getRegisterenhetsrettsandel();
      assertEquals(solgte.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrettsandel solgtAndel = solgte.get(0);
      assertEquals(solgtAndel.getTeller(), SOLGT_ANDEL_TELLER);
      assertEquals(solgtAndel.getNevner(), SOLGT_ANDEL_NEVNER);
      assertPerson(solgtAndel.getRettighetshaver(), DEFAULT_IDENTIFIKASJONSNUMMER, DEFAULT_PERSON_NAVN);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhet solgtAndelRegisterenhet = solgtAndel.getRealkobletTil();
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Borettslagsandel solgtAndelBorettslagsandel = solgtAndelRegisterenhet.getBorettslagsandel();
      assertEquals(solgtAndelBorettslagsandel.getAndelsnummer(), SOLGT_ANDEL_BORETTSLAGSANDELSNUMMER);
      assertEquals(solgtAndelBorettslagsandel.getBorettslagnavn(), SOLGT_ANDEL_BORETTSLAGSNAVN);
      assertEquals(solgtAndelBorettslagsandel.getBorettslagnummer(), SOLGT_ANDEL_BORETTSLAGNUMMER);

      assertNull(solgtAndelRegisterenhet.getMatrikkelenhet());

      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrett registerenhetsrett = omsattRegisterenhetsrett.getRegisterenhetsrett();
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode registerenhetsrettstype = registerenhetsrett.getRegisterenhetsrettstype();
      assertEquals(registerenhetsrettstype.getKodeverdi(), REGISTERENHETSRETTSTYPE_KODEVERDI);
   }

   private void assertMatrikkelenhetsendring(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhetsendring matrikkelenhetsendring) {
      assertEquals(matrikkelenhetsendring.getRettsstiftelsesreferanse(), MATRIKKELENHETSENDRING_RETTSSTIFTELSESREFERANSE);
      assertKode(matrikkelenhetsendring.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);
      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tekst> tekstList = matrikkelenhetsendring.getTekster().getTekst();
      assertEquals(tekstList.size(), 1);
      assertTekst(tekstList.get(0), FRITEKST, TEKST_TYPE_NAVN, TEKST_TYPE_KODEVERDI);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhet> fraMatrikkelenheter = matrikkelenhetsendring.getFra().getMatrikkelenhet();
      assertEquals(fraMatrikkelenheter.size(), 1);
      assertMatrikkelenhet(fraMatrikkelenheter.get(0), GAARDSNUMMER_OSLO, SEKSJONSNUMMER_OSLO, BRUKSNUMMER_OSLO,
            FESTENUMMER_OSLO, KOMMUNENAVN_OSLO, KOMMUNENUMMER_OSLO);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhet> tilMatrikkelenheter = matrikkelenhetsendring.getTil().getMatrikkelenhet();
      assertEquals(tilMatrikkelenheter.size(), 1);
      assertMatrikkelenhet(tilMatrikkelenheter.get(0), GAARDSNUMMER_OSLO, TIL_MATRIKKELENHET_SEKSJONSNUMMER, BRUKSNUMMER_OSLO,
            FESTENUMMER_OSLO, KOMMUNENAVN_OSLO, KOMMUNENUMMER_OSLO);

      List<MatrikkelenhetFraTil> omnummereringsListe = matrikkelenhetsendring.getOmnummereringAvUnderliggende().getMatrikkelenhetFraTil();
      assertEquals(omnummereringsListe.size(), 1);
      MatrikkelenhetFraTil matrikkelenhetFraTil = omnummereringsListe.get(0);
      assertEquals(matrikkelenhetFraTil.getFra().getBruksnummer(), MATRIKKELENHET_FRA_TIL_FRA_BRUKSNUMMER);
      assertEquals(matrikkelenhetFraTil.getTil().getKommunenummer(), MATRIKKELENHET_FRA_TIL_TIL_KOMMUNENUMMER);
   }

   private void assertMatrikkelenhet(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhet matrikkelenhet, int gaardsnummer, int seksjonsnummer, int bruksnummer, int festenummer,
                                     String kommunenavn, String kommunenummer) {
      assertEquals(matrikkelenhet.getGaardsnummer(), gaardsnummer);
      assertEquals(matrikkelenhet.getSeksjonsnummer(), seksjonsnummer);
      assertEquals(matrikkelenhet.getBruksnummer(), bruksnummer);
      assertEquals(matrikkelenhet.getFestenummer(), festenummer);
      assertEquals(matrikkelenhet.getKommunenavn(), kommunenavn);
      assertEquals(matrikkelenhet.getKommunenummer(), kommunenummer);
   }

   private void assertAnmerkningPaaPerson(no.kartverket.grunnbok.wsapi.internal.domain.innsending.AnmerkningPaaPerson anmerkningPaaPerson) {
      assertEquals(anmerkningPaaPerson.getRettsstiftelsesreferanse(), ANMERKNING_PAA_PERSON_RETTSTIFTELSESREFERANSE);
      assertEquals(anmerkningPaaPerson.getSaksnummer(), ANMERKNING_PAA_PERSON_SAKSNUMMER);

      assertPerson(anmerkningPaaPerson.getAnmerketPerson(), ANMERKET_PERSON_IDENTIFIKASJONSNUMMER, ANMERKET_PERSON_NAVN);
      assertPerson(anmerkningPaaPerson.getBostyrer(), BOSTYRER_IDENTIFIKASJONSNUMMER, BOSTYRER_NAVN);
      assertPerson(anmerkningPaaPerson.getKonkursbo(), KONKURS_BO_IDENTIFIKASJONSNUMMER, KONKURS_BO_NAVN);

      assertKode(anmerkningPaaPerson.getRettsstiftelsestype(), DEFAULT_KODE_NAVN, DEFAULT_KODEVERDI);

      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tekst> tekstList = anmerkningPaaPerson.getTekster().getTekst();
      assertEquals(tekstList.size(), 1);
      assertTekst(tekstList.get(0), FRITEKST, TEKST_TYPE_NAVN, TEKST_TYPE_KODEVERDI);
   }

   private void assertTekst(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tekst tekst, String fritekst, String tekstTypeNavn, String tekstTypeKodeverdi) {
      assertEquals(tekst.getFritekst(), fritekst);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode teksttype = tekst.getTeksttype();
      assertEquals(teksttype.getNavn(), tekstTypeNavn);
      assertEquals(teksttype.getKodeverdi(), tekstTypeKodeverdi);
   }

   private void assertKode(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kode kode, String navn, String kodeverdi) {
      assertEquals(kode.getNavn(), navn);
      assertEquals(kode.getKodeverdi(), kodeverdi);
   }

   private void assertIkkeDigitaleSignaturer(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Forsendelse wsForsendelse) {
      SignaturList ikkeDigitaleSignaturer = wsForsendelse.getIkkeDigitaleSignaturer();
      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.Signatur> signaturList = ikkeDigitaleSignaturer.getSignatur();
      assertEquals(signaturList.size(), 1);
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.Signatur signatur = signaturList.get(0);
      assertEquals(signatur.getGjelderDokumentreferanse(), GJELDER_DOKUMENTREFERANSE);
      assertEquals(signatur.getPersonidentifikasjonsnummer(), PERSONIDENTIFIKASJONSNUMMER);
   }

   private void assertSignertMelding(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Forsendelse wsForsendelse) {
      no.kartverket.grunnbok.wsapi.internal.domain.innsending.SignertMelding signertMelding = wsForsendelse.getSignertMelding();
      List<no.kartverket.grunnbok.wsapi.internal.domain.innsending.SDODokument> sdoDokument = signertMelding.getDokumenter().getSDODokument();
      assertEquals(sdoDokument.size(), 1);
      assertEquals(sdoDokument.get(0).getSignertDokument(), SIGNERT_MELDING_DOKUMENT);
      assertEquals(signertMelding.getFoelgebrev().getSignertDokument(), SIGNERT_MELDING_FOLGE_BREV);
   }

   private void assertPerson(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Person person, String identifikasjonsnummer, String navn) {
      assertEquals(person.getIdentifikasjonsnummer(), identifikasjonsnummer);
      assertEquals(person.getNavn(), navn);
   }

}
