package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;

public class StedsnavnContext {

   private String systemVersion;
   private String clientIdentification;
   private String locale;
   private LocalDateTime snapshotVersion;

   public StedsnavnContext(String systemVersion, String clientIdentification, String locale, LocalDateTime snapshotVersion) {
      this.systemVersion = systemVersion;
      this.clientIdentification = clientIdentification;
      this.locale = locale;
      this.snapshotVersion = snapshotVersion;
   }

   public String getSystemVersion() {
      return systemVersion;
   }

   public String getClientIdentification() {
      return clientIdentification;
   }

   public String getLocale() {
      return locale;
   }

   public LocalDateTime getSnapshotVersion() {
      return snapshotVersion;
   }
}
