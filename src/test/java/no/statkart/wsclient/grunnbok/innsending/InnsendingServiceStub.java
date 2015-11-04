package no.statkart.wsclient.grunnbok.innsending;

import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Behandlingsstatus;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Forsendelse;
import no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingService;
import no.kartverket.grunnbok.wsapi.internal.service.innsending.ServiceException;
import no.statkart.wsclient.grunnbok.innsending.ws.builder.BehandlingsstatusBuilder;

import javax.jws.WebService;

/**
 *
 */
@WebService(
      serviceName = "InnsendingServiceWS",
      portName = "InnsendingServicePort",
      targetNamespace = "http://kartverket.no/grunnbok/wsapi/internal/service/innsending",
      wsdlLocation = "innsending/InnsendingServiceWS.wsdl",
      endpointInterface = "no.kartverket.grunnbok.wsapi.internal.service.innsending.InnsendingService")
public class InnsendingServiceStub implements InnsendingService {

   @Override
   public Behandlingsstatus tinglysMelding(Forsendelse forsendelse) throws ServiceException {
      return BehandlingsstatusBuilder.defaultBehandlingsstatus().build();
   }

   @Override
   public Behandlingsstatus validerMelding(Forsendelse forsendelse) throws ServiceException {
      return null;
   }

   @Override
   public Behandlingsstatus findBehandlingsstatus(String innsendingId) throws ServiceException {
      return null;
   }

   @Override
   public String allokerInnsendingId() throws ServiceException {
      return "15";
   }
}
