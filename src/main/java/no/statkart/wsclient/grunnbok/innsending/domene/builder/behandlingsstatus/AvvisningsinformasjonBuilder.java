package no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Avvisningsinformasjon;
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

   public Avvisningsinformasjon build() {
      Avvisningsinformasjon avvisningsinformasjon = new Avvisningsinformasjon();
      avvisningsinformasjon.setKontrollresultater(kontrollresultater);
      return avvisningsinformasjon;
   }
}
