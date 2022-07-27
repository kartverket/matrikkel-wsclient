package no.statkart.wsclient.grunnbokv2.innsending.domene;


import java.util.ArrayList;
import java.util.List;

public class UsignertMelding {

   private List<Dokument> dokumenter = new ArrayList<>();
    private Foelgebrev foelgebrev;

   public List<Dokument> getDokumenter() {
      return dokumenter;
   }

   public void setDokumenter(List<Dokument> dokumenter) {
      this.dokumenter = dokumenter;
   }

   public Foelgebrev getFoelgebrev() {
      return foelgebrev;
   }

   public void setFoelgebrev(Foelgebrev foelgebrev) {
      this.foelgebrev = foelgebrev;
   }
}
