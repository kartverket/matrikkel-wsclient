package no.statkart.wsclient.grunnbokv2.innsending.domene;

import java.util.Objects;

public class Matrikkelenhet {

    private String kommunenummer;
    private String kommunenavn;
    private int gaardsnummer;
    private int bruksnummer;
    private int festenummer;
    private int seksjonsnummer;

    public int getBruksnummer() {
        return bruksnummer;
    }

    public void setBruksnummer(int bruksnummer) {
        this.bruksnummer = bruksnummer;
    }

    public int getFestenummer() {
        return festenummer;
    }

    public void setFestenummer(int festenummer) {
        this.festenummer = festenummer;
    }

    public int getGaardsnummer() {
        return gaardsnummer;
    }

    public void setGaardsnummer(int gaardsnummer) {
        this.gaardsnummer = gaardsnummer;
    }

    public String getKommunenavn() {
        return kommunenavn;
    }

    public void setKommunenavn(String kommunenavn) {
        this.kommunenavn = kommunenavn;
    }

    public String getKommunenummer() {
        return kommunenummer;
    }

    public void setKommunenummer(String kommunenummer) {
        this.kommunenummer = kommunenummer;
    }

    public int getSeksjonsnummer() {
        return seksjonsnummer;
    }

    public void setSeksjonsnummer(int seksjonsnummer) {
        this.seksjonsnummer = seksjonsnummer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kommunenummer, kommunenavn, gaardsnummer, bruksnummer, festenummer, seksjonsnummer);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Matrikkelenhet other = (Matrikkelenhet) obj;
        return Objects.equals(kommunenummer, other.getKommunenummer())
            && Objects.equals(kommunenavn, other.getKommunenavn())
            && Objects.equals(gaardsnummer, other.getGaardsnummer())
            && Objects.equals(bruksnummer, other.getBruksnummer())
            && Objects.equals(festenummer, other.getFestenummer())
            && Objects.equals(seksjonsnummer, other.getSeksjonsnummer())
            ;
    }
}
