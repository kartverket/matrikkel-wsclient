package no.statkart.wsclient.grunnbok.innsending.ws.builder;

import com.google.common.collect.Lists;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Avvisningsinformasjon;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kontrollresultat;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.KontrollresultatList;

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
      KontrollresultatList kontrollresultatList = new KontrollresultatList();
      kontrollresultatList.getKontrollresultat().addAll(kontrollresultater);
      avvisningsinformasjon.setKontrollresultater(kontrollresultatList);
      return avvisningsinformasjon;
   }
}
