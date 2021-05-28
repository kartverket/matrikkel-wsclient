package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.OperationalException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Set;
import java.util.function.Supplier;

import static no.statkart.wsclient.RestClient.buildHttpClient;

public class DefaultLandmalerregisterServiceWS implements LandmalerregisterServiceWS {

    private final String requestUrl;
    private final Supplier<CloseableHttpClient> clientProvider;

    DefaultLandmalerregisterServiceWS(String requestUrl, Supplier<CloseableHttpClient> clientProvider) {
        if (requestUrl == null || requestUrl.isEmpty()) {
            throw new IllegalArgumentException("URL til Landmålerregister er ikke satt.");
        }
        this.requestUrl = requestUrl;
        this.clientProvider = clientProvider;
    }

    public DefaultLandmalerregisterServiceWS(String requestUrl, String brukernavn, String passord) {
        this(requestUrl, () -> buildHttpClient(brukernavn, passord));
    }

    @Override
    public Set<LandmalerFraAAL> findLandmalerWS(String landmalernummer, String fornavn, String etternavn) throws LandmalerregisterSokException {
        // validerer input, og lager request-url
        String requestUrlParameters = LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernummer, fornavn, etternavn);
        HttpGet request = new HttpGet(requestUrl + requestUrlParameters);

        // http-request
        try (CloseableHttpClient client = clientProvider.get();
             CloseableHttpResponse response = client.execute(request)) {

            // valider responsobjektet
            validateResponse(response);

            // respons fra tjener er et jsonObject, med en liste
            String responseJson = EntityUtils.toString(response.getEntity());

            JSONObject landmalereJson = new JSONObject(responseJson);
            JSONArray landmaalere = landmalereJson.getJSONArray("landmaalere");

            return LandmalerregisterUtil.lagSetLandmalereFraAALFraJsonResponse(landmaalere);

        } catch (IOException e) {
            String msg = "Feil i kall til Landmålerregisteret: " + e;
            throw new OperationalException(msg, e);
        }
    }

    // Validerer responsobjektet.
    // 1. Riktig statuskode
    // 2. Riktig mimeType ved 200 ok
    static void validateResponse(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();

        if (HttpURLConnection.HTTP_OK == statusCode) {
            if (ContentType.get(response.getEntity()).getMimeType().equals("text/html")) {
                // TODO MAT-18034: inntil AAL-131 er løst, vil dette kunne bety enten at URL er feil (men fortsatt til AAL-systemet), eller at brukernavn og/eller passord er feil
                throw new LandmalerregisterSokException("Feil i kall til AAL. Sjekk URL, og brukernavn/passord.");
            }
        } else if (HttpURLConnection.HTTP_UNAUTHORIZED == statusCode) {
            // TODO MAT-18034: inntil AAL-131 er løst, vil dette bety at brukernavn og/eller passord er null
            throw new LandmalerregisterSokException("Feil brukernavn/passord i kall til Landmalerregisteret.");

        } else {
            throw new LandmalerregisterSokException("URL til Landmålerregister er ugyldig eller tjenesten kan være nede. Status: " + statusCode);
        }
    }
}