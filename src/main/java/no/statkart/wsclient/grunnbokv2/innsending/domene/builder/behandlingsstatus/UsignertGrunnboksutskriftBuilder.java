package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Registerenhet;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertPDFDokument;

import java.util.List;

public class UsignertGrunnboksutskriftBuilder {
    protected Registerenhet gjelderFor;
    protected String link;
    protected UsignertPDFDokument utskrift;
    protected List<String> dokumentreferanser;

    private UsignertGrunnboksutskriftBuilder() {
    }

    public static UsignertGrunnboksutskriftBuilder aUsignertGrunnboksutskrift() {
        return new UsignertGrunnboksutskriftBuilder();
    }

    public UsignertGrunnboksutskriftBuilder withGjelderFor(Registerenhet gjelderFor) {
        this.gjelderFor = gjelderFor;
        return this;
    }

    public UsignertGrunnboksutskriftBuilder withLink(String link) {
        this.link = link;
        return this;
    }

    public UsignertGrunnboksutskriftBuilder withUsignertUtskrift(UsignertPDFDokument signertUtskrift) {
        this.utskrift = signertUtskrift;
        return this;
    }

    public UsignertGrunnboksutskriftBuilder withDokumentreferanse(List<String> dokumentreferanser) {
        this.dokumentreferanser = dokumentreferanser;
        return this;
    }

    public UsignertGrunnboksutskrift build() {
        UsignertGrunnboksutskrift usignertGrunnboksutskrift = new UsignertGrunnboksutskrift();
        usignertGrunnboksutskrift.setGjelderFor(gjelderFor);
        usignertGrunnboksutskrift.setLink(link);
        usignertGrunnboksutskrift.setUtskrift(utskrift);
        usignertGrunnboksutskrift.setDokumentreferanser(dokumentreferanser);
        return usignertGrunnboksutskrift;
    }
}
