package no.statkart.wsclient.stedsnavn;

import java.util.Map;
import java.util.Objects;

public class LocalizedString {

    private Map<String, String> keyAndValues;

    public LocalizedString(Map<String, String> keyAndValues) {
        this.keyAndValues = Objects.requireNonNull(keyAndValues);
    }

    public Map<String, String> getKeyAndValues() {
        return keyAndValues;
    }
}
