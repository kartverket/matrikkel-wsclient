package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.UsignertPDFDokument;

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
        UsignertPDFDokument pdfDokument = new UsignertPDFDokument();
        pdfDokument.setUsignertDokument(usignertDokument);
        return pdfDokument;
    }
}
