package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.SDODokument;
import no.statkart.wsclient.grunnbok.innsending.domene.SignertMelding;

import java.util.List;


public class SignertMeldingBuilder {
   private List<SDODokument> dokumenter = Lists.newArrayList();
   private SDODokument foelgebrev;

   private SignertMeldingBuilder() {
   }

   public static SignertMeldingBuilder aSignertMelding() {
      return new SignertMeldingBuilder();
   }

   public SignertMeldingBuilder withDokumenter(List<SDODokument> dokumenter) {
      this.dokumenter = dokumenter;
      return this;
   }

   public SignertMeldingBuilder withFoelgebrev(SDODokument foelgebrev) {
      this.foelgebrev = foelgebrev;
      return this;
   }

   public SignertMeldingBuilder but() {
      return aSignertMelding().withDokumenter(dokumenter).withFoelgebrev(foelgebrev);
   }

   public SignertMelding build() {
      SignertMelding signertMelding = new SignertMelding();
      signertMelding.setDokumenter(dokumenter);
      signertMelding.setFoelgebrev(foelgebrev);
      return signertMelding;
   }
}
