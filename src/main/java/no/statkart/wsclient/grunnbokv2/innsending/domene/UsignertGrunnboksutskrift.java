package no.statkart.wsclient.grunnbokv2.innsending.domene;

import java.util.Collections;
import java.util.List;

public class UsignertGrunnboksutskrift {

    private Registerenhet gjelderFor;
    private String link;
    private UsignertPDFDokument utskrift;

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

    @SuppressWarnings("unused") //SKIF mapping
    private List<String> getDokumentreferanser() {
        return Collections.emptyList();
    }

    @SuppressWarnings("unused") //SKIF mapping
    private void setDokumentreferanser(List<String> dokumentreferanser) {
    }
}
