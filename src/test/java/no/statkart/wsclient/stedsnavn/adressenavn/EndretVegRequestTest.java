package no.statkart.wsclient.stedsnavn.adressenavn;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static no.statkart.wsclient.stedsnavn.adressenavn.AarsakTilEndringFraMatrikkelen.RETTELSE_AV_FEILFOERING;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test
 */
@Test
public class EndretVegRequestTest {

    public void nyEndretReq() {
        EndretVegRequest request = EndretVegRequest.Builder.builder()
            .tidsstempel(LocalDateTime.of(2019, Month.APRIL, 4, 16, 33, 28, 200))
            .vegId(1L)
            .adressekode(2)
            .adressenavn("Navn")
            .kommunenummer("0301")
            .koordinatsystem("KOORD")
            .kortnavn("kort")
            .nord(23.3)
            .ost(null)
            .vedtaksdato(LocalDate.of(2019, Month.APRIL, 2))
            .aarsakTilEndring(RETTELSE_AV_FEILFOERING)
            .build();

        assertThat(request).isNotNull();
        assertThat(request.getVegId()).isEqualTo(1L);
        assertThat(request.getAarsakTilEndring()).isEqualTo(RETTELSE_AV_FEILFOERING);
        assertThat(request.getOst()).isNull();
    }

}
