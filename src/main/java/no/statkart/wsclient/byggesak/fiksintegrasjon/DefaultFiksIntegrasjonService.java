package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.skif.exception.OperationalException;
import no.statkart.wsclient.byggesak.model.MeldingFraSaksystemDTO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class DefaultFiksIntegrasjonService implements FiksIntegrasjonService {
   private Logger logger = LoggerFactory.getLogger(DefaultFiksIntegrasjonService.class);

   private final String serviceBrukernavn;
   private final String servicePassord;
   private final String HENT_FORSENDELSER;
   private final String KVITTER_MOTTAK;

   public DefaultFiksIntegrasjonService(String serviceBrukernavn, String servicePassord, String hentForsendelserUrl, String kvitterMottakUrl) {
      this.serviceBrukernavn = serviceBrukernavn;
      this.servicePassord = servicePassord;
      this.HENT_FORSENDELSER = hentForsendelserUrl;
      this.KVITTER_MOTTAK = kvitterMottakUrl;
   }

   @Override
   public Set<MeldingFraSaksystemDTO> hentForsendelser(Set<String> eksisterendeForsendelseIds, URL privateKeyUrl) {
      HttpGet request = new HttpGet(HENT_FORSENDELSER);
      String responseJson;

      try(CloseableHttpResponse response = FiksMottakerRestClient.executeHttpsRequest(request, serviceBrukernavn, servicePassord)) {
         Objects.requireNonNull(response, "Tomt resultat fra kall til FIKS");
         responseJson = EntityUtils.toString(response.getEntity());
      } catch (IOException e) {
         logger.error("Get-kall til Fiks feilet: "+request.getRequestLine()+" Exception: "+e.getMessage(), e);
         throw new OperationalException("Feil i FIKS-kall: "+e.getMessage(), e);
      }

      // lag responsmeldinger fra serverresponsen som er nye og inneholder xml-fil
      Set<ResponsMelding> responsMeldinger = BehandleRespons.lagResponsMeldinger(responseJson, eksisterendeForsendelseIds);

      // last ned vedlegg
      LastNedVedlegg lastNedVedlegg = new LastNedVedlegg(serviceBrukernavn, servicePassord, privateKeyUrl);

      // last ned xml-fil for responsMelding
      for (ResponsMelding responsMelding : responsMeldinger) {
         responsMelding.setByggesakXml(lastNedVedlegg.lastNedVedlegg(responsMelding));
      }

      // fjern meldinger uten uten XML (hvis nedlasting har feilet)
      responsMeldinger.removeAll(
            responsMeldinger.stream()
                  .filter(o -> o.getByggesakXml() == null)
                  .collect(Collectors.toSet())
      );

      // filtrer ut de responsmeldingene som mangler obligatorisk informasjon
      responsMeldinger = responsMeldinger.stream()
            .filter(responsMelding -> responsMelding.validerResponsMelding().isEmpty())
            .collect(Collectors.toSet());

      return MeldingerFraSaksystemDTOBuilder.build(responsMeldinger);
   }

   @Override
   public boolean sjekkOmForsendelseErTilgjengelig(String forsendelseId) {
      HttpGet request = new HttpGet(HENT_FORSENDELSER);

      try(CloseableHttpResponse response = FiksMottakerRestClient.executeHttpsRequest(request, serviceBrukernavn, servicePassord)) {
         Objects.requireNonNull(response, "Respons fra server er null");
         String responseJson = EntityUtils.toString(response.getEntity());

         return responseJson.contains(forsendelseId);
      } catch (IOException e) {
         logger.error("Get-kall til FIKS feilet: "+request.getRequestLine()+" Exception: "+e.getMessage(), e);
         throw new OperationalException("Get-kall til FIKS feilet: "+e.getMessage());
      }
   }

   @Override
   public boolean kvitterMelding(String forsendelseId) {
      boolean tilgjengelig = sjekkOmForsendelseErTilgjengelig(forsendelseId);

      if(tilgjengelig) {
         HttpPost httpPost = new HttpPost(KVITTER_MOTTAK + forsendelseId);
         try(CloseableHttpResponse response = FiksMottakerRestClient.executeHttpsRequest(httpPost, serviceBrukernavn, servicePassord)) {
            return response != null;
         } catch (IOException e) {
            logger.error("Kvitter Mottatt feilet: "+e.getMessage(), e);
            throw new OperationalException("Kvitter til FIKS feilet: "+e.getMessage());
         }
      } else { return false; }
   }
}
