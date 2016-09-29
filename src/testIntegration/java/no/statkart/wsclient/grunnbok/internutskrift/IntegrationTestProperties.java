package no.statkart.wsclient.grunnbok.internutskrift;

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


   public String getGrunnbokUser() {
      return configuration.getString("grunnbok_brukernavn");
   }

   public String getGrunnbokPassword() {
      return configuration.getString("grunnbok_passord");
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
      return String.format("%s/grunnbok/wsapi/v1/GrunnboksutskriftInternServiceWS", getGrunnbokServerUrl());
   }

   public String getIdentServiceServiceUrl() {
      return String.format("%s/grunnbok/wsapi/v1/IdentServiceWS", getGrunnbokServerUrl());
   }
}
