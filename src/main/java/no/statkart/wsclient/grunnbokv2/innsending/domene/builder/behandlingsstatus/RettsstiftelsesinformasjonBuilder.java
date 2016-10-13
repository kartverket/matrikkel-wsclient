package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;


import no.statkart.wsclient.grunnbokv2.innsending.domene.Rettsstiftelsesinformasjon;

/**
 *
 */
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
