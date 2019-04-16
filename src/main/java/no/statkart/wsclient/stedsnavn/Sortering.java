package no.statkart.wsclient.stedsnavn;


public class Sortering {

   private StedsnavnBobleId.Sortering1KodeId sortering1KodeId;
   private StedsnavnBobleId.Sortering2KodeId sortering2KodeId;

   public Sortering(StedsnavnBobleId.Sortering1KodeId sortering1KodeId, StedsnavnBobleId.Sortering2KodeId sortering2KodeId) {
      this.sortering1KodeId = sortering1KodeId;
      this.sortering2KodeId = sortering2KodeId;
   }

   public StedsnavnBobleId.Sortering1KodeId getSortering1KodeId() {
      return sortering1KodeId;
   }

   public StedsnavnBobleId.Sortering2KodeId getSortering2KodeId() {
      return sortering2KodeId;
   }
}
