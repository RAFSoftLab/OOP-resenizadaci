package rs.raf.muzika;

import java.util.ArrayList;
import java.util.Date;

public abstract class Takmicenje extends Dogadjaj{

    private ArrayList<Ucesnik> ucesnici;

    public Takmicenje(String naziv, Date vremePocetka) {
        super(naziv, vremePocetka);

        ucesnici = new ArrayList<>();
    }

    public ArrayList<Ucesnik> getUcesnici() {
        return ucesnici;
    }

    public abstract boolean dodajUcesnika(Ucesnik ucesnik);

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + getUcesnici().size() + ")";
    }
}