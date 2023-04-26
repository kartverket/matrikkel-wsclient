package no.statkart.wsclient;

import no.statkart.skif.config.Configuration;
import no.statkart.skif.config.PropertiesConfiguration;
import no.statkart.skif.config.StackedConfiguration;
import no.statkart.skif.config.SystemConfiguration;

/**
 * Konfigurasjonsparametere for integrasjonstesting
 */
public class IntegrationTestProperties {
    private final StackedConfiguration configuration;

    public IntegrationTestProperties() {
        this(new PropertiesConfiguration("no/statkart/wsclient/integration-test.properties"));
    }

    public IntegrationTestProperties(Configuration configuration) {
        this.configuration = new StackedConfiguration(new SystemConfiguration());
        this.configuration.addConfiguration(configuration);
    }

    String getGrunnbokServerUrl() {
        return configuration.getString("grunnbok_server_url");
    }

    public String getClientIdentification() {
        return configuration.getString("grunnbok_clientIdentification", System.getProperty("user.name"));
    }

    public String getGrunnbokApiVersion() {
        return configuration.getString("grunnbok_apiVersion");
    }

    public String getGrunnbokGrunnboksutskriftInternServiceUrl() {
        return String.format("%s/grunnbok/wsapi/v2/GrunnboksutskriftInternServiceWS", getGrunnbokServerUrl());
    }

    public String getIdentServiceServiceUrl() {
        return String.format("%s/grunnbok/wsapi/v2/IdentServiceWS", getGrunnbokServerUrl());
    }

    public String getGrunnbokSaksinformasjonServiceUrl() {
        return String.format("%s/grunnbok/wsapi/v2/SaksinformasjonServiceWS", getGrunnbokServerUrl());
    }


    public String getGrunnbokStoreServiceUrl() {
        return String.format("%s/grunnbok/wsapi/v2/StoreServiceWS", getGrunnbokServerUrl());
    }

    public String getGrunnbokKodelisteServiceUrl() {
        return String.format("%s/grunnbok/wsapi/v2/KodelisteServiceWS", getGrunnbokServerUrl());
    }

    public String getRettsstiftelseServiceUrl() {
        return String.format("%s/grunnbok/wsapi/v2/RettsstiftelseServiceWS", getGrunnbokServerUrl());
    }

    public String getGrunnbokInnsendingServiceUrl() {
        return String.format("%s/grunnbok/wsapi/v2/InnsendingServiceWS", getGrunnbokServerUrl());
    }

    public String getLandbruksregisterUsername() {
        return System.getProperty("landbruksregister.username");
    }

    public String getLandbruksregisterPassword() {
        return System.getProperty("landbruksregister.password");
    }

    public String getLandbruksregisterUrl() {
        return configuration.getString("landbruksregister_url");
    }

    public String getLandmalerregisterUrl() {
        return configuration.getString("landmalerregister_url");
    }

    public String getGrunnbokMatFnUsername() {
        return System.getProperty("grunnbok.mat.username");
    }

    public String getGrunnbokMatFnPassword() {
        return System.getProperty("grunnbok.mat.password");
    }

    public String getGrunnbokTinglysingUsername() {
        return System.getProperty("grunnbok.tinglysing.username");
    }

    public String getGrunnbokTinglysingPassword() {
        return System.getProperty("grunnbok.tinglysing.password");
    }
}
