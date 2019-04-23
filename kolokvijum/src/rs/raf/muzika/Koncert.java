package rs.raf.muzika;

import java.util.Date;

public class Koncert extends Dogadjaj implements Glasanje {

    private String izvodjac;
    private int brojGlasova;

    public Koncert(String naziv, Date vremePocetka, String izvodjac) {
        super(naziv, vremePocetka);

        this.izvodjac = izvodjac;
    }

    public String getIzvodjac() {
        return izvodjac;
    }

    @Override
    public int getBrojGlasova() {
        return brojGlasova;
    }

    @Override
    public void dodajGlas() {
        brojGlasova++;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + getBrojGlasova() + ")";
    }
}