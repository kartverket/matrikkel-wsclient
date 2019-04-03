package no.statkart.wsclient.stedsnavn.endringslogg;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class StedInternMerknad extends StedsnavnEntityComponentWithHistory {

   private String tekst;
   private StedsnavnBobleId.StedMerknadstypeKodeId merknadstypeId;
   private List<String> fellesarkiv;

   public StedInternMerknad(Long id, LocalDateTime registreringsdato, String tekst, StedsnavnBobleId.StedMerknadstypeKodeId merknadstypeId, List<String> fellesarkiv) {
      super(id, registreringsdato);
      this.tekst = tekst;
      this.merknadstypeId = merknadstypeId;
      this.fellesarkiv = Objects.requireNonNull(fellesarkiv);
   }

   public String getTekst() {
      return tekst;
   }

   public StedsnavnBobleId.StedMerknadstypeKodeId getMerknadstypeId() {
      return merknadstypeId;
   }

   public List<String> getFellesarkiv() {
      return fellesarkiv;
   }
}
