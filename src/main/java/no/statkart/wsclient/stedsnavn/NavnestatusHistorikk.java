package no.statkart.wsclient.stedsnavn;


import java.time.LocalDate;

public class NavnestatusHistorikk {

   private LocalDate fraDato;
   private StedsnavnBobleId.NavnestatusKodeId navnestatusId;

   public NavnestatusHistorikk(LocalDate fraDato, StedsnavnBobleId.NavnestatusKodeId navnestatusId) {
      this.fraDato = fraDato;
      this.navnestatusId = navnestatusId;
   }

   public LocalDate getFraDato() {
      return fraDato;
   }

   public StedsnavnBobleId.NavnestatusKodeId getNavnestatusId() {
      return navnestatusId;
   }
}
