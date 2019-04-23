package rs.raf.softver;

public class Kandidat {

    private String ime, prezime;
    private int kvalitet;

    public Kandidat(String ime, String prezime, int kvalitet) {
        this.ime = ime;
        this.prezime = prezime;

        if(kvalitet < 0 || kvalitet > 20) {
            System.out.println("Kvalitet ne sme biti manji od 0 ili veci od 20");
            return;
        }

        this.kvalitet = kvalitet;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getKvalitet() {
        return kvalitet;
    }

    @Override
    public String toString() {
        return "Kandidat: " + getIme() + " " + getPrezime() + " " + getKvalitet();
    }
}