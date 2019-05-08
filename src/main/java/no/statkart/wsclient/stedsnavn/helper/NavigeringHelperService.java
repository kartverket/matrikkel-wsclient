package no.statkart.wsclient.stedsnavn.helper;

import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;

import java.util.List;
import java.util.Map;

public interface NavigeringHelperService {

    Map<StedsnavnBobleId.StedId, List<StedsnavnBobleId.StedsnavnId>> getStedsnavnForSteder(List<StedsnavnBobleId.StedId> stedIds, StedsnavnContext stedsnavnContext);

    Map<StedsnavnBobleId.StedsnavnId, List<StedsnavnBobleId.SkrivemaateId>> getSkrivemaaterForStedsnavn(List<StedsnavnBobleId.StedsnavnId> stedsnavnIds, StedsnavnContext wsCtx);

}
