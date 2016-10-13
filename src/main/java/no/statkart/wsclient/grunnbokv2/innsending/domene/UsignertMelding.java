package no.statkart.wsclient.grunnbokv2.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public class UsignertMelding {

   private List<Dokument> dokumenter = Lists.newArrayList();
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
