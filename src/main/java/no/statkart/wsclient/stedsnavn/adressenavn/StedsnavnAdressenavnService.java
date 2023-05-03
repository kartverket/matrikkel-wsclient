package no.statkart.wsclient.stedsnavn.adressenavn;

@SuppressWarnings("unused")
public interface StedsnavnAdressenavnService {

    void sendNyVegTilStedsnavn(NyVegRequest nyVegRequest);

    void sendEndretVegTilStedsnavn(EndretVegRequest endretVegRequest);

    void sendSlettetVegTilStedsnavn(SlettetVegRequest slettetVegRequest);

}
