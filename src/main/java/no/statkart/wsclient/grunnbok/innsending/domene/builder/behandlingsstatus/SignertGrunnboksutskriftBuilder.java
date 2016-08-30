package no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus;


import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.SDODokument;
import no.statkart.wsclient.grunnbok.innsending.domene.SignertGrunnboksutskrift;

import java.util.List;

/**
 *
 */
public class SignertGrunnboksutskriftBuilder {
   protected Registerenhet gjelderFor;
   protected SDODokument signertUtskrift;
   protected List<String> dokumentreferanser;

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

   public SignertGrunnboksutskriftBuilder withDokumentreferanse(List<String> dokumentreferanser) {
      this.dokumentreferanser = dokumentreferanser;
      return this;
   }

   public SignertGrunnboksutskriftBuilder but() {
      return aSignertGrunnboksutskrift().withGjelderFor(gjelderFor).withSignertUtskrift(signertUtskrift)
            .withDokumentreferanse(dokumentreferanser);
   }

   public SignertGrunnboksutskrift build() {
      SignertGrunnboksutskrift signertGrunnboksutskrift = new SignertGrunnboksutskrift();
      signertGrunnboksutskrift.setGjelderFor(gjelderFor);
      signertGrunnboksutskrift.setSignertUtskrift(signertUtskrift);
      signertGrunnboksutskrift.setDokumentreferanser(dokumentreferanser);
      return signertGrunnboksutskrift;
   }
}
