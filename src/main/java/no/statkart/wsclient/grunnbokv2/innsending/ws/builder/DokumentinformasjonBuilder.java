package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokumentinformasjon;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhet;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.RegisterenhetList;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Rettsstiftelsesinformasjon;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.RettsstiftelsesinformasjonList;

import java.util.ArrayList;
import java.util.List;

public class DokumentinformasjonBuilder {
    protected int dokumentaar;
    protected String dokumentreferanse;
    protected long dokumentnummer;
    protected String embetenummer;
    protected List<Rettsstiftelsesinformasjon> rettsstiftelsesinformasjonList = new ArrayList<>();
    private List<Registerenhet> paavirkerRegisterenheter = new ArrayList<>();

    private DokumentinformasjonBuilder() {
    }

    public static DokumentinformasjonBuilder aDokumentinformasjon() {
        return new DokumentinformasjonBuilder();
    }

    public DokumentinformasjonBuilder withDokumentaar(int dokumentaar) {
        this.dokumentaar = dokumentaar;
        return this;
    }

    public DokumentinformasjonBuilder withDokumentreferanse(String dokumentreferanse) {
        this.dokumentreferanse = dokumentreferanse;
        return this;
    }

    public DokumentinformasjonBuilder withDokumentnummer(long dokumentnummer) {
        this.dokumentnummer = dokumentnummer;
        return this;
    }

    public DokumentinformasjonBuilder withEmbetenummer(String embetenummer) {
        this.embetenummer = embetenummer;
        return this;
    }

    public DokumentinformasjonBuilder withRettsstiftelsesinformasjonList(List<Rettsstiftelsesinformasjon> rettsstiftelsesinformasjonList) {
        this.rettsstiftelsesinformasjonList = rettsstiftelsesinformasjonList;
        return this;
    }

    public DokumentinformasjonBuilder withPaavirkerRegisterenheter(List<Registerenhet> paavirkerRegisterenheter) {
        this.paavirkerRegisterenheter = paavirkerRegisterenheter;
        return this;
    }


    public DokumentinformasjonBuilder but() {
        return aDokumentinformasjon().withDokumentaar(dokumentaar).withDokumentreferanse(dokumentreferanse).withDokumentnummer(dokumentnummer).withEmbetenummer(embetenummer).withRettsstiftelsesinformasjonList(rettsstiftelsesinformasjonList).withPaavirkerRegisterenheter(paavirkerRegisterenheter);
    }

    public Dokumentinformasjon build() {
        Dokumentinformasjon dokumentinformasjon = new Dokumentinformasjon();
        dokumentinformasjon.setDokumentaar(dokumentaar);
        dokumentinformasjon.setDokumentreferanse(dokumentreferanse);
        dokumentinformasjon.setDokumentnummer(dokumentnummer);
        dokumentinformasjon.setEmbetenummer(embetenummer);

        RettsstiftelsesinformasjonList rettsstiftelsesinformasjon = new RettsstiftelsesinformasjonList();
        rettsstiftelsesinformasjon.getRettsstiftelsesinformasjon().addAll(rettsstiftelsesinformasjonList);
        dokumentinformasjon.setRettsstiftelsesinformasjon(rettsstiftelsesinformasjon);

        RegisterenhetList registerenhetList = new RegisterenhetList();
        registerenhetList.getRegisterenhet().addAll(paavirkerRegisterenheter);
        dokumentinformasjon.setPaavirkerRegisterenheter(registerenhetList);

        return dokumentinformasjon;
    }
}
