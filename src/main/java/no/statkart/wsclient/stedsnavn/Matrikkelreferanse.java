package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;

public abstract class Matrikkelreferanse {

   private long matrikkelId;
   private LocalDateTime oppdateringsdato;
   private String oppdatertAv;
   private LocalDateTime sluttdato;
   private String avsluttetAv;
   private LocalDateTime registreringsdato;

   public Matrikkelreferanse(LocalDateTime registreringsdato) {
      this.registreringsdato = registreringsdato;
   }

   public void setMatrikkelId(long matrikkelId) {
      this.matrikkelId = matrikkelId;
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

   public long getMatrikkelId() {
      return matrikkelId;
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
}
