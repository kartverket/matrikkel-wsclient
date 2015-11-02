package no.statkart.wsclient;

import org.joda.time.LocalTime;

public class DateTypeConverter {

   public static String printTime(LocalTime localTime) {
      return localTime.toString("HH:mm:ss");
   }

}
