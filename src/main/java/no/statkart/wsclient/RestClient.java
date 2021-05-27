package no.statkart.wsclient;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


/**
 * Felles REST-client som kan brukes for rest-kall.
 */
public class RestClient {

    /**
     * Utfører en http-request med eventuell brukernavn/passord
     */
    public static CloseableHttpClient buildHttpClient(String username, String password) {
        return HttpClientBuilder.create()
            .useSystemProperties() //mulighet for konfigurasjon av proxy via "https.proxyHost" og "https.proxyPort"
            .setDefaultCredentialsProvider(credentialsFrom(username, password))
            .build();
    }

    private static CredentialsProvider credentialsFrom(String username, String password) {
        // hvis det skal være credentials i requesten
        if (username != null && password != null) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

            credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(username, password)
            );

            return credentialsProvider;
        }
        return null;
    }
}