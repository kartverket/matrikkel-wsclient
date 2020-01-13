package no.statkart.wsclient.byggesak.fiksintegrasjon;

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
 * Klient for å utføre kall mot FIKS-systemet.
 */
class FiksMottakerRestClient {
   private static final Logger logger = LoggerFactory.getLogger(FiksMottakerRestClient.class);

   /**
    * Utfører et http-kall mot FIKS.
    *
    * @param request Get eller post mot rest-api.
    * @param username Service brukernavn pr. mottakersystem i FIKS.
    * @param password Service-passord pr. mottakersystem i FIKS.
    * @return HttpResponse med payload fra kall.
    */
   static CloseableHttpResponse executeHttpsRequest(HttpRequestBase request, String username, String password)  {

      HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

      CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
      credentialsProvider.setCredentials(
            AuthScope.ANY,
            new UsernamePasswordCredentials(username, password)
      );

      CloseableHttpClient client = httpClientBuilder
            .setDefaultCredentialsProvider(credentialsProvider)
            .build();
      try {
         CloseableHttpResponse response = client.execute(request);

         if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            logger.info("Kall til fiks "+request.getRequestLine()+" feilet: "
                  +response.getStatusLine().getStatusCode()+" "
                  +response.getStatusLine().getReasonPhrase());
            return null;
         } else {
            logger.info("Kall til fiks "+request.getRequestLine()+" ok: "+response.getStatusLine().getStatusCode());
            return response;
         }
      } catch (IOException e) {
         request.releaseConnection();
         logger.error("Feil i kall til FIKS: "+e.getMessage(), e);
         return null;
      }
   }
}
