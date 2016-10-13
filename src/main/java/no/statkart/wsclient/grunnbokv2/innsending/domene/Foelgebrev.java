package no.statkart.wsclient.grunnbokv2.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public class Foelgebrev {

   private Saksperson innsender;
   private Saksperson fakturamottaker;
   private Saksperson mottaker;

   private List<Referanse> dokumentrekkefoelge = Lists.newArrayList();
   private Kode maalform;

   public List<Referanse> getDokumentrekkefoelge() {
      return dokumentrekkefoelge;
   }

   public void setDokumentrekkefoelge(List<Referanse> dokumentrekkefoelge) {
      this.dokumentrekkefoelge = dokumentrekkefoelge;
   }

   public Saksperson getInnsender() {
      return innsender;
   }

   public void setInnsender(Saksperson innsender) {
      this.innsender = innsender;
   }

   public Saksperson getFakturamottaker() {
      return fakturamottaker;
   }

   public void setFakturamottaker(Saksperson fakturamottaker) {
      this.fakturamottaker = fakturamottaker;
   }

   public Saksperson getMottaker() {
      return mottaker;
   }

   public void setMottaker(Saksperson mottaker) {
      this.mottaker = mottaker;
   }

   public Kode getMaalform() {
      return maalform;
   }

   public void setMaalform(Kode maalform) {
      this.maalform = maalform;
   }
}
