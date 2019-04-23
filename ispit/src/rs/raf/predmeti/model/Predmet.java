package rs.raf.predmeti.model;

public class Predmet implements Comparable<Predmet> {

    private int godina, espb;
    private String naziv;
    private boolean obavezan;

    public Predmet(String naziv, int godina, boolean obavezan, int espb) {
        this.naziv = naziv;
        this.godina = godina;
        this.obavezan = obavezan;
        this.espb = espb;
    }

    public int getGodina() {
        return godina;
    }

    public int getEspb() {
        return espb;
    }

    public String getNaziv() {
        return naziv;
    }

    public boolean isObavezan() {
        return obavezan;
    }


    @Override
    public int compareTo(Predmet p) {
        return this.godina - p.godina;
    }

    @Override
    public String toString() {
        return naziv + " (" + espb + ")";
    }
}