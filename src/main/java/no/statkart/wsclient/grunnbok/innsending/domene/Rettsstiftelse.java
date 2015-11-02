package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public abstract class Rettsstiftelse {

   private String rettsstiftelsesreferanse;
   private Kode rettsstiftelsestype;
   private List<Tekst> tekster = Lists.newArrayList();

   public enum Rettsstiftelsestype {
      ANMERKNING_PAA_PERSON, SLETTING, OVERDRAGELSE_AV_REGISTERENHETSRETT, TVANGSFORRETNING, PANT, ANNEN_HEFTELSE, MATRIKKELENHETSENDRING;
   }

   public abstract Rettsstiftelsestype getRettstiftelsestype();

   public String getRettsstiftelsesreferanse() {
      return rettsstiftelsesreferanse;
   }

   public void setRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
   }

   public Kode getRettsstiftelsestype() {
      return rettsstiftelsestype;
   }

   public void setRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
   }

   public List<Tekst> getTekster() {
      return tekster;
   }

   public void setTekster(List<Tekst> tekster) {
      this.tekster = tekster;
   }
}
