package no.statkart.wsclient.grunnbokv2.innsending.domene;


public class Referanse {

   private String gjelderDokumentreferanse;
   private byte[] digest;
   private String digestAlgoritme;

   public byte[] getDigest() {
      return digest;
   }

   public void setDigest(byte[] digest) {
      this.digest = digest;
   }

   public String getDigestAlgoritme() {
      return digestAlgoritme;
   }

   public void setDigestAlgoritme(String digestAlgoritme) {
      this.digestAlgoritme = digestAlgoritme;
   }

   public String getGjelderDokumentreferanse() {
      return gjelderDokumentreferanse;
   }

   public void setGjelderDokumentreferanse(String gjelderDokumentreferanse) {
      this.gjelderDokumentreferanse = gjelderDokumentreferanse;
   }
}
