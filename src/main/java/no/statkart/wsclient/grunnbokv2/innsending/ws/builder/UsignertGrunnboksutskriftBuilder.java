package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.DokumentreferanseList;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhet;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.UsignertGrunnboksutskrift;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.UsignertPDFDokument;

import java.util.List;

public class UsignertGrunnboksutskriftBuilder {
    protected Registerenhet gjelderFor;
    protected String link;
    protected UsignertPDFDokument utskrift;
    protected List<String> dokumentreferanser;


    private UsignertGrunnboksutskriftBuilder() {
    }

    public static UsignertGrunnboksutskriftBuilder aSignertGrunnboksutskrift() {
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

    public UsignertGrunnboksutskriftBuilder withDokumentreferanser(List<String> dokumentreferanser) {
        this.dokumentreferanser = dokumentreferanser;
        return this;
    }

    public UsignertGrunnboksutskrift build() {
        final UsignertGrunnboksutskrift usignertGrunnboksutskrift = new UsignertGrunnboksutskrift();
        usignertGrunnboksutskrift.setGjelderFor(gjelderFor);
        usignertGrunnboksutskrift.setUtskrift(utskrift);
        usignertGrunnboksutskrift.setLink(link);
        DokumentreferanseList dokumentreferanseList = new DokumentreferanseList();
        dokumentreferanseList.getDokumentreferanse().addAll(dokumentreferanser);
        usignertGrunnboksutskrift.setDokumentreferanser(dokumentreferanseList);
        return usignertGrunnboksutskrift;
    }
}
