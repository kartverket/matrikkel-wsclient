package no.statkart.wsclient.grunnbok.innsending.domene;

/**
 *
 */
public class AnnenHeftelse extends HeftelseIRegisterenhetsrett {

   @Override
   public Rettsstiftelsestype getRettstiftelsestype() {
      return Rettsstiftelsestype.ANNEN_HEFTELSE;
   }
}
