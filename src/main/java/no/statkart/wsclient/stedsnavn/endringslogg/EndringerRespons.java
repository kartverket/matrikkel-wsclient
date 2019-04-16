package no.statkart.wsclient.stedsnavn.endringslogg;

import no.statkart.wsclient.stedsnavn.StedsnavnBoble;
import no.statkart.wsclient.stedsnavn.StedsnavnBobleId;

import java.util.List;
import java.util.Objects;

public class EndringerRespons {

   private List<Endring> endringList;
   private boolean alleEndringerFunnet;
   private StedsnavnBobleId.EndringId sisteEndringIdProsessert;
   private List<StedsnavnBoble> objects;

   public EndringerRespons(List<Endring> endringList, StedsnavnBobleId.EndringId sisteEndringIdProsessert, List<StedsnavnBoble> objects) {
      this.endringList = Objects.requireNonNull(endringList);
      this.sisteEndringIdProsessert = sisteEndringIdProsessert;
      this.objects = Objects.requireNonNull(objects);
   }

   public void setAlleEndringerFunnet(boolean alleEndringerFunnet) {
      this.alleEndringerFunnet = alleEndringerFunnet;
   }

   public List<Endring> getEndringList() {
      return endringList;
   }

   public boolean isAlleEndringerFunnet() {
      return alleEndringerFunnet;
   }

   public StedsnavnBobleId.EndringId getSisteEndringIdProsessert() {
      return sisteEndringIdProsessert;
   }

   public List<StedsnavnBoble> getObjects() {
      return objects;
   }

}
