package no.statkart.wsclient.grunnbok.innsending.domene;


import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.joda.time.LocalDateTime;

import java.util.Collection;
import java.util.EnumSet;

@SuppressWarnings("InstanceVariableMayNotBeInitialized")
public class Forsendelsesstatus {

   private String innsendingId;
   private String forsendelsesreferanse;
   private LocalDateTime registreringstidspunkt;
   private String behandlingsutfall;
   private String saksstatus;
   private Tinglysingsinformasjon tinglysingsinformasjon;
   private Avvisningsinformasjon avvisningsinformasjon;

   public enum Behandlingsutfall {
      UAVKLART,
      TINGLYST,
      FORELOEPIG_NEKTET,
      ANKET,
      NEKTET,
      AVVIST;

      public boolean erSluttUtfall() {
         return EnumSet.of(TINGLYST, AVVIST).contains(this);
      }

      public static Behandlingsutfall parse(String behandlingsutfall) {
         try {
            return valueOf(behandlingsutfall);
         } catch (IllegalArgumentException iae) {
            throw new RuntimeException("Unknown value received for behandlingsutfall: " + behandlingsutfall, iae);
         }
      }
   }

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

   public boolean erFerdigbehandlet() {
      String behandlingsutfall = getBehandlingsutfall();
      return behandlingsutfall != null && Behandlingsutfall.parse(behandlingsutfall).erSluttUtfall();
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

   public Behandlingsutfall getBehandlingsutfallAsEnum() {
      return behandlingsutfall != null ? Behandlingsutfall.parse(behandlingsutfall) : null;
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
