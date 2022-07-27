package no.statkart.wsclient.grunnbokv2.innsending.domene;


import java.util.ArrayList;
import java.util.List;

public class Dokument {

   private String dokumentreferanse;
   private List<Rettsstiftelse> rettsstiftelser = new ArrayList<>();

    public String getDokumentreferanse() {
      return dokumentreferanse;
   }

   public void setDokumentreferanse(String dokumentreferanse) {
      this.dokumentreferanse = dokumentreferanse;
   }

   public List<Rettsstiftelse> getRettsstiftelser() {
      return rettsstiftelser;
   }

   public void setRettsstiftelser(List<Rettsstiftelse> rettsstiftelser) {
      this.rettsstiftelser = rettsstiftelser;
   }
}
