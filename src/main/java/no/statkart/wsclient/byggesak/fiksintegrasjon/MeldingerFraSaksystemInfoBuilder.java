package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.geointegrasjon.rep.matrikkel.foering.v1.AdresseType;
import no.geointegrasjon.rep.matrikkel.foering.v1.BruksenhetListe;
import no.geointegrasjon.rep.matrikkel.foering.v1.BruksenhetType;
import no.geointegrasjon.rep.matrikkel.foering.v1.ByggesakType;
import no.geointegrasjon.rep.matrikkel.foering.v1.BygningListe;
import no.geointegrasjon.rep.matrikkel.foering.v1.BygningType;
import no.geointegrasjon.rep.matrikkel.foering.v1.EnergiforsyningType;
import no.geointegrasjon.rep.matrikkel.foering.v1.EnergiforsyningTypeType;
import no.geointegrasjon.rep.matrikkel.foering.v1.EtasjeListe;
import no.geointegrasjon.rep.matrikkel.foering.v1.EtasjeType;
import no.geointegrasjon.rep.matrikkel.foering.v1.MatrikkelnummerListe;
import no.geointegrasjon.rep.matrikkel.foering.v1.MatrikkelnummerType;
import no.geointegrasjon.rep.matrikkel.foering.v1.MatrikkelopplysningerType;
import no.geointegrasjon.rep.matrikkel.foering.v1.PartType;
import no.geointegrasjon.rep.matrikkel.foering.v1.VarmefordelingType;
import no.statkart.wsclient.byggesak.model.ByggesakBruksenhetDTO;
import no.statkart.wsclient.byggesak.model.ByggesakEtasjeDTO;
import no.statkart.wsclient.byggesak.model.ByggesakDTO;
import no.statkart.wsclient.byggesak.model.MeldingFraSaksystemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Hjelpeklasse for å bygge alle info-objekter som hører til en Melding-info
 * 1 melding representerer 1 byggesak fra 1 forsendelse.
 * En forsendelse kan ha flere byggesaker.
 */
class MeldingerFraSaksystemInfoBuilder {
   private static Logger logger = LoggerFactory.getLogger(MeldingerFraSaksystemInfoBuilder.class);
   /**
    * Bygger et set med infomeldinger basert på responsmeldingene.
    * Deserialiserer xml-filen med bygginfo.
    * @param responsMeldinger ResponsMeldinger som har data meldingen skal bygges fra.
    * @return Sett med ferdig behandlede objekter.
    */
   static Set<MeldingFraSaksystemDTO> build(Set<ResponsMelding> responsMeldinger) {

      Set<MeldingFraSaksystemDTO> meldingFraSaksystemDTOS = new HashSet<>();

      for(ResponsMelding responsMelding : responsMeldinger) {
         // fellesinfo pr. bygning i forsendelse
         String tittel = responsMelding.getTittel();
         Date date = responsMelding.getDate();
         String forsendelseId = responsMelding.getForsendelseId();

         // de-serialiser xml (ByggesakType er rot-noden)
         String byggesakXml = responsMelding.getByggesakXml();
         ByggesakType byggesakType = lagObjekterFraXML(byggesakXml);
         if(byggesakType == null) continue;

         String kommunenr = responsMelding.getKommunenr() != null ?
               responsMelding.getKommunenr() : finnKommuneNr(byggesakType.getMatrikkelopplysninger().getValue());
         /*
          * Hvis det ikke finnes noe informasjon om tilhørende kommune, skal ikke info lages
          */
         if(kommunenr != null) {
            // hent ut listen med bygninger
            MatrikkelopplysningerType matrikkelopplysningerType = Objects.requireNonNull(
                  evaluateObject(byggesakType.getMatrikkelopplysninger()), "Finner ikke matrikkelopplysninger");

            List<BygningType> bygningsliste = Objects.requireNonNull(
                  evaluateObject(matrikkelopplysningerType.getBygning()).getBygning(), "Finner ingen bygningsliste");

            // opprett en info-melding pr. bygning.
            for (BygningType bygningType : bygningsliste) {
               MeldingFraSaksystemDTO meldingFraSaksystemDTO = new MeldingFraSaksystemDTO();

               // metadata om forsendelsen
               meldingFraSaksystemDTO.setForsendelsesId(forsendelseId);
               meldingFraSaksystemDTO.setInfo(tittel + " " + byggesakType.getTittel() + " " + bygningType.hashCode());
               meldingFraSaksystemDTO.setCreatedAt(date.toInstant());
               meldingFraSaksystemDTO.setKommuneNummer(kommunenr);
               // hvilket brukstilfelle meldingen skal høre til
               meldingFraSaksystemDTO.setBrukstilfelleKode(byggesakType.getKategori().getKode());

               // BYGNINGSDATA
               ByggesakDTO byggesakDTO = new ByggesakDTO();

               // metadata byggesak
               byggesakDTO.setTittel(tittel);

               // tiltakshaver
               PartType tiltakshaver = evaluateObject(byggesakType.getTiltakshaver());
               if(tiltakshaver != null) {
                  String tiltakshaverIdNummer = Optional.ofNullable(evaluateObject(tiltakshaver.getFoedselsnummer()))
                        .orElse(evaluateObject(tiltakshaver.getOrganisasjonsnummer()));
                  byggesakDTO.setTiltakshaverIdNummer(tiltakshaverIdNummer);
               }

               // ansvarlig søker
               PartType ansvarligSoker = evaluateObject(byggesakType.getAnsvarligSoeker());
               if(ansvarligSoker != null) {
                  String ansvarligSokerIdNr = Optional.ofNullable(evaluateObject(ansvarligSoker.getFoedselsnummer()))
                        .orElse(evaluateObject(ansvarligSoker.getOrganisasjonsnummer()));
                  byggesakDTO.setAnsvarligSokerIdNummer(ansvarligSokerIdNr);
               }

               // Bygningsinformasjon
               meldingFraSaksystemDTO.setByggesakDTO(buildByggesakDTO(byggesakDTO, bygningType));
               meldingFraSaksystemDTOS.add(meldingFraSaksystemDTO);
            }
         }
      }
      return meldingFraSaksystemDTOS;
   }

   /*
    * Info om 1 bygning i byggesak-xml
    */
   private static ByggesakDTO buildByggesakDTO(ByggesakDTO byggesakDTO, BygningType bygningType) {
      // BYGNINGSDATA
      byggesakDTO.setBygningsnr(evaluateObject(bygningType.getBygningsnummer()));
      byggesakDTO.setBebygdAreal(evaluateObject(bygningType.getBebygdAreal()));
      byggesakDTO.setHarHeis(evaluateObject(bygningType.getHarHeis()));

      // KODER
      byggesakDTO.setAvlopsKode(getKodeOrNull(evaluateObject(bygningType.getAvlop())));
      byggesakDTO.setNaringsgruppeKode(getKodeOrNull(evaluateObject(bygningType.getNaeringsgruppe())));
      byggesakDTO.setVannforsyningsKode(getKodeOrNull(evaluateObject(bygningType.getVannforsyning())));

      // flere koder i liste
      EnergiforsyningType energiforsyningType = evaluateObject(bygningType.getEnergiforsyning());
      // oppvarmingskoder
      try{
         List<VarmefordelingType> varmefordelingListe = evaluateObject(energiforsyningType.getVarmefordeling()).getVarmefordeling();

         byggesakDTO.getOppvarmingsKoder().addAll(
               varmefordelingListe.stream()
                     .map(VarmefordelingType::getKode)
                     .collect(Collectors.toSet())
         );
      } catch (NullPointerException ignored) { }

      // energikildekoder
      try {
         List<EnergiforsyningTypeType> energiforsyningTypeTypeListe
               = evaluateObject(energiforsyningType.getEnergiforsyning()).getEnergiforsyningType();

         byggesakDTO.getEnergikildeKoder().addAll(
               energiforsyningTypeTypeListe.stream()
                     .map(EnergiforsyningTypeType::getKode)
                     .collect(Collectors.toSet())
         );

      } catch (NullPointerException ignored) { }

      // ETASJER
      EtasjeListe etasjeListe = evaluateObject(bygningType.getEtasjer());
      if(etasjeListe != null) {
         byggesakDTO.getEtasjer().addAll(buildByggesakEtasjeDTOs(etasjeListe.getEtasje()));
      }

      // BRUKSENHETER
      BruksenhetListe bruksenhetListe = evaluateObject(bygningType.getBruksenheter());
      if(bruksenhetListe != null) {
         byggesakDTO.getBruksenheter().addAll(buildByggesakBruksenhetDTOs(bruksenhetListe.getBruksenhet()));
      }

      return byggesakDTO;
   }

   /*
    * Info om 1 bruksenhet på 1 bygning
    */
   private static Set<ByggesakBruksenhetDTO> buildByggesakBruksenhetDTOs(List<BruksenhetType> bruksenhetListe) {
      Set<ByggesakBruksenhetDTO> bruksenhetDTOs = new HashSet<>();

      for (BruksenhetType bruksenhetType : bruksenhetListe) {
         ByggesakBruksenhetDTO bruksenhetDTO = new ByggesakBruksenhetDTO();

         // required
         bruksenhetDTO.setLopenr(bruksenhetType.getBruksenhetsnummer().getLoepenummer());
         bruksenhetDTO.setEtasjenr(bruksenhetType.getBruksenhetsnummer().getEtasjenummer());
         bruksenhetDTO.setEtasjeplanKode(bruksenhetType.getBruksenhetsnummer().getEtasjeplan().getKode());

         // generell info
         bruksenhetDTO.setBruksAreal(evaluateObject(bruksenhetType.getBruksareal()));
         bruksenhetDTO.setAntallRom(evaluateObject(bruksenhetType.getAntallRom()));
         bruksenhetDTO.setAntallBad(evaluateObject(bruksenhetType.getAntallBad()));
         bruksenhetDTO.setAntallWC(evaluateObject(bruksenhetType.getAntallWC()));

         // matrikkelnummer
         MatrikkelnummerType matrikkelnummerType = evaluateObject(bruksenhetType.getMatrikkelnummer());
         if(matrikkelnummerType != null) {
            bruksenhetDTO.setKommunenr(evaluateObject(matrikkelnummerType.getKommunenummer()));
            bruksenhetDTO.setGardsnr(evaluateObject(matrikkelnummerType.getGaardsnummer()));
            bruksenhetDTO.setBruksnr(evaluateObject(matrikkelnummerType.getBruksnummer()));
            bruksenhetDTO.setFestenr(evaluateObject(matrikkelnummerType.getFestenummer()));
            bruksenhetDTO.setSeksjonsnr(evaluateObject(matrikkelnummerType.getSeksjonsnummer()));
         }
         // adresse
         AdresseType adresseType = evaluateObject(bruksenhetType.getAdresse());
         if(adresseType != null) {
            bruksenhetDTO.setAdressekode(evaluateObject(adresseType.getAdressekode()));
            bruksenhetDTO.setAdressenummer(evaluateObject(adresseType.getAdressenummer()));
            bruksenhetDTO.setAdressebokstav(evaluateObject(adresseType.getAdressebokstav()));
         }

         final List<String> feilkoder = bruksenhetDTO.validerBruksenhetInfo();
         if(feilkoder.isEmpty()) bruksenhetDTOs.add(bruksenhetDTO);
      }

      return bruksenhetDTOs;
   }

   /*
    * Info om 1 etasje på 1 bygning
    */
   private static Set<ByggesakEtasjeDTO> buildByggesakEtasjeDTOs(List<EtasjeType> etasjeListe) {
      Set<ByggesakEtasjeDTO> etasjer = new HashSet<>();

      for (EtasjeType etasjeType : etasjeListe) {
         ByggesakEtasjeDTO etasjeDTO = new ByggesakEtasjeDTO();

         // required - informasjon som skal være med hvis det finnes EtasjeType-objekter
         etasjeDTO.setEtasjenr(etasjeType.getEtasjenummer());
         etasjeDTO.setEtasjeplanKode(etasjeType.getEtasjeplan().getKode());

         // diverse etasje-info
         etasjeDTO.setAntallBoenheter(evaluateObject(etasjeType.getAntallBoenheter()));

         etasjeDTO.setBruksarealTilAnnet(evaluateObject(etasjeType.getBruksarealTilAnnet()));
         etasjeDTO.setBruksarealTilBolig(evaluateObject(etasjeType.getBruksarealTilBolig()));
         etasjeDTO.setBruksarealTotalt(evaluateObject(etasjeType.getBruksarealTotalt()));

         etasjeDTO.setBruttoarealTilAnnet(evaluateObject(etasjeType.getBruttoarealTilAnnet()));
         etasjeDTO.setBruttoarealTilBolig(evaluateObject(etasjeType.getBruttoarealTilBolig()));
         etasjeDTO.setBruttoarealTilAnnet(evaluateObject(etasjeType.getBruksarealTotalt()));

         final List<String> feilkoder = etasjeDTO.validerEtasjeInfo();
         if(feilkoder.isEmpty()) etasjer.add(etasjeDTO);
      }

      return etasjer;
   }

   /*
    * Hvis det ikke finnes kommunetilhørighet i forsendelsens metadata,
    * prøv å finne kommunetilhørighet fra bruksenheter eller matrikkelnummerlisten
    */
   private static String finnKommuneNr(MatrikkelopplysningerType matrikkelopplysninger) {
      Set<String> potensielleKommunenr = new HashSet<>();
      BygningListe bygningListe = evaluateObject(matrikkelopplysninger.getBygning());

      // prøv fra bruksenhet
      if(bygningListe != null) {
         bygningListe.getBygning().stream()
               .filter(bygningType -> evaluateObject(bygningType.getBruksenheter()) != null)
               .flatMap(bygningType -> bygningType.getBruksenheter().getValue().getBruksenhet().stream())
               .map(bruksenhetType -> evaluateObject(bruksenhetType.getMatrikkelnummer()))
               .filter(Objects::nonNull)
               .map(matrikkelnummerType -> evaluateObject(matrikkelnummerType.getKommunenummer()))
               .filter(Objects::nonNull)
               .forEach(potensielleKommunenr::add);
      }
      // har vi funnet et kommunenummer
      if(!potensielleKommunenr.isEmpty()) return potensielleKommunenr.iterator().next();

      // sjekk matrikkelnummerliste
      MatrikkelnummerListe matrikkelnummerListe = evaluateObject(matrikkelopplysninger.getEiendomsidentifikasjon());
      if(matrikkelnummerListe != null) {
         matrikkelnummerListe.getMatrikkelnummer().stream()
               .map(matrikkelnummerType -> evaluateObject(matrikkelnummerType.getKommunenummer()))
               .filter(Objects::isNull)
               .forEach(potensielleKommunenr::add);
      }
      return potensielleKommunenr.isEmpty() ? null : potensielleKommunenr.iterator().next();
   }

   // lag objekter av xml basert på matrikkelfoering.xsd (modellen)
   private static ByggesakType lagObjekterFraXML(String xml) {
      try {
         JAXBContext jaxbContext = JAXBContext.newInstance("no.geointegrasjon.rep.matrikkel.foering.v1");
         Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

         XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));
         JAXBElement<ByggesakType> rootElement = unmarshaller.unmarshal(reader, ByggesakType.class);

         return rootElement.getValue();
      } catch (JAXBException | XMLStreamException e) {
         logger.error("Feil oppstått i deserialisering av XML data: "+e.getMessage());
         return null;
      }
   }

   // siden et jaxbelement kan være null, og value også kan være null
   private static <T> T evaluateObject(JAXBElement<T> element) {
      return element == null ? null : element.getValue();
   }

   // for å unngå nullpointer
   private static <T> String getKodeOrNull(T element) {
      try {
         return element != null ? (String) element.getClass().getMethod("getKode").invoke(element) : null;
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {return null; }
   }
}