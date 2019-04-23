package rs.raf.softver;

public abstract class Zaposleni {

    private String ime, prezime;
    private double plata;

    public Zaposleni(String ime, String prezime, double plata) {
        this.ime = ime;
        this.prezime = prezime;
        this.plata = plata;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    @Override
    public String toString() {
        return "Zaposleni: " + ime + " " + prezime + " " + plata;
    }
}