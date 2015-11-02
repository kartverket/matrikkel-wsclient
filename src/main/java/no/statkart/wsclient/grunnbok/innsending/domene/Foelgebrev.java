package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.collect.Lists;

import java.util.List;

public class Foelgebrev {

   private String innsendersIdentifikasjonsnummer;
   private List<Referanse> dokumentrekkefoelge = Lists.newArrayList();

   public List<Referanse> getDokumentrekkefoelge() {
      return dokumentrekkefoelge;
   }

   public void setDokumentrekkefoelge(List<Referanse> dokumentrekkefoelge) {
      this.dokumentrekkefoelge = dokumentrekkefoelge;
   }

   public String getInnsendersIdentifikasjonsnummer() {
      return innsendersIdentifikasjonsnummer;
   }

   public void setInnsendersIdentifikasjonsnummer(String innsendersIdentifikasjonsnummer) {
      this.innsendersIdentifikasjonsnummer = innsendersIdentifikasjonsnummer;
   }
}
