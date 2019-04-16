package no.statkart.wsclient.stedsnavn.endringslogg;

import no.statkart.wsclient.stedsnavn.StedsnavnBoble;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;

import java.time.LocalDateTime;

public class Endring extends StedsnavnBoble {

   private LocalDateTime endringstidspunkt;
   private Endringstype endringstype;
   private StedsnavnBobleId endretBubbleId;

   public Endring(StedsnavnBobleId id, LocalDateTime endringstidspunkt, Endringstype endringstype, StedsnavnBobleId endretBubbleId) {
      super(id);
      this.endringstidspunkt = endringstidspunkt;
      this.endringstype = endringstype;
      this.endretBubbleId = endretBubbleId;
   }

   public LocalDateTime getEndringstidspunkt() {
      return endringstidspunkt;
   }

   public Endringstype getEndringstype() {
      return endringstype;
   }

   public StedsnavnBobleId getEndretBubbleId() {
      return endretBubbleId;
   }
}
