package no.statkart.wsclient.stedsnavn.endringslogg;


import java.time.LocalDateTime;

public class KasusForSkrivemaate extends StedsnavnEntityComponentWithHistory {

   private StedsnavnBobleId.KasustypeKodeId kasusTilKjernenavnId;
   private String kjernenavn;
   private String variasjonstillegg;
   private String funksjonstillegg;

   public KasusForSkrivemaate(Long id, LocalDateTime registreringsdato, StedsnavnBobleId.KasustypeKodeId kasusTilKjernenavnId, String kjernenavn) {
      super(id, registreringsdato);
      this.kasusTilKjernenavnId = kasusTilKjernenavnId;
      this.kjernenavn = kjernenavn;
   }

   public StedsnavnBobleId.KasustypeKodeId getKasusTilKjernenavnId() {
      return kasusTilKjernenavnId;
   }

   public String getKjernenavn() {
      return kjernenavn;
   }

   public String getVariasjonstillegg() {
      return variasjonstillegg;
   }

   public String getFunksjonstillegg() {
      return funksjonstillegg;
   }

   public void setVariasjonstillegg(String variasjonstillegg) {
      this.variasjonstillegg = variasjonstillegg;
   }

   public void setFunksjonstillegg(String funksjonstillegg) {
      this.funksjonstillegg = funksjonstillegg;
   }
}
