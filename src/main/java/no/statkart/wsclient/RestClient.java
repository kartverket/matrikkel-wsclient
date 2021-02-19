package no.statkart.wsclient;

import no.statkart.skif.exception.OperationalException;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Felles REST-client som kan brukes for rest-kall.
 */
public class RestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    /**
     * Utfører en http-request med eventuell brukernavn/passord
     */
    public static CloseableHttpResponse executeHttpsRequest(HttpRequestBase request, String username, String password)  {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        // hvis det skal være credentials i requesten
        if (username != null && password != null) {

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
            AuthScope.ANY,
            new UsernamePasswordCredentials(username, password)
        );

            httpClientBuilder
                .setDefaultCredentialsProvider(credentialsProvider);
        }

        CloseableHttpClient client = httpClientBuilder.build();

        try {
            CloseableHttpResponse response = client.execute(request);

            // hvis statuskode ikke ok
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                logger.info("Rest-kall feilet: "+request.getRequestLine()+" "
                    +response.getStatusLine().getStatusCode()+" "
                    +response.getStatusLine().getReasonPhrase());

                throw new OperationalException("REST-kall feilet: Kall: " + request.getRequestLine() + " Status: " +
                    response.getStatusLine().getStatusCode() + " Årsak: " + response.getStatusLine().getReasonPhrase());

            } else {
                logger.info("REST-kall ok: "+request.getRequestLine()+" "+response.getStatusLine().getStatusCode());
                return response;
            }
        } catch (IOException e) {
            request.releaseConnection();
            String msg = "Feil i REST-kall: "+e.getMessage();
            logger.error(msg, e);
            throw new OperationalException(msg, e);
        }
    }
}