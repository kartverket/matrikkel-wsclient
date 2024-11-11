package no.statkart.wsclient.stedsnavn.endringslogg;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.EndringList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.endringslogg.Endringer;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedIdList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnIdList;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.endringslogg.EndringsloggService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.endringslogg.EndringsloggServiceWS;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.endringslogg.ServiceException;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.servicetyper.StedIdTilStedsnavnIdsMap;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.servicetyper.StedsnavnIdTilSkrivemaateIdsMap;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.skrivemaate.SkrivemaateService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.skrivemaate.SkrivemaateServiceWS;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.stedsnavn.StedsnavnService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.stedsnavn.StedsnavnServiceWS;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.store.StoreService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.store.StoreServiceWS;
import no.statkart.wsclient.HandlerResolverBuilder;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.stedsnavn.StedsnavnBoble;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;
import no.statkart.wsclient.stedsnavn.map.Mapper;

import jakarta.xml.ws.handler.HandlerResolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static no.statkart.wsclient.stedsnavn.StedsnavnBobleId.EndringId;

public class DefaultStedsnavnEndringsloggService implements StedsnavnEndringsloggService {

    private static EndringsloggServiceWS endringsloggWebserviceClient;
    private final EndringsloggService endringsloggWebservice;
    private static StedsnavnServiceWS stedsnavnWebServiceClient;
    private final StedsnavnService stedsnavnWebService;
    private static SkrivemaateServiceWS skrivemaateWebServiceClient;
    private final SkrivemaateService skrivemaateWebService;
    private static StoreServiceWS storeServiceWebserviceClient;
    private final StoreService storeServiceWebservice;

    public DefaultStedsnavnEndringsloggService(StedsnavnEndringsloggInitParams initParams) {
        HandlerResolver handlerResolverMedLogging = null;
        if (initParams.enableLoggingAvWS) {
            handlerResolverMedLogging = HandlerResolverBuilder.handlerResolverMedLogging();
        }

        if (endringsloggWebserviceClient == null) {
            synchronized (this) {
                if (endringsloggWebserviceClient == null) {
                    endringsloggWebserviceClient = new EndringsloggServiceWS();
                    if (initParams.enableLoggingAvWS) {
                        endringsloggWebserviceClient.setHandlerResolver(handlerResolverMedLogging);
                    }
                }
            }
        }
        if (stedsnavnWebServiceClient == null) {
            synchronized (this) {
                if (stedsnavnWebServiceClient == null) {
                    stedsnavnWebServiceClient = new StedsnavnServiceWS();
                    if (initParams.enableLoggingAvWS) {
                        stedsnavnWebServiceClient.setHandlerResolver(handlerResolverMedLogging);
                    }
                }
            }
        }
        if (skrivemaateWebServiceClient == null) {
            synchronized (this) {
                if (skrivemaateWebServiceClient == null) {
                    skrivemaateWebServiceClient = new SkrivemaateServiceWS();
                    if (initParams.enableLoggingAvWS) {
                        skrivemaateWebServiceClient.setHandlerResolver(handlerResolverMedLogging);
                    }
                }
            }
        }
        if (storeServiceWebserviceClient == null) {
            synchronized (this) {
                if (storeServiceWebserviceClient == null) {
                    storeServiceWebserviceClient = new StoreServiceWS();
                    if (initParams.enableLoggingAvWS) {
                        storeServiceWebserviceClient.setHandlerResolver(handlerResolverMedLogging);
                    }
                }
            }
        }

        String brukernavn = initParams.brukernavn;
        String passord = initParams.passord;

        endringsloggWebservice = WebServiceBuilder.builderv2(endringsloggWebserviceClient.getEndringsloggServicePort(), EndringsloggService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(initParams.endringsloggEndpointUrl)
            .build();

        stedsnavnWebService = WebServiceBuilder.builderv2(stedsnavnWebServiceClient.getStedsnavnServicePort(), StedsnavnService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(initParams.stedsnavnEndpointUrl)
            .build();

        skrivemaateWebService = WebServiceBuilder.builderv2(skrivemaateWebServiceClient.getSkrivemaateServicePort(), SkrivemaateService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(initParams.skrivemaateEndpointUrl)
            .build();
        storeServiceWebservice = WebServiceBuilder.builderv2(storeServiceWebserviceClient.getStoreServicePort(), StoreService.class)
            .withBruker(brukernavn)
            .withPassord(passord)
            .withEndpointUrl(initParams.storeServiceEndpointUrl)
            .build();
    }

    @Override
    public EndringId findSisteEndringId(StedsnavnContext stedsnavnContext) {
        try {
            return new EndringId(endringsloggWebservice.findSisteEndringId(Mapper.toWsCtx(stedsnavnContext)).getValue());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EndringerRespons findEndringerForSted(EndringId id, int maksAntall, StedsnavnContext stedsnavnContext) {
        try {
            Endringer endringer = endringsloggWebservice.findEndringer(Mapper.toWsEndringId(id),
                Mapper.toWsDomainklasse(Domainklasse.STEDSNAVN_BUBBLE_OBJECT), "",
                Mapper.toWsReturnerBobler(ReturnerBobler.ALDRI), maksAntall, Mapper.toWsCtx(stedsnavnContext));

            EndringList relevantEndringList = new EndringList();
            relevantEndringList.getItem().addAll(endringer.getEndringList().getItem().stream()
                .filter(wsEndring -> Mapper.bobleErStedStedsnavnEllerSkrivemaate(wsEndring.getEndretBubbleId()))
                .filter(wsEndring -> Endringstype.erNyopprettingEllerOppdatering(wsEndring.getEndringstype().value()))
                .collect(Collectors.toList()));
            endringer.setEndringList(relevantEndringList);

            return Mapper.mapEndringerRespons(endringer);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<StedsnavnBobleId.StedId, List<StedsnavnBobleId.StedsnavnId>> getStedsnavnForSteder(List<StedsnavnBobleId.StedId> stedIds, StedsnavnContext stedsnavnContext) {
        Map<StedsnavnBobleId.StedId, List<StedsnavnBobleId.StedsnavnId>> respons = new HashMap<>();
        try {
            StedIdList stedIdList = new StedIdList();
            stedIdList.getItem().addAll(toWsStedIds(stedIds));
            StedIdTilStedsnavnIdsMap stedsnavnForSteder = stedsnavnWebService.findStedsnavnForSteder(stedIdList, Mapper.toWsCtx(stedsnavnContext));
            stedsnavnForSteder.getEntry().forEach(entry -> {
                    StedId stedId = entry.getKey();
                    List<StedsnavnBobleId.StedsnavnId> stedsnavnIds = entry.getValue().getItem().stream()
                        .map(stedsnavnId -> new StedsnavnBobleId.StedsnavnId(stedsnavnId.getValue()))
                        .collect(Collectors.toList());

                    respons.put(new StedsnavnBobleId.StedId(stedId.getValue()), stedsnavnIds);
                }
            );
        } catch (no.statkart.stedsnavn.ssr.wsapi.v1.service.stedsnavn.ServiceException e) {
            throw new RuntimeException(e);
        }
        return respons;
    }

    public Map<StedsnavnBobleId.StedsnavnId, List<StedsnavnBobleId.SkrivemaateId>> getSkrivemaaterForStedsnavn(List<StedsnavnBobleId.StedsnavnId> stedsnavnIds, StedsnavnContext wsCtx) {
        Map<StedsnavnBobleId.StedsnavnId, List<StedsnavnBobleId.SkrivemaateId>> respons = new HashMap<>();
        try {
            StedsnavnIdList stedsnavnIdList = new StedsnavnIdList();
            stedsnavnIdList.getItem().addAll(toWsStedsnavnIds(stedsnavnIds));
            StedsnavnIdTilSkrivemaateIdsMap skrivemaaterForStedsnavn = skrivemaateWebService.findSkrivemaaterForStedsnavnList(stedsnavnIdList, Mapper.toWsCtx(wsCtx));
            skrivemaaterForStedsnavn.getEntry().forEach(entry -> {
                StedsnavnId stedsnavnId = entry.getKey();
                List<StedsnavnBobleId.SkrivemaateId> skrivemaateIds = entry.getValue().getItem().stream()
                    .map(skrivemaateId -> new StedsnavnBobleId.SkrivemaateId(skrivemaateId.getValue()))
                    .collect(Collectors.toList());

                respons.put(new StedsnavnBobleId.StedsnavnId(stedsnavnId.getValue()), skrivemaateIds);
            });

        } catch (no.statkart.stedsnavn.ssr.wsapi.v1.service.skrivemaate.ServiceException e) {
            throw new RuntimeException(e);
        }
        return respons;
    }

    @Override
    public StedsnavnBoble getObject(StedsnavnBobleId id, StedsnavnContext stedsnavnContext) {
        try {
            return Mapper.toDomainObject(storeServiceWebservice.getObject(Mapper.toWsBobleId(id), Mapper.toWsCtx(stedsnavnContext)));
        } catch (no.statkart.stedsnavn.ssr.wsapi.v1.service.store.ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StedsnavnBoble> getObjects(List<StedsnavnBobleId> ids, StedsnavnContext stedsnavnContext) {
        try {
            return Mapper.toDomainObjects(storeServiceWebservice.getObjects(Mapper.toWsBobleIds(ids), Mapper.toWsCtx(stedsnavnContext)));
        } catch (no.statkart.stedsnavn.ssr.wsapi.v1.service.store.ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    private List<StedId> toWsStedIds(List<StedsnavnBobleId.StedId> stedIds) {
        return stedIds.stream()
            .map(domainId -> {
                StedId wsId = new StedId();
                wsId.setValue(domainId.getValue());
                return wsId;
            }).collect(Collectors.toList());
    }

    private List<StedsnavnId> toWsStedsnavnIds(List<StedsnavnBobleId.StedsnavnId> stedsnavnIds) {
        return stedsnavnIds.stream()
            .map(domainId -> {
                StedsnavnId wsId = new StedsnavnId();
                wsId.setValue(domainId.getValue());
                return wsId;
            }).collect(Collectors.toList());
    }

}
