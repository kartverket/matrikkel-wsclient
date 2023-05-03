package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Behandlingsinformasjon;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Kontrollresultat;

import java.util.ArrayList;
import java.util.List;

public class AvvisningsinformasjonBuilder {
    protected List<Kontrollresultat> kontrollresultater = new ArrayList<>();

    private AvvisningsinformasjonBuilder() {
    }

    public static AvvisningsinformasjonBuilder anAvvisningsinformasjon() {
        return new AvvisningsinformasjonBuilder();
    }

    public AvvisningsinformasjonBuilder withKontrollresultater(List<Kontrollresultat> kontrollresultater) {
        this.kontrollresultater = kontrollresultater;
        return this;
    }

    public AvvisningsinformasjonBuilder but() {
        return anAvvisningsinformasjon().withKontrollresultater(kontrollresultater);
    }

    public Behandlingsinformasjon build() {
        Behandlingsinformasjon behandlingsinformasjon = new Behandlingsinformasjon();
        behandlingsinformasjon.getKontrollresultater().addAll(kontrollresultater);
        return behandlingsinformasjon;
    }
}
