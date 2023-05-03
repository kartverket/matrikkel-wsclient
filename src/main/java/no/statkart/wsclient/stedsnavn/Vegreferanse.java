package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;

public class Vegreferanse extends Matrikkelreferanse {

    private String kommunenummer;
    private int adressekode;

    public Vegreferanse(LocalDateTime registreringsdato, String kommunenummer) {
        super(registreringsdato);
        this.kommunenummer = kommunenummer;
    }

    public String getKommunenummer() {
        return kommunenummer;
    }

    public int getAdressekode() {
        return adressekode;
    }

    public void setAdressekode(int adressekode) {
        this.adressekode = adressekode;
    }
}
