package no.statkart.wsclient.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Set;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        logToSystemOut(context);
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
                logger.debug("\nOutbound message: {}", os.toString());
            } else {
                logger.debug("\nInbound message: {}", os.toString());
            }
        } catch (Exception e) {
            logger.error("Exception in handler: {}", e.getMessage(), e);
        }
    }
}
