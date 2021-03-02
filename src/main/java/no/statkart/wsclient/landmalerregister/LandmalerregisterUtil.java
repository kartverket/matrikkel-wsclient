package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.ValidationException;

import java.util.stream.Stream;

/**
 * Hjelpeklasser for søk mot AAL eller mock-implementasjon
 */
public class LandmalerregisterUtil {

    /**
     * Valider input og bygg parameter-string til URL
     *
     * @return Søkeparametere for request-strengen
     */
    public static String validateAndBuildUrlParameters(Long landmalernr, String fornavn, String etternavn) {
        // hvis alt er blankt
        if (landmalernr == null && Stream.of(fornavn, etternavn).allMatch(s -> s == null || s.trim().isEmpty())) {
            throw new ValidationException("Søk i Landmålerregister inneholder bare tomme felter.");
        }

        return buildUrlParameters(landmalernr, fornavn, etternavn);
    }

    // bygg en url med de parameterene som er angitt
    private static String buildUrlParameters(Long landmalernr, String fornavn, String etternavn) {

        // sjekk hva som er fylt ut
        boolean landmalerUtfylt = landmalernr != null;
        boolean fornavnUtfylt = fornavn != null && !fornavn.trim().isEmpty();
        boolean etternavnUtfylt = etternavn != null && !etternavn.trim().isEmpty();

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("?");

        // hvis landmåler er fylt ut
        if (landmalerUtfylt) {
            String urlLandmalernrParameter = "landmaalernummer=";
            urlBuilder.append(urlLandmalernrParameter);
            urlBuilder.append(landmalernr);

            // hvis det skal være flere parametre i url
            if (fornavnUtfylt || etternavnUtfylt) {
                urlBuilder.append("&");
            }
        }

        // hvis det skal være en navneparameter
        if (fornavnUtfylt || etternavnUtfylt) {
            String urlNavnParameter = "navn=";
            urlBuilder.append(urlNavnParameter);
        }

        // hvis fornavn er fylt ut
        if (fornavnUtfylt) {
            urlBuilder.append(fornavn);
            urlBuilder.append("%20");
        }

        // hvis etternavn er fylt ut
        if (etternavnUtfylt) {
            urlBuilder.append(etternavn);
        }

        return urlBuilder.toString();
    }
}