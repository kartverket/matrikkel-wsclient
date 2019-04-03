package no.statkart.wsclient.sdo.parser;

import no.statkart.wsclient.sdo.SDODecoder;
import no.statkart.wsclient.sdo.SDODecoderContext;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * @author Leif Lislegård
 */
public class SDOParserTest {

   @Test
   public void kanParsedoSeidSDOv1_0Dokumenter() throws Exception {
      final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sdo/eksempel-SDOv1.0.xml");
      final SDODecoder decoder = new SDODecoder(inputStream, new SDODecoderContext(false));

      Assertions.assertThat(decoder.getParsedContent())
            .containsOnlyKeys("application/pdf");


      final File pdfFile = new File(getClass().getClassLoader().getResource("sdo/eksempel-SDOv1.0.pdf").toURI());
      Assertions.assertThat(decoder.getParsedContent().values())
            .containsExactly(Files.readAllBytes(pdfFile.toPath()));
   }


   public static void main(String... args) {
      new SDOParserTest().visParsedeDokumenter("sdo/eksempel-SDOv1.0.xml");
   }

   void visParsedeDokumenter(String resourceName) {
      final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
      System.out.println(String.format("Parser %s ...", resourceName));
      final SDODecoder decoder = new SDODecoder(inputStream, new SDODecoderContext(false));
      for (Map.Entry<String, byte[]> entry : decoder.getParsedContent().entrySet()) {
         try {
            String extension = ".tmp";
            if (entry.getKey().contains("/")) {
               final String[] split = entry.getKey().split("/");
               extension = "." + split[split.length - 1];
            }
            final Path tempFile = Files.createTempFile(null, extension);
            System.out.println("Oppretter temp fil.. "  + tempFile);
            Files.write(tempFile, entry.getValue());

            System.out.println(" parset MIME type: " + entry.getKey());
            System.out.println(" åpner fil...");
            Desktop.getDesktop().open(tempFile.toFile());
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      System.out.println(String.format("Antall dokumenter funnet: %s", decoder.getParsedContent().size()));
   }

}
