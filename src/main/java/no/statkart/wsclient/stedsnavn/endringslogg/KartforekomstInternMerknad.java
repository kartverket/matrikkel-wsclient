package no.statkart.wsclient.stedsnavn.endringslogg;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class KartforekomstInternMerknad extends StedsnavnEntityComponentWithHistory {

   private String tekst;
   private StedsnavnBobleId.KartforekomstMerknadstypeKodeId merknadstypeId;
   private List<String> fellesarkiv;

   public KartforekomstInternMerknad(Long id, LocalDateTime registreringsdato, String tekst, StedsnavnBobleId.KartforekomstMerknadstypeKodeId merknadstypeId, List<String> fellesarkiv) {
      super(id, registreringsdato);
      this.tekst = tekst;
      this.merknadstypeId = merknadstypeId;
      this.fellesarkiv = Objects.requireNonNull(fellesarkiv);
   }

   public String getTekst() {
      return tekst;
   }

   public StedsnavnBobleId.KartforekomstMerknadstypeKodeId getMerknadstypeId() {
      return merknadstypeId;
   }

   public List<String> getFellesarkiv() {
      return fellesarkiv;
   }
}
