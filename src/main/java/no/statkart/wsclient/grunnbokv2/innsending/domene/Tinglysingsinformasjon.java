package no.statkart.wsclient.grunnbokv2.innsending.domene;

import java.util.ArrayList;
import java.util.List;

public class Tinglysingsinformasjon {

    private List<Dokumentinformasjon> dokumentinformasjon = new ArrayList<>();
    private List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter = new ArrayList<>();
    private List<UsignertGrunnboksutskrift> grunnboksutskrifter = new ArrayList<>();

    public List<Dokumentinformasjon> getDokumentinformasjon() {
        return dokumentinformasjon;
    }

    public void setDokumentinformasjon(List<Dokumentinformasjon> dokumentinformasjon) {
        this.dokumentinformasjon = dokumentinformasjon;
    }

    public List<SignertGrunnboksutskrift> getSignerteGrunnboksutskrifter() {
        return signerteGrunnboksutskrifter;
    }

    public void setSignerteGrunnboksutskrifter(List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter) {
        this.signerteGrunnboksutskrifter = signerteGrunnboksutskrifter;
    }

    public List<UsignertGrunnboksutskrift> getGrunnboksutskrifter() {
        return grunnboksutskrifter;
    }

    public void setGrunnboksutskrifter(List<UsignertGrunnboksutskrift> grunnboksutskrifter) {
        this.grunnboksutskrifter = grunnboksutskrifter;
    }
}
