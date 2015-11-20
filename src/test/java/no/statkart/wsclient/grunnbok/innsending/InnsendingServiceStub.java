package no.statkart.wsclient.grunnbok.innsending;

import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelsesstatus;
import no.kartverket.grunnbok.wsapi.v2.service.innsending.InnsendingService;
import no.kartverket.grunnbok.wsapi.v2.service.innsending.ServiceException;
import no.statkart.wsclient.grunnbok.innsending.ws.builder.ForsendelsesstatusBuilder;

import javax.jws.WebService;

/**
 *
 */
@WebService(
      serviceName = "InnsendingServiceWS",
      portName = "InnsendingServicePort",
      targetNamespace = "http://kartverket.no/grunnbok/wsapi/v2/service/innsending",
      wsdlLocation = "innsending/InnsendingServiceWS.wsdl",
      endpointInterface = "no.kartverket.grunnbok.wsapi.v2.service.innsending.InnsendingService")
public class InnsendingServiceStub implements InnsendingService {

   @Override
   public Forsendelsesstatus sendTilTinglysing(Forsendelse forsendelse) throws ServiceException {
      return ForsendelsesstatusBuilder.defaultForsendelsesstatus().build();
   }

   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) throws ServiceException {
      return null;
   }

   @Override
   public Forsendelsesstatus hentStatus(String innsendingId) throws ServiceException {
      return null;
   }

}
