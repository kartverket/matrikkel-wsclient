package no.statkart.wsclient.stedsnavn;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class SkrivemaatestatusHistorikk extends StedsnavnEntityComponentWithHistory {

   private LocalDate fraDato;
   private StedsnavnBobleId.SkrivemaatestatusKodeId skrivemaatestatusId;
   private boolean prioritertSkrivemaate;

   public SkrivemaatestatusHistorikk(Long id, LocalDateTime registreringsdato, LocalDate fraDato, StedsnavnBobleId.SkrivemaatestatusKodeId skrivemaatestatusId) {
      super(id, registreringsdato);
      this.fraDato = fraDato;
      this.skrivemaatestatusId = skrivemaatestatusId;
   }

   public void setPrioritertSkrivemaate(boolean prioritertSkrivemaate) {
      this.prioritertSkrivemaate = prioritertSkrivemaate;
   }

   public LocalDate getFraDato() {
      return fraDato;
   }

   public StedsnavnBobleId.SkrivemaatestatusKodeId getSkrivemaatestatusId() {
      return skrivemaatestatusId;
   }

   public boolean isPrioritertSkrivemaate() {
      return prioritertSkrivemaate;
   }
}
