package rs.raf.itdays;

import java.util.Date;

public abstract class Dogadjaj {

    private String nazivDogadjaja, imePredavaca;
    private Date vremePocetka;

    public Dogadjaj(String nazivDogadjaja, String imePredavaca, Date vremePocetka) {
        this.nazivDogadjaja = nazivDogadjaja;
        this.imePredavaca = imePredavaca;
        this.vremePocetka = vremePocetka;
    }

    public String getNazivDogadjaja() {
        return nazivDogadjaja;
    }

    public String getImePredavaca() {
        return imePredavaca;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getNazivDogadjaja()
                + " " + getImePredavaca() + " " + getVremePocetka();
    }
}