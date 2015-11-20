package no.statkart.wsclient.grunnbok.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhet;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SDODokument;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SignertGrunnboksutskrift;

/**
 *
 */
public class SignertGrunnboksutskriftBuilder {
   protected Registerenhet gjelderFor;
   protected SDODokument signertUtskrift;
   private String dokumentreferanse;

   private SignertGrunnboksutskriftBuilder() {
   }

   public static SignertGrunnboksutskriftBuilder aSignertGrunnboksutskrift() {
      return new SignertGrunnboksutskriftBuilder();
   }

   public SignertGrunnboksutskriftBuilder withGjelderFor(Registerenhet gjelderFor) {
      this.gjelderFor = gjelderFor;
      return this;
   }

   public SignertGrunnboksutskriftBuilder withSignertUtskrift(SDODokument signertUtskrift) {
      this.signertUtskrift = signertUtskrift;
      return this;
   }

   public SignertGrunnboksutskriftBuilder withDokumentreferanse(String dokumentreferanse) {
      this.dokumentreferanse = dokumentreferanse;
      return this;
   }

   public SignertGrunnboksutskriftBuilder but() {
      return aSignertGrunnboksutskrift().withGjelderFor(gjelderFor).withSignertUtskrift(signertUtskrift)
            .withDokumentreferanse(dokumentreferanse);
   }

   public SignertGrunnboksutskrift build() {
      SignertGrunnboksutskrift signertGrunnboksutskrift = new SignertGrunnboksutskrift();
      signertGrunnboksutskrift.setGjelderFor(gjelderFor);
      signertGrunnboksutskrift.setSignertUtskrift(signertUtskrift);
      signertGrunnboksutskrift.setDokumentreferanse(dokumentreferanse);
      return signertGrunnboksutskrift;
   }
}
