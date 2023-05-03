package no.statkart.wsclient.byggesak.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Data om 1 etasje på 1 bygning fra en byggesak
 */
public class ByggesakEtasjeDTO {

    Integer etasjenr; // required
    String etasjeplanKode; // required

    Integer antallBoenheter;

    Double bruksarealTilBolig;
    Double bruksarealTilAnnet;
    Double bruksarealTotalt;

    Double bruttoarealTilBolig;
    Double bruttoarealTilAnnet;
    Double bruttoarealTotalt;

    private List<String> feilkoder = new ArrayList<>();

    public ByggesakEtasjeDTO() {
    }

    public Integer getAntallBoenheter() {
        return antallBoenheter;
    }

    public void setAntallBoenheter(BigInteger antallBoenheter) {
        this.antallBoenheter = antallBoenheter != null ? antallBoenheter.intValue() : null;
    }

    public Double getBruksarealTilBolig() {
        return bruksarealTilBolig;
    }

    public void setBruksarealTilBolig(Double bruksarealTilBolig) {
        this.bruksarealTilBolig = bruksarealTilBolig;
    }

    public Double getBruksarealTilAnnet() {
        return bruksarealTilAnnet;
    }

    public void setBruksarealTilAnnet(Double bruksarealTilAnnet) {
        this.bruksarealTilAnnet = bruksarealTilAnnet;
    }

    public Double getBruksarealTotalt() {
        return bruksarealTotalt;
    }

    public void setBruksarealTotalt(Double bruksarealTotalt) {
        this.bruksarealTotalt = bruksarealTotalt;
    }

    public Double getBruttoarealTilBolig() {
        return bruttoarealTilBolig;
    }

    public void setBruttoarealTilBolig(Double bruttoarealTilBolig) {
        this.bruttoarealTilBolig = bruttoarealTilBolig;
    }

    public Double getBruttoarealTilAnnet() {
        return bruttoarealTilAnnet;
    }

    public void setBruttoarealTilAnnet(Double bruttoarealTilAnnet) {
        this.bruttoarealTilAnnet = bruttoarealTilAnnet;
    }

    public Double getBruttoarealTotalt() {
        return bruttoarealTotalt;
    }

    public void setBruttoarealTotalt(Double bruttoarealTotalt) {
        this.bruttoarealTotalt = bruttoarealTotalt;
    }

    public Integer getEtasjenr() {
        return etasjenr;
    }

    public void setEtasjenr(BigInteger etasjenr) {
        this.etasjenr = etasjenr != null ? etasjenr.intValue() : null;
    }

    public String getEtasjeplanKode() {
        return etasjeplanKode;
    }

    public void setEtasjeplanKode(String etasjeplanKode) {
        this.etasjeplanKode = etasjeplanKode;
    }

    /**
     * Obligatoriske felter i modellen.
     *
     * @return Liste med feilkoder.
     */
    public List<String> validerEtasjeInfo() {
        feilkoder.clear();

        if (etasjenr == null) feilkoder.add("Etasjenummer ikke satt på EtasjeInfo");
        if (etasjeplanKode == null) feilkoder.add("Etasjeplankode ikke satt på EtasjeInfo");

        return feilkoder;
    }
}
