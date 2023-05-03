package no.statkart.wsclient.grunnbokv2.innsending.domene;


import java.util.ArrayList;
import java.util.List;

public class Behandlingsinformasjon {

    private List<Kontrollresultat> kontrollresultater = new ArrayList<>();

    public List<Kontrollresultat> getKontrollresultater() {
        return kontrollresultater;
    }

    public void setKontrollresultater(List<Kontrollresultat> kontrollresultater) {
        this.kontrollresultater = kontrollresultater;
    }
}
