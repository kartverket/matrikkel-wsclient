package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import com.google.common.collect.Lists;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.*;

import java.util.List;

public class TinglysingsinformasjonBuilder {
   private List<Dokumentinformasjon> dokumentinformasjonList = Lists.newArrayList();
   private List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter = Lists.newArrayList();
   private List<UsignertGrunnboksutskrift> usignertGrunnboksutskrifter = Lists.newArrayList();

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
