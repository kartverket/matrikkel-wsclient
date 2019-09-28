package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.skif.exception.OperationalException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Hjelpeklasse for å lagre informasjon vedrørende 1 http-respons (1 forsendelse) fra FIKS.
 */
class BehandleRespons {
   private static Logger logger = LoggerFactory.getLogger(BehandleRespons.class);

   /**
    * Lager et set med ResponsMeldinger basert på respons fra FIKS.
    *
    * @param jsonRespons En json-array med 0 eller flere json-objecter som inneholder metadata for 1 forsendelse
    * @param eksisterendeIds Eksisterende forsendelser som er behandlet og lagret i basen
    * @return ferdig behandler set med responsmeldinger hvor urelevante forsendelser er utelukket.
    */
   static Set<ResponsMelding> lagResponsMeldinger(String jsonRespons, Set<String> eksisterendeIds) {
      Set<String> finalEksisterendeIds = Optional.ofNullable(eksisterendeIds).orElse(new HashSet<>());
      JSONArray jsonArray = new JSONArray(jsonRespons);
      // JsonArrayen inneholder 0 eller flere JsonObjecter. Lag en ResponsMelding for hvert JsonObject som inneholder en xml,
      // og filtrer bort de forsendelsene vi allerede har i basen
      return jsonArray.toList().stream()
            .map(object -> new JSONObject((Map) object))
            .filter(jsonObject -> sjekkXmlFilnavn(jsonObject) != null)
            .map(BehandleRespons::buildResponsMelding)
            .filter(Objects::nonNull)
            .filter(responsMelding -> !finalEksisterendeIds.contains(responsMelding.getForsendelseId()))
            .collect(Collectors.toSet());
   }

   // lager et tilpasset ResponsMelding-objekt som inneholder informasjon som er relevant
   private static ResponsMelding buildResponsMelding(JSONObject byggesakJsonObject) {
      ResponsMelding responsMelding = new ResponsMelding();
      try {
         responsMelding.setDate(new Date(byggesakJsonObject.getLong("date")));
         responsMelding.setDownloadUrl(byggesakJsonObject.getString("downloadUrl"));
         responsMelding.setForsendelseId(byggesakJsonObject.getString("id"));
         responsMelding.setTittel(byggesakJsonObject.getString("tittel"));
         responsMelding.setByggesakXml(null); // laster ned i et eget steg
         responsMelding.setXmlFilnavn(sjekkXmlFilnavn(byggesakJsonObject));
//         responsMelding.setKommunenr(byggesakJsonObject.getString("kommunenr")); // TODO MAT-15916 når de har endret på andre siden, skal denne inn igjen
      } catch (JSONException e) {
         logger.error("Feil i lesing av JSON-object for forsendelse: "+responsMelding.getForsendelseId(), e);
         return null;
      }
      return responsMelding;
   }

   // går gjennom filmetadata på mottatt object og sjekker (og returnerer) filnavnet av typen xml
   // pr. nå går man utifra at det alltid vil være 1 og bare 1 xml i en forsendelse, og at denne inneholder byggesakdata
   private static String sjekkXmlFilnavn(JSONObject jsonObject) {
      Set<String> keySet = jsonObject.keySet();
      if(!keySet.contains("filmetadata")) {
         throw new OperationalException("Filmetadata finnes ikke i forsendelsen");
      }
      JSONArray filmetadataArray = jsonObject.getJSONArray("filmetadata");

      for (Object filmetadataJsonObject : filmetadataArray) {
         // hvis denne filen er riktig filtype, returner filnavnet
         String mimetype = ((JSONObject) filmetadataJsonObject).getString("mimetype");
         if(mimetype.equals("text/xml") || mimetype.equals("application/xml")) {
            return ((JSONObject) filmetadataJsonObject).getString("filnavn");
         }
      }
      return null;
   }
}
