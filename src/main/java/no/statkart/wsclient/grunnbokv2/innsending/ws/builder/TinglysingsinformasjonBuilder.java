package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokumentinformasjon;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.DokumentinformasjonList;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.GrunnboksutskriftList;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SignertGrunnboksutskrift;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SignertGrunnboksutskriftList;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Tinglysingsinformasjon;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.UsignertGrunnboksutskrift;

import java.util.ArrayList;
import java.util.List;

public class TinglysingsinformasjonBuilder {
   private List<Dokumentinformasjon> dokumentinformasjonList = new ArrayList<>();
    private List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter = new ArrayList<>();
    private List<UsignertGrunnboksutskrift> usignertGrunnboksutskrifter = new ArrayList<>();

    private TinglysingsinformasjonBuilder() {
   }

   public static TinglysingsinformasjonBuilder aTinglysingsinformasjon() {
      return new TinglysingsinformasjonBuilder();
   }

   public TinglysingsinformasjonBuilder withDokumentinformasjon(List<Dokumentinformasjon> dokumentinformasjonList) {
      this.dokumentinformasjonList = dokumentinformasjonList;
      return this;
   }

   public TinglysingsinformasjonBuilder withUsignerteGrunnboksutskrifter(List<UsignertGrunnboksutskrift> usignerteGrunnboksutskrifter) {
       this.usignertGrunnboksutskrifter = usignerteGrunnboksutskrifter;
       return this;
   }

   public Tinglysingsinformasjon build() {
      Tinglysingsinformasjon tinglysingsinformasjon = new Tinglysingsinformasjon();
      DokumentinformasjonList dokumentinformasjon = new DokumentinformasjonList();
      dokumentinformasjon.getDokumentinformasjon().addAll(dokumentinformasjonList);
      tinglysingsinformasjon.setDokumentinformasjon(dokumentinformasjon);

      SignertGrunnboksutskriftList signertGrunnboksutskriftList = new SignertGrunnboksutskriftList();
      signertGrunnboksutskriftList.getSignertGrunnboksutskrift().addAll(signerteGrunnboksutskrifter);
      tinglysingsinformasjon.setSignerteGrunnboksutskrifter(signertGrunnboksutskriftList);

       GrunnboksutskriftList usignertGrunnboksutskriftList = new GrunnboksutskriftList();
       usignertGrunnboksutskriftList.getUsignertGrunnboksutskrift().addAll(usignertGrunnboksutskrifter);
       tinglysingsinformasjon.setGrunnboksutskrifter(usignertGrunnboksutskriftList);

       return tinglysingsinformasjon;
   }
}
