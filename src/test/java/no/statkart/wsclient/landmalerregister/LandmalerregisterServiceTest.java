package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.OperationalException;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LandmalerregisterServiceTest {


    private DefaultLandmalerregisterServiceWS buildService(CloseableHttpClient httpClient) {
        return new DefaultLandmalerregisterServiceWS("https://foo.bar/baz", () -> httpClient);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void urlManglerGirFeil() {
        assertThatCode(() -> new DefaultLandmalerregisterServiceWS(null, null, null))
            .hasMessage("URL til Landmålerregister er ikke satt.");
        assertThatCode(() -> new DefaultLandmalerregisterServiceWS("", null, null))
            .hasMessage("URL til Landmålerregister er ikke satt.");
    }

    @Test
    public void nettverksbruddGirFeilmelding() throws IOException {
        CloseableHttpClient httpClient = Mockito.mock(CloseableHttpClient.class);
        when(httpClient.execute(any())).thenThrow(new IOException("Spaghetti line!"));

        DefaultLandmalerregisterServiceWS defaultLandmalerregisterServiceWS = buildService(httpClient);
        assertThatThrownBy(() -> defaultLandmalerregisterServiceWS.findLandmalerWS(null, "Fornavn", null))
            .isInstanceOf(OperationalException.class)
            .hasMessage("Feil i kall til Landmålerregisteret: java.io.IOException: Spaghetti line!");
    }

    @Test
    public void ukjentTypeInnholdGirFeilmelding() throws IOException {
        CloseableHttpResponse response = response(
            httpStatus(200),
            httpEntity("text/html", "Oops, sorry!"));

        CloseableHttpClient httpClient = Mockito.mock(CloseableHttpClient.class);
        when(httpClient.execute(any())).thenReturn(response);

        DefaultLandmalerregisterServiceWS defaultLandmalerregisterServiceWS = buildService(httpClient);
        assertThatThrownBy(() -> defaultLandmalerregisterServiceWS.findLandmalerWS(null, "Fornavn", null))
            .isInstanceOf(LandmalerregisterSokException.class)
            .hasMessage("Feil i kall til AAL. Sjekk URL, og brukernavn/passord.");
    }

    @Test
    public void manglendeTilgangGirFeilmelding() throws IOException {
        CloseableHttpResponse response = response(httpStatus(401));

        CloseableHttpClient httpClient = Mockito.mock(CloseableHttpClient.class);
        when(httpClient.execute(any())).thenReturn(response);

        DefaultLandmalerregisterServiceWS defaultLandmalerregisterServiceWS = buildService(httpClient);
        assertThatThrownBy(() -> defaultLandmalerregisterServiceWS.findLandmalerWS(null, "Fornavn", null))
            .isInstanceOf(LandmalerregisterSokException.class)
            .hasMessage("Feil brukernavn/passord i kall til Landmalerregisteret.");
    }

    @Test
    public void ukjentFeilGirFeilmelding() throws IOException {
        CloseableHttpResponse response = response(httpStatus(404));

        CloseableHttpClient httpClient = Mockito.mock(CloseableHttpClient.class);
        when(httpClient.execute(any())).thenReturn(response);

        DefaultLandmalerregisterServiceWS defaultLandmalerregisterServiceWS = buildService(httpClient);
        assertThatThrownBy(() -> defaultLandmalerregisterServiceWS.findLandmalerWS(null, "Fornavn", null))
            .isInstanceOf(LandmalerregisterSokException.class)
            .hasMessage("URL til Landmålerregister er ugyldig eller tjenesten kan være nede. Status: 404");
    }

    private CloseableHttpResponse response(StatusLine httpStatus) {
        return response(httpStatus, null);
    }

    private CloseableHttpResponse response(StatusLine httpStatus, HttpEntity httpEntity) {
        CloseableHttpResponse response = Mockito.mock(CloseableHttpResponse.class);
        when(response.getStatusLine()).thenReturn(httpStatus);
        when(response.getEntity()).thenReturn(httpEntity);
        return response;
    }

    private HttpEntity httpEntity(String mimeType, String payload) throws IOException {
        HeaderElement contentHeaderElement = Mockito.mock(HeaderElement.class);
        when(contentHeaderElement.getName()).thenReturn(mimeType);
        when(contentHeaderElement.getValue()).thenReturn(StandardCharsets.UTF_8.name());
        when(contentHeaderElement.getParameters()).thenReturn(new NameValuePair[0]);

        Header contentType = Mockito.mock(Header.class);
        when(contentType.getElements()).thenReturn(new HeaderElement[]{contentHeaderElement});

        HttpEntity httpEntity = Mockito.mock(HttpEntity.class);
        when(httpEntity.getContentType()).thenReturn(contentType);
        when(httpEntity.getContentLength()).thenReturn((long) payload.length());
        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(payload.getBytes(StandardCharsets.UTF_8)));
        return httpEntity;
    }

    private StatusLine httpStatus(int value) {
        StatusLine statusLine = Mockito.mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(value);
        return statusLine;
    }

}
