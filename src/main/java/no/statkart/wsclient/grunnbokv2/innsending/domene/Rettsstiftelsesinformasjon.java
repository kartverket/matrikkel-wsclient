package no.statkart.wsclient.grunnbokv2.innsending.domene;

public class Rettsstiftelsesinformasjon {

    private String rettsstiftelsesreferanse;
    private Integer rettsstiftelsesnummer;

    public Integer getRettsstiftelsesnummer() {
        return rettsstiftelsesnummer;
    }

    public void setRettsstiftelsesnummer(Integer rettsstiftelsesnummer) {
        this.rettsstiftelsesnummer = rettsstiftelsesnummer;
    }

    public String getRettsstiftelsesreferanse() {
        return rettsstiftelsesreferanse;
    }

    public void setRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
        this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
    }
}
