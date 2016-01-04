package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.reflect.TypeToken;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Matrikkelenhetsendring;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.ObjectFactory;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.Rettsstiftelse;
import no.kartverket.grunnbok.wsapi.v2.domain.innsending.RettsstiftelseList;
import no.statkart.skif.mapper.Mapping;
import no.statkart.skif.mapper.TypeMapper;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring.TypeMatrikkelenhetsendring;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse.Rettsstiftelsestype;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring.TypeMatrikkelenhetsendring.*;
import static no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse.Rettsstiftelsestype.MATRIKKELENHETSENDRING;

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
      for (no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse rettsstiftelse : listOfDomainObjects) {
         Rettsstiftelsestype rettstiftelsestype = rettsstiftelse.getRettstiftelsestype();

         JAXBElement<? extends Rettsstiftelse> jaxbElement = null;
         if (rettstiftelsestype == MATRIKKELENHETSENDRING) {
            Matrikkelenhetsendring matrikkelenhetsendringWsObject = mapToWebServiceObject(rettsstiftelse, Matrikkelenhetsendring.class);
            TypeMatrikkelenhetsendring typeMatrikkelenhetsendring = ((no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring) rettsstiftelse).getTypeMatrikkelenhetsendring();

            if (typeMatrikkelenhetsendring == FRADELING) {
               jaxbElement = objectFactory.createRettsstiftelseListFradeling(matrikkelenhetsendringWsObject);
            } else if (typeMatrikkelenhetsendring == SAMMENSLAAING_AV_MATRIKKELENHETER) {
               jaxbElement = objectFactory.createRettsstiftelseListSammenslaaingAvMatrikkelenheter(matrikkelenhetsendringWsObject);
            } else if (typeMatrikkelenhetsendring == OVERFOERING_FRA_TIDLIGERE_FESTENUMMER) {
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
