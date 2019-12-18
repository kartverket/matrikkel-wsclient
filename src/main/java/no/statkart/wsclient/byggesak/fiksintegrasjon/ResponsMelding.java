package no.statkart.wsclient.byggesak.fiksintegrasjon;

import no.statkart.skif.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representerer 1 forsendelse fra FIKS.
 * Lagrer metadata fra forsendelsen.
 */
class ResponsMelding {
   private Logger logger = LoggerFactory.getLogger(ResponsMelding.class);

   /**
    * ForsendelsesId på (JSON) forsendelsen som hentes via FIKS.
    */
   String forsendelseId = null;
   /**
    * Kommunen meldingen gjelder
    */
   String kommunenr = null;
   /**
    * Dato på (JSON) forsendelsen.
    */
   Date date;
   /**
    * Filnavnet på xml-filen som inneholder byggesakdata.
    */
   String xmlFilnavn;

   /**
    * Hvis det finnes vedlegg, og det finnes en xml. Denne kan inneholde flere bygninger (flere brukstilfeller)
    */
   String byggesakXml = null;
   /**
    * Hvis forsendelsen har vedlegg, vil disse kunne lastes ned via denne URL. Vedlegget kan være pdf eller zip, men vi vil bare motta zip-filer.
    */
   String downloadUrl = null;

   /**
    * Tittel på forsendelsen.
    */
   String tittel;


   ResponsMelding() { }

   ResponsMelding(String xml) {

      this.byggesakXml = xml;
   }

   void setKommunenr(String kommunenr) { this.kommunenr = kommunenr; }

   String getKommunenr() { return kommunenr; }

   String getTittel() {
      return tittel;
   }

   void setTittel(String tittel) {
      this.tittel = tittel;
   }

   void setDownloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
   }

   String getDownloadUrl() {
      return downloadUrl;
   }

   void setForsendelseId(String forsendelseId) {
      this.forsendelseId = forsendelseId;
   }

   String getForsendelseId() {
      return forsendelseId;
   }

   void setDate(Date date) {
      this.date = date;
   }

   Date getDate() {
      return date;
   }

   void setByggesakXml(String byggesakXml) {
      this.byggesakXml = byggesakXml;
   }

   String getByggesakXml() {
      return byggesakXml;
   }

   void setXmlFilnavn(String xmlFilnavn) {
      this.xmlFilnavn = xmlFilnavn;
   }

   String getXmlFilnavn() {
      return xmlFilnavn;
   }

   List<String> validerResponsMelding() throws ValidationException {
      List<String> feilmeldinger = new ArrayList<>(0);

      if(this.forsendelseId == null) feilmeldinger.add("Mangler forsendelseId");
      if(this.byggesakXml == null) feilmeldinger.add("Mangler XML");
      if(this.downloadUrl == null) feilmeldinger.add("Mangler URL til vedlegg");

      if (!feilmeldinger.isEmpty()) {
         logger.debug("Responsmelding validerer ikke: " + this.forsendelseId);
      }
      return feilmeldinger;
   }
}
