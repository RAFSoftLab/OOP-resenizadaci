package rs.raf.restoran.model;

public class Jelo {

    private String naziv;
    private Obrok obrok;
    private float cena;

    public Jelo(String naziv, Obrok obrok, float cena) {
        this.naziv = naziv;
        this.obrok = obrok;
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public Obrok getObrok() {
        return obrok;
    }

    public float getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return getNaziv();
    }
}