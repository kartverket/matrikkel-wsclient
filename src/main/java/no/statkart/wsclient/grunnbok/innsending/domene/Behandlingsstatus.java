package no.statkart.wsclient.grunnbok.innsending.domene;


import org.joda.time.LocalDateTime;

public class Behandlingsstatus {

   private String innsendingId;
   private LocalDateTime registreringstidspunkt;
   private String behandlingsutfall;
   private String saksstatus;
   private Tinglysingsinformasjon tinglysingsinformasjon;
   private Avvisningsinformasjon avvisningsinformasjon;

   public Avvisningsinformasjon getAvvisningsinformasjon() {
      return avvisningsinformasjon;
   }

   public void setAvvisningsinformasjon(Avvisningsinformasjon avvisningsinformasjon) {
      this.avvisningsinformasjon = avvisningsinformasjon;
   }

   public String getInnsendingId() {
      return innsendingId;
   }

   public void setInnsendingId(String innsendingId) {
      this.innsendingId = innsendingId;
   }

   public String getBehandlingsutfall() {
      return behandlingsutfall;
   }

   public void setBehandlingsutfall(String behandlingsutfall) {
      this.behandlingsutfall = behandlingsutfall;
   }

   public LocalDateTime getRegistreringstidspunkt() {
      return registreringstidspunkt;
   }

   public void setRegistreringstidspunkt(LocalDateTime registreringstidspunkt) {
      this.registreringstidspunkt = registreringstidspunkt;
   }

   public String getSaksstatus() {
      return saksstatus;
   }

   public void setSaksstatus(String saksstatus) {
      this.saksstatus = saksstatus;
   }

   public Tinglysingsinformasjon getTinglysingsinformasjon() {
      return tinglysingsinformasjon;
   }

   public void setTinglysingsinformasjon(Tinglysingsinformasjon tinglysingsinformasjon) {
      this.tinglysingsinformasjon = tinglysingsinformasjon;
   }
}
