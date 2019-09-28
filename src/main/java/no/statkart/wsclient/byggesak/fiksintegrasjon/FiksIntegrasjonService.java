package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.wsclient.byggesak.model.MeldingFraSaksystemDTO;

import java.net.URL;
import java.util.Set;

/**
 * Interface for kommunikasjon mot FIKS REST-API
 */
public interface FiksIntegrasjonService {

   /**
    * Henter tilgjengelige forsendelser fra FIKS.
    *
    * @param forsendelseIds Eksisterende Id'er som er lagret
    * @param privateKeyUrl PrivateKey for å dekryptere vedlegg
    * @return En samling av Info-objekter som inneholder alle data fra forsendelse og byggesak
    */
    Set<MeldingFraSaksystemDTO> hentForsendelser(Set<String> forsendelseIds, URL privateKeyUrl);

   /**
    * Sjekker om vedlagt id fortsatt er tilgjengelig i listen av nye meldinger.
    *
    * @param forsendelseId Id på forsendelse.
    * @return true hvis den er tilgjengelig, false hvis ikke.
    */
    boolean sjekkOmForsendelseErTilgjengelig(String forsendelseId);

   /**
    * Kvitterer ut en forsendelse i FIKS.
    * Må gjøres når man starter føring av en byggesak.
    *
    * @param forsendelsesId Id på forsendelsen som skal kvitteres ut.
    */
   boolean kvitterMelding(String forsendelsesId);
}
