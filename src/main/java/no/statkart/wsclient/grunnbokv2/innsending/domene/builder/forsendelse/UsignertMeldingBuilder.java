package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Foelgebrev;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertMelding;

import java.util.List;

public class UsignertMeldingBuilder {
   private List<Dokument> dokumenter = Lists.newArrayList();
   private Foelgebrev foelgebrev;

   private UsignertMeldingBuilder() {
   }

   public static UsignertMeldingBuilder anUsignertMelding() {
      return new UsignertMeldingBuilder();
   }

   public UsignertMeldingBuilder withDokumenter(List<Dokument> dokumenter) {
      this.dokumenter = dokumenter;
      return this;
   }

   public UsignertMeldingBuilder withFoelgebrev(Foelgebrev foelgebrev) {
      this.foelgebrev = foelgebrev;
      return this;
   }

   public UsignertMeldingBuilder but() {
      return anUsignertMelding().withDokumenter(dokumenter).withFoelgebrev(foelgebrev);
   }

   public UsignertMelding build() {
      UsignertMelding usignertMelding = new UsignertMelding();
      usignertMelding.getDokumenter().addAll(dokumenter);
      usignertMelding.setFoelgebrev(foelgebrev);
      return usignertMelding;
   }
}
