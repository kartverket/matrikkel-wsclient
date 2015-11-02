package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Foelgebrev;
import no.statkart.wsclient.grunnbok.innsending.domene.Referanse;

import java.util.List;

/**
 *
 */
public class FoelgebrevBuilder {
   private List<Referanse> dokumentrekkefoelge = Lists.newArrayList();
   private String innsendersIdentifikasjonsnummer;

   private FoelgebrevBuilder() {
   }

   public static FoelgebrevBuilder aFoelgebrev() {
      return new FoelgebrevBuilder();
   }

   public FoelgebrevBuilder withDokumentrekkefoelge(List<Referanse> dokumentrekkefoelge) {
      this.dokumentrekkefoelge = dokumentrekkefoelge;
      return this;
   }

   public FoelgebrevBuilder withInnsendersIdentifikasjonsnummer(String innsendersIdentifikasjonsnummer) {
      this.innsendersIdentifikasjonsnummer = innsendersIdentifikasjonsnummer;
      return this;
   }

   public FoelgebrevBuilder but() {
      return aFoelgebrev().withDokumentrekkefoelge(dokumentrekkefoelge).withInnsendersIdentifikasjonsnummer(innsendersIdentifikasjonsnummer);
   }

   public Foelgebrev build() {
      Foelgebrev foelgebrev = new Foelgebrev();
      foelgebrev.setDokumentrekkefoelge(dokumentrekkefoelge);
      foelgebrev.setInnsendersIdentifikasjonsnummer(innsendersIdentifikasjonsnummer);
      return foelgebrev;
   }
}
