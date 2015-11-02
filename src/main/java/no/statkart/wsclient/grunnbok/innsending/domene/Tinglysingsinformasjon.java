package no.statkart.wsclient.grunnbok.innsending.domene;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Tinglysingsinformasjon {

   private List<Dokumentinformasjon> dokumentinformasjon = Lists.newArrayList();
   private List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter = Lists.newArrayList();

   public List<Dokumentinformasjon> getDokumentinformasjon() {
      return dokumentinformasjon;
   }

   public void setDokumentinformasjon(List<Dokumentinformasjon> dokumentinformasjon) {
      this.dokumentinformasjon = dokumentinformasjon;
   }

   public List<SignertGrunnboksutskrift> getSignerteGrunnboksutskrifter() {
      return signerteGrunnboksutskrifter;
   }

   public void setSignerteGrunnboksutskrifter(List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter) {
      this.signerteGrunnboksutskrifter = signerteGrunnboksutskrifter;
   }
}
