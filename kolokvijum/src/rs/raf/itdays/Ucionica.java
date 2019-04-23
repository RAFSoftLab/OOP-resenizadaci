package rs.raf.itdays;

public class Ucionica {

    private String naziv;
    private boolean saRacunarima, imaProjektor;
    private int brojMesta;

    public Ucionica(String naziv, boolean saRacunarima, boolean imaProjektor, int brojMesta) {
        this.naziv = naziv;
        this.saRacunarima = saRacunarima;
        this.imaProjektor = imaProjektor;
        this.brojMesta = brojMesta;
    }

    public String getNaziv() {
        return naziv;
    }

    public boolean isSaRacunarima() {
        return saRacunarima;
    }

    public boolean isImaProjektor() {
        return imaProjektor;
    }

    public int getBrojMesta() {
        return brojMesta;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getNaziv() + " " +
                isSaRacunarima() + " " + isImaProjektor() + " " + getBrojMesta();
    }
}