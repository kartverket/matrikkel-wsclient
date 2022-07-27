package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Foelgebrev;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Referanse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Saksperson;

import java.util.ArrayList;
import java.util.List;

public class FoelgebrevBuilder {
   private List<Referanse> dokumentrekkefoelge = new ArrayList<>();

    private Saksperson innsender;
   private Saksperson fakturamottaker;
   private Saksperson mottaker;

   private FoelgebrevBuilder() {
   }

   public static FoelgebrevBuilder aFoelgebrev() {
      return new FoelgebrevBuilder();
   }

   public FoelgebrevBuilder withDokumentrekkefoelge(List<Referanse> dokumentrekkefoelge) {
      this.dokumentrekkefoelge = dokumentrekkefoelge;
      return this;
   }

   public FoelgebrevBuilder withInnsender(Saksperson innsender) {
      this.innsender = innsender;
      return this;
   }

   public FoelgebrevBuilder withFakturamottaker(Saksperson fakturamottaker) {
      this.fakturamottaker = fakturamottaker;
      return this;
   }

   public FoelgebrevBuilder withMottaker(Saksperson mottaker) {
      this.mottaker = mottaker;
      return this;
   }

   public FoelgebrevBuilder but() {
      return aFoelgebrev().withDokumentrekkefoelge(dokumentrekkefoelge).withInnsender(innsender).withFakturamottaker(fakturamottaker).withMottaker(mottaker);
   }

   public Foelgebrev build() {
      Foelgebrev foelgebrev = new Foelgebrev();
      foelgebrev.getDokumentrekkefoelge().addAll(dokumentrekkefoelge);
      foelgebrev.setInnsender(innsender);
      foelgebrev.setFakturamottaker(fakturamottaker);
      foelgebrev.setMottaker(mottaker);
      return foelgebrev;
   }
}
