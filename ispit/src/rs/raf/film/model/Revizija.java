package rs.raf.film.model;

public class Revizija {

    private int filmId, ocena;
    private String komentar;

    public Revizija setFilmId(int filmId) {
        this.filmId = filmId;

        return this;
    }

    public Revizija setOcena(int ocena) {
        this.ocena = ocena;

        return this;
    }

    public Revizija setKomentar(String komentar) {
        this.komentar = komentar;

        return this;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }
}