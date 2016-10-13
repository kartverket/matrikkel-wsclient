
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by onila on 28.03.2014.
 */
public class LesInnWsdlTilResorces {

  public class AppEx extends Exception {
    public AppEx() {
    }

    public AppEx(String message) {
      super(message);
    }

    public AppEx(String message, Throwable cause) {
      super(message, cause);
    }

    public AppEx(Throwable cause) {
      super(cause);
    }
  }

  public class ProsEnt {
    private String inputURLNavn;
    private String outputFilNavn;

    public ProsEnt(String inputURLNavn, String outputFilNavn) {
      this.inputURLNavn = inputURLNavn;
      this.outputFilNavn = outputFilNavn;
    }

    public String getInputURLNavn() {
      return inputURLNavn;
    }

    public void setInputURLNavn(String inputURLNavn) {
      this.inputURLNavn = inputURLNavn;
    }

    public String getOutputFilNavn() {
      return outputFilNavn;
    }

    public void setOutputFilNavn(String outputFilNavn) {
      this.outputFilNavn = outputFilNavn;
    }
  }

  /**
   * Generer WSDL og refererte filer i en ressurs katalog
   * @param args wsdl outputkatalog
   * Eksempel: "https://test.grunnbok.no/grunnbok/wsapi/v1/GrunnboksutskriftServiceWS?WSDL" "C:/_tmp"
   */

  public static void main(String args[]) {
    try {
      new LesInnWsdlTilResorces().app(args);
    } catch (AppEx appEx) {
      appEx.printStackTrace();
    }
  }

  private List<ProsEnt> skalProsesseres = new ArrayList<ProsEnt>();
  private Set<String> outputFilNavnSet = new TreeSet<String>();

  private void app(String[] args) throws AppEx {

//    String wsdlURLName = "https://test.grunnbok.no/grunnbok/wsapi/v1/GrunnboksutskriftServiceWS?WSDL";
    String wsdlURLName = "https://nnriap034:7002/grunnbok/wsapi/v1/GrunnboksutskriftServiceWS?WSDL";
    if (args.length > 0) wsdlURLName = args[0];
    String endpoint = null;
    String wsnavn = null;
    String outputDir = "C:/_tmp";
    if (args.length > 1) outputDir = args[1];
//    String resourceDir = "wsdl";
//    if (args.length > 2) resourceDir = args[2];

    System.out.println("wsdlURLName: '" + wsdlURLName + "'");
    System.out.println("outputDir: '" + outputDir + "'");
//    System.out.println("resourceDir: '" + resourceDir + "'");

    if (wsdlURLName.endsWith("?WSDL") || wsdlURLName.endsWith("?wsdl")) {
      endpoint = wsdlURLName.substring(0,wsdlURLName.length() - 5);
      int slashPos = endpoint.lastIndexOf("/");
      if (slashPos < 0) {
        throw new AppEx("WSDL referanse '" + wsdlURLName + "' har ikke noe gyldig web service navn.");
      }
      wsnavn = endpoint.substring(slashPos + 1);
    } else {
      throw new AppEx("WSDL referanse '" + wsdlURLName + "' slutter ikke med ?WSDL/?wsdl");
    }

    System.out.println("Endpoint: " + endpoint);
    System.out.println("WS navn:  " + wsnavn);

    skalProsesseres.add(new ProsEnt(wsdlURLName, outputDir + "/" + wsnavn + ".wsdl"));

    while (skalProsesseres.size() > 0) {
      ProsEnt prosEnt = skalProsesseres.get(0);
      skalProsesseres.remove(0);

      URL url = null;
      try {
        url = new URL(prosEnt.inputURLNavn);
      } catch (MalformedURLException e) {
        throw new AppEx("Kan ikke omforme '" + prosEnt.inputURLNavn + "' til URL." ,e);
      }
      List<String> linjer = lesUrl(url);

      for (int ix = 0; ix < linjer.size(); ix++) {
        int linjeno = ix + 1;
        String linjeRef = "[" + prosEnt.inputURLNavn + ":" + linjeno + "]";
        String linje = linjer.get(ix);
        // Sjekk om dette er en linje som skal behandles - starter med <
        if (! linje.startsWith("<")) continue;
        int colPos = linje.indexOf(":import");
        if (colPos < 0) continue;
        int xsdLikPos = linje.lastIndexOf("?xsd=");
        if (xsdLikPos < 0) continue;
        int lastQuote = linje.indexOf("\"", xsdLikPos);
        if (lastQuote < 0) continue;
        int firstQuote = linje.lastIndexOf("\"", xsdLikPos);
        if (firstQuote < 0) continue;
        String nyInputUrl = linje.substring(firstQuote + 1, lastQuote);
        // Fjern trailing ?xsd=n
        int xsdLikPos2 = nyInputUrl.lastIndexOf("?xsd=");
        String checkMotWsnavn = nyInputUrl.substring(0, xsdLikPos2);
        int startCheckWsnavn = checkMotWsnavn.lastIndexOf("/");
        checkMotWsnavn = checkMotWsnavn.substring(startCheckWsnavn + 1);
        if (!checkMotWsnavn.equals(wsnavn)) {
          throw new AppEx("'" + checkMotWsnavn + "' ulik '" + wsnavn + "'. " + linjeRef);
//          System.out.println("'" + checkMotWsnavn + "' ulik '" + wsnavn + "'");
//          continue;
        }
        int lastLikPos = nyInputUrl.lastIndexOf("=");
        if (lastLikPos < 0) {
          throw new AppEx("Finner ikke = på slutten av linje. " + linjeRef);
//          System.out.println("Finner ikke = på slutten av linje");
//          continue;
        }
        String number = nyInputUrl.substring(lastLikPos + 1);
        String outputWriteFileName = outputDir + "/" + wsnavn + number + ".xsd";
        String outputRefFileName = /* resourceDir + "/" + */ wsnavn + number + ".xsd";
        String redigertLinje = linje.substring(0,firstQuote + 1) + outputRefFileName + linje.substring(lastQuote);
        System.out.println(redigertLinje);
        if (! outputFilNavnSet.contains(outputWriteFileName)) {
          skalProsesseres.add(new ProsEnt(nyInputUrl, outputWriteFileName));
          outputFilNavnSet.add(outputWriteFileName);
        }
        linjer.remove(ix);
        linjer.add(ix,redigertLinje);
      }


      File outputFile = new File(prosEnt.getOutputFilNavn());
      skrivTilFil(linjer, outputFile);

      outputFilNavnSet.add(prosEnt.getOutputFilNavn());

      System.out.println("URL: '" + prosEnt.getInputURLNavn() + "' lagret som FILE: '" + prosEnt.getOutputFilNavn());
    }

    System.exit(0);
  }

  private List<String> lesUrl(URL url) throws AppEx {
    List<String> ret = new ArrayList<String>();
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) ret.add(inputLine);
      in.close();
    } catch (IOException e) {
      throw new AppEx("Kan ikke lese in: " + url.toString(),e);
    }
    return ret;
  }

  private void skrivTilFil(List<String> linjer, File file) throws AppEx {
    try {
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
      for (String linje : linjer) {
        out.write(linje);
        out.newLine();
      }
      out.close();
    } catch (IOException e) {
      throw new AppEx("Kan ikke lese in: " + file.toString());
    }
  }


}
