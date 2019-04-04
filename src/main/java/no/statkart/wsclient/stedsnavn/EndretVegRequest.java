package no.statkart.wsclient.stedsnavn;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EndretVegRequest extends NyVegRequest {

   private AarsakTilEndringFraMatrikkelen aarsakTilEndring;

   private EndretVegRequest() {
   }

   public AarsakTilEndringFraMatrikkelen getAarsakTilEndring() {
      return aarsakTilEndring;
   }

   @SuppressWarnings({"unused", "WeakerAccess"})
   public static class Builder {

      private LocalDateTime tidsstempel;
      private long vegId;
      private String kommunenummer;
      private Double ost;
      private Double nord;
      private String koordinatsystem;
      private int adressekode;
      private String adressenavn;
      private String kortnavn;
      private LocalDate vedtaksdato;
      private AarsakTilEndringFraMatrikkelen aarsakTilEndring;

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

      public Builder ost(Double ost) {
         this.ost = ost;
         return this;
      }

      public Builder nord(Double nord) {
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

      public Builder aarsakTilEndring(AarsakTilEndringFraMatrikkelen aarsakTilEndring) {
         this.aarsakTilEndring = aarsakTilEndring;
         return this;
      }

      public EndretVegRequest build() {
         EndretVegRequest request = new EndretVegRequest();
         request.tidsstempel = tidsstempel;
         request.vegId = vegId;
         request.kommunenummer = kommunenummer;
         request.ost = ost;
         request.nord = nord;
         request.koordinatsystem = koordinatsystem;
         request.adressekode = adressekode;
         request.adressenavn = adressenavn;
         request.kortnavn = kortnavn;
         request.vedtaksdato = vedtaksdato;
         request.aarsakTilEndring = aarsakTilEndring;

         return request;
      }
   }

}
