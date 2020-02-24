package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import com.google.common.collect.Lists;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.*;

import java.util.List;

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
      DokumentinformasjonList dokumentinformasjon = new DokumentinformasjonList();
      dokumentinformasjon.getDokumentinformasjon().addAll(dokumentinformasjonList);
      tinglysingsinformasjon.setDokumentinformasjon(dokumentinformasjon);

      SignertGrunnboksutskriftList signertGrunnboksutskriftList = new SignertGrunnboksutskriftList();
      signertGrunnboksutskriftList.getSignertGrunnboksutskrift().addAll(signerteGrunnboksutskrifter);
      tinglysingsinformasjon.setSignerteGrunnboksutskrifter(signertGrunnboksutskriftList);
      return tinglysingsinformasjon;
   }
}
