package no.statkart.wsclient.stedsnavn.adressenavn;

public enum AarsakTilEndringFraMatrikkelen {

    IKKE_OPPGITT("IkkeOppgitt"),
    VEDTAK_I_NY_NAVNESAK("VedtakINyNavnesak"),
    VEDTAK_I_KLAGESAK("VedtakIKlagesak"),
    VEDTAK_GJORT_AV_KLAGENEMNDA("VedtakGjortAvKlagenemnda"),
    RETTELSE_AV_FEILFOERING("RettelseAvFeilfoering");

    private String kodeverdi;

    AarsakTilEndringFraMatrikkelen(String kodeverdi) {
        this.kodeverdi = kodeverdi;
    }

    public String getKodeverdi() {
        return kodeverdi;
    }
}
