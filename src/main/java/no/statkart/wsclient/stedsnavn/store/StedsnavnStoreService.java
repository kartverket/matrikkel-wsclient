package no.statkart.wsclient.stedsnavn.store;

import no.statkart.wsclient.stedsnavn.StedsnavnBoble;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;
import no.statkart.wsclient.stedsnavn.StedsnavnContext;

import java.util.List;

public interface StedsnavnStoreService {

    StedsnavnBoble getObject(StedsnavnBobleId id, StedsnavnContext stedsnavnContext);

    List<StedsnavnBoble> getObjects(List<StedsnavnBobleId> ids, StedsnavnContext stedsnavnContext);
}
