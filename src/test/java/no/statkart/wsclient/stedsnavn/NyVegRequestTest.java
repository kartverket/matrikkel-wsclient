package no.statkart.wsclient.stedsnavn;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class NyVegRequestTest {

   public void nyReq() {
      NyVegRequest request = NyVegRequest.Builder.builder()
            .vegId(2L)
            .adressekode(2)
            .adressenavn("Navn")
            .kommunenummer("0301")
            .koordinatsystem("KOORD")
            .kortnavn("kort")
            .nord(23.3)
            .ost(9.8)
            .vedtaksdato(LocalDate.of(2019, Month.APRIL, 17))
            .build();

      assertThat(request).isNotNull();
      assertThat(request.getVegId()).isEqualTo(2L);
      assertThat(request.getVedtaksdato()).isEqualTo(LocalDate.of(2019, Month.APRIL, 17));
   }

}
