package no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokumentinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelsesinformasjon;

import java.util.List;

/**
 *
 */
public class DokumentinformasjonBuilder {
   protected int dokumentaar;
   protected String dokumentreferanse;
   protected long dokumentnummer;
   protected String embetenummer;
   protected List<Rettsstiftelsesinformasjon> rettsstiftelsesinformasjonList = Lists.newArrayList();

   private DokumentinformasjonBuilder() {
   }

   public static DokumentinformasjonBuilder aDokumentinformasjon() {
      return new DokumentinformasjonBuilder();
   }

   public DokumentinformasjonBuilder withDokumentaar(int dokumentaar) {
      this.dokumentaar = dokumentaar;
      return this;
   }

   public DokumentinformasjonBuilder withDokumentreferanse(String dokumentreferanse) {
      this.dokumentreferanse = dokumentreferanse;
      return this;
   }

   public DokumentinformasjonBuilder withDokumentnummer(long dokumentnummer) {
      this.dokumentnummer = dokumentnummer;
      return this;
   }

   public DokumentinformasjonBuilder withEmbetenummer(String embetenummer) {
      this.embetenummer = embetenummer;
      return this;
   }

   public DokumentinformasjonBuilder withRettsstiftelsesinformasjonList(List<Rettsstiftelsesinformasjon> rettsstiftelsesinformasjonList) {
      this.rettsstiftelsesinformasjonList = rettsstiftelsesinformasjonList;
      return this;
   }

   public DokumentinformasjonBuilder but() {
      return aDokumentinformasjon().withDokumentaar(dokumentaar).withDokumentreferanse(dokumentreferanse).withDokumentnummer(dokumentnummer).withEmbetenummer(embetenummer).withRettsstiftelsesinformasjonList(rettsstiftelsesinformasjonList);
   }

   public Dokumentinformasjon build() {
      Dokumentinformasjon dokumentinformasjon = new Dokumentinformasjon();
      dokumentinformasjon.setDokumentaar(dokumentaar);
      dokumentinformasjon.setDokumentreferanse(dokumentreferanse);
      dokumentinformasjon.setDokumentnummer(dokumentnummer);
      dokumentinformasjon.setEmbetenummer(embetenummer);
      dokumentinformasjon.setRettsstiftelsesinformasjon(rettsstiftelsesinformasjonList);
      return dokumentinformasjon;
   }
}
