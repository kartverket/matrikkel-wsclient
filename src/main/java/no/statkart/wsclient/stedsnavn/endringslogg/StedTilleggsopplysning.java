package no.statkart.wsclient.stedsnavn.endringslogg;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class StedTilleggsopplysning extends StedsnavnEntityComponentWithHistory{

   private String tekst;
   //URLList
   private List<String> eksterneOpplysninger;
   private StedsnavnBobleId.StedTilleggsopplysningstypeKodeId tilleggsopplysningstypeId;

   public StedTilleggsopplysning(Long id, LocalDateTime registreringsdato, String tekst, List<String> eksterneOpplysninger, StedsnavnBobleId.StedTilleggsopplysningstypeKodeId tilleggsopplysningstypeId) {
      super(id, registreringsdato);
      this.tekst = tekst;
      this.eksterneOpplysninger = Objects.requireNonNull(eksterneOpplysninger);
      this.tilleggsopplysningstypeId = tilleggsopplysningstypeId;
   }

   public String getTekst() {
      return tekst;
   }

   public List<String> getEksterneOpplysninger() {
      return eksterneOpplysninger;
   }

   public StedsnavnBobleId.StedTilleggsopplysningstypeKodeId getTilleggsopplysningstypeId() {
      return tilleggsopplysningstypeId;
   }
}
