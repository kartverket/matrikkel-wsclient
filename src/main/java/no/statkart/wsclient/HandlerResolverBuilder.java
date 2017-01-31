package no.statkart.wsclient;

import no.statkart.wsclient.handler.LoggingHandler;
import no.statkart.wsclient.handler.WSSecurityUsernameTokenHandler;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HandlerResolverBuilder {

   private boolean enableLogging;
   private String brukernavn;
   private String passord;
   private boolean enableWSSecurity;

   List<Handler> handlerChain;

   public static HandlerResolverBuilder builder() {
      return new HandlerResolverBuilder();
   }

   public HandlerResolverBuilder enableLogging() {
      enableLogging = true;
      return this;
   }

   public HandlerResolverBuilder enableWSSecurity(String brukernavn, String passord) {
      enableWSSecurity = true;
      this.brukernavn = brukernavn;
      this.passord = passord;
      return this;
   }

   public HandlerResolver build() {
      buildHandlers();
      return new HandlerResolver() {
         @Override
         public List<Handler> getHandlerChain(PortInfo portInfo) {
            return handlerChain;
         }
      };
   }

   private void buildHandlers() {
      handlerChain = new ArrayList<>();
      if( enableLogging ) {
         handlerChain.add(new LoggingHandler());
      }
      if( enableWSSecurity ) {
         handlerChain.add(new WSSecurityUsernameTokenHandler(brukernavn, passord));
      }
   }

}
