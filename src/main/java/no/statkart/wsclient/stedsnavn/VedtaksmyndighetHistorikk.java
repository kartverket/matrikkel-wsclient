package no.statkart.wsclient.stedsnavn;

import java.time.LocalDate;

public class VedtaksmyndighetHistorikk {

    private LocalDate fraDato;
    private StedsnavnBobleId.VedtaksmyndighetKodeId vedtaksmyndighetId;

    public VedtaksmyndighetHistorikk(LocalDate fraDato, StedsnavnBobleId.VedtaksmyndighetKodeId vedtaksmyndighetId) {
        this.fraDato = fraDato;
        this.vedtaksmyndighetId = vedtaksmyndighetId;
    }

    public LocalDate getFraDato() {
        return fraDato;
    }

    public StedsnavnBobleId.VedtaksmyndighetKodeId getVedtaksmyndighetId() {
        return vedtaksmyndighetId;
    }
}
