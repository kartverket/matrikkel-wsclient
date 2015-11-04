package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.RettsstiftelseReferanse;

/**
 *
 */
public class RettsstiftelseReferanseBuilder {
   private int dokumentaar;
   private long dokumentnummer;
   private String embetenummer;
   private int rettsstiftelsesnummer;
   private Kode rettsstiftelsestype;

   private RettsstiftelseReferanseBuilder() {
   }

   public static RettsstiftelseReferanseBuilder aRettsstiftelseReferanse() {
      return new RettsstiftelseReferanseBuilder();
   }

   public RettsstiftelseReferanseBuilder withDokumentaar(int dokumentaar) {
      this.dokumentaar = dokumentaar;
      return this;
   }

   public RettsstiftelseReferanseBuilder withDokumentnummer(long dokumentnummer) {
      this.dokumentnummer = dokumentnummer;
      return this;
   }

   public RettsstiftelseReferanseBuilder withEmbetenummer(String embetenummer) {
      this.embetenummer = embetenummer;
      return this;
   }

   public RettsstiftelseReferanseBuilder withRettsstiftelsesnummer(int rettsstiftelsesnummer) {
      this.rettsstiftelsesnummer = rettsstiftelsesnummer;
      return this;
   }

   public RettsstiftelseReferanseBuilder withRettsstiftelsestype(Kode rettsstiftelsestype) {
      this.rettsstiftelsestype = rettsstiftelsestype;
      return this;
   }

   public RettsstiftelseReferanseBuilder but() {
      return aRettsstiftelseReferanse().withDokumentaar(dokumentaar).withDokumentnummer(dokumentnummer).withEmbetenummer(embetenummer).withRettsstiftelsesnummer(rettsstiftelsesnummer).withRettsstiftelsestype(rettsstiftelsestype);
   }

   public RettsstiftelseReferanse build() {
      RettsstiftelseReferanse rettsstiftelseReferanse = new RettsstiftelseReferanse();
      rettsstiftelseReferanse.setDokumentaar(dokumentaar);
      rettsstiftelseReferanse.setDokumentnummer(dokumentnummer);
      rettsstiftelseReferanse.setEmbetenummer(embetenummer);
      rettsstiftelseReferanse.setRettsstiftelsesnummer(rettsstiftelsesnummer);
      rettsstiftelseReferanse.setRettsstiftelsestype(rettsstiftelsestype);
      return rettsstiftelseReferanse;
   }
}
