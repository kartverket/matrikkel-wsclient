package no.statkart.wsclient.grunnbokv2.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public class Dokument {

   private String dokumentreferanse;
   private List<Rettsstiftelse> rettsstiftelser = Lists.newArrayList();

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
