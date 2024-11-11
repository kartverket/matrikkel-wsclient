package no.statkart.wsclient.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Set;

public class WSSecurityUsernameTokenHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger logger = LoggerFactory.getLogger(WSSecurityUsernameTokenHandler.class);
    private String bruker;
    private String pass;

    public WSSecurityUsernameTokenHandler(String brukernavn, String passord) {
        this.bruker = brukernavn;
        this.pass = passord;
    }

    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        //if this is a request, true for outbound messages, false for inbound
        if (isRequest) {

            try {
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                //if no header, add one
                if (soapHeader == null) {
                    soapHeader = soapEnv.addHeader();
                }

                //add a soap header, "Security"
                QName sequrity = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse");
                SOAPHeaderElement securityHeaderElement = soapHeader.addHeaderElement(sequrity);
                SOAPElement usernameTokenElement = securityHeaderElement.addChildElement("UsernameToken", "wsse");
                SOAPElement usernameElement = usernameTokenElement.addChildElement("Username", "wsse");
                SOAPElement passwordElement = usernameTokenElement.addChildElement("Password", "wsse");
                passwordElement.addAttribute(QName.valueOf("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");

                usernameElement.setValue(bruker);
                passwordElement.setValue(pass);

                soapMsg.saveChanges();

                //tracking

            } catch (SOAPException e) {
                logger.error(e.getMessage(), e);
            }
        }

        //continue other handler chain
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        logToSystemOut(context);
        return true;
    }

    public void close(MessageContext messageContext) {
    }

    private void logToSystemOut(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        SOAPMessage message = smc.getMessage();
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            message.writeTo(os);
            if (outboundProperty) {
                logger.info("\nOutbound message:" + os.toString());
            } else {
                logger.info("\nInbound message:" + os.toString());
            }
        } catch (Exception e) {
            logger.error("Exception in handler: " + e.getMessage(), e);
        }
    }
}
