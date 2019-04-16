package no.statkart.wsclient.stedsnavn.endringslogg.impl;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringer;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.endringslogg.EndringsloggService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.endringslogg.EndringsloggServiceWS;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.endringslogg.ServiceException;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;
import no.statkart.wsclient.stedsnavn.endringslogg.*;
import no.statkart.wsclient.stedsnavn.map.Mapper;

import java.util.List;

import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.EndringId;

public class DefaultStedsnavnEndringsloggService implements StedsnavnEndringsloggService {

   private static EndringsloggServiceWS webserviceClient;
   private final EndringsloggService webservice;

   public DefaultStedsnavnEndringsloggService(final String brukernavn, final String passord, final String endpointUrl) {
      if (webserviceClient == null) {
         synchronized (this) {
            if (webserviceClient == null) {
               webserviceClient = new EndringsloggServiceWS();
            }
         }
      }
      webservice = WebServiceBuilder.builderv2(webserviceClient.getEndringsloggServicePort(), EndringsloggService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(endpointUrl)
            .doCreateProxy()
            .build();
   }

   @Override
   public Kontroll calcEndringskontroll(EndringId id, Domainklasse domainklasse, String filter, int maksAntall, StedsnavnContext stedsnavnContext) {
      try {
         return Mapper.toDomainKontroll(webservice.calcEndringskontroll(Mapper.toWsEndringId(id), Mapper.toWsDomainklasse(domainklasse), filter, maksAntall, Mapper.toWsCtx(stedsnavnContext)));
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public EndringId findSisteEndringId(StedsnavnContext stedsnavnContext) {
      try {
         return new EndringId(webservice.findSisteEndringId(Mapper.toWsCtx(stedsnavnContext)).getValue());
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Kontroll calcObjektkontrollForList(List<StedsnavnBobleId> ids, Domainklasse domainklasse, StedsnavnContext stedsnavnContext) {
      try {
         return Mapper.toDomainKontroll(webservice.calcObjektkontrollForList(Mapper.toWsBobleIds(ids), Mapper.toWsDomainklasse(domainklasse), Mapper.toWsCtx(stedsnavnContext)));
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public EndringerRespons findEndringer(EndringId id, Domainklasse domainklasse, String filter, ReturnerBobler returnerBobler, int maksAntall, StedsnavnContext stedsnavnContext) {
      try {
         Endringer endringer = webservice.findEndringer(Mapper.toWsEndringId(id), Mapper.toWsDomainklasse(domainklasse), filter, Mapper.toWsReturnerBobler(returnerBobler), maksAntall, Mapper.toWsCtx(stedsnavnContext));
         return Mapper.mapEndringerRespons(endringer);
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
   }

}
