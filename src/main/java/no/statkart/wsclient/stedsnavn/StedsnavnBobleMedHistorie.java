package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;

public class StedsnavnBobleMedHistorie extends StedsnavnBoble {

   private LocalDateTime oppdateringsdato;
   private String oppdatertAv;
   private LocalDateTime sluttdato;
   private String avsluttetAv;
   private LocalDateTime registreringsdato;

   StedsnavnBobleMedHistorie(StedsnavnBobleId id, LocalDateTime registreringsdato) {
      super(id);
      this.registreringsdato = registreringsdato;
   }

   public LocalDateTime getOppdateringsdato() {
      return oppdateringsdato;
   }

   public String getOppdatertAv() {
      return oppdatertAv;
   }

   public LocalDateTime getSluttdato() {
      return sluttdato;
   }

   public String getAvsluttetAv() {
      return avsluttetAv;
   }

   public LocalDateTime getRegistreringsdato() {
      return registreringsdato;
   }

   public void setOppdateringsdato(LocalDateTime oppdateringsdato) {
      this.oppdateringsdato = oppdateringsdato;
   }

   public void setOppdatertAv(String oppdatertAv) {
      this.oppdatertAv = oppdatertAv;
   }

   public void setSluttdato(LocalDateTime sluttdato) {
      this.sluttdato = sluttdato;
   }

   public void setAvsluttetAv(String avsluttetAv) {
      this.avsluttetAv = avsluttetAv;
   }
}
