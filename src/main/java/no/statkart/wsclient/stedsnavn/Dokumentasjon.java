package no.statkart.wsclient.stedsnavn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class Dokumentasjon extends StedsnavnEntityComponentWithHistory {

   public Dokumentasjon(Long id, LocalDateTime registreringsdato) {
      super(id, registreringsdato);
   }

   public static class OffentligDokument extends Dokumentasjon {

      private LocalDate dokumentdato;
      private String beskrivelse;
      private StedsnavnBobleId.DokumenttypeKodeId dokumenttypeId;

      public OffentligDokument(Long id, LocalDateTime registreringsdato, LocalDate dokumentdato, String beskrivelse, StedsnavnBobleId.DokumenttypeKodeId dokumenttypeId) {
         super(id, registreringsdato);
         this.dokumentdato = dokumentdato;
         this.beskrivelse = beskrivelse;
         this.dokumenttypeId = dokumenttypeId;
      }

      public LocalDate getDokumentdato() {
         return dokumentdato;
      }

      public String getBeskrivelse() {
         return beskrivelse;
      }

      public StedsnavnBobleId.DokumenttypeKodeId getDokumenttypeId() {
         return dokumenttypeId;
      }
   }

   public static class Kildedokument extends Dokumentasjon {

      private LocalDate dokumentdato;
      private String beskrivelse;
      private StedsnavnBobleId.DokumenttypeKodeId dokumenttypeId;

      public Kildedokument(Long id, LocalDateTime registreringsdato, LocalDate dokumentdato, String beskrivelse, StedsnavnBobleId.DokumenttypeKodeId dokumenttypeId) {
         super(id, registreringsdato);
         this.dokumentdato = dokumentdato;
         this.beskrivelse = beskrivelse;
         this.dokumenttypeId = dokumenttypeId;
      }

      public LocalDate getDokumentdato() {
         return dokumentdato;
      }

      public String getBeskrivelse() {
         return beskrivelse;
      }

      public StedsnavnBobleId.DokumenttypeKodeId getDokumenttypeId() {
         return dokumenttypeId;
      }
   }

   public static class LokaleInnsamlinger extends Dokumentasjon {

      private LocalDate kildedato;
      private String innsamler;
      private String beskrivelse;

      public LokaleInnsamlinger(Long id, LocalDateTime registreringsdato, LocalDate kildedato, String innsamler) {
         super(id, registreringsdato);
         this.kildedato = kildedato;
         this.innsamler = innsamler;
      }

      public void setBeskrivelse(String beskrivelse) {
         this.beskrivelse = beskrivelse;
      }

      public LocalDate getKildedato() {
         return kildedato;
      }

      public String getInnsamler() {
         return innsamler;
      }

      public String getBeskrivelse() {
         return beskrivelse;
      }
   }

   public static class KildeBokreferanse extends Dokumentasjon {

      private StedsnavnBobleId.BokId bokId;
      private String side;
      private String referansetekst;

      public KildeBokreferanse(Long id, LocalDateTime registreringsdato, StedsnavnBobleId.BokId bokId) {
         super(id, registreringsdato);
         this.bokId = bokId;
      }

      public StedsnavnBobleId.BokId getBokId() {
         return bokId;
      }

      public String getSide() {
         return side;
      }

      public String getReferansetekst() {
         return referansetekst;
      }

      public void setSide(String side) {
         this.side = side;
      }

      public void setReferansetekst(String referansetekst) {
         this.referansetekst = referansetekst;
      }
   }

   public static class Opplysning extends Dokumentasjon {

      private LocalDate kildedato;
      private String informant;
      private String tekst;

      public Opplysning(Long id, LocalDateTime registreringsdato, LocalDate kildedato, String informant, String tekst) {
         super(id, registreringsdato);
         this.kildedato = kildedato;
         this.informant = informant;
         this.tekst = tekst;
      }

      public LocalDate getKildedato() {
         return kildedato;
      }

      public String getInformant() {
         return informant;
      }

      public String getTekst() {
         return tekst;
      }
   }

   public static class OffentligBokreferanse extends Dokumentasjon {

      private StedsnavnBobleId.BokId bokId;
      private String side;
      private String referansetekst;

      public OffentligBokreferanse(Long id, LocalDateTime registreringsdato, StedsnavnBobleId.BokId bokId) {
         super(id, registreringsdato);
         this.bokId = bokId;
      }

      public StedsnavnBobleId.BokId getBokId() {
         return bokId;
      }

      public String getSide() {
         return side;
      }

      public String getReferansetekst() {
         return referansetekst;
      }

      public void setSide(String side) {
         this.side = side;
      }

      public void setReferansetekst(String referansetekst) {
         this.referansetekst = referansetekst;
      }
   }

   public static class DokumentertVedtak extends Dokumentasjon {

      private StedsnavnBobleId.VedtakId vedtakId;

      public DokumentertVedtak(Long id, LocalDateTime registreringsdato, StedsnavnBobleId.VedtakId vedtakId) {
         super(id, registreringsdato);
         this.vedtakId = vedtakId;
      }

      public StedsnavnBobleId.VedtakId getVedtakId() {
         return vedtakId;
      }
   }

   public static class DokumentertKlage extends Dokumentasjon {

      private StedsnavnBobleId.KlageId klageId;

      public DokumentertKlage(Long id, LocalDateTime registreringsdato, StedsnavnBobleId.KlageId klageId) {
         super(id, registreringsdato);
         this.klageId = klageId;
      }

      public StedsnavnBobleId.KlageId getKlageId() {
         return klageId;
      }
   }

   public static class Kartforekomst extends Dokumentasjon {

      private String posisjon;
      private StedsnavnBobleId.KartproduktId kartproduktId;
      private List<KartforekomstInternMerknad> interneMerknader;

      public Kartforekomst(Long id, LocalDateTime registreringsdato, String posisjon, StedsnavnBobleId.KartproduktId kartproduktId, List<KartforekomstInternMerknad> interneMerknader) {
         super(id, registreringsdato);
         this.posisjon = posisjon;
         this.kartproduktId = kartproduktId;
         this.interneMerknader = Objects.requireNonNull(interneMerknader);
      }

      public String getPosisjon() {
         return posisjon;
      }

      public StedsnavnBobleId.KartproduktId getKartproduktId() {
         return kartproduktId;
      }

      public List<KartforekomstInternMerknad> getInterneMerknader() {
         return interneMerknader;
      }
   }

   public static class AnnenKartforekomst extends Dokumentasjon {

      private String posisjon;
      private StedsnavnBobleId.KartproduktId kartproduktId;
      private List<KartforekomstInternMerknad> interneMerknader;

      public AnnenKartforekomst(Long id, LocalDateTime registreringsdato, String posisjon, StedsnavnBobleId.KartproduktId kartproduktId, List<KartforekomstInternMerknad> interneMerknader) {
         super(id, registreringsdato);
         this.posisjon = posisjon;
         this.kartproduktId = kartproduktId;
         this.interneMerknader = Objects.requireNonNull(interneMerknader);
      }

      public String getPosisjon() {
         return posisjon;
      }

      public StedsnavnBobleId.KartproduktId getKartproduktId() {
         return kartproduktId;
      }

      public List<KartforekomstInternMerknad> getInterneMerknader() {
         return interneMerknader;
      }

   }

   public static class DokumentertSamlevedtak extends Dokumentasjon {

      private StedsnavnBobleId.DelAvSamlevedtakId delAvSamlevedtakId;

      public DokumentertSamlevedtak(Long id, LocalDateTime registreringsdato, StedsnavnBobleId.DelAvSamlevedtakId delAvSamlevedtakId) {
         super(id, registreringsdato);
         this.delAvSamlevedtakId = delAvSamlevedtakId;
      }

      public StedsnavnBobleId.DelAvSamlevedtakId getDelAvSamlevedtakId() {
         return delAvSamlevedtakId;
      }
   }

}
