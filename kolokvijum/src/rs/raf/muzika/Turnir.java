package rs.raf.muzika;

import java.util.Date;
import java.util.Random;

public class Turnir extends Takmicenje {

    public Turnir(String naziv, Date vremePocetka) {
        super(naziv, vremePocetka);
    }

    @Override
    public boolean dodajUcesnika(Ucesnik ucesnik) {
        for(Ucesnik u : getUcesnici()) {
            if(u.equals(ucesnik)) {
                return false;
            }
        }

        getUcesnici().add(ucesnik);

        return true;
    }

    public Ucesnik odrediPobednika() {
        try {
            return getUcesnici().get(new Random().nextInt(getUcesnici().size()));
        }catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
}