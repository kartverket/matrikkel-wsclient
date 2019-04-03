package no.statkart.wsclient.stedsnavn.endringslogg.impl;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.KasusForSkrivemaateList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaateInternMerknadList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaatestatusHistorikkList;
import no.statkart.wsclient.stedsnavn.endringslogg.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static no.statkart.wsclient.stedsnavn.endringslogg.impl.DokumentasjonMapper.toDomeneDokumentasjon;
import static no.statkart.wsclient.stedsnavn.endringslogg.impl.Mapper.*;

class SkrivemaateMapper {

   static Skrivemaate mapSkrivemaate(no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate wsSkrivemaate) {
      Skrivemaate skrivemaate = new Skrivemaate(toDomainBobleId(wsSkrivemaate.getId()), wsSkrivemaate.getRegistreringsdato().getTimestamp());
      setFellesFelterForHistoriskBoble(wsSkrivemaate, skrivemaate);

      skrivemaate.setSkrivemaatenummer(wsSkrivemaate.getSkrivemaatenummer());
      skrivemaate.setStedsnavnId(new StedsnavnBobleId.StedsnavnId(wsSkrivemaate.getStedsnavnId().getValue()));
      skrivemaate.setNormertFraId(new StedsnavnBobleId.SkrivemaateId(wsSkrivemaate.getNormertFraId().getValue()));
      skrivemaate.setRekkefoelgeId(new StedsnavnBobleId.RekkefoelgeKodeId(wsSkrivemaate.getRekkefoelgeId().getValue()));
      skrivemaate.setKasuser(toDomeneKasuser(wsSkrivemaate.getKasuser()));
      skrivemaate.setKortnavn(wsSkrivemaate.getKortnavn());
      skrivemaate.setSkrivemaatestatusHistorikker(toDomeneStatusHistorikker(wsSkrivemaate.getSkrivemaatestatusHistorikker()));
      skrivemaate.setInterneMerknader(toDomeneInterneMerknader(wsSkrivemaate.getInterneMerknader()));
      skrivemaate.setDokumentasjon(toDomeneDokumentasjon(wsSkrivemaate.getDokumentasjon()));

      return skrivemaate;
   }

   private static List<SkrivemaateInternMerknad> toDomeneInterneMerknader(SkrivemaateInternMerknadList interneMerknader) {
      return interneMerknader.getItem().stream()
            .map(ws -> {
               SkrivemaateInternMerknad stedsnavnInternMerknad = new SkrivemaateInternMerknad(ws.getId(), regDato(ws), ws.getTekst(),
                     new StedsnavnBobleId.SkrivemaateMerknadstypeKodeId(ws.getMerknadstypeId().getValue()), ws.getFellesarkiv().getItem());
               setFellesFelterForHistoriskKomponent(ws, stedsnavnInternMerknad);
               return stedsnavnInternMerknad;
            })
            .collect(Collectors.toList());
   }

   private static List<SkrivemaatestatusHistorikk> toDomeneStatusHistorikker(SkrivemaatestatusHistorikkList skrivemaatestatusHistorikker) {
      return skrivemaatestatusHistorikker.getItem().stream()
            .map(ws -> {
               SkrivemaatestatusHistorikk domene = new SkrivemaatestatusHistorikk(
                     ws.getId(),
                     regDato(ws),
                     ws.getFraDato().getDate(),
                     new StedsnavnBobleId.SkrivemaatestatusKodeId(ws.getSkrivemaatestatusId().getValue()));
               setFellesFelterForHistoriskKomponent(ws, domene);
               domene.setPrioritertSkrivemaate(ws.isPrioritertSkrivemaate());
               return domene;
            }).collect(Collectors.toList());
   }

   private static List<KasusForSkrivemaate> toDomeneKasuser(KasusForSkrivemaateList kasusList) {
      return kasusList.getItem().stream()
            .map(ws -> {
               KasusForSkrivemaate domene = new KasusForSkrivemaate(ws.getId(), regDato(ws),
                     new StedsnavnBobleId.KasustypeKodeId(ws.getKasusTilKjernenavnId().getValue()), ws.getKjernenavn());
               setFellesFelterForHistoriskKomponent(ws, domene);

               domene.setVariasjonstillegg(ws.getVariasjonstillegg());
               domene.setFunksjonstillegg(ws.getFunksjonstillegg());

               return domene;
            })
            .collect(toList());
   }

}
