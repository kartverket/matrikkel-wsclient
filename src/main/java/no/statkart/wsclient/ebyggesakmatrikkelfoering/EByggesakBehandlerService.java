package no.statkart.wsclient.ebyggesakmatrikkelfoering;

import no.statkart.skif.exception.ImplementationException;

import java.util.Set;

public interface EByggesakBehandlerService {

    Set<MeldingFraFIKS> hentNyeMeldinger() throws ImplementationException;

    void oppdaterEByggesak(int id);
}
