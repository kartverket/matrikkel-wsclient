package no.statkart.wsclient.grunnbokv2.innsending.domene;


public class Registerenhet {

    private Matrikkelenhet matrikkelenhet;
    private Borettslagsandel borettslagsandel;

    public Borettslagsandel getBorettslagsandel() {
        return borettslagsandel;
    }

    public void setBorettslagsandel(Borettslagsandel borettslagsandel) {
        this.borettslagsandel = borettslagsandel;
    }

    public Matrikkelenhet getMatrikkelenhet() {
        return matrikkelenhet;
    }

    public void setMatrikkelenhet(Matrikkelenhet matrikkelenhet) {
        this.matrikkelenhet = matrikkelenhet;
    }
}
