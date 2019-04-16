package no.statkart.wsclient.stedsnavn;


import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.TypeId.*;

public abstract class StedsnavnBobleId {

   private String value;
   private TypeId typeId;

   public enum TypeId {
      BOK,
      DEL_AV_SAMLE,
      DOKUMENT_TYPE,
      ENDRING,
      KART,
      KART_PRODUKT,
      KASUS,
      KLAGE,
      KOMMUNE,
      LAND,
      NAVNE_OBJEKT,
      NAVNE_SAK,
      NAVNE_STATUS,
      POSISJON,
      REKKEFOELGE,
      SKRIVEMAATE,
      SKRIVEMAATE_MERKNAD,
      SKRIVEMAATE_STATUS,
      SORT_1,
      SORT_2,
      SPRAAK,
      SPRAAK_PRIO,
      STED,
      STED_MERKNAD,
      STEDSNAVN,
      STEDSNAVN_MERKNAD,
      STEDSNAVN_TILLEGG,
      STED_STATUS,
      STED_TILLEGG,
      VEDTAK,
      VEDTAKS_MYND
   }

   public StedsnavnBobleId(String value, TypeId typeId) {
      this.value = value;
      this.typeId = typeId;
   }

   public TypeId getTypeId() {
      return typeId;
   }

   public String getValue() {
      return value;
   }


   public static class EndringId extends StedsnavnBobleId {
      public EndringId(String value) {
         super(value, ENDRING);
      }
   }

   public static class KartforekomstMerknadstypeKodeId extends StedsnavnBobleId {
      public KartforekomstMerknadstypeKodeId(String value) {
         super(value, KART);
      }
   }

   public static class DelAvSamlevedtakId extends StedsnavnBobleId {
      public DelAvSamlevedtakId(String value) {
         super(value, DEL_AV_SAMLE);
      }
   }

   public static class BokId extends StedsnavnBobleId {
      public BokId(String value) {
         super(value, BOK);
      }
   }

   public static class DokumenttypeKodeId extends StedsnavnBobleId {
      public DokumenttypeKodeId(String value) {
         super(value, DOKUMENT_TYPE);
      }
   }

   public static class KartproduktId extends StedsnavnBobleId {
      public KartproduktId(String value) {
         super(value, KART_PRODUKT);
      }
   }

   public static class KasustypeKodeId extends StedsnavnBobleId {
      public KasustypeKodeId(String value) {
         super(value, KASUS);
      }
   }

   public static class KlageId extends StedsnavnBobleId {
      public KlageId(String value) {
         super(value, KLAGE);
      }
   }

   public static class KommuneId extends StedsnavnBobleId {
      public KommuneId(String value) {
         super(value, KOMMUNE);
      }
   }

   public static class LandKodeId extends StedsnavnBobleId {
      public LandKodeId(String value) {
         super(value, LAND);
      }
   }

   public static class NavneobjekttypeKodeId extends StedsnavnBobleId {
      public NavneobjekttypeKodeId(String value) {
         super(value, NAVNE_OBJEKT);
      }
   }

   public static class NavnesakstatusKodeId extends StedsnavnBobleId {
      public NavnesakstatusKodeId(String value) {
         super(value, NAVNE_SAK);
      }
   }

   public static class NavnestatusKodeId extends StedsnavnBobleId {
      public NavnestatusKodeId(String value) {
         super(value, NAVNE_STATUS);
      }
   }

   public static class PosisjonId extends StedsnavnBobleId {
      public PosisjonId(String value) {
         super(value, POSISJON);
      }
   }

   public static class RekkefoelgeKodeId extends StedsnavnBobleId {
      public RekkefoelgeKodeId(String value) {
         super(value, REKKEFOELGE);
      }
   }

   public static class SkrivemaateId extends StedsnavnBobleId {
      public SkrivemaateId(String value) {
         super(value, SKRIVEMAATE);
      }
   }

   public static class SkrivemaateMerknadstypeKodeId extends StedsnavnBobleId {
      public SkrivemaateMerknadstypeKodeId(String value) {
         super(value, SKRIVEMAATE_MERKNAD);
      }
   }

   public static class SkrivemaatestatusKodeId extends StedsnavnBobleId {
      public SkrivemaatestatusKodeId(String value) {
         super(value, SKRIVEMAATE_STATUS);
      }
   }

   public static class Sortering1KodeId extends StedsnavnBobleId {
      public Sortering1KodeId(String value) {
         super(value, SORT_1);
      }
   }

   public static class Sortering2KodeId extends StedsnavnBobleId {
      public Sortering2KodeId(String value) {
         super(value, SORT_2);
      }
   }

   public static class SpraakKodeId extends StedsnavnBobleId {
      public SpraakKodeId(String value) {
         super(value, SPRAAK);
      }
   }

   public static class SpraakprioriteringKodeId extends StedsnavnBobleId {
      public SpraakprioriteringKodeId(String value) {
         super(value, SPRAAK_PRIO);
      }
   }

   public static class StedId extends StedsnavnBobleId {
      public StedId(String value) {
         super(value, STED);
      }
   }

   public static class StedMerknadstypeKodeId extends StedsnavnBobleId {
      public StedMerknadstypeKodeId(String value) {
         super(value, STED_MERKNAD);
      }
   }

   public static class StedsnavnId extends StedsnavnBobleId {
      public StedsnavnId(String value) {
         super(value, STEDSNAVN);
      }
   }

   public static class StedsnavnMerknadstypeKodeId extends StedsnavnBobleId {
      public StedsnavnMerknadstypeKodeId(String value) {
         super(value, STEDSNAVN_MERKNAD);
      }
   }

   public static class StedsnavnTilleggsopplysningstypeKodeId extends StedsnavnBobleId {
      public StedsnavnTilleggsopplysningstypeKodeId(String value) {
         super(value, STEDSNAVN_TILLEGG);
      }
   }

   public static class StedstatusKodeId extends StedsnavnBobleId {
      public StedstatusKodeId(String value) {
         super(value, STED_STATUS);
      }
   }

   public static class StedTilleggsopplysningstypeKodeId extends StedsnavnBobleId {
      public StedTilleggsopplysningstypeKodeId(String value) {
         super(value, STED_TILLEGG);
      }
   }

   public static class VedtakId extends StedsnavnBobleId {
      public VedtakId(String value) {
         super(value, VEDTAK);
      }
   }

   public static class VedtaksmyndighetKodeId extends StedsnavnBobleId {
      public VedtaksmyndighetKodeId(String value) {
         super(value, VEDTAKS_MYND);
      }
   }
}
