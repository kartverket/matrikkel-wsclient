package no.statkart.wsclient.grunnbok.innsending;

import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus;

public interface InnsendingServiceWS {

   Forsendelsesstatus valider(Forsendelse forsendelse);

   Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse);

   Forsendelsesstatus hentStatus(String innsendingId);

}
