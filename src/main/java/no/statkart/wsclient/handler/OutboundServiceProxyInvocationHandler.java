package no.statkart.wsclient.handler;

import com.sun.xml.ws.client.ClientTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.WebServiceException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Klasse som forsøker et kall til en tjenste opptil flere ganger, dersom den får noen feil, som er mulig at serveren man
 * kaller mot kan hente seg inn.
 */
public class OutboundServiceProxyInvocationHandler implements InvocationHandler {
   private static final Logger logger = LoggerFactory.getLogger(OutboundServiceProxyInvocationHandler.class);

   private Object service;
   private int retries;
   private int sleepTime;

   public OutboundServiceProxyInvocationHandler(Object service, int retries) {
      this.service = service;
      this.retries = retries;
   }

   public OutboundServiceProxyInvocationHandler(Object service, int retries, int sleepTime) {
      this.service = service;
      this.retries = retries;
      this.sleepTime = sleepTime;
   }

   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      int forsok = 0;
      Exception exception;
      do {
         forsok++;
         try {
            return method.invoke(service, args);
         } catch (UndeclaredThrowableException e) {
            throw e.getCause();
         } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof ClientTransportException) {
               logger.error(method.getDeclaringClass().getSimpleName() + "." + method.getName() + "() feilet på forsøk: " + forsok, t);
               exception = (Exception) t;
            } else if (t instanceof WebServiceException) {
               Throwable cause = t.getCause();
               if (cause instanceof IOException) {
                  logger.error(method.getDeclaringClass().getSimpleName() + "." + method.getName() + "() feilet på forsøk: " + forsok, t);
                  exception = (Exception) t;
               } else {
                  throw t;
               }
            } else {
               throw t;
            }
         } catch (ClientTransportException e) {
            logger.error(method.getDeclaringClass().getSimpleName() + "." + method.getName() + "() feilet på forsøk: " + forsok, e);
            exception = e;
         } catch (WebServiceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
               logger.error(method.getDeclaringClass().getSimpleName() + "." + method.getName() + "() feilet på forsøk: " + forsok, e);
               exception = e;
            } else {
               throw e;
            }
         }

         //Hvis vi trenger å legge inn en delay mellom hvert kall, så gjør det her.
         try {
            Thread.sleep(sleepTime);
         } catch (InterruptedException e) {
            logger.warn("Thread sleep interrupted", e);
         }

      } while (forsok <= retries);

      //Hvis vi kommer hit så har vi gjort 1 + retries forsøk, som alle har feilet. Da sender vi exception ut.
      throw exception;
   }
}
