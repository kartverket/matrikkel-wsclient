package no.statkart.wsclient.landmalerregister;

import no.statkart.skif.exception.OperationalException;

/**
 * Exception som brukes ved direkte feil i HTTP-request mot Landmålerregisteret (AAL)
 */
public class LandmalerregisterSokException extends OperationalException {

    public LandmalerregisterSokException(String message) {
        super(message);
    }

    public LandmalerregisterSokException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
