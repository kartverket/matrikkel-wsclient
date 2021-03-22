package no.statkart.wsclient.grunnbokv2.innsending;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelsesstatus;
import no.statkart.wsclient.grunnbokv2.innsending.domene.UsignertGrunnboksutskrift;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.DokumentBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.ForsendelseBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.UsignertMeldingBuilder;
import no.statkart.wsclient.grunnbokv2.innsending.testdatafactory.ForsendelseFactory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXParseException;

import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceException;

import static org.testng.Assert.assertNotNull;

/**
 * Test av {@link DefaultInnsendingServiceWS} som mapper
 */
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

   public void tinglysFradelingForretning() throws Exception {
      Forsendelse forsendelse = ForsendelseFactory.fradelingForsendelse();
      Forsendelsesstatus forsendelsesstatus = innsendingService.sendTilTinglysing(forsendelse);
      assertNotNull(forsendelsesstatus);
   }

   @Test
   public void invalidForsendelse() throws Exception {
      final Dokument dokumentWithoutDokumentReferanse = DokumentBuilder.aDokument().withDokumentreferanse(null).build();
      final ForsendelseBuilder invalidForsendelse = ForsendelseFactory.defaultForsendelse()
            .but()
            .withUsignertMelding(UsignertMeldingBuilder.anUsignertMelding()
                  .withDokumenter(Lists.newArrayList(dokumentWithoutDokumentReferanse))
                  .build());

      Assertions.assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
         @Override
         public void call() throws Throwable {
            innsendingService.sendTilTinglysing(invalidForsendelse.build());
         }
      }).isInstanceOf(WebServiceException.class)
            .hasCauseInstanceOf(SAXParseException.class)
            .hasMessageContaining("Invalid content was found starting with element ")
            .hasMessageContaining("rettsstiftelser");
   }

   public void hentStatusPakkerUtGrunnboksutskrift() {
      final String innsendingId = "2";
      final Forsendelsesstatus forsendelsesstatus = innsendingService.hentStatus(innsendingId);

      Preconditions.checkState("TINGLYST".equals(forsendelsesstatus.getBehandlingsutfall()));
      Preconditions.checkState(forsendelsesstatus.getTinglysingsinformasjon().getSignerteGrunnboksutskrifter().isEmpty());
      Preconditions.checkState(!forsendelsesstatus.getTinglysingsinformasjon().getGrunnboksutskrifter().isEmpty());


       for (UsignertGrunnboksutskrift usignertGrunnboksutskrift : forsendelsesstatus.getTinglysingsinformasjon().getGrunnboksutskrifter()) {
         Preconditions.checkNotNull(usignertGrunnboksutskrift.getUtskrift());
         Preconditions.checkNotNull(usignertGrunnboksutskrift.getUtskrift().getUsignertDokument());
      }
   }
}
