package no.statkart.wsclient.grunnbok.innsending.domene;


/**
 *
 */
public class SignertGrunnboksutskrift {

   private Registerenhet gjelderFor;
   private SDODokument signertUtskrift;

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
}
