package no.statkart.wsclient.grunnbokv2.innsending.validate;

import no.statkart.wsclient.grunnbokv2.innsending.InnsendingServiceMapper;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @since 3.12
 */
public class InnsendingValidator {

    private static final JAXBContext jaxbContext;
    private static final Marshaller marshaller;
    private static Schema schema;

    static {
        try {
            jaxbContext = JAXBContext.newInstance("no.kartverket.grunnbok.wsapi.v2.domain.innsending");
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setSchema(null);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new StreamSource(Thread.currentThread().getContextClassLoader().getResource("innsending/schema/innsending.xsd").openStream()));
        } catch (JAXBException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void validateForsendelseXml(Forsendelse forsendelse) throws JAXBException, IOException, SAXException {
        InnsendingServiceMapper mapper = InnsendingServiceMapper.getInstance();
        no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse jaxbForsendelse
            = mapper.getMapping().d2w(forsendelse, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse.class);

        JAXBElement<no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse> rootElement =
            new JAXBElement<>(
                new QName("http://kartverket.no/grunnbok/wsapi/v2/domain/innsending", "forsendelse"),
                no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse.class,
                jaxbForsendelse);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        marshaller.marshal(rootElement, bos);
        byte[] xmlBytes = bos.toByteArray();
        try {
            schema.newValidator().validate(new StreamSource(new ByteArrayInputStream(xmlBytes)));
        } catch (SAXParseException e) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(xmlBytes), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            sb.append("An ").append(e.getClass().getName()).append(" was caught:\n");
            sb.append(e.toString());
            String readLine;
            int lineNumber = 1;
            while ((readLine = reader.readLine()) != null) {
                sb.append('\t');
                sb.append(readLine);
                sb.append('\n');
                if (e.getLineNumber() == lineNumber) {
                    for (int j = 1; j < e.getColumnNumber(); ++j) {
                        sb.append(' ');
                    }
                    sb.append("^--- HERE ---");
                    sb.append('\n');
                    break;
                } else {
                    ++lineNumber;
                }
            }
            throw new IllegalArgumentException(sb.toString());
//         fail(sb.toString());
        }
    }

}
