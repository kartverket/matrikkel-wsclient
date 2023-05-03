package no.statkart.wsclient.sdo;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;

import java.io.IOException;

public class InvalidSDOException extends RuntimeException {
    @SuppressWarnings("WeakerAccess")
    static final int MAX_LENGTH = 100;

    /**
     * @param cause       rotårsak, ikke null
     * @param inputStream b64 encodede data
     */
    public InvalidSDOException(Throwable cause, ByteSource inputStream) {
        super(buildMessage(inputStream), cause);
    }

    private static String buildMessage(ByteSource inputStream) {
        try {
            final byte[] bytes = ByteStreams.toByteArray(inputStream.openStream());
            //System.arraycopy(bytes, 0, new );
            return "Innholdet er ikke en gyldig SDO '" + getLimitedString(new String(bytes, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getLimitedString(String content) {
        return content.length() > MAX_LENGTH ? content.substring(0, MAX_LENGTH) : content;
    }
}
