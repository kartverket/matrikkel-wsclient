package no.statkart.wsclient.byggesak;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

/**
 * Hjelpeklasse for å bygge en string fra en inputstream
 */
public class BuildString {

    public static String buildStringFromStream(InputStream inputStream, Charset charset) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        return bufferedReader.lines().collect(Collectors.joining("\n"));
    }
}
