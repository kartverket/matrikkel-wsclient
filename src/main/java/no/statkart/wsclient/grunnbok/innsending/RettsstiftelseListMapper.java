package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.reflect.TypeToken;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.*;
import no.statkart.skif.mapper.Mapping;
import no.statkart.skif.mapper.TypeMapper;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring.TypeMatrikkelenhetsendring;
import no.statkart.wsclient.grunnbok.innsending.domene.OverdragelseAvRegisterenhetsrett.TypeOverdragelseAvRegisterenhetsrett;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse.Rettsstiftelsestype;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring.TypeMatrikkelenhetsendring.*;
import static no.statkart.wsclient.grunnbok.innsending.domene.OverdragelseAvRegisterenhetsrett.TypeOverdragelseAvRegisterenhetsrett.*;
import static no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse.Rettsstiftelsestype.*;

/**
 *
 */
class RettsstiftelseListMapper implements TypeMapper<RettsstiftelseList, List<no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse>> {

   private Mapping mapping;

   @Override
   public RettsstiftelseList mapDomainObject(List<no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse> listOfDomainObjects) {
      RettsstiftelseList wsObject = new RettsstiftelseList();
      List<JAXBElement<? extends Rettsstiftelse>> rettsstiftelser = wsObject.getEierskifteMatrikkelenhetOrOverdragelseAvFesterettOrEierskifteBorettslagsandel();

      ObjectFactory objectFactory = new ObjectFactory();
      for( no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse rettsstiftelse : listOfDomainObjects ) {
         Rettsstiftelsestype rettstiftelsestype = rettsstiftelse.getRettstiftelsestype();

         JAXBElement<? extends Rettsstiftelse> jaxbElement = null;
         if( ANMERKNING_PAA_PERSON == rettstiftelsestype ) {
            jaxbElement = objectFactory.createRettsstiftelseListAnmerkningPaaPerson(mapToWebServiceObject(rettsstiftelse, AnmerkningPaaPerson.class));
         } else if( SLETTING == rettstiftelsestype ) {
            jaxbElement = objectFactory.createRettsstiftelseListSletting(mapToWebServiceObject(rettsstiftelse, Sletting.class));
         } else if( TVANGSFORRETNING == rettstiftelsestype ) {
            jaxbElement = objectFactory.createRettsstiftelseListTvangsforretning(mapToWebServiceObject(rettsstiftelse, Tvangsforretning.class));
         } else if( ANNEN_HEFTELSE == rettstiftelsestype ) {
            jaxbElement = objectFactory.createRettsstiftelseListAnnenHeftelse(mapToWebServiceObject(rettsstiftelse, AnnenHeftelse.class));
         } else if( PANT == rettstiftelsestype ) {
            jaxbElement = objectFactory.createRettsstiftelseListPant(mapToWebServiceObject(rettsstiftelse, Pant.class));
         } else if( OVERDRAGELSE_AV_REGISTERENHETSRETT == rettstiftelsestype ) {
            OverdragelseAvRegisterenhetsrett overdragelseAvRegisterenhetsrettWsObject = mapToWebServiceObject(rettsstiftelse, OverdragelseAvRegisterenhetsrett.class);
            TypeOverdragelseAvRegisterenhetsrett typeOverdragelse = ((no.statkart.wsclient.grunnbok.innsending.domene.OverdragelseAvRegisterenhetsrett) rettsstiftelse).getTypeOverdragelseAvRegisterenhetsrett();

            if( EIERSKIFTE_MATRIKKELENHET == typeOverdragelse ) {
               jaxbElement = objectFactory.createRettsstiftelseListEierskifteMatrikkelenhet(overdragelseAvRegisterenhetsrettWsObject);
            } else if( OVERDRAGELSE_AV_FESTERETT == typeOverdragelse ) {
               jaxbElement = objectFactory.createRettsstiftelseListOverdragelseAvFesterett(overdragelseAvRegisterenhetsrettWsObject);
            } else if( EIERSKIFTE_BORETTSLAGSANDEL == typeOverdragelse ) {
               jaxbElement = objectFactory.createRettsstiftelseListEierskifteBorettslagsandel(overdragelseAvRegisterenhetsrettWsObject);
            }
         } else if( MATRIKKELENHETSENDRING == rettstiftelsestype ) {
            Matrikkelenhetsendring matrikkelenhetsendringWsObject = mapToWebServiceObject(rettsstiftelse, Matrikkelenhetsendring.class);
            TypeMatrikkelenhetsendring typeMatrikkelenhetsendring = ((no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring) rettsstiftelse).getTypeMatrikkelenhetsendring();

            if( FRADELING == typeMatrikkelenhetsendring ) {
               jaxbElement = objectFactory.createRettsstiftelseListFradeling(matrikkelenhetsendringWsObject);
            } else if( SAMMENSLAAING_AV_MATRIKKELENHETER == typeMatrikkelenhetsendring ) {
               jaxbElement = objectFactory.createRettsstiftelseListSammenslaaingAvMatrikkelenheter(matrikkelenhetsendringWsObject);
            } else if( OVERFOERING_FRA_TIDLIGERE_FESTENUMMER == typeMatrikkelenhetsendring ) {
               jaxbElement = objectFactory.createRettsstiftelseListOverfoeringFraTidligereFestenummer(matrikkelenhetsendringWsObject);
            }
         }

         rettsstiftelser.add(jaxbElement);
      }
      return wsObject;
   }

   private <T extends Rettsstiftelse> T mapToWebServiceObject(no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse rettsstiftelse, Class<T> typeOfRettsstiftelseClazz) {
      return getMapping().d2w(rettsstiftelse, typeOfRettsstiftelseClazz);
   }

   @Override
   public List<no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse> mapWsapiObject(RettsstiftelseList source) {
      throw new UnsupportedOperationException("No need to map this way.");
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
   public Class<RettsstiftelseList> getWsapiClass() {
      return RettsstiftelseList.class;
   }

   @SuppressWarnings("unchecked")
   @Override
   public Class<List<no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse>> getDomainClass() {
      return (Class<List<no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse>>) new TypeToken<List<no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse>>() {
      }.getRawType();
   }

}
