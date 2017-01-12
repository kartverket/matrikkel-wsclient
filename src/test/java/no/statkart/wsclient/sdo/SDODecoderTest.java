package no.statkart.wsclient.sdo;

import com.google.common.base.Charsets;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SDODecoderTest {




   /**
    * Verifiserer at base64 decoding fungerer
    */
   @Test
   public void base64DecodingKanParameteriseres() throws Exception {
      Assertions.assertThat(decodedPayloadAsString("PD94bWwgdmVyc2lvbj0iMS4wIiA/Pjxyb290PnRlc3RQYXlsb2FkPC9yb290Pg==", new SDODecoderContext(true)))
            .isEqualTo("<?xml version=\"1.0\" ?><root>testPayload</root>");

      Assertions.assertThat(decodedPayloadAsString("<?xml version=\"1.0\" ?><root>testPayload</root>", new SDODecoderContext(false)))
            .isEqualTo("<?xml version=\"1.0\" ?><root>testPayload</root>");
   }


   private String decodedPayloadAsString(String payload, SDODecoderContext context) throws IOException {
      InputStream inputStream = new ByteArrayInputStream(payload.getBytes(context.getCharset()));
      final SDODecoder SDODecoder = new SDODecoder(inputStream, context);

      return SDODecoder.decodedInput().asCharSource(Charsets.UTF_8).read();
   }

}