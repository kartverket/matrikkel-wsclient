package no.statkart.wsclient.grunnbokv2.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Kontrollresultat {

   private Integer dokumentindeks;
   private Integer rettsstiftelsesindeks;
   private String kodeverdi;
   private String navn;
   private String utfall;
   private List<Begrunnelse> begrunnelser = Lists.newArrayList();

   public List<Begrunnelse> getBegrunnelser() {
      return begrunnelser;
   }

   public void setBegrunnelser(List<Begrunnelse> begrunnelser) {
      this.begrunnelser = begrunnelser;
   }

   public Integer getDokumentindeks() {
      return dokumentindeks;
   }

   public void setDokumentindeks(Integer dokumentindeks) {
      this.dokumentindeks = dokumentindeks;
   }

   public String getKodeverdi() {
      return kodeverdi;
   }

   public void setKodeverdi(String kodeverdi) {
      this.kodeverdi = kodeverdi;
   }

   public String getNavn() {
      return navn;
   }

   public void setNavn(String navn) {
      this.navn = navn;
   }

   public Integer getRettsstiftelsesindeks() {
      return rettsstiftelsesindeks;
   }

   public void setRettsstiftelsesindeks(Integer rettsstiftelsesindeks) {
      this.rettsstiftelsesindeks = rettsstiftelsesindeks;
   }

   public String getUtfall() {
      return utfall;
   }

   public void setUtfall(String utfall) {
      this.utfall = utfall;
   }
}
