package rs.raf.predmeti.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int espb;
    private String ime, indeks;
    private List<Predmet> predmeti;

    public Student() {
        predmeti = new ArrayList<Predmet>();
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void addPredmet(Predmet predmet) {
        predmeti.add(predmet);
    }

    @Override
    public String toString() {
        return ime + "," + indeks + "," + espb;
    }

    public boolean hasData() {
        return !ime.isEmpty() && !indeks.isEmpty();
    }
}