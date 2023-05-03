package no.statkart.wsclient.sdo;

import java.nio.charset.Charset;

public class SDODecoderContext {
    private final boolean base64Encoded;
    private final String encoding;

    public SDODecoderContext(boolean base64Encoded) {
        this.base64Encoded = base64Encoded;
        this.encoding = "UTF-8";
    }

    public boolean isBase64Encoded() {
        return base64Encoded;
    }

    public String getEncoding() {
        return encoding;
    }

    public Charset getCharset() {
        return Charset.forName(getEncoding());
    }
}
