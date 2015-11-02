package no.statkart.wsclient.grunnbok.innsending.domene;

/**
 *
 */
public class RettsstiftelseReferanse {

   private int dokumentaar;
   private long dokumentnummer;
   private String embetenummer;
   private int rettsstiftelsesnummer;
   private Kode rettsstiftelsestype;

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

   public String getEmbetenummer() {
      return embetenummer;
   }

   public void setEmbetenummer(String embetenummer) {
      this.embetenummer = embetenummer;
   }

   public int getRettsstiftelsesnummer() {
      return rettsstiftelsesnummer;
   }

   public void setRettsstiftelsesnummer(int rettsstiftelsesnummer) {
      this.rettsstiftelsesnummer = rettsstiftelsesnummer;
   }

   public Kode getRettsstiftelsestype() {
      return rettsstiftelsestype;
   }

   public void setRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
   }
}
