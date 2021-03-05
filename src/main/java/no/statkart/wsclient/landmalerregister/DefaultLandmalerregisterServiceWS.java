package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.OperationalException;
import no.statkart.wsclient.RestClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DefaultLandmalerregisterServiceWS implements LandmalerregisterServiceWS {
    private static final Logger logger = LoggerFactory.getLogger(DefaultLandmalerregisterServiceWS.class);

    private final String requestUrl;

    public DefaultLandmalerregisterServiceWS(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public Set<LandmalerFraAAL> findLandmalerWS(Long landmalernr, String fornavn, String etternavn) {

        Set<LandmalerFraAAL> landmalerResultat;

        // validerer input, og lager request-url
        String requestUrlParameters = LandmalerregisterUtil.validateAndBuildUrlParameters(landmalernr, fornavn, etternavn);
        HttpGet request = new HttpGet(requestUrl + requestUrlParameters);

        // http-request
        try (CloseableHttpResponse response = RestClient.executeHttpsRequest(request, null, null)) {

            // respons fra tjener er et jsonObject, med en liste
            String responseJson = EntityUtils.toString(response.getEntity());
            JSONObject landmalereJson = new JSONObject(responseJson);
            JSONArray landmaalere = landmalereJson.getJSONArray("landmaalere");

            landmalerResultat = LandmalerregisterUtil.lagSetLandmalereFraAALFraJsonResponse(landmaalere);

        } catch (IOException e) {
            String msg = "REST-kall feilet: "+request.getRequestLine() + " Årsak: " + e.getMessage();
            logger.error(msg);
            throw new OperationalException(msg, e);
        }
        return landmalerResultat;
    }
}