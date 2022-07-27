package no.statkart.wsclient.grunnbokv2.innsending.domene;


import java.util.ArrayList;
import java.util.List;


public class SignertMelding {

   private List<SDODokument> dokumenter = new ArrayList<>();
    private SDODokument foelgebrev;

   public List<SDODokument> getDokumenter() {
      return dokumenter;
   }

   public void setDokumenter(List<SDODokument> dokumenter) {
      this.dokumenter = dokumenter;
   }

   public SDODokument getFoelgebrev() {
      return foelgebrev;
   }

   public void setFoelgebrev(SDODokument foelgebrev) {
      this.foelgebrev = foelgebrev;
   }
}
