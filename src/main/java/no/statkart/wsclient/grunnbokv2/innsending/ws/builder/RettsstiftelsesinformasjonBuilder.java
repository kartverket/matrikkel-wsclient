package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Rettsstiftelsesinformasjon;

public class RettsstiftelsesinformasjonBuilder {
   protected Integer rettsstiftelsesnummer;
   protected String rettsstiftelsesreferanse;

   private RettsstiftelsesinformasjonBuilder() {
   }

   public static RettsstiftelsesinformasjonBuilder aRettsstiftelsesinformasjon() {
      return new RettsstiftelsesinformasjonBuilder();
   }

   public RettsstiftelsesinformasjonBuilder withRettsstiftelsesnummer(Integer rettsstiftelsesnummer) {
      this.rettsstiftelsesnummer = rettsstiftelsesnummer;
      return this;
   }

   public RettsstiftelsesinformasjonBuilder withRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
      this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
      return this;
   }

   public RettsstiftelsesinformasjonBuilder but() {
      return aRettsstiftelsesinformasjon().withRettsstiftelsesnummer(rettsstiftelsesnummer).withRettsstiftelsesreferanse(rettsstiftelsesreferanse);
   }

   public Rettsstiftelsesinformasjon build() {
      Rettsstiftelsesinformasjon rettsstiftelsesinformasjon = new Rettsstiftelsesinformasjon();
      rettsstiftelsesinformasjon.setRettsstiftelsesnummer(rettsstiftelsesnummer);
      rettsstiftelsesinformasjon.setRettsstiftelsesreferanse(rettsstiftelsesreferanse);
      return rettsstiftelsesinformasjon;
   }
}
