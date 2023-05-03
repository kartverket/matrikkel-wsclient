package no.statkart.wsclient.sdo.parsers;

import com.google.common.io.ByteSource;

public abstract class SDOParser {

    transient boolean matched;
    transient String mimeType;
    transient byte[] utskrift;


    public abstract boolean parse(ByteSource source) throws Exception;


    public String getMimeType() {
        return mimeType;
    }

    public byte[] getUtskrift() {
        return utskrift;
    }

}

