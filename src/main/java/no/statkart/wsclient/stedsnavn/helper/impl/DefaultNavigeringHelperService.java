package no.statkart.wsclient.stedsnavn.helper.impl;

import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.sted.StedIdList;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnId;
import no.statkart.stedsnavn.ssr.wsapi.v1.domain.stedsnavn.StedsnavnIdList;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.servicetyper.StedIdTilStedsnavnIdsMap;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.servicetyper.StedsnavnIdTilSkrivemaateIdsMap;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.skrivemaate.SkrivemaateService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.skrivemaate.SkrivemaateServiceWS;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.stedsnavn.StedsnavnService;
import no.statkart.stedsnavn.ssr.wsapi.v1.service.stedsnavn.StedsnavnServiceWS;
import no.statkart.wsclient.WebServiceBuilder;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;
import no.statkart.wsclient.stedsnavn.helper.NavigeringHelperService;
import no.statkart.wsclient.stedsnavn.map.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultNavigeringHelperService implements NavigeringHelperService {

    private static StedsnavnServiceWS stedsnavnWebServiceClient;
    private final StedsnavnService stedsnavnWebService;
    private static SkrivemaateServiceWS skrivemaateWebServiceClient;
    private final SkrivemaateService skrivemaateWebService;

    public DefaultNavigeringHelperService(final String brukernavn, final String passord, final String endpointUrl) {
        if (stedsnavnWebServiceClient == null) {
            synchronized (this) {
                if (stedsnavnWebServiceClient == null) {
                    stedsnavnWebServiceClient = new StedsnavnServiceWS();
                }
            }
        }
        if (skrivemaateWebServiceClient == null) {
            synchronized (this) {
                if (skrivemaateWebServiceClient == null) {
                    skrivemaateWebServiceClient = new SkrivemaateServiceWS();
                }
            }
        }
        stedsnavnWebService = WebServiceBuilder.builderv2(stedsnavnWebServiceClient.getStedsnavnServicePort(), StedsnavnService.class)
                .withBruker(brukernavn)
                .withPassord(passord)
                .withEndpointUrl(endpointUrl)
                .doCreateProxy()
                .build();

        skrivemaateWebService = WebServiceBuilder.builderv2(skrivemaateWebServiceClient.getSkrivemaateServicePort(), SkrivemaateService.class)
                .withBruker(brukernavn)
                .withPassord(passord)
                .withEndpointUrl(endpointUrl)
                .doCreateProxy()
                .build();
    }

    public Map<StedsnavnBobleId.StedId, List<StedsnavnBobleId.StedsnavnId>> getStedsnavnForSteder(List<StedsnavnBobleId.StedId> stedIds, StedsnavnContext stedsnavnContext) {
        Map<StedsnavnBobleId.StedId, List<StedsnavnBobleId.StedsnavnId>> respons = new HashMap<>();
        StedIdList list = new StedIdList();
        list.getItem().addAll(toWsStedIds(stedIds));
        try {
            no.statkart.stedsnavn.ssr.wsapi.v1.domain.StedsnavnContext wsCtx = Mapper.toWsCtx(stedsnavnContext);
            StedIdTilStedsnavnIdsMap stedsnavnForSteder = stedsnavnWebService.findStedsnavnForSteder(list, wsCtx);
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
