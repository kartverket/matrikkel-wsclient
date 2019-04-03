package no.statkart.wsclient.stedsnavn.endringslogg.impl;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.DokumentasjonList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.KartforekomstInternMerknadList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.kilde.*;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.*;
import no.statkart.wsclient.stedsnavn.endringslogg.Dokumentasjon;
import no.statkart.wsclient.stedsnavn.endringslogg.KartforekomstInternMerknad;
import no.statkart.wsclient.stedsnavn.endringslogg.StedsnavnBobleId;

import java.util.List;
import java.util.stream.Collectors;

import static no.statkart.wsclient.stedsnavn.endringslogg.impl.Mapper.regDato;
import static no.statkart.wsclient.stedsnavn.endringslogg.impl.Mapper.setFellesFelterForHistoriskKomponent;


class DokumentasjonMapper {

   static List<Dokumentasjon> toDomeneDokumentasjon(DokumentasjonList wsDokumentasjonList) {
      return wsDokumentasjonList.getItem().stream()
            .map(wsDokumentasjon -> {
               Dokumentasjon domain;
               if (wsDokumentasjon instanceof Kartforekomst) {
                  Kartforekomst kartforekomst = (Kartforekomst) wsDokumentasjon;
                  domain = new Dokumentasjon.Kartforekomst(kartforekomst.getId(),
                        regDato(kartforekomst),
                        kartforekomst.getPosisjon(),
                        new StedsnavnBobleId.KartproduktId(kartforekomst.getKartproduktId().getValue()),
                        toDomeneKartforekomstInternMerknader(kartforekomst.getInterneMerknader()));
               } else if (wsDokumentasjon instanceof OffentligDokument) {
                  OffentligDokument offentligDokument = (OffentligDokument) wsDokumentasjon;
                  domain = new Dokumentasjon.OffentligDokument(offentligDokument.getId(),
                        regDato(offentligDokument),
                        offentligDokument.getDokumentdato().getDate(),
                        offentligDokument.getBeskrivelse(),
                        new StedsnavnBobleId.DokumenttypeKodeId(offentligDokument.getDokumenttypeId().getValue()));
               } else if (wsDokumentasjon instanceof OffentligBokreferanse) {
                  OffentligBokreferanse ws = (OffentligBokreferanse) wsDokumentasjon;
                  Dokumentasjon.OffentligBokreferanse offentligBokreferanse = new Dokumentasjon.OffentligBokreferanse(ws.getId(), regDato(ws), new StedsnavnBobleId.BokId(ws.getBokId().getValue()));
                  offentligBokreferanse.setReferansetekst(ws.getReferansetekst());
                  offentligBokreferanse.setSide(ws.getSide());
                  domain = offentligBokreferanse;
               } else if (wsDokumentasjon instanceof DokumentertSamlevedtak) {
                  DokumentertSamlevedtak ws = (DokumentertSamlevedtak) wsDokumentasjon;
                  domain = new Dokumentasjon.DokumentertSamlevedtak(ws.getId(), regDato(ws), new StedsnavnBobleId.DelAvSamlevedtakId(ws.getDelAvSamlevedtakId().getValue()));
               } else if (wsDokumentasjon instanceof DokumentertVedtak) {
                  DokumentertVedtak ws = (DokumentertVedtak) wsDokumentasjon;
                  domain = new Dokumentasjon.DokumentertVedtak(ws.getId(), regDato(ws), new StedsnavnBobleId.VedtakId(ws.getVedtakId().getValue()));
               } else if (wsDokumentasjon instanceof DokumentertKlage) {
                  DokumentertKlage ws = (DokumentertKlage) wsDokumentasjon;
                  domain = new Dokumentasjon.DokumentertKlage(ws.getId(), regDato(ws), new StedsnavnBobleId.KlageId(ws.getKlageId().getValue()));
               } else if (wsDokumentasjon instanceof AnnenKartforekomst) {
                  AnnenKartforekomst ws = (AnnenKartforekomst) wsDokumentasjon;
                  domain = new Dokumentasjon.AnnenKartforekomst(ws.getId(), regDato(ws), ws.getPosisjon(), new StedsnavnBobleId.KartproduktId(ws.getKartproduktId().getValue()),
                        toDomeneKartforekomstInternMerknader(ws.getInterneMerknader()));
               } else if (wsDokumentasjon instanceof Kildedokument) {
                  Kildedokument ws = (Kildedokument) wsDokumentasjon;
                  domain = new Dokumentasjon.Kildedokument(ws.getId(), regDato(ws), ws.getDokumentdato().getDate(), ws.getBeskrivelse(), new StedsnavnBobleId.DokumenttypeKodeId(ws.getDokumenttypeId().getValue()));
               } else if (wsDokumentasjon instanceof KildeBokreferanse) {
                  KildeBokreferanse ws = (KildeBokreferanse) wsDokumentasjon;
                  Dokumentasjon.KildeBokreferanse kildeBokreferanse = new Dokumentasjon.KildeBokreferanse(ws.getId(), regDato(ws), new StedsnavnBobleId.BokId(ws.getBokId().getValue()));
                  kildeBokreferanse.setSide(ws.getSide());
                  kildeBokreferanse.setReferansetekst(ws.getReferansetekst());
                  domain = kildeBokreferanse;
               } else if (wsDokumentasjon instanceof LokaleInnsamlinger) {
                  LokaleInnsamlinger ws = (LokaleInnsamlinger) wsDokumentasjon;
                  Dokumentasjon.LokaleInnsamlinger lokaleInnsamlinger = new Dokumentasjon.LokaleInnsamlinger(ws.getId(), regDato(ws), ws.getKildedato().getDate(), ws.getInnsamler());
                  lokaleInnsamlinger.setBeskrivelse(ws.getBeskrivelse());
                  domain = lokaleInnsamlinger;
               } else if (wsDokumentasjon instanceof Opplysning) {
                  Opplysning ws = (Opplysning) wsDokumentasjon;
                  domain = new Dokumentasjon.Opplysning(ws.getId(), regDato(ws), ws.getKildedato().getDate(), ws.getInformant(), ws.getTekst());
               } else {
                  throw new RuntimeException(String.format("Ingen mapping for %s", wsDokumentasjon.getClass()));
               }

               setFellesFelterForHistoriskKomponent(wsDokumentasjon, domain);
               return domain;
            })
            .collect(Collectors.toList());
   }

   private static List<KartforekomstInternMerknad> toDomeneKartforekomstInternMerknader(KartforekomstInternMerknadList ws) {
      return ws.getItem().stream()
            .map(wsKIMerknad -> {
               KartforekomstInternMerknad internMerknad = new KartforekomstInternMerknad(wsKIMerknad.getId(), regDato(wsKIMerknad), wsKIMerknad.getTekst(), new StedsnavnBobleId.KartforekomstMerknadstypeKodeId(wsKIMerknad.getMerknadstypeId().getValue()),
                     wsKIMerknad.getFellesarkiv().getItem());
               setFellesFelterForHistoriskKomponent(wsKIMerknad, internMerknad);
               return internMerknad;
            })
            .collect(Collectors.toList());
   }

}
