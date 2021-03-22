package no.statkart.wsclient.grunnbokv2.innsending.domene;

/**
 * Fra 4.1 er denne tom, men må eksistere for mapping
 */
public class SDODokument {

   private byte[] signertDokument;

   public byte[] getSignertDokument() {
      return signertDokument;
   }

   public void setSignertDokument(byte[] signertDokument) {
      this.signertDokument = signertDokument;
   }
}
