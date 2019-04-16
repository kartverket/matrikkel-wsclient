package no.statkart.wsclient.stedsnavn;

import java.time.LocalDate;

public class StedstatusHistorikk {

   private LocalDate fraDato;
   private StedsnavnBobleId.StedstatusKodeId stedstatusId;

   public StedstatusHistorikk(LocalDate fraDato, StedsnavnBobleId.StedstatusKodeId stedstatusId) {
      this.fraDato = fraDato;
      this.stedstatusId = stedstatusId;
   }

   public LocalDate getFraDato() {
      return fraDato;
   }

   public StedsnavnBobleId.StedstatusKodeId getStedstatusId() {
      return stedstatusId;
   }
}
