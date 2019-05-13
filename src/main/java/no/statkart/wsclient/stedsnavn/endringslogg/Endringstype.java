package no.statkart.wsclient.stedsnavn.endringslogg;

import java.util.Arrays;
import java.util.EnumSet;

public enum Endringstype {

    NYOPPRETTING("Nyoppretting"),
    OPPDATERING("Oppdatering");
    private final String value;

    Endringstype(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Endringstype fromValue(String v) {
        return Arrays.stream(values())
                .filter(endringstype -> endringstype.value.equals(v))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Fant ingen match for value: " + v));
    }

    public static boolean erNyopprettingEllerOppdatering(String value) {
        return EnumSet.of(Endringstype.NYOPPRETTING, Endringstype.OPPDATERING).contains(fromValue(value));
    }
}
