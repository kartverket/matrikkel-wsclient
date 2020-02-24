package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Referanse;

public class ReferanseBuilder {
   private byte[] digest;
   private String gjelderDokumentreferanse;
   private String digestAlgoritme;

   private ReferanseBuilder() {
   }

   public static ReferanseBuilder aReferanse() {
      return new ReferanseBuilder();
   }

   public ReferanseBuilder withDigest(byte[] digest) {
      this.digest = digest;
      return this;
   }

   public ReferanseBuilder withGjelderDokumentreferanse(String gjelderDokumentreferanse) {
      this.gjelderDokumentreferanse = gjelderDokumentreferanse;
      return this;
   }

   public ReferanseBuilder withDigestAlgoritme(String digestAlgoritme) {
      this.digestAlgoritme = digestAlgoritme;
      return this;
   }

   public ReferanseBuilder but() {
      return aReferanse().withDigest(digest).withGjelderDokumentreferanse(gjelderDokumentreferanse).withDigestAlgoritme(digestAlgoritme);
   }

   public Referanse build() {
      Referanse referanse = new Referanse();
      referanse.setDigest(digest);
      referanse.setGjelderDokumentreferanse(gjelderDokumentreferanse);
      referanse.setDigestAlgoritme(digestAlgoritme);
      return referanse;
   }
}
