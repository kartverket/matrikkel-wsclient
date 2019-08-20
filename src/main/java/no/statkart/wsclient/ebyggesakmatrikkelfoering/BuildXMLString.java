package no.statkart.wsclient.ebyggesakmatrikkelfoering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BuildXMLString {

   public static String buildStringFromURL(URL url) throws IOException {

      StringBuilder stringBuilder = new StringBuilder();

      InputStream inputStream = url.openStream();

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

      String readLine;
      while((readLine = bufferedReader.readLine()) != null) {
         stringBuilder.append(readLine);
      }

      return stringBuilder.toString();
   }
}
