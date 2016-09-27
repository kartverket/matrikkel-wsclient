package no.statkart.wsclient;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for å verifisere at kildekode blir kompilert med riktig encoding.
 */
public class ProjectEncodingTest {

   @Test
   public void spesialtegnErLikEscapedeSpesialtegn() {

      final String spesialtegn = "æøå";
      System.out.println("Spesialtegn: " + spesialtegn);

      assertEquals(spesialtegn, "\u00E6\u00F8\u00E5");
   }

}
