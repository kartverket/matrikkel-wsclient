package no.statkart.wsclient.grunnbokv2.innsending;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;

public interface InnsendingServiceWS {

   Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse);

   Forsendelsesstatus hentStatus(String innsendingId);

}
