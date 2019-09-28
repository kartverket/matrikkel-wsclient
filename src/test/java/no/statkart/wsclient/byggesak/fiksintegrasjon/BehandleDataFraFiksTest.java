package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.skif.exception.ValidationException;
import no.statkart.wsclient.byggesak.model.ByggesakBruksenhetDTO;
import no.statkart.wsclient.byggesak.model.ByggesakEtasjeDTO;
import no.statkart.wsclient.byggesak.model.MeldingFraSaksystemDTO;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BehandleDataFraFiksTest {

   ClassLoader classLoader = getClass().getClassLoader();
   String eksempelForsendelseId = "9205d6c4-86cf-4b9d-a174-e3a898031594";
   URL dekryptertZipfil = classLoader.getResource("byggesak/dekryptertZipfil.zip");
   URL eksempelXmlUrl = classLoader.getResource("byggesak/eksempelMed2Bygninger.xml");
   URL eksempelJsonUrl = classLoader.getResource("byggesak/eksempelJson.json");
   URL getEksempelJsonFeilMimetypeUrl = classLoader.getResource("byggesak/eksempelJsonFeilMimetype.json");
   String eksempelXml;
   String eksempelJson;
   String eksempelJsonFeilMimetype;

   @BeforeClass
   public void setup() {
      try {
         eksempelXml = IOUtils.toString(eksempelXmlUrl.openStream(), Charset.defaultCharset());
         eksempelJson = IOUtils.toString(eksempelJsonUrl.openStream(), Charset.defaultCharset());
         eksempelJsonFeilMimetype = IOUtils.toString(getEksempelJsonFeilMimetypeUrl.openStream(), Charset.defaultCharset());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

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
      Objects.requireNonNull(dekryptertZipfil, "Finner ikke filen: "+dekryptertZipfil);

      try(final ZipInputStream zipInputStream = new ZipInputStream(dekryptertZipfil.openStream())) {
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
      try {
         responsMelding.validerResponsMelding();
         fail("Skulle kastet exception når XML mangler");
      } catch (ValidationException e) {
         assertThat(e.getMessage()).contains("Mangler XML");
      }

      responsMelding.setDownloadUrl(null);
      responsMelding.setForsendelseId(null);

      try {
         responsMelding.validerResponsMelding();
         fail("Skulle kastet exception når alt mangler");
      } catch (ValidationException e) {
         assertThat(e.getMessage()).contains(Arrays.asList("Mangler URL", "Mangler forsendelseId"));
      }

   }

   @Test
   public void testAtByggesakXmlMed2BygningerGir2MeldingerFraSaksystemInfos() {
      Set<ResponsMelding> responsMeldinger = BehandleRespons.lagResponsMeldinger(eksempelJson, null);
      responsMeldinger.forEach(responsMelding -> responsMelding.setByggesakXml(eksempelXml));
      assertThat(responsMeldinger.size()).as("Sjekk antall responsmeldinger som er opprettet").isEqualTo(1);
      String forsendelseId = responsMeldinger.iterator().next().getForsendelseId();

      Set<MeldingFraSaksystemDTO> meldingFraSaksystemDTOS = MeldingerFraSaksystemInfoBuilder.build(responsMeldinger);
      assertThat(meldingFraSaksystemDTOS.size()).as("Sjekk antall MeldingFraSaksystem som er opprettet").isEqualTo(2);
      meldingFraSaksystemDTOS.forEach(melding -> assertThat(melding.getForsendelsesId()).isEqualTo(forsendelseId));
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
