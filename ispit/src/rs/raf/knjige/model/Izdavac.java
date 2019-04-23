package rs.raf.knjige.model;

public class Izdavac {

    private String naziv;
    private float popust;

    public Izdavac(String naziv, float popust) {
        this.naziv = naziv;
        this.popust = popust;
    }

    public String getNaziv() {
        return naziv;
    }

    public float getPopust() {
        return popust;
    }

    @Override
    public String toString() {
        return naziv;
    }
}