package no.statkart.wsclient.grunnbok.internutskrift;

import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v1.domain.basistyper.Timestamp;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.MatrikkelenhetId;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.MatrikkelenhetIdent;
import no.kartverket.grunnbok.wsapi.v1.domain.register.registerenhet.MatrikkelenhetIdentList;
import no.kartverket.grunnbok.wsapi.v1.service.exception.ServiceException;
import no.kartverket.grunnbok.wsapi.v1.service.servicetyper.MatrikkelenhetIdentTilMatrikkelenhetIdMap;
import no.statkart.skif.exception.ImplementationException;
import no.statkart.skif.exception.OperationalException;
import no.statkart.skif.util.NullHostnameVerifier;
import no.statkart.wsclient.grunnbok.ident.DefaultIdentWS;
import no.statkart.wsclient.grunnbok.ident.IdentWS;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collections;
import java.util.Iterator;

public class GrunnbokHelper {
   private final transient IntegrationTestProperties config = new IntegrationTestProperties();
   private IdentWS identService;


   GrunnbokHelper() {
      setup();
   }

   static MatrikkelenhetIdent matrikkelenhetIdent(String kommunenummer, Number gardsnummer, Number bruksnummer) {
      final MatrikkelenhetIdent matrikkelenhetIdent = new MatrikkelenhetIdent();
      matrikkelenhetIdent.setKommunenummer(kommunenummer);
      matrikkelenhetIdent.setGaardsnummer(gardsnummer != null ? gardsnummer.intValue() : 0);
      matrikkelenhetIdent.setBruksnummer(bruksnummer != null ? bruksnummer.intValue() : 0);
      return matrikkelenhetIdent;
   }

   static MatrikkelenhetIdentList matrikkelenhetIdentList(MatrikkelenhetIdent idents) {
      final MatrikkelenhetIdentList identList = new MatrikkelenhetIdentList();
      Collections.addAll(identList.getItem(), idents);
      return identList;
   }

   public GrunnbokContext context() {
      GrunnbokContext context = new GrunnbokContext();
      context.setApiVersion(config.getGrunnbokApiVersion());
      context.setClientIdentification(config.getClientIdentification());
      context.setLocale("nb_NO");
      context.setSnapshotVersion(CURRENT());
      return context;
   }

   private static Timestamp CURRENT() {
      try {
         XMLGregorianCalendar date = DatatypeFactory.newInstance()
               .newXMLGregorianCalendar(9999, 1, 1, 0, 0, 0, 0, 1);
         Timestamp value = new Timestamp();
         value.setTimestamp(date);
         return value;
      } catch (DatatypeConfigurationException e) {
         throw new OperationalException("Kunne ikke opprette XML Calendar", e);
      }
   }

   public MatrikkelenhetId findMatrikkelenhetId(MatrikkelenhetIdent matrikkelenhetIdent) {
      try {
         final MatrikkelenhetIdentTilMatrikkelenhetIdMap matrikkelenhetIdsForIdents = identService.findMatrikkelenhetIdsForIdents(matrikkelenhetIdentList(matrikkelenhetIdent), context());
         final Iterator<MatrikkelenhetIdentTilMatrikkelenhetIdMap.Entry> iterator = matrikkelenhetIdsForIdents.getEntry().iterator();
         if (iterator.hasNext()) {
            return iterator.next().getValue();
         }
         return null;
      } catch (ServiceException e) {
         throw new ImplementationException("Feil med testdata!?", e);
      }
   }

   private void setup() {
      final String grunnbokUser = config.getGrunnbokUser();
      final String grunnbokPassword = config.getGrunnbokPassword();

      if (identService == null) {
         identService = new DefaultIdentWS(grunnbokUser, grunnbokPassword, config.getIdentServiceServiceUrl(), new NullHostnameVerifier());
      }

   }
}
