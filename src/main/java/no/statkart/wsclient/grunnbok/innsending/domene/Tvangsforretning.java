package no.statkart.wsclient.grunnbok.innsending.domene;


/**
 *
 */
public class Tvangsforretning extends HeftelseMedBeloep {

   private AvholdtTvangsforretning avholdtTvangsforretning;

   @Override
   public Rettsstiftelsestype getRettstiftelsestype() {
      return Rettsstiftelsestype.TVANGSFORRETNING;
   }

   public AvholdtTvangsforretning getAvholdtTvangsforretning() {
      return avholdtTvangsforretning;
   }

   public void setAvholdtTvangsforretning(AvholdtTvangsforretning avholdtTvangsforretning) {
      this.avholdtTvangsforretning = avholdtTvangsforretning;
   }
}
