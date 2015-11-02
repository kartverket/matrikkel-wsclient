package no.statkart.wsclient.grunnbok.innsending.domene;

import com.google.common.collect.Lists;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.List;

public class AvholdtTvangsforretning {

   private LocalDate avholdtDato;
   private LocalTime avholdtKlokkeslett;
   private List<Person> saksoekere = Lists.newArrayList();
   private List<Person> saksoekte = Lists.newArrayList();
   private List<Person> prosessfullmektiger = Lists.newArrayList();

   public LocalDate getAvholdtDato() {
      return avholdtDato;
   }

   public void setAvholdtDato(LocalDate avholdtDato) {
      this.avholdtDato = avholdtDato;
   }

   public LocalTime getAvholdtKlokkeslett() {
      return avholdtKlokkeslett;
   }

   public void setAvholdtKlokkeslett(LocalTime avholdtKlokkeslett) {
      this.avholdtKlokkeslett = avholdtKlokkeslett;
   }

   public List<Person> getProsessfullmektiger() {
      return prosessfullmektiger;
   }

   public void setProsessfullmektiger(List<Person> prosessfullmektiger) {
      this.prosessfullmektiger = prosessfullmektiger;
   }

   public List<Person> getSaksoekere() {
      return saksoekere;
   }

   public void setSaksoekere(List<Person> saksoekere) {
      this.saksoekere = saksoekere;
   }

   public List<Person> getSaksoekte() {
      return saksoekte;
   }

   public void setSaksoekte(List<Person> saksoekte) {
      this.saksoekte = saksoekte;
   }
}
