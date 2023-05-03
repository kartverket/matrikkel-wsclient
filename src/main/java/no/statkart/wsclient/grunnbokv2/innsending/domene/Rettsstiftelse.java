package no.statkart.wsclient.grunnbokv2.innsending.domene;


import java.util.ArrayList;
import java.util.List;

public abstract class Rettsstiftelse {

    private String rettsstiftelsesreferanse;
    private Kode rettsstiftelsestype;
    private List<Tekst> tekster = new ArrayList<>();
    private Boolean skalHaGebyr;
    private Kode aarsakGebyrfritak;

    public enum Rettsstiftelsestype {
        MATRIKKELENHETSENDRING
    }

    public abstract Rettsstiftelsestype getRettstiftelsestype();

    public String getRettsstiftelsesreferanse() {
        return rettsstiftelsesreferanse;
    }

    public void setRettsstiftelsesreferanse(String rettsstiftelsesreferanse) {
        this.rettsstiftelsesreferanse = rettsstiftelsesreferanse;
    }

    public Kode getRettsstiftelsestype() {
        return rettsstiftelsestype;
    }

    public void setRettsstiftelsestype(Kode rettsstiftelsestype) {
        this.rettsstiftelsestype = rettsstiftelsestype;
    }

    public List<Tekst> getTekster() {
        return tekster;
    }

    public void setTekster(List<Tekst> tekster) {
        this.tekster = tekster;
    }

    public Boolean getSkalHaGebyr() {
        return skalHaGebyr;
    }

    public void setSkalHaGebyr(Boolean skalHaGebyr) {
        this.skalHaGebyr = skalHaGebyr;
    }

    public Kode getAarsakGebyrfritak() {
        return aarsakGebyrfritak;
    }

    public void setAarsakGebyrfritak(Kode aarsakGebyrfritak) {
        this.aarsakGebyrfritak = aarsakGebyrfritak;
    }
}
