package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.wsclient.byggesak.model.ByggesakBruksenhetDTO;
import no.statkart.wsclient.byggesak.model.ByggesakEtasjeDTO;
import no.statkart.wsclient.byggesak.model.ByggesakmeldingDTO;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.Assertions.fail;

@SuppressWarnings("ConstantConditions")
public class BehandleDataFraFiksTest {

   static final ClassLoader classLoader = BehandleDataFraFiksTest.class.getClassLoader();
   static final String eksempelForsendelseId = "9205d6c4-86cf-4b9d-a174-e3a898031594";
   static final String eksempelXml = contentOf(classLoader.getResource("byggesak/eksempelMed2Bygninger.xml"), UTF_8);
   static final String eksempelJson = contentOf(classLoader.getResource("byggesak/eksempelJson.json"), UTF_8);
   static final String eksempelJsonFeilMimetype = contentOf(classLoader.getResource("byggesak/eksempelJsonFeilMimetype.json"), UTF_8);

   @Test
   public void testLagResponsMeldinger() {
      final Set<ResponsMelding> responsMeldinger = BehandleRespons.lagResponsMeldinger(eksempelJson, null);
      assertThat(responsMeldinger.size()).isEqualTo(1);

      final Set<ResponsMelding> responsMeldingerUtenXml = BehandleRespons.lagResponsMeldinger(eksempelJsonFeilMimetype, null);
      assertThat(responsMeldingerUtenXml).isEmpty();

      final Set<ResponsMelding> responsMeldingerMedEksisterendeForsendelse = BehandleRespons.lagResponsMeldinger(eksempelJson, Collections.singleton(eksempelForsendelseId));
      assertThat(responsMeldingerMedEksisterendeForsendelse).isEmpty();
   }

   @Test
   public void testHenteUtXmlFraZip() {
      final URL zipFil = Objects.requireNonNull(classLoader.getResource("byggesak/dekryptertZipfil.zip"));

      try (final ZipInputStream zipInputStream = new ZipInputStream(zipFil.openStream())) {
         assertThat(zipInputStream.getNextEntry().getName()).isEqualTo("eksempelMed2Bygninger.xml");
      } catch (Exception e) {
         e.printStackTrace();
         fail("Feil ved lesing av zip-fil");
      }
   }

   @Test
   public void validerResponsMelding() {
      ResponsMelding responsMelding = new ResponsMelding(eksempelXml);
      responsMelding.setForsendelseId("12345");
      responsMelding.setDownloadUrl("www.eksempel.com");
      responsMelding.validerResponsMelding();

      responsMelding.setByggesakXml(null);
      assertThat(responsMelding.validerResponsMelding()).isNotEmpty();
      assertThat(responsMelding.validerResponsMelding()).contains("Mangler XML");

      responsMelding.setDownloadUrl(null);
      responsMelding.setForsendelseId(null);
      assertThat(responsMelding.validerResponsMelding().size()).isEqualTo(3);
      assertThat(responsMelding.validerResponsMelding()).contains("Mangler URL til vedlegg", "Mangler forsendelseId", "Mangler XML");
   }

   @Test
   public void testAtByggesakXmlMed2BygningerGir2ByggesakmeldingDTOs() {
      Set<ResponsMelding> responsMeldinger = BehandleRespons.lagResponsMeldinger(eksempelJson, null);
      responsMeldinger.forEach(responsMelding -> responsMelding.setByggesakXml(eksempelXml));
      assertThat(responsMeldinger.size()).as("Sjekk antall responsmeldinger som er opprettet").isEqualTo(1);
      String forsendelseId = responsMeldinger.iterator().next().getForsendelseId();

      Set<ByggesakmeldingDTO> byggesakmeldingDTOS = ByggesakmeldingerDTOBuilder.build(responsMeldinger);
      assertThat(byggesakmeldingDTOS.size()).as("Sjekk antall Byggesakmelding som er opprettet").isEqualTo(2);
      byggesakmeldingDTOS.forEach(melding -> assertThat(melding.getForsendelsesId()).isEqualTo(forsendelseId));
   }

   @Test
   public void testValiderBruksenhetInfo() {
      ByggesakBruksenhetDTO byggesakBruksenhetDTO = new ByggesakBruksenhetDTO();
      byggesakBruksenhetDTO.validerBruksenhetInfo()
            .forEach(s -> assertThat(s).contains("ikke satt"));

      byggesakBruksenhetDTO.setEtasjeplanKode("H");
      byggesakBruksenhetDTO.setEtasjenr(BigInteger.ONE);
      byggesakBruksenhetDTO.setLopenr(BigInteger.ONE);

      assertThat(byggesakBruksenhetDTO.validerBruksenhetInfo()).isEmpty();
   }

   @Test
   public void testValiderEtasjeInfo() {
      ByggesakEtasjeDTO etasjeInfo = new ByggesakEtasjeDTO();
      etasjeInfo.validerEtasjeInfo()
            .forEach(s -> assertThat(s).contains("ikke satt"));

      etasjeInfo.setEtasjeplanKode("H");
      etasjeInfo.setEtasjenr(BigInteger.ONE);
      assertThat(etasjeInfo.validerEtasjeInfo()).isEmpty();
   }
}
