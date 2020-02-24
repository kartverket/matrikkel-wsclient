package no.statkart.wsclient;

import org.joda.time.LocalTime;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class DateTypeConverterTest {

   public void printTime() {
      LocalTime localTimeWithMillis = new LocalTime(12, 23, 2, 745);
      String timeString = localTimeWithMillis.toString("HH:mm:ss");
      assertEquals(timeString, "12:23:02");
   }

}
