package no.statkart.wsclient.grunnbok.innsending.domene;

/**
 *
 */
public class Signatur {

   private String gjelderDokumentreferanse;
   private String personidentifikasjonsnummer;

   public String getGjelderDokumentreferanse() {
      return gjelderDokumentreferanse;
   }

   public void setGjelderDokumentreferanse(String gjelderDokumentreferanse) {
      this.gjelderDokumentreferanse = gjelderDokumentreferanse;
   }

   public String getPersonidentifikasjonsnummer() {
      return personidentifikasjonsnummer;
   }

   public void setPersonidentifikasjonsnummer(String personidentifikasjonsnummer) {
      this.personidentifikasjonsnummer = personidentifikasjonsnummer;
   }
}
