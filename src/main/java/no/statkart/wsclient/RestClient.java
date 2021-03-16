package no.statkart.wsclient;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Felles REST-client som kan brukes for rest-kall.
 */
public class RestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    /**
     * Utfører en http-request med eventuell brukernavn/passord
     */
    public static CloseableHttpClient buildHttpClient(String username, String password) {
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

        return httpClientBuilder.build();
    }
}