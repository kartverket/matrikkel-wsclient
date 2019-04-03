package no.statkart.wsclient.sdo;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import no.statkart.skif.exception.ImplementationException;
import no.statkart.wsclient.sdo.parsers.SDOParser;
import no.statkart.wsclient.sdo.parsers.SeidSDOv1_0Parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class SDODecoder {
   static final List<? extends Class<? extends SDOParser>> PARSERS = Arrays.asList(
         SeidSDOv1_0Parser.class
   );

   final SDODecoderContext context;
   final InputStream inputStream;

   byte[] rawBytes;
   final Map<String, byte[]> content = new LinkedHashMap<>();

   /**
    * @param inputStream ikke null
    * @param context ikke null
    * @throws InvalidSDOException, hvis innholdet ikke kan parses som en sdo
    */
   public SDODecoder(InputStream inputStream, SDODecoderContext context) throws InvalidSDOException{
      this.context = checkNotNull(context);
      this.inputStream = checkNotNull(inputStream);
      try {
         processContent();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void processContent() throws IOException {
      rawBytes = ByteStreams.toByteArray(inputStream);
      final ByteSource source = decodedInput();
      try {
         final String firstLine = source.asCharSource(Charsets.UTF_8).readFirstLine();
         if (firstLine != null && firstLine.startsWith("<?xml ")) {
            parseXML(source);
         } else {
            throw new Exception("Ukjent format for parsing av sdo data - forventer XML struktur!");
         }
      } catch (Throwable t) {
         throw new InvalidSDOException(t, source);
      }
   }

   private void parseXML(ByteSource source) throws Exception {
      for (Class<? extends SDOParser> parserClazz : PARSERS) {
         final SDOParser sdoParser = parserClazz.getDeclaredConstructor().newInstance();
         if (sdoParser.parse(source)) {
            content.put(sdoParser.getMimeType(), sdoParser.getUtskrift());
            return;
         }
      }

      throw new ImplementationException("Ukjent xml-struktur for SDO!");
   }

   ByteSource decodedInput() {
      return new ByteSource() {
         @Override
         public InputStream openStream() throws IOException {
            if (context.isBase64Encoded()) {
               return decodeToStream(rawBytes, context.getEncoding());
            }
            return new ByteArrayInputStream(rawBytes);
         }
      };
   }

   public static InputStream decodeToStream(byte[] content, String encoding) throws UnsupportedEncodingException {
      return new ByteArrayInputStream(decode(content, encoding).getBytes(encoding));
   }

   public static String decode(byte[] content, String targetEncoding) {
      try {
         return new String(java.util.Base64.getDecoder().decode(content), targetEncoding); //Java 8
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * PS: fungerer kun med ett dokument som kan representeres i flere mime typer.
    */
   public Map<String, byte[]> getParsedContent() {
      return content;
   }
}
