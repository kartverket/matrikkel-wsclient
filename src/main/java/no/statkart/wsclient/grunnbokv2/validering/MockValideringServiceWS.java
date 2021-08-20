package no.statkart.wsclient.grunnbokv2.validering;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Kontrollresultat;

import java.util.Collections;
import java.util.List;

import static no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.AvvisningsinformasjonBuilder.anAvvisningsinformasjon;
import static no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus.ForsendelsesstatusBuilder.aBehandlingsstatus;

public class MockValideringServiceWS implements ValideringServiceWS {
   @Override
   public Forsendelsesstatus valider(Forsendelse forsendelse) {
      List<Kontrollresultat> kontrollResultater = Collections.emptyList();
      return aBehandlingsstatus()
            .withAvvisningsinformasjon(anAvvisningsinformasjon()
                  .withKontrollresultater(kontrollResultater).build())
            .withBehandlingsutfall("UAVKLART")
            .build();
   }
}
