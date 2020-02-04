package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.skif.exception.OperationalException;
import no.statkart.wsclient.byggesak.model.ByggesakmeldingDTO;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static no.statkart.wsclient.byggesak.BuildString.buildStringFromStream;

/**
 * En mockup-implementasjon for å simulere flyten fra FIKS.
 * Brukes i tester da vanlige tester ikke kan gå direkte mot FIKS.
 */
public class MockFiksIntegrasjonService implements FiksIntegrasjonService {

   private Map<String, ByggesakmeldingDTO> mockupBaseForMeldinger = new HashMap<>();

   public MockFiksIntegrasjonService(int antallMeldinger, URL byggesakEksempel) {
      createMeldingerWithRandomId(antallMeldinger, byggesakEksempel);
   }

   @Override
   public Set<ByggesakmeldingDTO> hentForsendelser(Set<String> forsendelseIds, URL privateKeyUrl) {
      return mockupBaseForMeldinger.values().stream()
            .filter(melding -> !forsendelseIds.contains(melding.getForsendelsesId()))
            .collect(Collectors.toSet());
   }

   @Override
   public boolean sjekkOmForsendelseErTilgjengelig(String forsendelseId) {
      return mockupBaseForMeldinger.get(forsendelseId) != null;
   }

   @Override
   public boolean kvitterMelding(String forsendelsesId) {
      if(mockupBaseForMeldinger.get(forsendelsesId) != null) {
         mockupBaseForMeldinger.remove(forsendelsesId);
         return true;
      }
      return false;
   }

   private void createMeldingerWithRandomId(int antallMeldinger, URL byggesakEksempel) {
      String byggesakXml;
      try {
         Objects.requireNonNull(byggesakEksempel, "Fil ikke funnet.");
         byggesakXml = buildStringFromStream(byggesakEksempel.openStream(), StandardCharsets.UTF_8);
      } catch (IOException e) {
         throw new OperationalException("Opprettelse av byggesak-xml feilet");
      }

      for(int i = 0; i < antallMeldinger; i++) {
         ResponsMelding responsMelding = new ResponsMelding(byggesakXml);
         responsMelding.setForsendelseId(String.valueOf(new Random().nextInt()));
         responsMelding.setKommunenr("100000201");
         responsMelding.setDate(new Date());
         responsMelding.setTittel("Eksempel-byggesak for test-rammeverk "+responsMelding.getForsendelseId());

         Set<ByggesakmeldingDTO> meldinger = ByggesakmeldingerDTOBuilder.build(Collections.singleton(responsMelding));
         mockupBaseForMeldinger.put(responsMelding.getForsendelseId(), meldinger.iterator().next());
      }
   }
}

