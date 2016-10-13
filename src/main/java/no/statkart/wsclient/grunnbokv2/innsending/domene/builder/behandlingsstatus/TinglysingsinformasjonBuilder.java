package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Dokumentinformasjon;
import no.statkart.wsclient.grunnbokv2.innsending.domene.SignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Tinglysingsinformasjon;

import java.util.List;

/**
 *
 */
public class TinglysingsinformasjonBuilder {
   private List<Dokumentinformasjon> dokumentinformasjonList = Lists.newArrayList();
   private List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter = Lists.newArrayList();

   private TinglysingsinformasjonBuilder() {
   }

   public static TinglysingsinformasjonBuilder aTinglysingsinformasjon() {
      return new TinglysingsinformasjonBuilder();
   }

   public TinglysingsinformasjonBuilder withDokumentinformasjon(List<Dokumentinformasjon> dokumentinformasjonList) {
      this.dokumentinformasjonList = dokumentinformasjonList;
      return this;
   }

   public TinglysingsinformasjonBuilder withSignerteGrunnboksutskrifter(List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter) {
      this.signerteGrunnboksutskrifter = signerteGrunnboksutskrifter;
      return this;
   }

   public TinglysingsinformasjonBuilder but() {
      return aTinglysingsinformasjon().withDokumentinformasjon(dokumentinformasjonList).withSignerteGrunnboksutskrifter(signerteGrunnboksutskrifter);
   }

   public Tinglysingsinformasjon build() {
      Tinglysingsinformasjon tinglysingsinformasjon = new Tinglysingsinformasjon();
      tinglysingsinformasjon.setDokumentinformasjon(dokumentinformasjonList);
      tinglysingsinformasjon.setSignerteGrunnboksutskrifter(signerteGrunnboksutskrifter);
      return tinglysingsinformasjon;
   }
}
