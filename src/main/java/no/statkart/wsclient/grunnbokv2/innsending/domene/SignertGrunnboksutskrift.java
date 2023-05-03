package no.statkart.wsclient.grunnbokv2.innsending.domene;


import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.List;

/**
 * Etter overgangsfasen i grunnboken vil denne være tom.
 * 4.1 må støtte denne inntil videre.
 */
public class SignertGrunnboksutskrift {

    private Registerenhet gjelderFor;
    private SDODokument signertUtskrift;
    @XmlElement //ignored
    public List<String> dokumentreferanser = Collections.emptyList();
    private byte[] utskrift;
    private String mimeType;

    @SuppressWarnings("unused") //SKIF mapping
    private List<String> getDokumentreferanser() {
        return null;
    }

    @SuppressWarnings("unused") //SKIF mapping
    private void setDokumentreferanser(List<String> dokumentreferanser) {
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

    public byte[] getUtskrift() {
        return utskrift;
    }

    public void setUtskrift(byte[] utskrift) {
        this.utskrift = utskrift;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
