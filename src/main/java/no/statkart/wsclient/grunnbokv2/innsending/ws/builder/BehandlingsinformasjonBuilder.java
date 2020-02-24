package no.statkart.wsclient.grunnbokv2.innsending.ws.builder;

import com.google.common.collect.Lists;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Behandlingsinformasjon;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Kontrollresultat;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.KontrollresultatList;

import java.util.List;

public class BehandlingsinformasjonBuilder {
   protected List<Kontrollresultat> kontrollresultater = Lists.newArrayList();

   private BehandlingsinformasjonBuilder() {
   }

   public static BehandlingsinformasjonBuilder anAvvisningsinformasjon() {
      return new BehandlingsinformasjonBuilder();
   }

   public BehandlingsinformasjonBuilder withKontrollresultater(List<Kontrollresultat> kontrollresultater) {
      this.kontrollresultater = kontrollresultater;
      return this;
   }

   public BehandlingsinformasjonBuilder but() {
      return anAvvisningsinformasjon().withKontrollresultater(kontrollresultater);
   }

   public Behandlingsinformasjon build() {
      Behandlingsinformasjon avvisningsinformasjon = new Behandlingsinformasjon();
      KontrollresultatList kontrollresultatList = new KontrollresultatList();
      kontrollresultatList.getKontrollresultat().addAll(kontrollresultater);
      avvisningsinformasjon.setKontrollresultater(kontrollresultatList);
      return avvisningsinformasjon;
   }
}
