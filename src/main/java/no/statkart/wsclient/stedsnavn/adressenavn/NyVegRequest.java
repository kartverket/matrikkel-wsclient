package no.statkart.wsclient.stedsnavn.adressenavn;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NyVegRequest {

    LocalDateTime tidsstempel;
    long vegId;
    String kommunenummer;
    Double ost;
    Double nord;
    String koordinatsystem;
    int adressekode;
    String adressenavn;
    String kortnavn;
    LocalDate vedtaksdato;

    NyVegRequest() {
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

    public Double getOst() {
        return ost;
    }

    public Double getNord() {
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

        public NyVegRequest build() {
            NyVegRequest nyVegRequest = new NyVegRequest();
            nyVegRequest.tidsstempel = tidsstempel;
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
