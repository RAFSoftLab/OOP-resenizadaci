package rs.raf.muzika;

public class Ucesnik implements Comparable, Glasanje {

    private String ime;
    private int brojGlasova;
    private TipUcesnika tipUcesnika;

    public Ucesnik(String ime, TipUcesnika tipUcesnika) {
        this.ime = ime;
        this.tipUcesnika = tipUcesnika;
    }

    public String getIme() {
        return ime;
    }

    public TipUcesnika getTipUcesnika() {
        return tipUcesnika;
    }

    @Override
    public void dodajGlas() {
        brojGlasova++;
    }

    @Override
    public int getBrojGlasova() {
        return brojGlasova;
    }

    @Override
    public int compareTo(Object o) {
        try {
            return brojGlasova - ((Ucesnik) o).getBrojGlasova();
        }catch(ClassCastException e) {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Ucesnik u = (Ucesnik) obj;

            return getTipUcesnika() == u.getTipUcesnika() && getIme().equals(u.getIme());
        }catch(ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return getIme();
    }
}