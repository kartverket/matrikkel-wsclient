package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.*;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Forsendelse;
import no.statkart.skif.mapper.AbstractMapper;
import no.statkart.skif.mapper.AbstractTypeMapper;
import no.statkart.skif.mapper.CollectionMapperFactory;
import no.statkart.skif.mapper.DefaultTypeMapperFactory;
import no.statkart.skif.mapper.IdentityTypeMapperFactory;
import no.statkart.skif.mapper.Mapping;
import no.statkart.skif.mapper.MappingResolver;
import no.statkart.skif.mapper.TypeMapper;
import no.statkart.wsclient.grunnbok.innsending.domene.Begrunnelse;
import no.statkart.wsclient.grunnbok.innsending.domene.Behandlingsstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.Beloep;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.DelAvRett;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokumentinformasjon;
import no.statkart.wsclient.grunnbok.innsending.domene.ErklaeringOmSivilstand;
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

public class InnsendingServiceMapper extends AbstractMapper<InnsendingServiceMapping> {

   public static final String WS_PACKAGE_NAME = "no.kartverket.grunnbok.wsapi.internal.domain.innsending";
   public static final String DOMAIN_PACKAGE_NAME = "no.statkart.wsclient.grunnbok.innsending.domene";

   @SuppressWarnings("unchecked")
   InnsendingServiceMapper() {
      super(InnsendingServiceMapping.class);
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

      addMapper(new InnsendingListMapper<SignaturList, List<Signatur>>(getMapping(), SignaturList.class, (Class<List<Signatur>>) new TypeToken<List<Signatur>>() {
      }.getRawType()) {
         @Override
         public SignaturList mapDomainObject(List<Signatur> source) {
            SignaturList signaturList = new SignaturList();
            for( Signatur signatur : source ) {
               signaturList.getSignatur().add(getMapping().d2w(signatur, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Signatur.class));
            }
            return signaturList;
         }

         @Override
         public List<Signatur> mapWsapiObject(SignaturList source) {
            List<Signatur> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Signatur wsSignatur : source.getSignatur() ) {
               domainObjects.add(getMapping().w2d(wsSignatur, Signatur.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<SDODokumentList, List<SDODokument>>(getMapping(), SDODokumentList.class, (Class<List<SDODokument>>) new TypeToken<List<SDODokument>>() {
      }.getRawType()) {
         @Override
         public SDODokumentList mapDomainObject(List<SDODokument> source) {
            SDODokumentList wsObject = new SDODokumentList();
            for( SDODokument domainObject : source ) {
               wsObject.getSDODokument().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.SDODokument.class));
            }
            return wsObject;
         }

         @Override
         public List<SDODokument> mapWsapiObject(SDODokumentList source) {
            List<SDODokument> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.SDODokument wsObject : source.getSDODokument() ) {
               domainObjects.add(getMapping().w2d(wsObject, SDODokument.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<DokumentList, List<Dokument>>(getMapping(), DokumentList.class, (Class<List<Dokument>>) new TypeToken<List<Dokument>>() {
      }.getRawType()) {
         @Override
         public DokumentList mapDomainObject(List<Dokument> source) {
            DokumentList wsObject = new DokumentList();
            for( Dokument domainObject : source ) {
               wsObject.getDokument().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokument.class));
            }
            return wsObject;
         }

         @Override
         public List<Dokument> mapWsapiObject(DokumentList source) {
            List<Dokument> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokument wsObject : source.getDokument() ) {
               domainObjects.add(getMapping().w2d(wsObject, Dokument.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<TekstList, List<Tekst>>(getMapping(), TekstList.class, (Class<List<Tekst>>) new TypeToken<List<Tekst>>() {
      }.getRawType()) {
         @Override
         public TekstList mapDomainObject(List<Tekst> source) {
            TekstList wsObject = new TekstList();
            for( Tekst domainObject : source ) {
               wsObject.getTekst().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tekst.class));
            }
            return wsObject;
         }

         @Override
         public List<Tekst> mapWsapiObject(TekstList source) {
            List<Tekst> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Tekst wsObject : source.getTekst() ) {
               domainObjects.add(getMapping().w2d(wsObject, Tekst.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<ReferanseList, List<Referanse>>(getMapping(), ReferanseList.class, (Class<List<Referanse>>) new TypeToken<List<Referanse>>() {
      }.getRawType()) {
         @Override
         public ReferanseList mapDomainObject(List<Referanse> source) {
            ReferanseList wsObject = new ReferanseList();
            for( Referanse domainObject : source ) {
               wsObject.getReferanse().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Referanse.class));
            }
            return wsObject;
         }

         @Override
         public List<Referanse> mapWsapiObject(ReferanseList source) {
            List<Referanse> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Referanse wsObject : source.getReferanse() ) {
               domainObjects.add(getMapping().w2d(wsObject, Referanse.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<MatrikkelenhetList, List<Matrikkelenhet>>(getMapping(), MatrikkelenhetList.class, (Class<List<Matrikkelenhet>>) new TypeToken<List<Matrikkelenhet>>() {
      }.getRawType()) {
         @Override
         public MatrikkelenhetList mapDomainObject(List<Matrikkelenhet> source) {
            MatrikkelenhetList wsObject = new MatrikkelenhetList();
            for( Matrikkelenhet domainObject : source ) {
               wsObject.getMatrikkelenhet().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhet.class));
            }
            return wsObject;
         }

         @Override
         public List<Matrikkelenhet> mapWsapiObject(MatrikkelenhetList source) {
            List<Matrikkelenhet> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Matrikkelenhet wsObject : source.getMatrikkelenhet() ) {
               domainObjects.add(getMapping().w2d(wsObject, Matrikkelenhet.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<ErklaeringOmSivilstandList, List<ErklaeringOmSivilstand>>(getMapping(), ErklaeringOmSivilstandList.class, (Class<List<ErklaeringOmSivilstand>>) new TypeToken<List<ErklaeringOmSivilstand>>() {
      }.getRawType()) {
         @Override
         public ErklaeringOmSivilstandList mapDomainObject(List<ErklaeringOmSivilstand> source) {
            ErklaeringOmSivilstandList wsObject = new ErklaeringOmSivilstandList();
            for( ErklaeringOmSivilstand domainObject : source ) {
               wsObject.getErklaeringOmSivilstand().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.ErklaeringOmSivilstand.class));
            }
            return wsObject;
         }

         @Override
         public List<ErklaeringOmSivilstand> mapWsapiObject(ErklaeringOmSivilstandList source) {
            List<ErklaeringOmSivilstand> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.ErklaeringOmSivilstand wsObject : source.getErklaeringOmSivilstand() ) {
               domainObjects.add(getMapping().w2d(wsObject, ErklaeringOmSivilstand.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<OmsattRegisterenhetsrettList, List<OmsattRegisterenhetsrett>>(getMapping(), OmsattRegisterenhetsrettList.class, (Class<List<OmsattRegisterenhetsrett>>) new TypeToken<List<OmsattRegisterenhetsrett>>() {
      }.getRawType()) {
         @Override
         public OmsattRegisterenhetsrettList mapDomainObject(List<OmsattRegisterenhetsrett> source) {
            OmsattRegisterenhetsrettList wsObject = new OmsattRegisterenhetsrettList();
            for( OmsattRegisterenhetsrett domainObject : source ) {
               wsObject.getOmsattRegisterenhetsrett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.OmsattRegisterenhetsrett.class));
            }
            return wsObject;
         }

         @Override
         public List<OmsattRegisterenhetsrett> mapWsapiObject(OmsattRegisterenhetsrettList source) {
            List<OmsattRegisterenhetsrett> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.OmsattRegisterenhetsrett wsObject : source.getOmsattRegisterenhetsrett() ) {
               domainObjects.add(getMapping().w2d(wsObject, OmsattRegisterenhetsrett.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<RegisterenhetsrettsandelList, List<Registerenhetsrettsandel>>(getMapping(), RegisterenhetsrettsandelList.class, (Class<List<Registerenhetsrettsandel>>) new TypeToken<List<Registerenhetsrettsandel>>() {
      }.getRawType()) {
         @Override
         public RegisterenhetsrettsandelList mapDomainObject(List<Registerenhetsrettsandel> source) {
            RegisterenhetsrettsandelList wsObject = new RegisterenhetsrettsandelList();
            for( Registerenhetsrettsandel domainObject : source ) {
               wsObject.getRegisterenhetsrettsandel().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrettsandel.class));
            }
            return wsObject;
         }

         @Override
         public List<Registerenhetsrettsandel> mapWsapiObject(RegisterenhetsrettsandelList source) {
            List<Registerenhetsrettsandel> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrettsandel wsObject : source.getRegisterenhetsrettsandel() ) {
               domainObjects.add(getMapping().w2d(wsObject, Registerenhetsrettsandel.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<PersonList, List<Person>>(getMapping(), PersonList.class, (Class<List<Person>>) new TypeToken<List<Person>>() {
      }.getRawType()) {
         @Override
         public PersonList mapDomainObject(List<Person> source) {
            PersonList wsObject = new PersonList();
            for( Person domainObject : source ) {
               wsObject.getPerson().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Person.class));
            }
            return wsObject;
         }

         @Override
         public List<Person> mapWsapiObject(PersonList source) {
            List<Person> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Person wsObject : source.getPerson() ) {
               domainObjects.add(getMapping().w2d(wsObject, Person.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<BeloepList, List<Beloep>>(getMapping(), BeloepList.class, (Class<List<Beloep>>) new TypeToken<List<Beloep>>() {
      }.getRawType()) {
         @Override
         public BeloepList mapDomainObject(List<Beloep> source) {
            BeloepList wsObject = new BeloepList();
            for( Beloep domainObject : source ) {
               wsObject.getBeloep().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Beloep.class));
            }
            return wsObject;
         }

         @Override
         public List<Beloep> mapWsapiObject(BeloepList source) {
            List<Beloep> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Beloep wsObject : source.getBeloep() ) {
               domainObjects.add(getMapping().w2d(wsObject, Beloep.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<DelAvRegisterenhetsrettList, List<DelAvRegisterenhetsrett>>(getMapping(), DelAvRegisterenhetsrettList.class, (Class<List<DelAvRegisterenhetsrett>>) new TypeToken<List<DelAvRegisterenhetsrett>>() {
      }.getRawType()) {
         @Override
         public DelAvRegisterenhetsrettList mapDomainObject(List<DelAvRegisterenhetsrett> source) {
            DelAvRegisterenhetsrettList wsObject = new DelAvRegisterenhetsrettList();
            for( DelAvRegisterenhetsrett domainObject : source ) {
               wsObject.getDelAvRegisterenhetsrett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRegisterenhetsrett.class));
            }
            return wsObject;
         }

         @Override
         public List<DelAvRegisterenhetsrett> mapWsapiObject(DelAvRegisterenhetsrettList source) {
            List<DelAvRegisterenhetsrett> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRegisterenhetsrett wsObject : source.getDelAvRegisterenhetsrett() ) {
               domainObjects.add(getMapping().w2d(wsObject, DelAvRegisterenhetsrett.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<RegisterenhetsrettList, List<Registerenhetsrett>>(getMapping(), RegisterenhetsrettList.class, (Class<List<Registerenhetsrett>>) new TypeToken<List<Registerenhetsrett>>() {
      }.getRawType()) {
         @Override
         public RegisterenhetsrettList mapDomainObject(List<Registerenhetsrett> source) {
            RegisterenhetsrettList wsObject = new RegisterenhetsrettList();
            for( Registerenhetsrett domainObject : source ) {
               wsObject.getRegisterenhetsrett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrett.class));
            }
            return wsObject;
         }

         @Override
         public List<Registerenhetsrett> mapWsapiObject(RegisterenhetsrettList source) {
            List<Registerenhetsrett> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Registerenhetsrett wsObject : source.getRegisterenhetsrett() ) {
               domainObjects.add(getMapping().w2d(wsObject, Registerenhetsrett.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<DelAvRettList, List<DelAvRett>>(getMapping(), DelAvRettList.class, (Class<List<DelAvRett>>) new TypeToken<List<DelAvRett>>() {
      }.getRawType()) {
         @Override
         public DelAvRettList mapDomainObject(List<DelAvRett> source) {
            DelAvRettList wsObject = new DelAvRettList();
            for( DelAvRett domainObject : source ) {
               wsObject.getDelAvRett().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRett.class));
            }
            return wsObject;
         }

         @Override
         public List<DelAvRett> mapWsapiObject(DelAvRettList source) {
            List<DelAvRett> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.DelAvRett wsObject : source.getDelAvRett() ) {
               domainObjects.add(getMapping().w2d(wsObject, DelAvRett.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<DokumentinformasjonList, List<Dokumentinformasjon>>(getMapping(), DokumentinformasjonList.class, (Class<List<Dokumentinformasjon>>) new TypeToken<List<Dokumentinformasjon>>() {
      }.getRawType()) {
         @Override
         public DokumentinformasjonList mapDomainObject(List<Dokumentinformasjon> source) {
            DokumentinformasjonList wsObject = new DokumentinformasjonList();
            for( Dokumentinformasjon domainObject : source ) {
               wsObject.getDokumentinformasjon().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokumentinformasjon.class));
            }
            return wsObject;
         }

         @Override
         public List<Dokumentinformasjon> mapWsapiObject(DokumentinformasjonList source) {
            List<Dokumentinformasjon> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokumentinformasjon wsObject : source.getDokumentinformasjon() ) {
               domainObjects.add(getMapping().w2d(wsObject, Dokumentinformasjon.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<RettsstiftelsesinformasjonList, List<Rettsstiftelsesinformasjon>>(getMapping(), RettsstiftelsesinformasjonList.class, (Class<List<Rettsstiftelsesinformasjon>>) new TypeToken<List<Rettsstiftelsesinformasjon>>() {
      }.getRawType()) {
         @Override
         public RettsstiftelsesinformasjonList mapDomainObject(List<Rettsstiftelsesinformasjon> source) {
            RettsstiftelsesinformasjonList wsObject = new RettsstiftelsesinformasjonList();
            for( Rettsstiftelsesinformasjon domainObject : source ) {
               wsObject.getRettsstiftelsesinformasjon().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Rettsstiftelsesinformasjon.class));
            }
            return wsObject;
         }

         @Override
         public List<Rettsstiftelsesinformasjon> mapWsapiObject(RettsstiftelsesinformasjonList source) {
            List<Rettsstiftelsesinformasjon> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Rettsstiftelsesinformasjon wsObject : source.getRettsstiftelsesinformasjon() ) {
               domainObjects.add(getMapping().w2d(wsObject, Rettsstiftelsesinformasjon.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<SignertGrunnboksutskriftList, List<SignertGrunnboksutskrift>>(getMapping(), SignertGrunnboksutskriftList.class, (Class<List<SignertGrunnboksutskrift>>) new TypeToken<List<SignertGrunnboksutskrift>>() {
      }.getRawType()) {
         @Override
         public SignertGrunnboksutskriftList mapDomainObject(List<SignertGrunnboksutskrift> source) {
            SignertGrunnboksutskriftList wsObject = new SignertGrunnboksutskriftList();
            for( SignertGrunnboksutskrift domainObject : source ) {
               wsObject.getSignertGrunnboksutskrift().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.SignertGrunnboksutskrift.class));
            }
            return wsObject;
         }

         @Override
         public List<SignertGrunnboksutskrift> mapWsapiObject(SignertGrunnboksutskriftList source) {
            List<SignertGrunnboksutskrift> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.SignertGrunnboksutskrift wsObject : source.getSignertGrunnboksutskrift() ) {
               domainObjects.add(getMapping().w2d(wsObject, SignertGrunnboksutskrift.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<KontrollresultatList, List<Kontrollresultat>>(getMapping(), KontrollresultatList.class, (Class<List<Kontrollresultat>>) new TypeToken<List<Kontrollresultat>>() {
      }.getRawType()) {
         @Override
         public KontrollresultatList mapDomainObject(List<Kontrollresultat> source) {
            KontrollresultatList wsObject = new KontrollresultatList();
            for( Kontrollresultat domainObject : source ) {
               wsObject.getKontrollresultat().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kontrollresultat.class));
            }
            return wsObject;
         }

         @Override
         public List<Kontrollresultat> mapWsapiObject(KontrollresultatList source) {
            List<Kontrollresultat> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Kontrollresultat wsObject : source.getKontrollresultat() ) {
               domainObjects.add(getMapping().w2d(wsObject, Kontrollresultat.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<BegrunnelseList, List<Begrunnelse>>(getMapping(), BegrunnelseList.class, (Class<List<Begrunnelse>>) new TypeToken<List<Begrunnelse>>() {
      }.getRawType()) {
         @Override
         public BegrunnelseList mapDomainObject(List<Begrunnelse> source) {
            BegrunnelseList wsObject = new BegrunnelseList();
            for( Begrunnelse domainObject : source ) {
               wsObject.getBegrunnelse().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.Begrunnelse.class));
            }
            return wsObject;
         }

         @Override
         public List<Begrunnelse> mapWsapiObject(BegrunnelseList source) {
            List<Begrunnelse> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.Begrunnelse wsObject : source.getBegrunnelse() ) {
               domainObjects.add(getMapping().w2d(wsObject, Begrunnelse.class));
            }
            return domainObjects;
         }
      });

      addMapper(new InnsendingListMapper<MatrikkelenhetFraTilList, List<MatrikkelenhetFraTil>>(getMapping(), MatrikkelenhetFraTilList.class, (Class<List<MatrikkelenhetFraTil>>) new TypeToken<List<MatrikkelenhetFraTil>>() {
      }.getRawType()) {
         @Override
         public MatrikkelenhetFraTilList mapDomainObject(List<MatrikkelenhetFraTil> source) {
            MatrikkelenhetFraTilList wsObject = new MatrikkelenhetFraTilList();
            for( MatrikkelenhetFraTil domainObject : source ) {
               wsObject.getMatrikkelenhetFraTil().add(getMapping().d2w(domainObject, no.kartverket.grunnbok.wsapi.internal.domain.innsending.MatrikkelenhetFraTil.class));
            }
            return wsObject;
         }

         @Override
         public List<MatrikkelenhetFraTil> mapWsapiObject(MatrikkelenhetFraTilList source) {
            List<MatrikkelenhetFraTil> domainObjects = Lists.newArrayList();
            for( no.kartverket.grunnbok.wsapi.internal.domain.innsending.MatrikkelenhetFraTil wsObject : source.getMatrikkelenhetFraTil() ) {
               domainObjects.add(getMapping().w2d(wsObject, MatrikkelenhetFraTil.class));
            }
            return domainObjects;
         }
      });

      addMapper(new RettsstiftelseListMapper());

   }

   Forsendelse mapForsendelse(no.statkart.wsclient.grunnbok.innsending.domene.Forsendelse domainForsendelse) {
      return getMapping().d2w(domainForsendelse, Forsendelse.class);
   }

   Behandlingsstatus mapBehandlingsstatus(no.kartverket.grunnbok.wsapi.internal.domain.innsending.Behandlingsstatus wsBehandlingsstatus) {
      return getMapping().w2d(wsBehandlingsstatus, Behandlingsstatus.class);
   }

   private static class LocalDateTimeMapper extends AbstractTypeMapper<LocalDateTime, LocalDateTime, InnsendingServiceMapping> {
      public LocalDateTimeMapper() {
         super(LocalDateTime.class, LocalDateTime.class, InnsendingServiceMapping.class);
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

   private static class LocalDateMapper extends AbstractTypeMapper<LocalDate, LocalDate, InnsendingServiceMapping> {
      public LocalDateMapper() {
         super(LocalDate.class, LocalDate.class, InnsendingServiceMapping.class);
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

   private static class LocalTimeMapper extends AbstractTypeMapper<LocalTime, LocalTime, InnsendingServiceMapping> {
      public LocalTimeMapper() {
         super(LocalTime.class, LocalTime.class, InnsendingServiceMapping.class);
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
      private Class<WsapiT> wsApiClass;
      private Class<DomainT> domainClass;

      public InnsendingListMapper(Mapping mapping, Class<WsapiT> wsApiClass, Class<DomainT> domainClass) {
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

}
