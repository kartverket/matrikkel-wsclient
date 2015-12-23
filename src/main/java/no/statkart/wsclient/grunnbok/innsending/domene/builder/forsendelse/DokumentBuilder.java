package no.statkart.wsclient.grunnbok.innsending.domene.builder.forsendelse;

import com.google.common.collect.Lists;
import no.statkart.wsclient.grunnbok.innsending.domene.Dokument;
import no.statkart.wsclient.grunnbok.innsending.domene.Matrikkelenhetsendring;
import no.statkart.wsclient.grunnbok.innsending.domene.Rettsstiftelse;

import java.util.List;

/**
 *
 */
public class DokumentBuilder {
   private String dokumentreferanse;
   private List<Rettsstiftelse> rettsstiftelser = Lists.newArrayList();

   private DokumentBuilder() {
   }

   public static DokumentBuilder aDokument() {
      DokumentBuilder dokumentBuilder = new DokumentBuilder();
      dokumentBuilder.withDokumentreferanse("1"); //vi vil alltid kun ha ett dokument, kan derfor hardkode denne verdien
      return dokumentBuilder;
   }

   public DokumentBuilder withDokumentreferanse(String dokumentreferanse) {
      this.dokumentreferanse = dokumentreferanse;
      return this;
   }


   public DokumentBuilder withMatrikkelenhetsendring(Matrikkelenhetsendring rettsstiftelse) {
      rettsstiftelser.add(rettsstiftelse);
      return this;
   }


   public Dokument build() {
      Dokument dokument = new Dokument();
      dokument.setDokumentreferanse(dokumentreferanse);
      dokument.setRettsstiftelser(rettsstiftelser);
      return dokument;
   }


}
