package no.statkart.wsclient.stedsnavn;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class StedsnavnTilleggsopplysning extends StedsnavnEntityComponentWithHistory {

   private String tekst;
   //URLList
   private List<String> eksterneOpplysninger;
   private StedsnavnBobleId.StedsnavnTilleggsopplysningstypeKodeId tilleggsopplysningstypeId;

   public StedsnavnTilleggsopplysning(Long id, LocalDateTime registreringsdato, String tekst, StedsnavnBobleId.StedsnavnTilleggsopplysningstypeKodeId tilleggsopplysningstypeId,
                                      List<String> eksterneOpplysninger) {
      super(id, registreringsdato);
      this.tekst = tekst;
      this.tilleggsopplysningstypeId = tilleggsopplysningstypeId;
      this.eksterneOpplysninger = Objects.requireNonNull(eksterneOpplysninger);
   }

   public String getTekst() {
      return tekst;
   }

   public List<String> getEksterneOpplysninger() {
      return eksterneOpplysninger;
   }

   public StedsnavnBobleId.StedsnavnTilleggsopplysningstypeKodeId getTilleggsopplysningstypeId() {
      return tilleggsopplysningstypeId;
   }
}

