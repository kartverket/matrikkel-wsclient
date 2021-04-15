package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ImplementationException;
import no.statkart.wsclient.RestClient;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

public class DefaultLandmalerregisterServiceWS implements LandmalerregisterServiceWS {
    private static final Logger logger = LoggerFactory.getLogger(DefaultLandmalerregisterServiceWS.class);

    private final String requestUrl;
    private final String brukernavn;
    private final String passord;

    public DefaultLandmalerregisterServiceWS(String requestUrl, String brukernavn, String passord) {
        this.requestUrl = requestUrl;
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    @Override
    public Set<LandmalerFraAAL> findLandmalerWS(String landmalernummer, String fornavn, String etternavn) throws LandmalerregisterSokException{
        // hvis url til landmålerregister ikke er satt
        if (requestUrl == null) {
            String msg = "URL til Landmålerregister er ikke satt.";
            logger.error(msg);
            throw new ImplementationException(msg);
        }

        Set<LandmalerFraAAL> landmalerResultat;

        // validerer input, og lager request-url
        String requestUrlParameters = LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernummer, fornavn, etternavn);
        HttpGet request = new HttpGet(requestUrl + requestUrlParameters);

        // http-request
        try (CloseableHttpClient client = RestClient.buildHttpClient(brukernavn, passord);
             CloseableHttpResponse response = client.execute(request)) {

            int statusCode = response.getStatusLine().getStatusCode();

            // http-request gikk bra
            if (HttpStatus.SC_OK == statusCode) {
                // respons fra tjener er et jsonObject, med en liste
                String responseJson = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject landmalereJson = new JSONObject(responseJson);
                    JSONArray landmaalere = landmalereJson.getJSONArray("landmaalere");

                    landmalerResultat = LandmalerregisterUtil.lagSetLandmalereFraAALFraJsonResponse(landmaalere);
                }
                // todo MAT-18034: inntil videre kan dette også bety feil brukernavn/passord, fordi AAL gir ut 200 selvom bruker og pass er feil
                catch (JSONException ignored) {
                    String msg = "Feil i respons fra Landmalerregisteret. Dobbeltsjekk at riktig URL/tjeneste er angitt, og at det er riktig brukernavn og passord.";
                    logger.error(msg);
                    throw new LandmalerregisterSokException(msg);
                }
            }  // feil brukernavn og/eller passord
            else if (HttpStatus.SC_UNAUTHORIZED == statusCode) {
                String msg = "Feil brukernavn/passord i kall til Landmalerregisteret.";
                logger.error(msg);
                throw new LandmalerregisterSokException(msg);
            }

            // url kan være feil
            else {
                String msg = "URL til Landmålerregister er ugyldig eller tjenesten kan være nede. Status: " + statusCode;
                logger.error(msg);
                throw new LandmalerregisterSokException(msg);
            }
        } catch (IOException e) {

            String msg = "URL er ukjent, eller Landmålerregisteret er nede: " + e;
            logger.error(msg);
            throw new LandmalerregisterSokException(msg, e);
        }

        return landmalerResultat;
    }
}