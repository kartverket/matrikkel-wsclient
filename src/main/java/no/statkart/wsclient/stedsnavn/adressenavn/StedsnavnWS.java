package no.statkart.wsclient.stedsnavn.adressenavn;

@SuppressWarnings("unused")
public interface StedsnavnWS {

   void sendNyVegTilStedsnavn(NyVegRequest nyVegRequest);

   void sendEndretVegTilStedsnavn(EndretVegRequest endretVegRequest);

   void sendSlettetVegTilStedsnavn(SlettetVegRequest slettetVegRequest);

}
