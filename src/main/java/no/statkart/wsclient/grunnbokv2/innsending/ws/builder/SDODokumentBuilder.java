package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;


import no.kartverket.grunnbok.wsapi.v2.domain.innsending.SDODokument;

public class SDODokumentBuilder {
   private byte[] signertDokument;

   private SDODokumentBuilder() {
   }

   public static SDODokumentBuilder aSDODokument() {
      return new SDODokumentBuilder();
   }

   public SDODokumentBuilder withSignertDokument(byte[] signertDokument) {
      this.signertDokument = signertDokument;
      return this;
   }

   public SDODokumentBuilder but() {
      return aSDODokument().withSignertDokument(signertDokument);
   }

   public SDODokument build() {
      SDODokument sDODokument = new SDODokument();
      sDODokument.setSignertDokument(signertDokument);
      return sDODokument;
   }
}
