package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;

public class SlettetVegRequest {
   private LocalDateTime tidsstempel;
   private long vegId;
   private String kommunenummer;
   private int adressekode;

   private SlettetVegRequest() {
   }

   public LocalDateTime getTidsstempel() {
      return tidsstempel;
   }

   public long getVegId() {
      return vegId;
   }

   public String getKommunenummer() {
      return kommunenummer;
   }

   public int getAdressekode() {
      return adressekode;
   }

   @SuppressWarnings("unused")
   public static class Builder {

      private LocalDateTime tidsstempel;
      private long vegId;
      private String kommunenummer;
      private int adressekode;

      private Builder() {
      }

      public static Builder builder() {
         return new Builder();
      }

      public Builder tidsstempel(LocalDateTime tidsstempel) {
         this.tidsstempel = tidsstempel;
         return this;
      }

      public Builder vegId(Long vegId) {
         this.vegId = vegId;
         return this;
      }

      public Builder kommunenummer(String knr) {
         this.kommunenummer = knr;
         return this;
      }

      public Builder adressekode(int adressekode) {
         this.adressekode = adressekode;
         return this;
      }

      public SlettetVegRequest build() {
         SlettetVegRequest request = new SlettetVegRequest();
         request.tidsstempel = tidsstempel;
         request.vegId = vegId;
         request.kommunenummer = kommunenummer;
         request.adressekode = adressekode;

         return request;
      }
   }


}
