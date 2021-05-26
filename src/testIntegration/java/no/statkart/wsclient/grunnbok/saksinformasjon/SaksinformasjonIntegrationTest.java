package no.statkart.wsclient.grunnbok.saksinformasjon;

import no.kartverket.grunnbok.wsapi.v2.domain.basistyper.GrunnbokContext;
import no.kartverket.grunnbok.wsapi.v2.domain.register.saksinformasjon.Saksinformasjon;
import no.kartverket.grunnbok.wsapi.v2.domain.register.saksinformasjon.SaksinformasjonId;
import no.kartverket.grunnbok.wsapi.v2.exception.ServiceException;
import no.statkart.skif.util.NullHostnameVerifier;
import no.statkart.wsclient.IntegrationTestProperties;
import no.statkart.wsclient.grunnbok.GrunnbokHelper;
import no.statkart.wsclient.grunnbokv2.saksinformasjon.DefaultSaksinformasjonWS;
import no.statkart.wsclient.grunnbokv2.saksinformasjon.SaksinformasjonWS;
import no.statkart.wsclient.grunnbokv2.store.DefaultStoreWS;
import no.statkart.wsclient.grunnbokv2.store.StoreWS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integrajonstest av WS signatur og retur verdier ifra Grunnboksystemet er som forventet [MAT-12778].
 */
public class SaksinformasjonIntegrationTest {

   private SaksinformasjonWS saksinformasjon;
   private GrunnbokHelper grunnbokHelper;
   private StoreWS store;


   /**
    * @see SaksinformasjonWS#findSaksinformasjonIdForInnsendingId(String, GrunnbokContext)
    */
   @Test
   public void testFinnSaksinformasjonId() throws Exception {
      final SaksinformasjonId saksinformasjonIdForInnsendingId =
          saksinformasjon.findSaksinformasjonIdForInnsendingId("828487498", grunnbokHelper.context());
      assertThat(saksinformasjonIdForInnsendingId).isNotNull();

      assertThat(saksinformasjonIdForInnsendingId.getValue()).isNotNull().isEqualTo("828487497");

      Saksinformasjon saksinformasjon = (Saksinformasjon) store.getObject(saksinformasjonIdForInnsendingId, grunnbokHelper.context());
      assertThat(saksinformasjon).isNotNull();
      assertThat(saksinformasjon.getInnsender().getReferanse()).isNotNull().isEqualTo("195/3000702/3194022/89-20-0237 Geminivegen 11B");
   }


   @BeforeTest
   public void setUp() throws ServiceException {
      final IntegrationTestProperties config = new IntegrationTestProperties();
      final String grunnbokUser = config.getGrunnbokUser();
      final String grunnbokPassword = config.getGrunnbokPassword();
      if (saksinformasjon == null) {
         final NullHostnameVerifier hostnameVerifier = new NullHostnameVerifier();
         try {
            saksinformasjon = new DefaultSaksinformasjonWS(grunnbokUser, grunnbokPassword, config.getGrunnbokSaksinformasjonServiceUrl());
         } catch (Throwable t) {
            t.printStackTrace();
         }
      }
      if (store == null) {
         final NullHostnameVerifier hostnameVerifier = new NullHostnameVerifier();
         try {
            store = new DefaultStoreWS(grunnbokUser, grunnbokPassword, config.getGrunnbokStoreServiceUrl());
         } catch (Throwable t) {
            t.printStackTrace();
         }
      }
      grunnbokHelper = new GrunnbokHelper();
   }

   @AfterTest
   public void teardown() throws ServiceException {
      store = null;
      saksinformasjon = null;
      grunnbokHelper = null;
   }


}
