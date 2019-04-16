package no.statkart.wsclient.stedsnavn.store.impl;

import no.statkart.stedsnavn.ssr.wsapi.v1.service.store.ServiceException;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.store.StoreService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.store.StoreServiceWS;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.stedsnavn.StedsnavnBoble;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;
import no.statkart.wsclient.stedsnavn.map.Mapper;
import no.statkart.wsclient.stedsnavn.store.StedsnavnStoreService;

import java.util.List;

public class DefaultStedsnavnStoreService implements StedsnavnStoreService {

    private static StoreServiceWS webserviceClient;
    private final StoreService webservice;

    public DefaultStedsnavnStoreService(final String brukernavn, final String passord, final String endpointUrl) {
        if (webserviceClient == null) {
            synchronized (this) {
                if (webserviceClient == null) {
                    webserviceClient = new StoreServiceWS();
                }
            }
        }
        webservice = WebServiceBuilder.builderv2(webserviceClient.getStoreServicePort(), StoreService.class)
                .withBruker(brukernavn)
                .withPassord(passord)
                .withEndpointUrl(endpointUrl)
                .doCreateProxy()
                .build();
    }

    @Override
    public StedsnavnBoble getObject(StedsnavnBobleId id, StedsnavnContext stedsnavnContext) {
        try {
            return Mapper.toDomainObject(webservice.getObject(Mapper.toWsBobleId(id), Mapper.toWsCtx(stedsnavnContext)));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StedsnavnBoble> getObjects(List<StedsnavnBobleId> ids, StedsnavnContext stedsnavnContext) {
        try {
            return Mapper.toDomainObjects(webservice.getObjects(Mapper.toWsBobleIds(ids), Mapper.toWsCtx(stedsnavnContext)));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
