package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Omsetning {

   private Boolean utlystTilSalgPaaDetFrieMarked;
   private Kode omsetningstype;
   private Beloep vederlag;
   private Dokumentavgiftsplikt dokumentavgiftsplikt;
   private List<OmsattRegisterenhetsrett> omsatteRegisterenhetsretter = Lists.newArrayList();

   public Dokumentavgiftsplikt getDokumentavgiftsplikt() {
      return dokumentavgiftsplikt;
   }

   public void setDokumentavgiftsplikt(Dokumentavgiftsplikt dokumentavgiftsplikt) {
      this.dokumentavgiftsplikt = dokumentavgiftsplikt;
   }

   public List<OmsattRegisterenhetsrett> getOmsatteRegisterenhetsretter() {
      return omsatteRegisterenhetsretter;
   }

   public void setOmsatteRegisterenhetsretter(List<OmsattRegisterenhetsrett> omsatteRegisterenhetsretter) {
      this.omsatteRegisterenhetsretter = omsatteRegisterenhetsretter;
   }

   public Kode getOmsetningstype() {
      return omsetningstype;
   }

   public void setOmsetningstype(Kode omsetningstype) {
      this.omsetningstype = omsetningstype;
   }

   public Boolean getUtlystTilSalgPaaDetFrieMarked() {
      return utlystTilSalgPaaDetFrieMarked;
   }

   public void setUtlystTilSalgPaaDetFrieMarked(Boolean utlystTilSalgPaaDetFrieMarked) {
      this.utlystTilSalgPaaDetFrieMarked = utlystTilSalgPaaDetFrieMarked;
   }

   public Beloep getVederlag() {
      return vederlag;
   }

   public void setVederlag(Beloep vederlag) {
      this.vederlag = vederlag;
   }
}
