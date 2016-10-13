package no.statkart.wsclient.grunnbokv2.innsending.domene.builder.behandlingsstatus;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Behandlingsinformasjon;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Tinglysingsinformasjon;
import org.joda.time.LocalDateTime;

/**
 *
 */
public class ForsendelsesstatusBuilder {

   protected Behandlingsinformasjon behandlingsinformasjon;
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

   public ForsendelsesstatusBuilder withAvvisningsinformasjon(Behandlingsinformasjon behandlingsinformasjon) {
      this.behandlingsinformasjon = behandlingsinformasjon;
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
      return aBehandlingsstatus().withAvvisningsinformasjon(behandlingsinformasjon).withInnsendingId(innsendingId)
            .withForsendelsesreferanse(forsendelsesreferanse).withRegistreringstidspunkt(registreringstidspunkt)
            .withBehandlingsutfall(behandlingsutfall).withSaksstatus(saksstatus)
            .withTinglysingsinformasjon(tinglysingsinformasjon);
   }

   public Forsendelsesstatus build() {
      Forsendelsesstatus forsendelsesstatus = new Forsendelsesstatus();
      forsendelsesstatus.setBehandlingsinformasjon(behandlingsinformasjon);
      forsendelsesstatus.setInnsendingId(innsendingId);
      forsendelsesstatus.setForsendelsesreferanse(forsendelsesreferanse);
      forsendelsesstatus.setRegistreringstidspunkt(registreringstidspunkt);
      forsendelsesstatus.setBehandlingsutfall(behandlingsutfall);
      forsendelsesstatus.setSaksstatus(saksstatus);
      forsendelsesstatus.setTinglysingsinformasjon(tinglysingsinformasjon);
      return forsendelsesstatus;
   }

}
