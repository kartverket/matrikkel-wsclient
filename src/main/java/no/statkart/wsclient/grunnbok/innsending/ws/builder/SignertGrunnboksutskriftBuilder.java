package no.statkart.wsclient.grunnbok.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.DokumentreferanseList;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhet;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SDODokument;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SignertGrunnboksutskrift;

import java.util.List;

/**
 *
 */
public class SignertGrunnboksutskriftBuilder {
   protected Registerenhet gjelderFor;
   protected SDODokument signertUtskrift;
   private List<String> dokumentreferanser;

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

   public SignertGrunnboksutskriftBuilder withDokumentreferanser(List<String> dokumentreferanser) {
      this.dokumentreferanser = dokumentreferanser;
      return this;
   }

   public SignertGrunnboksutskriftBuilder but() {
      return aSignertGrunnboksutskrift().withGjelderFor(gjelderFor).withSignertUtskrift(signertUtskrift)
            .withDokumentreferanser(dokumentreferanser);
   }

   public SignertGrunnboksutskrift build() {
      SignertGrunnboksutskrift signertGrunnboksutskrift = new SignertGrunnboksutskrift();
      signertGrunnboksutskrift.setGjelderFor(gjelderFor);
      signertGrunnboksutskrift.setSignertUtskrift(signertUtskrift);
      DokumentreferanseList dokumentreferanseList = new DokumentreferanseList();
      dokumentreferanseList.getDokumentreferanse().addAll(dokumentreferanser);
      signertGrunnboksutskrift.setDokumentreferanser(dokumentreferanseList);
      return signertGrunnboksutskrift;
   }
}
