package no.statkart.wsclient.grunnbok.innsending;

import no.statkart.wsclient.grunnbok.innsending.domene.Behandlingsstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;

public interface InnsendingService {

   String allokerInnsendingId();

   Behandlingsstatus validerMelding(Forsendelse forsendelse);

   Behandlingsstatus tinglysMelding(Forsendelse forsendelse);

   Behandlingsstatus findBehandlingsstatus(String innsendingId);

}
