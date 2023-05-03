package no.statkart.wsclient;

import com.sun.xml.ws.developer.JAXWSProperties;
import no.statkart.wsclient.handler.OutboundServiceProxyInvocationHandler;

import javax.net.ssl.HostnameVerifier;
import javax.xml.ws.BindingProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;


@SuppressWarnings("unchecked")
public final class WebServiceBuilder<T> {
    private static final int TIMEOUT_MILLIS = 30_000; //30 sekunder
    private static final int DEFAULT_RETRIES = 2; //så 2 retries betyr at det er totalt 3 forsøk på kallet
    private static final int SLEEPTIME_MILLIS = 60_000; //1 minutt - Ventetid mellom hvert kall til tjenesten, ved flere forsøk.

    private int timeout;
    private int retries;
    private String brukernavn;
    private String passord;
    private String endpointUrl;
    private HostnameVerifier hostnameVerifier;
    private boolean createProxy;
    private T unproxiedService;
    private Class<T> clazzOfWebService;
    private int sleepTime;

    private WebServiceBuilder(T unproxiedService, Class<T> clazzOfWebService) {
        this.unproxiedService = unproxiedService;
        this.clazzOfWebService = clazzOfWebService;
    }

    public static <T> WebServiceBuilder<T> builder(T unproxiedService, Class<T> clazzOfWebService) {
        WebServiceBuilder<T> builder = new WebServiceBuilder<>(unproxiedService, clazzOfWebService);
        return builder
            .defaultTimeout()
            .defaultRetries()
            .defaultSleepTime()
            .defaultHostnameVerifier();
    }

    public static <T> WebServiceBuilder<T> builderv2(T unproxiedService, Class<T> clazzOfWebService) {
        WebServiceBuilder<T> builder = new WebServiceBuilder<>(unproxiedService, clazzOfWebService);
        return builder
            .defaultTimeout()
            .defaultRetries()
            .defaultSleepTime();
    }

    private WebServiceBuilder defaultSleepTime() {
        this.sleepTime = SLEEPTIME_MILLIS;
        return this;
    }

    private WebServiceBuilder<T> defaultTimeout() {
        this.timeout = TIMEOUT_MILLIS;
        return this;
    }

    public WebServiceBuilder<T> withTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    private WebServiceBuilder<T> defaultRetries() {
        this.retries = DEFAULT_RETRIES;
        return this;
    }

    public WebServiceBuilder<T> withRetries(int numRetries) {
        this.retries = numRetries;
        return this;
    }

    public WebServiceBuilder<T> withBruker(String brukernavn) {
        this.brukernavn = brukernavn;
        return this;
    }

    public WebServiceBuilder<T> withPassord(String passord) {
        this.passord = passord;
        return this;
    }

    public WebServiceBuilder<T> withEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
        return this;
    }

    private WebServiceBuilder<T> defaultHostnameVerifier() {
        this.hostnameVerifier = new NullHostnameVerifier();
        return this;
    }

    public WebServiceBuilder<T> withHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public WebServiceBuilder<T> doCreateProxy() {
        createProxy = true;
        return this;
    }

    public T build() {
        BindingProvider bindingProvider = (BindingProvider) unproxiedService;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();

        if (hostnameVerifier != null) {
            requestContext.put(JAXWSProperties.HOSTNAME_VERIFIER, hostnameVerifier);
        }
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
        requestContext.put(BindingProvider.USERNAME_PROPERTY, brukernavn);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, passord);

        //TODO: Må en virkelig spesifisere vendor-spesifikk timeout-property?
        requestContext.put("weblogic.wsee.transport.read.timeout", timeout / 1000);//Sekunder Weblogic HTTP Stack
        requestContext.put(JAXWSProperties.REQUEST_TIMEOUT, timeout);//Millis Sun/Oracle HTTP Stack

        requestContext.put("weblogic.wsee.transport.connection.timeout", timeout / 1000);//Sekunder Weblogic HTTP Stack
        requestContext.put(JAXWSProperties.CONNECT_TIMEOUT, timeout);//Millis Sun/Oracle HTTP Stack

        if (createProxy) {
            //The service may be wrapped in a proxy object it self
            T service = clazzOfWebService.cast(unproxiedService);
            InvocationHandler invocationHandler = new OutboundServiceProxyInvocationHandler(service, retries, sleepTime);
            return (T) Proxy.newProxyInstance(clazzOfWebService.getClassLoader(), new Class[]{clazzOfWebService}, invocationHandler);
        } else {
            return unproxiedService;
        }
    }

}
