package no.statkart.wsclient.byggesak.fiksintegrasjon;

/**
 * Objekt som holder informasjon som trengs for å utføre kall til FIKS.
 */
public class FiksCredentialsHolder {
   private final String serviceBrukernavn;
   private final String servicePassord;
   private final String hentForsendelserUrl;
   private final String kvitterMottakUrl;

   public FiksCredentialsHolder(String serviceBrukernavn, String servicePassord, String hentForsendelserUrl, String kvitterMottakUrl) {
      this.serviceBrukernavn = serviceBrukernavn;
      this.servicePassord = servicePassord;
      this.hentForsendelserUrl = hentForsendelserUrl;
      this.kvitterMottakUrl = kvitterMottakUrl;
   }

   public String getServiceBrukernavn() {
      return serviceBrukernavn;
   }

   public String getServicePassord() {
      return servicePassord;
   }

   public String getHentForsendelserUrl() {
      return hentForsendelserUrl;
   }

   public String getKvitterMottakUrl() {
      return kvitterMottakUrl;
   }
}
