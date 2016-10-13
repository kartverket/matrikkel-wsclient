package no.statkart.wsclient.grunnbokv2.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;


public class SignertMelding {

   private List<SDODokument> dokumenter = Lists.newArrayList();
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
