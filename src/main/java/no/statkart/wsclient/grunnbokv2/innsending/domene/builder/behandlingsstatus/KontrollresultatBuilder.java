package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Begrunnelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Kontrollresultat;

import java.util.List;

public class KontrollresultatBuilder {
   protected List<Begrunnelse> begrunnelser = Lists.newArrayList();
   protected Integer dokumentindeks;
   protected Integer rettsstiftelsesindeks;
   protected String kodeverdi;
   protected String navn;
   protected String utfall;

   private KontrollresultatBuilder() {
   }

   public static KontrollresultatBuilder aKontrollresultat() {
      return new KontrollresultatBuilder();
   }

   public KontrollresultatBuilder withBegrunnelser(List<Begrunnelse> begrunnelser) {
      this.begrunnelser = begrunnelser;
      return this;
   }

   public KontrollresultatBuilder withDokumentindeks(Integer dokumentindeks) {
      this.dokumentindeks = dokumentindeks;
      return this;
   }

   public KontrollresultatBuilder withRettsstiftelsesindeks(Integer rettsstiftelsesindeks) {
      this.rettsstiftelsesindeks = rettsstiftelsesindeks;
      return this;
   }

   public KontrollresultatBuilder withKodeverdi(String kodeverdi) {
      this.kodeverdi = kodeverdi;
      return this;
   }

   public KontrollresultatBuilder withNavn(String navn) {
      this.navn = navn;
      return this;
   }

   public KontrollresultatBuilder withUtfall(String utfall) {
      this.utfall = utfall;
      return this;
   }

   public KontrollresultatBuilder but() {
      return aKontrollresultat().withBegrunnelser(begrunnelser).withDokumentindeks(dokumentindeks).withRettsstiftelsesindeks(rettsstiftelsesindeks).withKodeverdi(kodeverdi).withNavn(navn).withUtfall(utfall);
   }

   public Kontrollresultat build() {
      Kontrollresultat kontrollresultat = new Kontrollresultat();
      kontrollresultat.setBegrunnelser(begrunnelser);
      kontrollresultat.setDokumentindeks(dokumentindeks);
      kontrollresultat.setRettsstiftelsesindeks(rettsstiftelsesindeks);
      kontrollresultat.setKodeverdi(kodeverdi);
      kontrollresultat.setNavn(navn);
      kontrollresultat.setUtfall(utfall);
      return kontrollresultat;
   }
}
