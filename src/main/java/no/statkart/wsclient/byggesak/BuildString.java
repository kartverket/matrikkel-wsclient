package no.statkart.wsclient.byggesak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Hjelpeklasse for å bygge en string fra en inputstream
 */
public class BuildString {

   public static String buildStringFromStream(InputStream inputStream) throws IOException {
      StringBuilder stringBuilder = new StringBuilder();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

      String readLine;
      while((readLine = bufferedReader.readLine()) != null) {
         stringBuilder.append(readLine);
      }
      return stringBuilder.toString();
   }
}
