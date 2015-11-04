package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbok.innsending.domene.Signatur;

/**
 *
 */
public class SignaturBuilder {
   private String gjelderDokumentreferanse;
   private String personidentifikasjonsnummer;

   private SignaturBuilder() {
   }

   public static SignaturBuilder aSignatur() {
      return new SignaturBuilder();
   }

   public SignaturBuilder withGjelderDokumentreferanse(String gjelderDokumentreferanse) {
      this.gjelderDokumentreferanse = gjelderDokumentreferanse;
      return this;
   }

   public SignaturBuilder withPersonidentifikasjonsnummer(String personidentifikasjonsnummer) {
      this.personidentifikasjonsnummer = personidentifikasjonsnummer;
      return this;
   }

   public SignaturBuilder but() {
      return aSignatur().withGjelderDokumentreferanse(gjelderDokumentreferanse).withPersonidentifikasjonsnummer(personidentifikasjonsnummer);
   }

   public Signatur build() {
      Signatur signatur = new Signatur();
      signatur.setGjelderDokumentreferanse(gjelderDokumentreferanse);
      signatur.setPersonidentifikasjonsnummer(personidentifikasjonsnummer);
      return signatur;
   }
}
