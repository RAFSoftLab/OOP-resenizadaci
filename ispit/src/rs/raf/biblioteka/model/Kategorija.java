package rs.raf.biblioteka.model;

public enum Kategorija {
    STUDENT(15.0), ZAPOSLEN(0.0), PENZIONER(10.0);

    double popust;

    Kategorija(double popust) {
        this.popust = popust;
    }

    public double getCena(double cena) {
        return popust * cena * 0.01;
    }


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}