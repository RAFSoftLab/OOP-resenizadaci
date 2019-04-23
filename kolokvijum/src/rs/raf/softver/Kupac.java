package rs.raf.softver;

public class Kupac {

    private String ime, prezime;

    public Kupac(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    @Override
    public String toString() {
        return "Kupac: " + getIme() + " " + getPrezime();
    }
}