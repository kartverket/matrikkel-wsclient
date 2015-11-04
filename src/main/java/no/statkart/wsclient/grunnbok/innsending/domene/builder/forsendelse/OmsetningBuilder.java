package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Beloep;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokumentavgiftsplikt;
import no.statkart.wsclient.grunnbok.innsending.domene.Kode;
import no.statkart.wsclient.grunnbok.innsending.domene.OmsattRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Omsetning;

import java.util.List;

/**
 *
 */
public class OmsetningBuilder {
   private Dokumentavgiftsplikt dokumentavgiftsplikt;
   private Boolean utlystTilSalgPaaDetFrieMarked;
   private Kode omsetningstype;
   private Beloep vederlag;
   private List<OmsattRegisterenhetsrett> omsatteRegisterenhetsretter = Lists.newArrayList();

   private OmsetningBuilder() {
   }

   public static OmsetningBuilder anOmsetning() {
      return new OmsetningBuilder();
   }

   public OmsetningBuilder withDokumentavgiftsplikt(Dokumentavgiftsplikt dokumentavgiftsplikt) {
      this.dokumentavgiftsplikt = dokumentavgiftsplikt;
      return this;
   }

   public OmsetningBuilder withUtlystTilSalgPaaDetFrieMarked(Boolean utlystTilSalgPaaDetFrieMarked) {
      this.utlystTilSalgPaaDetFrieMarked = utlystTilSalgPaaDetFrieMarked;
      return this;
   }

   public OmsetningBuilder withOmsetningstype(Kode omsetningstype) {
      this.omsetningstype = omsetningstype;
      return this;
   }

   public OmsetningBuilder withVederlag(Beloep vederlag) {
      this.vederlag = vederlag;
      return this;
   }

   public OmsetningBuilder withOmsatteRegisterenhetsretter(List<OmsattRegisterenhetsrett> omsatteRegisterenhetsretter) {
      this.omsatteRegisterenhetsretter = omsatteRegisterenhetsretter;
      return this;
   }

   public OmsetningBuilder but() {
      return anOmsetning().withDokumentavgiftsplikt(dokumentavgiftsplikt).withUtlystTilSalgPaaDetFrieMarked(utlystTilSalgPaaDetFrieMarked).withOmsetningstype(omsetningstype).withVederlag(vederlag).withOmsatteRegisterenhetsretter(omsatteRegisterenhetsretter);
   }

   public Omsetning build() {
      Omsetning omsetning = new Omsetning();
      omsetning.setDokumentavgiftsplikt(dokumentavgiftsplikt);
      omsetning.setUtlystTilSalgPaaDetFrieMarked(utlystTilSalgPaaDetFrieMarked);
      omsetning.setOmsetningstype(omsetningstype);
      omsetning.setVederlag(vederlag);
      omsetning.setOmsatteRegisterenhetsretter(omsatteRegisterenhetsretter);
      return omsetning;
   }
}
