package no.statkart.wsclient.stedsnavn.endringslogg;

import java.util.Arrays;

public enum Endringstype {

   NYOPPRETTING("Nyoppretting"),
   TYPEENDRING("Typeendring"),
   OPPDATERING("Oppdatering"),
   SLETTING("Sletting");

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
}
