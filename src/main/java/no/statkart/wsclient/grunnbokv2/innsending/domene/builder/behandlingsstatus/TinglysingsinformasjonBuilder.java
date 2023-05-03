package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Dokumentinformasjon;
import no.statkart.wsclient.grunnbokv2.innsending.domene.SignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Tinglysingsinformasjon;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertGrunnboksutskrift;

import java.util.ArrayList;
import java.util.List;

public class TinglysingsinformasjonBuilder {
    private List<Dokumentinformasjon> dokumentinformasjonList = new ArrayList<>();
    private List<SignertGrunnboksutskrift> signerteGrunnboksutskrifter = new ArrayList<>();
    private List<UsignertGrunnboksutskrift> usignertGrunnboksutskrifter = new ArrayList<>();

    private TinglysingsinformasjonBuilder() {
    }

    public static TinglysingsinformasjonBuilder aTinglysingsinformasjon() {
        return new TinglysingsinformasjonBuilder();
    }

    public TinglysingsinformasjonBuilder withDokumentinformasjon(List<Dokumentinformasjon> dokumentinformasjonList) {
        this.dokumentinformasjonList = dokumentinformasjonList;
        return this;
    }

    public TinglysingsinformasjonBuilder withUsignerteGrunnboksutskrifter(List<UsignertGrunnboksutskrift> usignertGrunnboksutskrifter) {
        this.usignertGrunnboksutskrifter = usignertGrunnboksutskrifter;
        return this;
    }

    public Tinglysingsinformasjon build() {
        Tinglysingsinformasjon tinglysingsinformasjon = new Tinglysingsinformasjon();
        tinglysingsinformasjon.getDokumentinformasjon().addAll(dokumentinformasjonList);
        tinglysingsinformasjon.getSignerteGrunnboksutskrifter().addAll(signerteGrunnboksutskrifter);
        tinglysingsinformasjon.getGrunnboksutskrifter().addAll(usignertGrunnboksutskrifter);
        return tinglysingsinformasjon;
    }
}
