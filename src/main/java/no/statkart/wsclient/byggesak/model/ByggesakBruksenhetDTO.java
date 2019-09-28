package no.statkart.wsclient.byggesak.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Data for 1 bruksenhet på 1 bygning i en byggesak
 */
public class ByggesakBruksenhetDTO {

   // bruksenhetsnummer - required
   Integer lopenr = null;
   Integer etasjenr = null;
   String etasjeplanKode = null;

   // generell info
   Double bruksAreal;
   Integer antallRom;
   Integer antallBad;
   Integer antallWC;

   // matrikkelenhet
   String kommunenr;
   Integer gardsnr;
   Integer bruksnr;
   Integer festenr;
   Integer seksjonsnr;

   // adresse
   Integer adressekode;
   Integer adressenummer;
   String adressebokstav;

   // koder
   String bruksenhetstypeKode;
   String kjokkentilgangKode;

   List<String> feilkoder = new ArrayList<>();

   public ByggesakBruksenhetDTO() {}

   public Double getBruksAreal() {
      return bruksAreal;
   }

   public void setBruksAreal(Double bruksAreal) {
      this.bruksAreal = bruksAreal;
   }

   public String getKommunenr() {
      return kommunenr;
   }

   public void setKommunenr(String kommunenr) {
      this.kommunenr = kommunenr;
   }

   public Integer getGardsnr() {
      return gardsnr;
   }

   public void setGardsnr(BigInteger gardsnr) {
      this.gardsnr = gardsnr != null ? gardsnr.intValue() : 0;
   }

   public Integer getBruksnr() {
      return bruksnr;
   }

   public void setBruksnr(BigInteger bruksnr) {
      this.bruksnr = bruksnr != null ? bruksnr.intValue() : 0;
   }

   public Integer getFestenr() {
      return festenr;
   }

   public void setFestenr(BigInteger festenr) {
      this.festenr = festenr != null ? festenr.intValue() : 0;
   }

   public Integer getSeksjonsnr() {
      return seksjonsnr;
   }

   public void setSeksjonsnr(BigInteger seksjonsnr) {
      this.seksjonsnr = seksjonsnr != null ? seksjonsnr.intValue() : 0;
   }

   public Integer getAdressekode() {
      return adressekode;
   }

   public void setAdressekode(String adressekode) {
      this.adressekode = adressekode != null ? Integer.valueOf(adressekode) : null;
   }

   public Integer getAdressenummer() {
      return adressenummer;
   }

   public void setAdressenummer(String adressenummer) {

      this.adressenummer = adressenummer != null ? Integer.valueOf(adressenummer) : null;
   }

   public String getAdressebokstav() {
      return adressebokstav;
   }

   public void setAdressebokstav(String adressebokstav) {
      this.adressebokstav = adressebokstav;
   }

   public Integer getEtasjenr() {
      return etasjenr;
   }

   public void setEtasjenr(BigInteger etasjenr) {
      this.etasjenr = etasjenr.intValue();
   }

   public Integer getLopenr() {
      return lopenr;
   }

   public void setLopenr(BigInteger lopenr) {
      this.lopenr = lopenr != null ? lopenr.intValue() : null;
   }

   public Integer getAntallRom() {
      return antallRom;
   }

   public void setAntallRom(BigInteger antallRom) {
      this.antallRom = antallRom != null ? antallRom.intValue() : null;
   }

   public Integer getAntallBad() {
      return antallBad;
   }

   public void setAntallBad(BigInteger antallBad) {
      this.antallBad = antallBad != null ? antallBad.intValue() : null;
   }

   public Integer getAntallWC() {
      return antallWC;
   }

   public void setAntallWC(BigInteger antallWC) {
      this.antallWC = antallWC != null ? antallWC.intValue() : null;
   }

   public String getEtasjeplanKode() {
      return etasjeplanKode;
   }

   public void setEtasjeplanKode(String etasjeplanKode) {
      this.etasjeplanKode = etasjeplanKode;
   }

   public String getBruksenhetstypeKode() {
      return bruksenhetstypeKode;
   }

   public void setBruksenhetstypeKode(String bruksenhetstypeKode) {
      this.bruksenhetstypeKode = bruksenhetstypeKode;
   }

   public String getKjokkentilgangKode() {
      return kjokkentilgangKode;
   }

   public void setKjokkentilgangKode(String kjokkentilgangKode) {
      this.kjokkentilgangKode = kjokkentilgangKode;
   }

   /**
    * Obligatoriske felt i modellen.
    *
    * @return Liste med feil.
    */
   public List<String> validerBruksenhetInfo() {
      feilkoder.clear();

      if(lopenr == null) feilkoder.add("Løpenummer ikke satt på BruksenhetInfo");
      if(etasjenr == null) feilkoder.add("Etasjenummer ikke satt på BruksenhetInfo");
      if(etasjeplanKode == null) feilkoder.add("Etasjeplankode ikke satt på BruksenhetInfo");

      return feilkoder;
   }
}
