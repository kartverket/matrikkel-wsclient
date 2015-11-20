package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.*;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelse;
import no.statkart.skif.mapper.*;
import no.statkart.wsclient.grunnbok.innsending.domene.Begrunnelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Beloep;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRett;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokumentinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.ErklaeringOmSivilstand;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Kontrollresultat;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhet;
import no.statkart.wsclient.grunnbok.innsending.domene.MatrikkelenhetFraTil;
import no.statkart.wsclient.grunnbok.innsending.domene.OmsattRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Person;
import no.statkart.wsclient.grunnbok.innsending.domene.Referanse;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Registerenhetsrettsandel;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelsesinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.SDODokument;
import no.statkart.wsclient.grunnbok.innsending.domene.Signatur;
import no.statkart.wsclient.grunnbok.innsending.domene.SignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbok.innsending.domene.Tekst;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
public class InnsendingServiceMapper extends AbstractMapper<Mapping> {

   public static final String WS_PACKAGE_NAME = "no.kartverket.grunnbok.wsapi.v2.domain.innsending";
   public static final String DOMAIN_PACKAGE_NAME = "no.statkart.wsclient.grunnbok.innsending.domene";

   InnsendingServiceMapper() {
      super(Mapping.class);
      MappingResolver mappingResolver = new MappingResolver();
      mappingResolver.addPackageMapping(WS_PACKAGE_NAME, DOMAIN_PACKAGE_NAME);
      setMappingResolver(mappingResolver);

      IdentityTypeMapperFactory identityTypeMapperFactory = new IdentityTypeMapperFactory();
      identityTypeMapperFactory.useIdentityMappingForBasicTypes();
      addMapperFactory(identityTypeMapperFactory);
      addMapperFactory(new DefaultTypeMapperFactory());
      addMapperFactory(new CollectionMapperFactory());

      addMapper(new LocalDateTimeMapper());
      addMapper(new LocalDateMapper());
      addMapper(new LocalTimeMapper());

      addMapper(new SignaturListMapper());
      addMapper(new SDODokumentListMapper());
      addMapper(new DokumentListMapper());
      addMapper(new TekstListMapper());
      addMapper(new ReferanseListMapper());
      addMapper(new MatrikkelenhetListMapper());
      addMapper(new ErklaeringOmSivilstandListMapper());
      addMapper(new OmsattRegisterenhetsrettListMapper());
      addMapper(new RegisterenhetsrettsandelListMapper());
      addMapper(new PersonListMapper());
      addMapper(new BeloepListMapper());
      addMapper(new DelAvRegisterenhetsrettListMapper());
      addMapper(new RegisterenhetsrettListMapper());
      addMapper(new DelAvRettListMapper());
      addMapper(new DokumentinformasjonListMapper());
      addMapper(new RettsstiftelsesinformasjonListMapper());
      addMapper(new SignertGrunnboksutskriftListMapper());
      addMapper(new KontrollresultatListMapper());
      addMapper(new BegrunnelseListMapper());
      addMapper(new MatrikkelenhetFraTilListMapper());
      addMapper(new RettsstiftelseListMapper());
   }

   Forsendelse mapForsendelse(no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse domainForsendelse) {
      return getMapping().d2w(domainForsendelse, Forsendelse.class);
   }

   Forsendelsesstatus mapForsendelsesstatus(no.kartverket.grunnbok.wsapi.v2.domain.innsending.Forsendelsesstatus wsForsendelsesstatus) {
      return getMapping().w2d(wsForsendelsesstatus, Forsendelsesstatus.class);
   }

   private static class LocalDateTimeMapper extends AbstractTypeMapper<LocalDateTime, LocalDateTime, Mapping> {
      public LocalDateTimeMapper() {
         super(LocalDateTime.class, LocalDateTime.class, Mapping.class);
      }

      @Override
      public LocalDateTime mapDomainObject(LocalDateTime source) {
         return source;
      }

      @Override
      public LocalDateTime mapWsapiObject(LocalDateTime source) {
         return source;
      }
   }

   private static class LocalDateMapper extends AbstractTypeMapper<LocalDate, LocalDate, Mapping> {
      public LocalDateMapper() {
         super(LocalDate.class, LocalDate.class, Mapping.class);
      }

      @Override
      public LocalDate mapDomainObject(LocalDate source) {
         return source;
      }

      @Override
      public LocalDate mapWsapiObject(LocalDate source) {
         return source;
      }
   }

   private static class LocalTimeMapper extends AbstractTypeMapper<LocalTime, LocalTime, Mapping> {
      public LocalTimeMapper() {
         super(LocalTime.class, LocalTime.class, Mapping.class);
      }

      @Override
      public LocalTime mapDomainObject(LocalTime source) {
         return source;
      }

      @Override
      public LocalTime mapWsapiObject(LocalTime source) {
         return source;
      }
   }

   private abstract class InnsendingListMapper<WsapiT, DomainT extends Collection<?>> implements TypeMapper<WsapiT, DomainT> {
      private Mapping mapping;
      private final Class<WsapiT> wsApiClass;
      private final Class<DomainT> domainClass;

      protected InnsendingListMapper(Mapping mapping, Class<WsapiT> wsApiClass, Class<DomainT> domainClass) {
         this.mapping = mapping;
         this.wsApiClass = wsApiClass;
         this.domainClass = domainClass;
      }

      @Override
      public Mapping getMapping() {
         return mapping;
      }

      @Override
      public void setMapping(Mapping mapping) {
         this.mapping = mapping;
      }

      @Override
      public Class<WsapiT> getWsapiClass() {
         return wsApiClass;
      }

      @Override
      public Class<DomainT> getDomainClass() {
         return domainClass;
      }

   }

   private class DelAvRettListMapper extends InnsendingListMapper<DelAvRettList, List<DelAvRett>> {
      public DelAvRettListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), DelAvRettList.class, (Class<List<DelAvRett>>) new TypeToken<List<DelAvRett>>() {
         }.getRawType());
      }

      @Override
      public DelAvRettList mapDomainObject(List<DelAvRett> source) {
         DelAvRettList wsObject = new DelAvRettList();
         for( DelAvRett domainObject : source ) {
            wsObject.getDelAvRett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.DelAvRett.class));
         }
         return wsObject;
      }

      @Override
      public List<DelAvRett> mapWsapiObject(DelAvRettList source) {
         List<DelAvRett> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.DelAvRett wsObject : source.getDelAvRett() ) {
            domainObjects.add(getMapping().w2d(wsObject, DelAvRett.class));
         }
         return domainObjects;
      }
   }

   private class BegrunnelseListMapper extends InnsendingListMapper<BegrunnelseList, List<Begrunnelse>> {
      public BegrunnelseListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), BegrunnelseList.class, (Class<List<Begrunnelse>>) new TypeToken<List<Begrunnelse>>() {
         }.getRawType());
      }

      @Override
      public BegrunnelseList mapDomainObject(List<Begrunnelse> source) {
         BegrunnelseList wsObject = new BegrunnelseList();
         for( Begrunnelse domainObject : source ) {
            wsObject.getBegrunnelse().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Begrunnelse.class));
         }
         return wsObject;
      }

      @Override
      public List<Begrunnelse> mapWsapiObject(BegrunnelseList source) {
         List<Begrunnelse> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Begrunnelse wsObject : source.getBegrunnelse() ) {
            domainObjects.add(getMapping().w2d(wsObject, Begrunnelse.class));
         }
         return domainObjects;
      }
   }

   private class MatrikkelenhetFraTilListMapper extends InnsendingListMapper<MatrikkelenhetFraTilList, List<MatrikkelenhetFraTil>> {
      public MatrikkelenhetFraTilListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), MatrikkelenhetFraTilList.class, (Class<List<MatrikkelenhetFraTil>>) new TypeToken<List<MatrikkelenhetFraTil>>() {
         }.getRawType());
      }

      @Override
      public MatrikkelenhetFraTilList mapDomainObject(List<MatrikkelenhetFraTil> source) {
         MatrikkelenhetFraTilList wsObject = new MatrikkelenhetFraTilList();
         for( MatrikkelenhetFraTil domainObject : source ) {
            wsObject.getMatrikkelenhetFraTil().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.MatrikkelenhetFraTil.class));
         }
         return wsObject;
      }

      @Override
      public List<MatrikkelenhetFraTil> mapWsapiObject(MatrikkelenhetFraTilList source) {
         List<MatrikkelenhetFraTil> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.MatrikkelenhetFraTil wsObject : source.getMatrikkelenhetFraTil() ) {
            domainObjects.add(getMapping().w2d(wsObject, MatrikkelenhetFraTil.class));
         }
         return domainObjects;
      }
   }

   private class KontrollresultatListMapper extends InnsendingListMapper<KontrollresultatList, List<Kontrollresultat>> {
      public KontrollresultatListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), KontrollresultatList.class, (Class<List<Kontrollresultat>>) new TypeToken<List<Kontrollresultat>>() {
         }.getRawType());
      }

      @Override
      public KontrollresultatList mapDomainObject(List<Kontrollresultat> source) {
         KontrollresultatList wsObject = new KontrollresultatList();
         for( Kontrollresultat domainObject : source ) {
            wsObject.getKontrollresultat().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Kontrollresultat.class));
         }
         return wsObject;
      }

      @Override
      public List<Kontrollresultat> mapWsapiObject(KontrollresultatList source) {
         List<Kontrollresultat> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Kontrollresultat wsObject : source.getKontrollresultat() ) {
            domainObjects.add(getMapping().w2d(wsObject, Kontrollresultat.class));
         }
         return domainObjects;
      }
   }

   private class SignertGrunnboksutskriftListMapper extends InnsendingListMapper<SignertGrunnboksutskriftList, List<SignertGrunnboksutskrift>> {
      public SignertGrunnboksutskriftListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), SignertGrunnboksutskriftList.class, (Class<List<SignertGrunnboksutskrift>>) new TypeToken<List<SignertGrunnboksutskrift>>() {
         }.getRawType());
      }

      @Override
      public SignertGrunnboksutskriftList mapDomainObject(List<SignertGrunnboksutskrift> source) {
         SignertGrunnboksutskriftList wsObject = new SignertGrunnboksutskriftList();
         for( SignertGrunnboksutskrift domainObject : source ) {
            wsObject.getSignertGrunnboksutskrift().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.SignertGrunnboksutskrift.class));
         }
         return wsObject;
      }

      @Override
      public List<SignertGrunnboksutskrift> mapWsapiObject(SignertGrunnboksutskriftList source) {
         List<SignertGrunnboksutskrift> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.SignertGrunnboksutskrift wsObject : source.getSignertGrunnboksutskrift() ) {
            domainObjects.add(getMapping().w2d(wsObject, SignertGrunnboksutskrift.class));
         }
         return domainObjects;
      }
   }

   private class RettsstiftelsesinformasjonListMapper extends InnsendingListMapper<RettsstiftelsesinformasjonList, List<Rettsstiftelsesinformasjon>> {
      public RettsstiftelsesinformasjonListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), RettsstiftelsesinformasjonList.class, (Class<List<Rettsstiftelsesinformasjon>>) new TypeToken<List<Rettsstiftelsesinformasjon>>() {
         }.getRawType());
      }

      @Override
      public RettsstiftelsesinformasjonList mapDomainObject(List<Rettsstiftelsesinformasjon> source) {
         RettsstiftelsesinformasjonList wsObject = new RettsstiftelsesinformasjonList();
         for( Rettsstiftelsesinformasjon domainObject : source ) {
            wsObject.getRettsstiftelsesinformasjon().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Rettsstiftelsesinformasjon.class));
         }
         return wsObject;
      }

      @Override
      public List<Rettsstiftelsesinformasjon> mapWsapiObject(RettsstiftelsesinformasjonList source) {
         List<Rettsstiftelsesinformasjon> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Rettsstiftelsesinformasjon wsObject : source.getRettsstiftelsesinformasjon() ) {
            domainObjects.add(getMapping().w2d(wsObject, Rettsstiftelsesinformasjon.class));
         }
         return domainObjects;
      }
   }

   private class DokumentinformasjonListMapper extends InnsendingListMapper<DokumentinformasjonList, List<Dokumentinformasjon>> {
      public DokumentinformasjonListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), DokumentinformasjonList.class, (Class<List<Dokumentinformasjon>>) new TypeToken<List<Dokumentinformasjon>>() {
         }.getRawType());
      }

      @Override
      public DokumentinformasjonList mapDomainObject(List<Dokumentinformasjon> source) {
         DokumentinformasjonList wsObject = new DokumentinformasjonList();
         for( Dokumentinformasjon domainObject : source ) {
            wsObject.getDokumentinformasjon().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokumentinformasjon.class));
         }
         return wsObject;
      }

      @Override
      public List<Dokumentinformasjon> mapWsapiObject(DokumentinformasjonList source) {
         List<Dokumentinformasjon> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokumentinformasjon wsObject : source.getDokumentinformasjon() ) {
            domainObjects.add(getMapping().w2d(wsObject, Dokumentinformasjon.class));
         }
         return domainObjects;
      }
   }

   private class RegisterenhetsrettListMapper extends InnsendingListMapper<RegisterenhetsrettList, List<Registerenhetsrett>> {
      public RegisterenhetsrettListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), RegisterenhetsrettList.class, (Class<List<Registerenhetsrett>>) new TypeToken<List<Registerenhetsrett>>() {
         }.getRawType());
      }

      @Override
      public RegisterenhetsrettList mapDomainObject(List<Registerenhetsrett> source) {
         RegisterenhetsrettList wsObject = new RegisterenhetsrettList();
         for( Registerenhetsrett domainObject : source ) {
            wsObject.getRegisterenhetsrett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhetsrett.class));
         }
         return wsObject;
      }

      @Override
      public List<Registerenhetsrett> mapWsapiObject(RegisterenhetsrettList source) {
         List<Registerenhetsrett> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhetsrett wsObject : source.getRegisterenhetsrett() ) {
            domainObjects.add(getMapping().w2d(wsObject, Registerenhetsrett.class));
         }
         return domainObjects;
      }
   }

   private class DelAvRegisterenhetsrettListMapper extends InnsendingListMapper<DelAvRegisterenhetsrettList, List<DelAvRegisterenhetsrett>> {
      public DelAvRegisterenhetsrettListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), DelAvRegisterenhetsrettList.class, (Class<List<DelAvRegisterenhetsrett>>) new TypeToken<List<DelAvRegisterenhetsrett>>() {
         }.getRawType());
      }

      @Override
      public DelAvRegisterenhetsrettList mapDomainObject(List<DelAvRegisterenhetsrett> source) {
         DelAvRegisterenhetsrettList wsObject = new DelAvRegisterenhetsrettList();
         for( DelAvRegisterenhetsrett domainObject : source ) {
            wsObject.getDelAvRegisterenhetsrett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.DelAvRegisterenhetsrett.class));
         }
         return wsObject;
      }

      @Override
      public List<DelAvRegisterenhetsrett> mapWsapiObject(DelAvRegisterenhetsrettList source) {
         List<DelAvRegisterenhetsrett> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.DelAvRegisterenhetsrett wsObject : source.getDelAvRegisterenhetsrett() ) {
            domainObjects.add(getMapping().w2d(wsObject, DelAvRegisterenhetsrett.class));
         }
         return domainObjects;
      }
   }

   private class BeloepListMapper extends InnsendingListMapper<BeloepList, List<Beloep>> {
      public BeloepListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), BeloepList.class, (Class<List<Beloep>>) new TypeToken<List<Beloep>>() {
         }.getRawType());
      }

      @Override
      public BeloepList mapDomainObject(List<Beloep> source) {
         BeloepList wsObject = new BeloepList();
         for( Beloep domainObject : source ) {
            wsObject.getBeloep().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Beloep.class));
         }
         return wsObject;
      }

      @Override
      public List<Beloep> mapWsapiObject(BeloepList source) {
         List<Beloep> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Beloep wsObject : source.getBeloep() ) {
            domainObjects.add(getMapping().w2d(wsObject, Beloep.class));
         }
         return domainObjects;
      }
   }

   private class PersonListMapper extends InnsendingListMapper<PersonList, List<Person>> {
      public PersonListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), PersonList.class, (Class<List<Person>>) new TypeToken<List<Person>>() {
         }.getRawType());
      }

      @Override
      public PersonList mapDomainObject(List<Person> source) {
         PersonList wsObject = new PersonList();
         for( Person domainObject : source ) {
            wsObject.getPerson().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Person.class));
         }
         return wsObject;
      }

      @Override
      public List<Person> mapWsapiObject(PersonList source) {
         List<Person> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Person wsObject : source.getPerson() ) {
            domainObjects.add(getMapping().w2d(wsObject, Person.class));
         }
         return domainObjects;
      }
   }

   private class RegisterenhetsrettsandelListMapper extends InnsendingListMapper<RegisterenhetsrettsandelList, List<Registerenhetsrettsandel>> {
      public RegisterenhetsrettsandelListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), RegisterenhetsrettsandelList.class, (Class<List<Registerenhetsrettsandel>>) new TypeToken<List<Registerenhetsrettsandel>>() {
         }.getRawType());
      }

      @Override
      public RegisterenhetsrettsandelList mapDomainObject(List<Registerenhetsrettsandel> source) {
         RegisterenhetsrettsandelList wsObject = new RegisterenhetsrettsandelList();
         for( Registerenhetsrettsandel domainObject : source ) {
            wsObject.getRegisterenhetsrettsandel().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhetsrettsandel.class));
         }
         return wsObject;
      }

      @Override
      public List<Registerenhetsrettsandel> mapWsapiObject(RegisterenhetsrettsandelList source) {
         List<Registerenhetsrettsandel> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Registerenhetsrettsandel wsObject : source.getRegisterenhetsrettsandel() ) {
            domainObjects.add(getMapping().w2d(wsObject, Registerenhetsrettsandel.class));
         }
         return domainObjects;
      }
   }

   private class OmsattRegisterenhetsrettListMapper extends InnsendingListMapper<OmsattRegisterenhetsrettList, List<OmsattRegisterenhetsrett>> {
      public OmsattRegisterenhetsrettListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), OmsattRegisterenhetsrettList.class, (Class<List<OmsattRegisterenhetsrett>>) new TypeToken<List<OmsattRegisterenhetsrett>>() {
         }.getRawType());
      }

      @Override
      public OmsattRegisterenhetsrettList mapDomainObject(List<OmsattRegisterenhetsrett> source) {
         OmsattRegisterenhetsrettList wsObject = new OmsattRegisterenhetsrettList();
         for( OmsattRegisterenhetsrett domainObject : source ) {
            wsObject.getOmsattRegisterenhetsrett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.OmsattRegisterenhetsrett.class));
         }
         return wsObject;
      }

      @Override
      public List<OmsattRegisterenhetsrett> mapWsapiObject(OmsattRegisterenhetsrettList source) {
         List<OmsattRegisterenhetsrett> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.OmsattRegisterenhetsrett wsObject : source.getOmsattRegisterenhetsrett() ) {
            domainObjects.add(getMapping().w2d(wsObject, OmsattRegisterenhetsrett.class));
         }
         return domainObjects;
      }
   }

   private class ErklaeringOmSivilstandListMapper extends InnsendingListMapper<ErklaeringOmSivilstandList, List<ErklaeringOmSivilstand>> {
      public ErklaeringOmSivilstandListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), ErklaeringOmSivilstandList.class, (Class<List<ErklaeringOmSivilstand>>) new TypeToken<List<ErklaeringOmSivilstand>>() {
         }.getRawType());
      }

      @Override
      public ErklaeringOmSivilstandList mapDomainObject(List<ErklaeringOmSivilstand> source) {
         ErklaeringOmSivilstandList wsObject = new ErklaeringOmSivilstandList();
         for( ErklaeringOmSivilstand domainObject : source ) {
            wsObject.getErklaeringOmSivilstand().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.ErklaeringOmSivilstand.class));
         }
         return wsObject;
      }

      @Override
      public List<ErklaeringOmSivilstand> mapWsapiObject(ErklaeringOmSivilstandList source) {
         List<ErklaeringOmSivilstand> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.ErklaeringOmSivilstand wsObject : source.getErklaeringOmSivilstand() ) {
            domainObjects.add(getMapping().w2d(wsObject, ErklaeringOmSivilstand.class));
         }
         return domainObjects;
      }
   }

   private class MatrikkelenhetListMapper extends InnsendingListMapper<MatrikkelenhetList, List<Matrikkelenhet>> {
      public MatrikkelenhetListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), MatrikkelenhetList.class, (Class<List<Matrikkelenhet>>) new TypeToken<List<Matrikkelenhet>>() {
         }.getRawType());
      }

      @Override
      public MatrikkelenhetList mapDomainObject(List<Matrikkelenhet> source) {
         MatrikkelenhetList wsObject = new MatrikkelenhetList();
         for( Matrikkelenhet domainObject : source ) {
            wsObject.getMatrikkelenhet().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhet.class));
         }
         return wsObject;
      }

      @Override
      public List<Matrikkelenhet> mapWsapiObject(MatrikkelenhetList source) {
         List<Matrikkelenhet> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhet wsObject : source.getMatrikkelenhet() ) {
            domainObjects.add(getMapping().w2d(wsObject, Matrikkelenhet.class));
         }
         return domainObjects;
      }
   }

   private class ReferanseListMapper extends InnsendingListMapper<ReferanseList, List<Referanse>> {
      public ReferanseListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), ReferanseList.class, (Class<List<Referanse>>) new TypeToken<List<Referanse>>() {
         }.getRawType());
      }

      @Override
      public ReferanseList mapDomainObject(List<Referanse> source) {
         ReferanseList wsObject = new ReferanseList();
         for( Referanse domainObject : source ) {
            wsObject.getReferanse().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Referanse.class));
         }
         return wsObject;
      }

      @Override
      public List<Referanse> mapWsapiObject(ReferanseList source) {
         List<Referanse> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Referanse wsObject : source.getReferanse() ) {
            domainObjects.add(getMapping().w2d(wsObject, Referanse.class));
         }
         return domainObjects;
      }
   }

   private class TekstListMapper extends InnsendingListMapper<TekstList, List<Tekst>> {
      public TekstListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), TekstList.class, (Class<List<Tekst>>) new TypeToken<List<Tekst>>() {
         }.getRawType());
      }

      @Override
      public TekstList mapDomainObject(List<Tekst> source) {
         TekstList wsObject = new TekstList();
         for( Tekst domainObject : source ) {
            wsObject.getTekst().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Tekst.class));
         }
         return wsObject;
      }

      @Override
      public List<Tekst> mapWsapiObject(TekstList source) {
         List<Tekst> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Tekst wsObject : source.getTekst() ) {
            domainObjects.add(getMapping().w2d(wsObject, Tekst.class));
         }
         return domainObjects;
      }
   }

   private class DokumentListMapper extends InnsendingListMapper<DokumentList, List<Dokument>> {
      public DokumentListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), DokumentList.class, (Class<List<Dokument>>) new TypeToken<List<Dokument>>() {
         }.getRawType());
      }

      @Override
      public DokumentList mapDomainObject(List<Dokument> source) {
         DokumentList wsObject = new DokumentList();
         for( Dokument domainObject : source ) {
            wsObject.getDokument().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokument.class));
         }
         return wsObject;
      }

      @Override
      public List<Dokument> mapWsapiObject(DokumentList source) {
         List<Dokument> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Dokument wsObject : source.getDokument() ) {
            domainObjects.add(getMapping().w2d(wsObject, Dokument.class));
         }
         return domainObjects;
      }
   }

   private class SDODokumentListMapper extends InnsendingListMapper<SDODokumentList, List<SDODokument>> {
      public SDODokumentListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), SDODokumentList.class, (Class<List<SDODokument>>) new TypeToken<List<SDODokument>>() {
         }.getRawType());
      }

      @Override
      public SDODokumentList mapDomainObject(List<SDODokument> source) {
         SDODokumentList wsObject = new SDODokumentList();
         for( SDODokument domainObject : source ) {
            wsObject.getSDODokument().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.v2.domain.innsending.SDODokument.class));
         }
         return wsObject;
      }

      @Override
      public List<SDODokument> mapWsapiObject(SDODokumentList source) {
         List<SDODokument> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.SDODokument wsObject : source.getSDODokument() ) {
            domainObjects.add(getMapping().w2d(wsObject, SDODokument.class));
         }
         return domainObjects;
      }
   }

   private class SignaturListMapper extends InnsendingListMapper<SignaturList, List<Signatur>> {
      public SignaturListMapper() {
         super(InnsendingServiceMapper.this.getMapping(), SignaturList.class, (Class<List<Signatur>>) new TypeToken<List<Signatur>>() {
         }.getRawType());
      }

      @Override
      public SignaturList mapDomainObject(List<Signatur> source) {
         SignaturList signaturList = new SignaturList();
         for( Signatur signatur : source ) {
            signaturList.getSignatur().add(getMapping().d2w(signatur, no.kartverket.grunnbok.wsapi.v2.domain.innsending.Signatur.class));
         }
         return signaturList;
      }

      @Override
      public List<Signatur> mapWsapiObject(SignaturList source) {
         List<Signatur> domainObjects = Lists.newArrayList();
         for( no.kartverket.grunnbok.wsapi.v2.domain.innsending.Signatur wsSignatur : source.getSignatur() ) {
            domainObjects.add(getMapping().w2d(wsSignatur, Signatur.class));
         }
         return domainObjects;
      }
   }
}
