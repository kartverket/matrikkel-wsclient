package no.statkart.wsclient.sdo.parsers;

import com.google.common.io.ByteSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public class SeidSDOv1_0Parser extends SDOParser {
    final XPath xpath = XPathFactory.newInstance().newXPath();
    final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

    public SeidSDOv1_0Parser() {
        domFactory.setNamespaceAware(false);
    }

    public boolean parse(ByteSource source) throws Exception {
        matched = false;

        final DocumentBuilder builder = domFactory.newDocumentBuilder();

        try (InputStream inputStream = source.openStream()) {
            final Document document = builder.parse(new InputSource(inputStream));
            try {
                String signersDocument = (String) xpath.evaluate("SDOList/SDO/SignedObject/SignersDocument", document, XPathConstants.STRING);
                utskrift = DatatypeConverter.parseBase64Binary(signersDocument);

                mimeType = (String) xpath.evaluate("SDOList/SDO/SDODataPart/SignatureElement/CMSSignatureElement/SignersDocumentFormat/MimeType", document, XPathConstants.STRING);

                matched = true;
            } catch (NullPointerException e) {
                return false;
            }
        }

        return matched;
    }


}
