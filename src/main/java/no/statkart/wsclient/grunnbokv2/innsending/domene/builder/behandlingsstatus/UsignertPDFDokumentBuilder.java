package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;

import no.statkart.wsclient.grunnbokv2.innsending.domene.SDODokument;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertPDFDokument;

public class UsignertPDFDokumentBuilder {

    private byte[] usignertDokument;

    private UsignertPDFDokumentBuilder() {}

    public static UsignertPDFDokumentBuilder aUsignertPDFDokument() {
        return new UsignertPDFDokumentBuilder();
    }

    public UsignertPDFDokumentBuilder withUsignertDokument(byte[] usignertDokument) {
        this.usignertDokument = usignertDokument;
        return this;
    }

    public UsignertPDFDokument build() {
        UsignertPDFDokument usignertPDFDokument = new UsignertPDFDokument();
        usignertPDFDokument.setUsignertDokument(usignertDokument);
        return usignertPDFDokument;
    }
}
