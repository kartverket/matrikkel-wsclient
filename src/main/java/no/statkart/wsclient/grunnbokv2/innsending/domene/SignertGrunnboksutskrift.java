package no.statkart.wsclient.grunnbokv2.innsending.domene;


import java.util.List;

/**
 *
 */
public class SignertGrunnboksutskrift {

   private Registerenhet gjelderFor;
   private SDODokument signertUtskrift;
   private List<String> dokumentreferanser;
   private byte[] utskrift;
   private String mimeType;


   public List<String> getDokumentreferanser() {
      return dokumentreferanser;
   }

   public void setDokumentreferanser(List<String> dokumentreferanse) {
      this.dokumentreferanser = dokumentreferanse;
   }

   public Registerenhet getGjelderFor() {
      return gjelderFor;
   }

   public void setGjelderFor(Registerenhet gjelderFor) {
      this.gjelderFor = gjelderFor;
   }

   public SDODokument getSignertUtskrift() {
      return signertUtskrift;
   }

   public void setSignertUtskrift(SDODokument signertUtskrift) {
      this.signertUtskrift = signertUtskrift;
   }

   public byte[] getUtskrift() {
      return utskrift;
   }

   public void setUtskrift(byte[] utskrift) {
      this.utskrift = utskrift;
   }

   public String getMimeType() {
      return mimeType;
   }

   public void setMimeType(String mimeType) {
      this.mimeType = mimeType;
   }
}
