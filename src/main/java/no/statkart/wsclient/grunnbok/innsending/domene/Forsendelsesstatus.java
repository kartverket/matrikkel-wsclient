package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.joda.time.LocalDateTime;

import java.util.Collection;

public class Forsendelsesstatus {

   private String innsendingId;
   private String forsendelsesreferanse;
   private LocalDateTime registreringstidspunkt;
   private String behandlingsutfall;
   private String saksstatus;
   private Tinglysingsinformasjon tinglysingsinformasjon;
   private Avvisningsinformasjon avvisningsinformasjon;

   public SignertGrunnboksutskrift findBekreftetGrunnboksutskriftForMatrikkelenhet(final Matrikkelenhet matrikkelenhet) {
      if (tinglysingsinformasjon != null) {
         Collection<SignertGrunnboksutskrift> utskrifterForMatrikkelenhet = Collections2.filter(tinglysingsinformasjon.getSignerteGrunnboksutskrifter(), new Predicate<SignertGrunnboksutskrift>() {
            @Override
            public boolean apply(SignertGrunnboksutskrift signertGrunnboksutskrift) {
               return matrikkelenhet.equals(signertGrunnboksutskrift.getGjelderFor().getMatrikkelenhet());
            }
         });
         if (utskrifterForMatrikkelenhet.size() == 1) {
            return utskrifterForMatrikkelenhet.iterator().next();
         }
      }
      return null;
   }

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

   public String getForsendelsesreferanse() {
      return forsendelsesreferanse;
   }

   public void setForsendelsesreferanse(String forsendelsesreferanse) {
      this.forsendelsesreferanse = forsendelsesreferanse;
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
