package no.statkart.wsclient.stedsnavn.endringslogg;

import no.statkart.wsclient.stedsnavn.StedsnavnBoble;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;

import java.util.List;
import java.util.Map;

import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.EndringId;

public interface StedsnavnEndringsloggService {

    StedsnavnBobleId findSisteEndringId(StedsnavnContext stedsnavnContext);

    /**
     * @param id               Id 1 før den en ønsker å begynne på
     * @param maksAntall       Maks chunk size
     * @param stedsnavnContext Context
     * @return Endringer på Sted av type Nyoppretting eller Oppdatering
     */
    EndringerRespons findEndringerForSted(EndringId id, int maksAntall, StedsnavnContext stedsnavnContext);

    Map<StedsnavnBobleId.StedId, List<StedsnavnBobleId.StedsnavnId>> getStedsnavnForSteder(List<StedsnavnBobleId.StedId> stedIds, StedsnavnContext stedsnavnContext);

    Map<StedsnavnBobleId.StedsnavnId, List<StedsnavnBobleId.SkrivemaateId>> getSkrivemaaterForStedsnavn(List<StedsnavnBobleId.StedsnavnId> stedsnavnIds, StedsnavnContext wsCtx);

    StedsnavnBoble getObject(StedsnavnBobleId id, StedsnavnContext stedsnavnContext);

    List<StedsnavnBoble> getObjects(List<StedsnavnBobleId> ids, StedsnavnContext stedsnavnContext);

    class StedsnavnEndringsloggInitParams {
        String brukernavn;
        String passord;
        String endringsloggEndpointUrl;
        String stedsnavnEndpointUrl;
        String skrivemaateEndpointUrl;
        String storeServiceEndpointUrl;
        boolean enableLoggingAvWS;

        private StedsnavnEndringsloggInitParams() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            String brukernavn;
            String passord;
            String endringsloggEndpointUrl;
            String stedsnavnEndpointUrl;
            String skrivemaateEndpointUrl;
            String storeServiceEndpointUrl;
            boolean enableLoggingAvWS;

            private Builder() {
            }

            public Builder bruker(String brukernavn) {
                this.brukernavn = brukernavn;
                return this;
            }

            public Builder passord(String passord) {
                this.passord = passord;
                return this;
            }

            public Builder endringsloggUrl(String endringsloggEndpointUrl) {
                this.endringsloggEndpointUrl = endringsloggEndpointUrl;
                return this;
            }

            public Builder stedsnavnUrl(String stedsnavnEndpointUrl) {
                this.stedsnavnEndpointUrl = stedsnavnEndpointUrl;
                return this;
            }

            public Builder skrivemaateUrl(String skrivemaateEndpointUrl) {
                this.skrivemaateEndpointUrl = skrivemaateEndpointUrl;
                return this;
            }

            public Builder storeServiceUrl(String storeServiceEndpointUrl) {
                this.storeServiceEndpointUrl = storeServiceEndpointUrl;
                return this;
            }

            public Builder doEnableLoggingAvWS(boolean enableLoggingAvWS) {
                this.enableLoggingAvWS = enableLoggingAvWS;
                return this;
            }

            public StedsnavnEndringsloggInitParams build() {
                StedsnavnEndringsloggInitParams initParams = new StedsnavnEndringsloggInitParams();
                initParams.brukernavn = brukernavn;
                initParams.passord = passord;
                initParams.endringsloggEndpointUrl = endringsloggEndpointUrl;
                initParams.stedsnavnEndpointUrl = stedsnavnEndpointUrl;
                initParams.skrivemaateEndpointUrl = skrivemaateEndpointUrl;
                initParams.storeServiceEndpointUrl = storeServiceEndpointUrl;
                initParams.enableLoggingAvWS = enableLoggingAvWS;
                return initParams;
            }

        }

    }

}
