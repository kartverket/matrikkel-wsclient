package no.statkart.wsclient.byggesak.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Holder informasjon vedrørende 1 bygning fra en byggesak
 */
public class ByggesakDTO {

   // generell info om byggesaken
    String tittel;
    String ansvarligSokerIdNummer;
    String tiltakshaverIdNummer;

   // Bygningsdata
    Long bygningsnr;
    Double bebygdAreal;
    Boolean harHeis;

    String avlopsKode;
    String bygningstypeKode;
    String naringsgruppeKode;
    String vannforsyningsKode;
    Set<String> energikildeKoder = new HashSet<>();
    Set<String> oppvarmingsKoder = new HashSet<>();

    Set<ByggesakBruksenhetDTO> bruksenheter = new HashSet<>();
    Set<ByggesakEtasjeDTO> etasjer = new HashSet<>();

   public String getTittel() {
      return tittel;
   }

   public void setTittel(String tittel) {
      this.tittel = tittel;
   }

   public String getAnsvarligSokerIdNummer() {
      return ansvarligSokerIdNummer;
   }

   public void setAnsvarligSokerIdNummer(String ansvarligSokerIdNummer) { this.ansvarligSokerIdNummer = ansvarligSokerIdNummer; }

   public String getTiltakshaverIdNummer() {
      return tiltakshaverIdNummer;
   }

   public void setTiltakshaverIdNummer(String tiltakshaverIdNummer) { this.tiltakshaverIdNummer = tiltakshaverIdNummer; }

   public Long getBygningsnr() { return bygningsnr; }

   public void setBygningsnr(String bygningsnr) {
      try {
         this.bygningsnr = Long.valueOf(bygningsnr);
      } catch (NumberFormatException ignored) {
         this.bygningsnr = null;
      }
   }

   public Double getBebygdAreal() {
      return bebygdAreal;
   }

   public void setBebygdAreal(Double bebygdAreal) {
      this.bebygdAreal = bebygdAreal;
   }

   public Boolean getHarHeis() {
      return harHeis;
   }

   public void setHarHeis(Boolean harHeis) {
      this.harHeis = harHeis;
   }

   public String getAvlopsKode() {
      return avlopsKode;
   }

   public void setAvlopsKode(String avlopsKode) {
      this.avlopsKode = avlopsKode;
   }

   public String getBygningstypeKode() {
      return bygningstypeKode;
   }

   public void setBygningstypeKode(String bygningstypeKode) {
      this.bygningstypeKode = bygningstypeKode;
   }

   public String getNaringsgruppeKode() {
      return naringsgruppeKode;
   }

   public void setNaringsgruppeKode(String naringsgruppeKode) {
      this.naringsgruppeKode = naringsgruppeKode;
   }

   public String getVannforsyningsKode() {
      return vannforsyningsKode;
   }

   public void setVannforsyningsKode(String vannforsyningsKode) {
      this.vannforsyningsKode = vannforsyningsKode;
   }

   public Set<String> getEnergikildeKoder() {
      return energikildeKoder;
   }

   public void setEnergikildeKoder(Set<String> energikildeKoder) {
      this.energikildeKoder = energikildeKoder;
   }

   public Set<String> getOppvarmingsKoder() {
      return oppvarmingsKoder;
   }

   public void setOppvarmingsKoder(Set<String> oppvarmingsKoder) {
      this.oppvarmingsKoder = oppvarmingsKoder;
   }

   public Set<ByggesakBruksenhetDTO> getBruksenheter() {
      return bruksenheter;
   }

   public void setBruksenheter(Set<ByggesakBruksenhetDTO> bruksenheter) {
      this.bruksenheter = bruksenheter;
   }

   public Set<ByggesakEtasjeDTO> getEtasjer() {
      return etasjer;
   }

   public void setEtasjer(Set<ByggesakEtasjeDTO> etasjer) {
      this.etasjer = etasjer;
   }
}
