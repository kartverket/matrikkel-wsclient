package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Dokumentinformasjon {

   private String dokumentreferanse;
   private int dokumentaar;
   private long dokumentnummer;
   private String embetenummer;
   private List<Rettsstiftelsesinformasjon> rettsstiftelsesinformasjon = Lists.newArrayList();

   public int getDokumentaar() {
      return dokumentaar;
   }

   public void setDokumentaar(int dokumentaar) {
      this.dokumentaar = dokumentaar;
   }

   public long getDokumentnummer() {
      return dokumentnummer;
   }

   public void setDokumentnummer(long dokumentnummer) {
      this.dokumentnummer = dokumentnummer;
   }

   public String getDokumentreferanse() {
      return dokumentreferanse;
   }

   public void setDokumentreferanse(String dokumentreferanse) {
      this.dokumentreferanse = dokumentreferanse;
   }

   public String getEmbetenummer() {
      return embetenummer;
   }

   public void setEmbetenummer(String embetenummer) {
      this.embetenummer = embetenummer;
   }

   public List<Rettsstiftelsesinformasjon> getRettsstiftelsesinformasjon() {
      return rettsstiftelsesinformasjon;
   }

   public void setRettsstiftelsesinformasjon(List<Rettsstiftelsesinformasjon> rettsstiftelsesinformasjon) {
      this.rettsstiftelsesinformasjon = rettsstiftelsesinformasjon;
   }
}
