package no.statkart.wsclient.grunnbok.innsending.domene.builder;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.AvholdtTvangsforretning;
import no.statkart.wsclient.grunnbok.innsending.domene.Person;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.List;

/**
 *
 */
public class AvholdtTvangsforretningBuilder {
   private LocalDate avholdtDato;
   private LocalTime avholdtKlokkeslett;
   private List<Person> saksoekere = Lists.newArrayList();
   private List<Person> saksoekte = Lists.newArrayList();
   private List<Person> prosessfullmektiger = Lists.newArrayList();

   private AvholdtTvangsforretningBuilder() {
   }

   public static AvholdtTvangsforretningBuilder anAvholdtTvangsforretning() {
      return new AvholdtTvangsforretningBuilder();
   }

   public AvholdtTvangsforretningBuilder withAvholdtDato(LocalDate avholdtDato) {
      this.avholdtDato = avholdtDato;
      return this;
   }

   public AvholdtTvangsforretningBuilder withAvholdtKlokkeslett(LocalTime avholdtKlokkeslett) {
      this.avholdtKlokkeslett = avholdtKlokkeslett;
      return this;
   }

   public AvholdtTvangsforretningBuilder withSaksoekere(List<Person> saksoekere) {
      this.saksoekere = saksoekere;
      return this;
   }

   public AvholdtTvangsforretningBuilder withSaksoekte(List<Person> saksoekte) {
      this.saksoekte = saksoekte;
      return this;
   }

   public AvholdtTvangsforretningBuilder withProsessfullmektiger(List<Person> prosessfullmektiger) {
      this.prosessfullmektiger = prosessfullmektiger;
      return this;
   }

   public AvholdtTvangsforretningBuilder but() {
      return anAvholdtTvangsforretning().withAvholdtDato(avholdtDato).withAvholdtKlokkeslett(avholdtKlokkeslett).withSaksoekere(saksoekere).withSaksoekte(saksoekte).withProsessfullmektiger(prosessfullmektiger);
   }

   public AvholdtTvangsforretning build() {
      AvholdtTvangsforretning avholdtTvangsforretning = new AvholdtTvangsforretning();
      avholdtTvangsforretning.setAvholdtDato(avholdtDato);
      avholdtTvangsforretning.setAvholdtKlokkeslett(avholdtKlokkeslett);
      avholdtTvangsforretning.setSaksoekere(saksoekere);
      avholdtTvangsforretning.setSaksoekte(saksoekte);
      avholdtTvangsforretning.setProsessfullmektiger(prosessfullmektiger);
      return avholdtTvangsforretning;
   }
}
