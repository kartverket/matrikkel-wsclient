package no.statkart.wsclient.stedsnavn.endringslogg.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.*;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.BokId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.KartproduktId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.koder.DokumenttypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.koder.KartforekomstMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.DelAvSamlevedtakId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.KlageId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.dokumentasjon.offentligbruk.VedtakId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringer;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.LandKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.SpraakKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.koder.VedtaksmyndighetKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.kommune.KommuneId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.posisjon.PosisjonId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.SkrivemaateId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.KasustypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.RekkefoelgeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaateMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.koder.SkrivemaatestatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.koder.*;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnesakstatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.NavnestatusKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.StedsnavnMerknadstypeKodeId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.koder.StedsnavnTilleggsopplysningstypeKodeId;
import no.statkart.wsclient.stedsnavn.endringslogg.StedsnavnContext;
import no.statkart.wsclient.stedsnavn.endringslogg.StedsnavnEntityComponentWithHistory;
import no.statkart.wsclient.stedsnavn.endringslogg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static no.statkart.wsclient.stedsnavn.endringslogg.StedsnavnBobleId.TypeId.*;
import static no.statkart.wsclient.stedsnavn.endringslogg.impl.SkrivemaateMapper.mapSkrivemaate;
import static no.statkart.wsclient.stedsnavn.endringslogg.impl.StedMapper.mapSted;
import static no.statkart.wsclient.stedsnavn.endringslogg.impl.StedsnavnMapper.mapStedsnavn;

class Mapper {

   private static final Logger logger = LoggerFactory.getLogger(Mapper.class);

   private static Map<StedsnavnBobleId.TypeId, Class<? extends StedsnavnBubbleObjectId>> typeIdTilWsIdMap = ImmutableMap.<StedsnavnBobleId.TypeId, Class<? extends StedsnavnBubbleObjectId>>builder()
         .put(BOK, BokId.class)
         .put(DEL_AV_SAMLE, DelAvSamlevedtakId.class)
         .put(DOKUMENT_TYPE, DokumenttypeKodeId.class)
         .put(KART, KartforekomstMerknadstypeKodeId.class)
         .put(KART_PRODUKT, KartproduktId.class)
         .put(KASUS, KasustypeKodeId.class)
         .put(KLAGE, KlageId.class)
         .put(KOMMUNE, KommuneId.class)
         .put(LAND, LandKodeId.class)
         .put(NAVNE_OBJEKT, NavneobjekttypeKodeId.class)
         .put(NAVNE_SAK, NavnesakstatusKodeId.class)
         .put(NAVNE_STATUS, NavnestatusKodeId.class)
         .put(POSISJON, PosisjonId.class)
         .put(REKKEFOELGE, RekkefoelgeKodeId.class)
         .put(SKRIVEMAATE, SkrivemaateId.class)
         .put(SKRIVEMAATE_MERKNAD, SkrivemaateMerknadstypeKodeId.class)
         .put(SKRIVEMAATE_STATUS, SkrivemaatestatusKodeId.class)
         .put(SORT_1, Sortering1KodeId.class)
         .put(SORT_2, Sortering2KodeId.class)
         .put(SPRAAK, SpraakKodeId.class)
         .put(SPRAAK_PRIO, SpraakprioriteringKodeId.class)
         .put(STED, StedId.class)
         .put(STED_MERKNAD, StedMerknadstypeKodeId.class)
         .put(STEDSNAVN, StedsnavnId.class)
         .put(STEDSNAVN_MERKNAD, StedsnavnMerknadstypeKodeId.class)
         .put(STEDSNAVN_TILLEGG, StedsnavnTilleggsopplysningstypeKodeId.class)
         .put(STED_STATUS, StedstatusKodeId.class)
         .put(STED_TILLEGG, StedTilleggsopplysningstypeKodeId.class)
         .put(VEDTAK, VedtakId.class)
         .put(VEDTAKS_MYND, VedtaksmyndighetKodeId.class)
         .build();

   static EndringerRespons mapEndringerRespons(Endringer endringer) {
      EndringerRespons respons = new EndringerRespons(
            toDomainEndringer(endringer.getEndringList()),
            toDomainEndringId(endringer.getSisteEndringIdProsessert()),
            toDomainObjects(endringer.getObjects()));
      respons.setAlleEndringerFunnet(endringer.isAlleEndringerFunnet());

      return respons;
   }

   static no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext toWsCtx(StedsnavnContext stedsnavnContext) {
      no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext wsStedsnavnContext = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext();

      wsStedsnavnContext.setClientIdentification(stedsnavnContext.getClientIdentification());
      wsStedsnavnContext.setLocale(stedsnavnContext.getLocale());

      Timestamp snapshotVersion = new Timestamp();
      snapshotVersion.setTimestamp(stedsnavnContext.getSnapshotVersion());
      wsStedsnavnContext.setSnapshotVersion(snapshotVersion);

      wsStedsnavnContext.setSystemVersion(stedsnavnContext.getSystemVersion());

      return wsStedsnavnContext;
   }

   static no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Domainklasse toWsDomainklasse(Domainklasse domainklasse) {
      return no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Domainklasse.fromValue(domainklasse.getKodeverdi());
   }

   static StedsnavnBubbleObjectIdList toWsBobleIds(List<StedsnavnBobleId> ids) {
      StedsnavnBubbleObjectIdList stedsnavnBubbleObjectIdList = new StedsnavnBubbleObjectIdList();
      stedsnavnBubbleObjectIdList.getItem().addAll(ids.stream()
            .map(stedsnavnBobleId -> {
               try {
                  Class<? extends StedsnavnBubbleObjectId> wsIdClass = typeIdTilWsIdMap.get(stedsnavnBobleId.getTypeId());
                  StedsnavnBubbleObjectId bobleId = wsIdClass.getDeclaredConstructor().newInstance();
                  bobleId.setValue(stedsnavnBobleId.getValue());
                  return bobleId;
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
            })
            .collect(Collectors.toList()));
      return stedsnavnBubbleObjectIdList;
   }

   static Kontroll toDomainKontroll(no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Kontroll wsKontroll) {
      Kontroll kontroll = new Kontroll();
      kontroll.setAntall(wsKontroll.getAntall());
      kontroll.setIdChecksum(wsKontroll.getIdChecksum());
      return kontroll;
   }

   static no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId toWsEndringId(StedsnavnBobleId.EndringId id) {
      no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId wsId = new no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId();
      wsId.setValue(id.getValue());
      return wsId;
   }


   static no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.ReturnerBobler toWsReturnerBobler(ReturnerBobler returnerBobler) {
      return no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.ReturnerBobler.fromValue(returnerBobler.getKodeverdi());
   }

   static void setFellesFelterForHistoriskBoble(StedsnavnBubbleObjectWithHistory wsObject, StedsnavnBobleMedHistorie domeneObjekt) {
      domeneObjekt.setOppdateringsdato(wsObject.getOppdateringsdato().getTimestamp());
      domeneObjekt.setOppdatertAv(wsObject.getOppdatertAv());
      domeneObjekt.setSluttdato(wsObject.getSluttdato().getTimestamp());
      domeneObjekt.setAvsluttetAv(wsObject.getAvsluttetAv());
   }

   static void setFellesFelterForHistoriskKomponent(no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnEntityComponentWithHistory ws, StedsnavnEntityComponentWithHistory domeneKomponent) {
      domeneKomponent.setOppdateringsdato(ws.getOppdateringsdato().getTimestamp());
      domeneKomponent.setOppdatertAv(ws.getOppdatertAv());
      domeneKomponent.setSluttdato(ws.getSluttdato().getTimestamp());
      domeneKomponent.setAvsluttetAv(ws.getAvsluttetAv());
   }

   static LocalDateTime regDato(no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnEntityComponentWithHistory ws) {
      return ws.getRegistreringsdato().getTimestamp();
   }

   static StedsnavnBobleId toDomainBobleId(StedsnavnBubbleObjectId wsBubbleId) {
      String value = wsBubbleId.getValue();
      StedsnavnBobleId bobleId;

      if (wsBubbleId instanceof BokId) {
         bobleId = new StedsnavnBobleId.BokId(value);
      } else if (wsBubbleId instanceof DelAvSamlevedtakId) {
         bobleId = new StedsnavnBobleId.DelAvSamlevedtakId(value);
      } else if (wsBubbleId instanceof DokumenttypeKodeId) {
         bobleId = new StedsnavnBobleId.DokumenttypeKodeId(value);
      } else if (wsBubbleId instanceof KartforekomstMerknadstypeKodeId) {
         bobleId = new StedsnavnBobleId.KartforekomstMerknadstypeKodeId(value);
      } else if (wsBubbleId instanceof KartproduktId) {
         bobleId = new StedsnavnBobleId.KartproduktId(value);
      } else if (wsBubbleId instanceof KasustypeKodeId) {
         bobleId = new StedsnavnBobleId.KasustypeKodeId(value);
      } else if (wsBubbleId instanceof KlageId) {
         bobleId = new StedsnavnBobleId.KlageId(value);
      } else if (wsBubbleId instanceof KommuneId) {
         bobleId = new StedsnavnBobleId.KommuneId(value);
      } else if (wsBubbleId instanceof LandKodeId) {
         bobleId = new StedsnavnBobleId.LandKodeId(value);
      } else if (wsBubbleId instanceof NavneobjekttypeKodeId) {
         bobleId = new StedsnavnBobleId.NavneobjekttypeKodeId(value);
      } else if (wsBubbleId instanceof NavnesakstatusKodeId) {
         bobleId = new StedsnavnBobleId.NavnesakstatusKodeId(value);
      } else if (wsBubbleId instanceof NavnestatusKodeId) {
         bobleId = new StedsnavnBobleId.NavnestatusKodeId(value);
      } else if (wsBubbleId instanceof PosisjonId) {
         bobleId = new StedsnavnBobleId.PosisjonId(value);
      } else if (wsBubbleId instanceof RekkefoelgeKodeId) {
         bobleId = new StedsnavnBobleId.RekkefoelgeKodeId(value);
      } else if (wsBubbleId instanceof SkrivemaateId) {
         bobleId = new StedsnavnBobleId.SkrivemaateId(value);
      } else if (wsBubbleId instanceof SkrivemaateMerknadstypeKodeId) {
         bobleId = new StedsnavnBobleId.SkrivemaateMerknadstypeKodeId(value);
      } else if (wsBubbleId instanceof SkrivemaatestatusKodeId) {
         bobleId = new StedsnavnBobleId.SkrivemaatestatusKodeId(value);
      } else if (wsBubbleId instanceof Sortering1KodeId) {
         bobleId = new StedsnavnBobleId.Sortering1KodeId(value);
      } else if (wsBubbleId instanceof Sortering2KodeId) {
         bobleId = new StedsnavnBobleId.Sortering2KodeId(value);
      } else if (wsBubbleId instanceof SpraakKodeId) {
         bobleId = new StedsnavnBobleId.SpraakKodeId(value);
      } else if (wsBubbleId instanceof SpraakprioriteringKodeId) {
         bobleId = new StedsnavnBobleId.SpraakprioriteringKodeId(value);
      } else if (wsBubbleId instanceof StedId) {
         bobleId = new StedsnavnBobleId.StedId(value);
      } else if (wsBubbleId instanceof StedMerknadstypeKodeId) {
         bobleId = new StedsnavnBobleId.StedMerknadstypeKodeId(value);
      } else if (wsBubbleId instanceof StedsnavnId) {
         bobleId = new StedsnavnBobleId.StedsnavnId(value);
      } else if (wsBubbleId instanceof StedsnavnMerknadstypeKodeId) {
         bobleId = new StedsnavnBobleId.StedsnavnMerknadstypeKodeId(value);
      } else if (wsBubbleId instanceof StedsnavnTilleggsopplysningstypeKodeId) {
         bobleId = new StedsnavnBobleId.StedsnavnTilleggsopplysningstypeKodeId(value);
      } else if (wsBubbleId instanceof StedstatusKodeId) {
         bobleId = new StedsnavnBobleId.StedstatusKodeId(value);
      } else if (wsBubbleId instanceof StedTilleggsopplysningstypeKodeId) {
         bobleId = new StedsnavnBobleId.StedTilleggsopplysningstypeKodeId(value);
      } else if (wsBubbleId instanceof VedtakId) {
         bobleId = new StedsnavnBobleId.VedtakId(value);
      } else if (wsBubbleId instanceof VedtaksmyndighetKodeId) {
         bobleId = new StedsnavnBobleId.VedtaksmyndighetKodeId(value);
      } else {
         throw new RuntimeException(String.format("Ikke mapping for: %s", wsBubbleId.getClass()));
      }

      return bobleId;
   }

   private static List<StedsnavnBoble> toDomainObjects(StedsnavnBubbleObjectList objects) {
      ImmutableList.Builder<StedsnavnBoble> builder = ImmutableList.builder();
      objects.getItem()
            .forEach(stedsnavnBubbleObject -> {
               if (stedsnavnBubbleObject instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted) {
                  builder.add(mapSted((no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.Sted) stedsnavnBubbleObject));
               } else if (stedsnavnBubbleObject instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn) {
                  builder.add(mapStedsnavn((no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.Stedsnavn) stedsnavnBubbleObject));
               } else if (stedsnavnBubbleObject instanceof no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate) {
                  builder.add(mapSkrivemaate((no.statkart.stedsnavn.ssr.wsapi.v1.domain.skrivemaate.Skrivemaate) stedsnavnBubbleObject));
               } else {
                  logger.info("Ikke definert mapping for objekt av type: {}", stedsnavnBubbleObject.getClass());
               }
            });
      return builder.build();
   }

   private static List<Endring> toDomainEndringer(EndringList endringList) {
      return endringList.getItem()
            .stream()
            .map(Mapper::toDomainEndring)
            .collect(Collectors.toList());
   }

   private static Endring toDomainEndring(no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endring wsEndring) {
      no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId wsEndringId = (no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId) wsEndring.getId();
      StedsnavnBubbleObjectId endretBubbleId = wsEndring.getEndretBubbleId();
      Timestamp endringstidspunkt = wsEndring.getEndringstidspunkt();
      no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringstype endringstype = wsEndring.getEndringstype();

      return new Endring(toDomainEndringId(wsEndringId), endringstidspunkt.getTimestamp(), Endringstype.fromValue(endringstype.value()), toDomainBobleId(endretBubbleId));
   }

   private static StedsnavnBobleId.EndringId toDomainEndringId(no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringId id) {
      return new StedsnavnBobleId.EndringId(id.getValue());
   }
}
