package no.statkart.wsclient.grunnbok.innsending;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbok.innsending.domene.SDODokument;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.ForsendelseBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.SDODokumentBuilder;
import no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse.SignertMeldingBuilder;
import no.statkart.wsclient.grunnbok.innsending.testdatafactory.ForsendelseFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceException;

import static org.testng.Assert.assertNotNull;

@Test
public class DefaultInnsendingServiceWSIT {

   private static final String ADDRESS_TO_LOCAL_STUB_SERVICE = "http://localhost:9000/services/InnsendingService";

   private static Endpoint endpoint;
   private static InnsendingServiceWS innsendingService;

   @BeforeTest
   public static void setUp() {
      endpoint = Endpoint.publish(ADDRESS_TO_LOCAL_STUB_SERVICE, new InnsendingServiceStub());
      innsendingService = new DefaultInnsendingServiceWS("grunnbokdevEkstern", "", ADDRESS_TO_LOCAL_STUB_SERVICE, true);
   }

   @AfterTest
   public static void tearDown() {
      endpoint.stop();
   }

   public void tinglysMelding() throws Exception {
      Forsendelsesstatus forsendelsesstatus = innsendingService.sendTilTinglysing(ForsendelseFactory.defaultForsendelse().build());
      assertNotNull(forsendelsesstatus);
   }

   @Test(expectedExceptions = WebServiceException.class)
   public void invalidForsendelse() throws Exception {
      SDODokument sdoDokumentWithoutBytes = SDODokumentBuilder.aSDODokument().build();
      ForsendelseBuilder invalidForsendelse = ForsendelseFactory.defaultForsendelse()
            .but()
            .withSignertMelding(SignertMeldingBuilder.aSignertMelding()
                  .withDokumenter(Lists.newArrayList(sdoDokumentWithoutBytes))
                  .build());

      innsendingService.sendTilTinglysing(invalidForsendelse.build());
   }

}
