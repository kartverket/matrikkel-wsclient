package no.statkart.wsclient.grunnbokv2.innsending.domene;


import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.joda.time.LocalDateTime;

import java.util.Collection;
import java.util.EnumSet;

import static com.google.common.base.Objects.equal;

@SuppressWarnings("InstanceVariableMayNotBeInitialized")
public class Forsendelsesstatus {

   private String innsendingId;
   private String forsendelsesreferanse;
   private LocalDateTime registreringstidspunkt;
   private String behandlingsutfall;
   private String saksstatus;
   private Long saksnummer;
   private Tinglysingsinformasjon tinglysingsinformasjon;
   private Behandlingsinformasjon behandlingsinformasjon;

   public enum Behandlingsutfall {
      UAVKLART,
      TINGLYST,
      FORELOEPIG_NEKTET,
      ANKET,
      NEKTET,
      AVVIST;

      public boolean erSluttUtfall() {
         return EnumSet.of(TINGLYST, AVVIST, NEKTET).contains(this);
      }

      public static Behandlingsutfall parse(String behandlingsutfall) {
         try {
            return valueOf(behandlingsutfall);
         } catch (IllegalArgumentException iae) {
            throw new RuntimeException("Unknown value received for behandlingsutfall: " + behandlingsutfall, iae);
         }
      }
   }

   public UsignertGrunnboksutskrift findBekreftetGrunnboksutskriftForMatrikkelenhet(final Matrikkelenhet matrikkelenhet) {
      if (tinglysingsinformasjon != null) {
         Collection<UsignertGrunnboksutskrift> utskrifterForMatrikkelenhet = Collections2.filter(

             tinglysingsinformasjon.getGrunnboksutskrifter(), usignertGrunnboksutskrift -> {
                 Matrikkelenhet matrikkelenhetForGrunnboksutskrift = usignertGrunnboksutskrift.getGjelderFor().getMatrikkelenhet();
                 return equal(matrikkelenhet.getKommunenummer(), matrikkelenhetForGrunnboksutskrift.getKommunenummer())
                     && equal(matrikkelenhet.getGaardsnummer(), matrikkelenhetForGrunnboksutskrift.getGaardsnummer())
                     && equal(matrikkelenhet.getBruksnummer(), matrikkelenhetForGrunnboksutskrift.getBruksnummer())
                     && equal(matrikkelenhet.getFestenummer(), matrikkelenhetForGrunnboksutskrift.getFestenummer())
                     && equal(matrikkelenhet.getSeksjonsnummer(), matrikkelenhetForGrunnboksutskrift.getSeksjonsnummer());
             }
         );

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

   public Behandlingsinformasjon getBehandlingsinformasjon() {
      return behandlingsinformasjon;
   }

   public void setBehandlingsinformasjon(Behandlingsinformasjon behandlingsinformasjon) {
      this.behandlingsinformasjon = behandlingsinformasjon;
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

   public Long getSaksnummer() {
      return saksnummer;
   }

   public void setSaksnummer(Long saksnummer) {
      this.saksnummer = saksnummer;
   }

   public Tinglysingsinformasjon getTinglysingsinformasjon() {
      return tinglysingsinformasjon;
   }

   public void setTinglysingsinformasjon(Tinglysingsinformasjon tinglysingsinformasjon) {
      this.tinglysingsinformasjon = tinglysingsinformasjon;
   }
}
