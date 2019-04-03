package no.statkart.wsclient.stedsnavn.endringslogg;

public enum Domainklasse {

   STEDSNAVN_BUBBLE_OBJECT("StedsnavnBubbleObject"),
   STEDSNAVN_DB_KODE("StedsnavnDbKode"),
   FYLKE("Fylke"),
   KOMMUNE("Kommune"),
   POSISJON("Posisjon"),
   STED("Sted"),
   STEDSNAVN("Stedsnavn"),
   SKRIVEMAATE("Skrivemaate"),
   NAVNESAK("Navnesak"),
   VEDTAK("Vedtak"),
   SAMLEVEDTAK("Samlevedtak"),
   VEDTAKSOMRAADE("Vedtaksomraade"),
   DEL_AV_SAMLEVEDTAK("DelAvSamlevedtak"),
   KLAGE("Klage"),
   KARTPRODUKT("Kartprodukt"),
   BOK("Bok");

   private final String kodeverdi;

   Domainklasse(String kodeverdi) {
      this.kodeverdi = kodeverdi;
   }

   public String getKodeverdi() {
      return kodeverdi;
   }
}
