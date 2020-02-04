package no.statkart.wsclient.byggesak.model;

import java.time.Instant;
import java.util.Date;

/**
 * Ferdig behandlet objekt fra FIKS.
 * Det lages en Melding pr. byggesak.
 */
public class ByggesakmeldingDTO {
   /**
    * Id til forsendelsen i FIKS. Brukes for å kvittere ut.
    */
   private String forsendelsesId;
   /**
    * Generell info om bygget som skal føres.
    */
   private String info;
   /**
    * Bygget må ha knytning til et kommunenummer.
    */
   private String kommuneNummer;
   /**
    * Dato m/klokkeslett for endringen
    */
   private Instant createdAt;
   /**
    * Bygningsinformasjonen som hører til denne meldingen.
    */
    private ByggesakDTO byggesakDTO;
   /**
    * Sier noe om hvilket brukstilfelle denne hører til.
    */
   private String brukstilfelleKode;

   public ByggesakmeldingDTO() {}

   public String getBrukstilfelleKode() {
      return brukstilfelleKode;
   }

   public void setBrukstilfelleKode(String brukstilfelleKode) {
      this.brukstilfelleKode = brukstilfelleKode;
   }

   public String getInfo() { return info; }

   public void setInfo(String info) { this.info = info; }

   public String getKommuneNummer() { return kommuneNummer; }

   public void setKommuneNummer(String kommuneNummer) { this.kommuneNummer = kommuneNummer; }

   public Instant getCreatedAt() { return createdAt; }

   public void setCreatedAt(Instant createdAt) {
      this.createdAt = createdAt != null ? createdAt : new Date().toInstant();
   }
   public String getForsendelsesId() { return forsendelsesId; }

   public void setForsendelsesId(String forsendelsesId) { this.forsendelsesId = forsendelsesId; }

   public ByggesakDTO getByggesakDTO() { return byggesakDTO; }

   public void setByggesakDTO(ByggesakDTO byggesakDTO) { this.byggesakDTO = byggesakDTO; }
}
