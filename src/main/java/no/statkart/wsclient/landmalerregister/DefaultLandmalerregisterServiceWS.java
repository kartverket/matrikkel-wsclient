package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.OperationalException;
import no.statkart.wsclient.RestClient;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
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

    private final String requestUrl;
    private final String brukernavn;
    private final String passord;

    public DefaultLandmalerregisterServiceWS(String requestUrl, String brukernavn, String passord) {
        this.requestUrl = requestUrl;
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    @Override
    public Set<LandmalerFraAAL> findLandmalerWS(String landmalernummer, String fornavn, String etternavn) throws LandmalerregisterSokException {
        // hvis url til landmålerregister ikke er satt
        if (requestUrl == null) {
            String msg = "URL til Landmålerregister er ikke satt.";
            throw new ImplementationException(msg);
        }

        Set<LandmalerFraAAL> landmalerResultat;

        // validerer input, og lager request-url
        String requestUrlParameters = LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernummer, fornavn, etternavn);
        HttpGet request = new HttpGet(requestUrl + requestUrlParameters);

        // http-request
        try (CloseableHttpClient client = RestClient.buildHttpClient(brukernavn, passord);
             CloseableHttpResponse response = client.execute(request)) {

            // valider responsobjektet
            validateResponse(response);

            // respons fra tjener er et jsonObject, med en liste
            String responseJson = EntityUtils.toString(response.getEntity());

            JSONObject landmalereJson = new JSONObject(responseJson);
            JSONArray landmaalere = landmalereJson.getJSONArray("landmaalere");

            landmalerResultat = LandmalerregisterUtil.lagSetLandmalereFraAALFraJsonResponse(landmaalere);

        } catch (IOException e) {
            String msg = "Feil i kall til Landmålerregisteret: " + e;
            throw new OperationalException(msg, e);
        }

        return landmalerResultat;
    }

    // Validerer responsobjektet.
    // 1. Riktig statuskode
    // 2. Riktig mimeType ved 200 ok
    private void validateResponse(CloseableHttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();

        if (HttpStatus.SC_OK == statusCode) {
            if (ContentType.get(response.getEntity()).getMimeType().equals("text/html")) {
                // TODO MAT-18034: inntil AAL-131 er løst, vil dette kunne bety enten at URL er feil (men fortsatt til AAL-systemet), eller at brukernavn og/eller passord er feil
                throw new LandmalerregisterSokException("Feil i kall til AAL. Sjekk URL, og brukernavn/passord.");
            }
        } else if (HttpStatus.SC_UNAUTHORIZED == statusCode) {
            // TODO MAT-18034: inntil AAL-131 er løst, vil dette bety at brukernavn og/eller passord er null
            throw new LandmalerregisterSokException("Feil brukernavn/passord i kall til Landmalerregisteret.");

        } else {
            throw new LandmalerregisterSokException("URL til Landmålerregister er ugyldig eller tjenesten kan være nede. Status: " + statusCode);
        }
    }
}