package no.statkart.wsclient.grunnbokv2.innsending.domene;


import java.util.List;

/**
 * Fra 4.1 vil denne være tom, men må eksistere for mapping
 */
public class SignertGrunnboksutskrift {

    private Registerenhet gjelderFor;
    private SDODokument signertUtskrift;
    private List<String> dokumentreferanser;

    public List<String> getDokumentreferanser() {
        return dokumentreferanser;
    }

    public void setDokumentreferanser(List<String> dokumentreferanse) {
        this.dokumentreferanser = dokumentreferanse;
    }

    public Registerenhet getGjelderFor() {
        return gjelderFor;
    }

    public void setGjelderFor(Registerenhet gjelderFor) {
        this.gjelderFor = gjelderFor;
    }

    public SDODokument getSignertUtskrift() {
        return signertUtskrift;
    }

    public void setSignertUtskrift(SDODokument signertUtskrift) {
        this.signertUtskrift = signertUtskrift;
    }
}
