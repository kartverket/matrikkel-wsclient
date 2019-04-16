package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class SkrivemaateInternMerknad extends StedsnavnEntityComponentWithHistory {

   private String tekst;
   private StedsnavnBobleId.SkrivemaateMerknadstypeKodeId merknadstypeId;
   private List<String> fellesarkiv;

   public SkrivemaateInternMerknad(Long id, LocalDateTime registreringsdato, String tekst, StedsnavnBobleId.SkrivemaateMerknadstypeKodeId merknadstypeId, List<String> fellesarkiv) {
      super(id, registreringsdato);
      this.tekst = tekst;
      this.merknadstypeId = merknadstypeId;
      this.fellesarkiv = Objects.requireNonNull(fellesarkiv);
   }

   public String getTekst() {
      return tekst;
   }

   public StedsnavnBobleId.SkrivemaateMerknadstypeKodeId getMerknadstypeId() {
      return merknadstypeId;
   }

   public List<String> getFellesarkiv() {
      return fellesarkiv;
   }
}
