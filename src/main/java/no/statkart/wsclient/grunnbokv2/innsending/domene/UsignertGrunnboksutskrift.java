package no.statkart.wsclient.grunnbokv2.innsending.domene;

import java.util.List;

public class UsignertGrunnboksutskrift {

    private Registerenhet gjelderFor;
    private String link;
    private UsignertPDFDokument utskrift;
    private List<String> dokumentreferanser;

    public Registerenhet getGjelderFor() {
        return gjelderFor;
    }

    public void setGjelderFor(Registerenhet gjelderFor) {
        this.gjelderFor = gjelderFor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UsignertPDFDokument getUtskrift() {
        return utskrift;
    }

    public void setUtskrift(UsignertPDFDokument utskrift) {
        this.utskrift = utskrift;
    }

    public List<String> getDokumentreferanser() {
        return dokumentreferanser;
    }

    public void setDokumentreferanser(List<String> dokumentreferanser) {
        this.dokumentreferanser = dokumentreferanser;
    }
}