package no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Behandlingsinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.Kontrollresultat;

import java.util.List;

/**
 *
 */
public class AvvisningsinformasjonBuilder {
   protected List<Kontrollresultat> kontrollresultater = Lists.newArrayList();

   private AvvisningsinformasjonBuilder() {
   }

   public static AvvisningsinformasjonBuilder anAvvisningsinformasjon() {
      return new AvvisningsinformasjonBuilder();
   }

   public AvvisningsinformasjonBuilder withKontrollresultater(List<Kontrollresultat> kontrollresultater) {
      this.kontrollresultater = kontrollresultater;
      return this;
   }

   public AvvisningsinformasjonBuilder but() {
      return anAvvisningsinformasjon().withKontrollresultater(kontrollresultater);
   }

   public Behandlingsinformasjon build() {
      Behandlingsinformasjon behandlingsinformasjon = new Behandlingsinformasjon();
      behandlingsinformasjon.setKontrollresultater(kontrollresultater);
      return behandlingsinformasjon;
   }
}
