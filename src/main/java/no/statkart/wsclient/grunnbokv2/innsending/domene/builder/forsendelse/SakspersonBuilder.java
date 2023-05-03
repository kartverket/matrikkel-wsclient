package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse;


import no.statkart.wsclient.grunnbokv2.innsending.domene.Saksperson;

public class SakspersonBuilder {

    private String identifikasjonsnummer;
    private String referanse;

    private SakspersonBuilder() {
    }

    public static SakspersonBuilder aSaksperson() {
        return new SakspersonBuilder();
    }

    public SakspersonBuilder withIdentifikasjonsnummer(String identifikasjonsnummer) {
        this.identifikasjonsnummer = identifikasjonsnummer;
        return this;
    }

    public SakspersonBuilder withReferanse(String referanse) {
        this.referanse = referanse;
        return this;
    }

    public SakspersonBuilder but() {
        return aSaksperson().withIdentifikasjonsnummer(identifikasjonsnummer).withReferanse(referanse);
    }

    public Saksperson build() {
        Saksperson saksperson = new Saksperson();
        saksperson.setIdentifikasjonsnummer(identifikasjonsnummer);
        saksperson.setReferanse(referanse);
        return saksperson;
    }

}
