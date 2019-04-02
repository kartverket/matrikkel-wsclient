package no.statkart.wsclient.stedsnavn;

import java.time.LocalDate;

public class NyVegRequest {

   long vegId;
   String kommunenummer;
   double ost;
   double nord;
   String koordinatsystem;
   int adressekode;
   String adressenavn;
   String kortnavn;
   LocalDate vedtaksdato;

   NyVegRequest() {
   }

   public long getVegId() {
      return vegId;
   }

   public String getKommunenummer() {
      return kommunenummer;
   }

   public double getOst() {
      return ost;
   }

   public double getNord() {
      return nord;
   }

   public String getKoordinatsystem() {
      return koordinatsystem;
   }

   public int getAdressekode() {
      return adressekode;
   }

   public String getAdressenavn() {
      return adressenavn;
   }

   public String getKortnavn() {
      return kortnavn;
   }

   public LocalDate getVedtaksdato() {
      return vedtaksdato;
   }


   @SuppressWarnings({"unused", "WeakerAccess"})
   public static class Builder {

      private long vegId;
      private String kommunenummer;
      private double ost;
      private double nord;
      private String koordinatsystem;
      private int adressekode;
      private String adressenavn;
      private String kortnavn;
      private LocalDate vedtaksdato;

      private Builder() {
      }

      public static Builder builder() {
         return new Builder();
      }

      public Builder vegId(Long vegId) {
         this.vegId = vegId;
         return this;
      }

      public Builder kommunenummer(String knr) {
         this.kommunenummer = knr;
         return this;
      }

      public Builder ost(double ost) {
         this.ost = ost;
         return this;
      }

      public Builder nord(double nord) {
         this.nord = nord;
         return this;
      }

      public Builder koordinatsystem(String koordinatsystem) {
         this.koordinatsystem = koordinatsystem;
         return this;
      }

      public Builder adressekode(int adressekode) {
         this.adressekode = adressekode;
         return this;
      }

      public Builder adressenavn(String adressenavn) {
         this.adressenavn = adressenavn;
         return this;
      }

      public Builder kortnavn(String kortnavn) {
         this.kortnavn = kortnavn;
         return this;
      }

      public Builder vedtaksdato(LocalDate vedtaksdato) {
         this.vedtaksdato = vedtaksdato;
         return this;
      }

      public NyVegRequest build() {
         NyVegRequest nyVegRequest = new NyVegRequest();
         nyVegRequest.vegId = vegId;
         nyVegRequest.kommunenummer = kommunenummer;
         nyVegRequest.ost = ost;
         nyVegRequest.nord = nord;
         nyVegRequest.koordinatsystem = koordinatsystem;
         nyVegRequest.adressekode = adressekode;
         nyVegRequest.adressenavn = adressenavn;
         nyVegRequest.kortnavn = kortnavn;
         nyVegRequest.vedtaksdato = vedtaksdato;

         return nyVegRequest;
      }
   }

}
