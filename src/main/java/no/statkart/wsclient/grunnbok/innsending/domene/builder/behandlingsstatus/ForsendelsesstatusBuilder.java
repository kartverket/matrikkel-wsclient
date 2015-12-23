package no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus;

import no.statkart.wsclient.grunnbok.innsending.domene.Avvisningsinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Tinglysingsinformasjon;
import org.joda.time.LocalDateTime;

/**
 *
 */
public class ForsendelsesstatusBuilder {

   protected Avvisningsinformasjon avvisningsinformasjon;
   protected String innsendingId;
   private String forsendelsesreferanse;
   protected LocalDateTime registreringstidspunkt;
   protected String behandlingsutfall;
   protected String saksstatus;
   protected Tinglysingsinformasjon tinglysingsinformasjon;

   private ForsendelsesstatusBuilder() {
   }

   public static ForsendelsesstatusBuilder aBehandlingsstatus() {
      return new ForsendelsesstatusBuilder();
   }

   public ForsendelsesstatusBuilder withAvvisningsinformasjon(Avvisningsinformasjon avvisningsinformasjon) {
      this.avvisningsinformasjon = avvisningsinformasjon;
      return this;
   }

   public ForsendelsesstatusBuilder withInnsendingId(String innsendingId) {
      this.innsendingId = innsendingId;
      return this;
   }

   public ForsendelsesstatusBuilder withForsendelsesreferanse(String forsendelsesreferanse) {
      this.forsendelsesreferanse = forsendelsesreferanse;
      return this;
   }

   public ForsendelsesstatusBuilder withRegistreringstidspunkt(LocalDateTime registreringstidspunkt) {
      this.registreringstidspunkt = registreringstidspunkt;
      return this;
   }

   public ForsendelsesstatusBuilder withBehandlingsutfall(String behandlingsutfall) {
      this.behandlingsutfall = behandlingsutfall;
      return this;
   }

   public ForsendelsesstatusBuilder withSaksstatus(String saksstatus) {
      this.saksstatus = saksstatus;
      return this;
   }

   public ForsendelsesstatusBuilder withTinglysingsinformasjon(Tinglysingsinformasjon tinglysingsinformasjon) {
      this.tinglysingsinformasjon = tinglysingsinformasjon;
      return this;
   }

   public ForsendelsesstatusBuilder but() {
      return aBehandlingsstatus().withAvvisningsinformasjon(avvisningsinformasjon).withInnsendingId(innsendingId)
            .withForsendelsesreferanse(forsendelsesreferanse).withRegistreringstidspunkt(registreringstidspunkt)
            .withBehandlingsutfall(behandlingsutfall).withSaksstatus(saksstatus)
            .withTinglysingsinformasjon(tinglysingsinformasjon);
   }

   public Forsendelsesstatus build() {
      Forsendelsesstatus forsendelsesstatus = new Forsendelsesstatus();
      forsendelsesstatus.setAvvisningsinformasjon(avvisningsinformasjon);
      forsendelsesstatus.setInnsendingId(innsendingId);
      forsendelsesstatus.setForsendelsesreferanse(forsendelsesreferanse);
      forsendelsesstatus.setRegistreringstidspunkt(registreringstidspunkt);
      forsendelsesstatus.setBehandlingsutfall(behandlingsutfall);
      forsendelsesstatus.setSaksstatus(saksstatus);
      forsendelsesstatus.setTinglysingsinformasjon(tinglysingsinformasjon);
      return forsendelsesstatus;
   }

}
