package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Avvisningsinformasjon {

   private List<Kontrollresultat> kontrollresultater = Lists.newArrayList();

   public List<Kontrollresultat> getKontrollresultater() {
      return kontrollresultater;
   }

   public void setKontrollresultater(List<Kontrollresultat> kontrollresultater) {
      this.kontrollresultater = kontrollresultater;
   }
}
