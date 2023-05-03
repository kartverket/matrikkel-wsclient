package no.statkart.wsclient.landbruksregister;

import no.slf.lib.server.ws.WsEiendomDTO;


public interface LandbruksregisterWS {

    WsEiendomDTO getEiendom(String kommunenr, String gardsnr, String bruksnr, String festenr, int landbrukseiendom, int eiere,
                            int bedrifter, int skonti, int grunneiendommer);
}
