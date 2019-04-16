package no.statkart.wsclient.stedsnavn;

import java.time.LocalDate;

public class NavneobjekttypeHistorikk {

   private LocalDate fraDato;
   private StedsnavnBobleId.NavneobjekttypeKodeId navneobjekttypeKodeId;

   public NavneobjekttypeHistorikk(LocalDate fraDato, StedsnavnBobleId.NavneobjekttypeKodeId navneobjekttypeKodeId) {
      this.fraDato = fraDato;
      this.navneobjekttypeKodeId = navneobjekttypeKodeId;
   }

   public LocalDate getFraDato() {
      return fraDato;
   }

   public StedsnavnBobleId.NavneobjekttypeKodeId getNavneobjekttypeKodeId() {
      return navneobjekttypeKodeId;
   }
}
