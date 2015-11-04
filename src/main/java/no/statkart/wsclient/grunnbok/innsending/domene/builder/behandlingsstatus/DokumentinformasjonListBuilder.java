package no.statkart.wsclient.grunnbok.innsending.domene.builder.behandlingsstatus;

import no.kartverket.grunnbok.wsapi.internal.domain.innsending.Dokumentinformasjon;
import no.kartverket.grunnbok.wsapi.internal.domain.innsending.DokumentinformasjonList;

import java.util.List;

public class DokumentinformasjonListBuilder {

   protected List<Dokumentinformasjon> listOfDokumentinformasjon;

   private DokumentinformasjonListBuilder() {
   }

   public static DokumentinformasjonListBuilder aDokumentinformasjonList() {
      return new DokumentinformasjonListBuilder();
   }

   public DokumentinformasjonListBuilder withDokumentinformasjonList(List<Dokumentinformasjon> dokumentinformasjonList) {
      this.listOfDokumentinformasjon = dokumentinformasjonList;
      return this;
   }

   public DokumentinformasjonListBuilder but() {
      return aDokumentinformasjonList().withDokumentinformasjonList(listOfDokumentinformasjon);
   }

   public DokumentinformasjonList build() {
      DokumentinformasjonList dokumentinformasjonList = new DokumentinformasjonList();
      dokumentinformasjonList.getDokumentinformasjon().addAll(listOfDokumentinformasjon);
      return dokumentinformasjonList;
   }
}
