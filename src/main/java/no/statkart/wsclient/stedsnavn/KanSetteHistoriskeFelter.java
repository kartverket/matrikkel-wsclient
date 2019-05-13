package no.statkart.wsclient.stedsnavn;

import java.time.LocalDateTime;

public interface KanSetteHistoriskeFelter {

    void setOppdateringsdato(LocalDateTime oppdateringsdato);

    void setOppdatertAv(String oppdatertAv);

    void setSluttdato(LocalDateTime sluttdato);

    void setAvsluttetAv(String avsluttetAv);
}
