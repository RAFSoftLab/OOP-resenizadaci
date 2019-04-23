package rs.raf.muzika;

import java.util.Date;

public class Izbor extends Takmicenje {

    public Izbor(String naziv, Date vremePocetka) {
        super(naziv, vremePocetka);
    }

    @Override
    public boolean dodajUcesnika(Ucesnik ucesnik) {
        if(ucesnik.getTipUcesnika() != TipUcesnika.MUZICAR) {
            return false;
        }

        for(Ucesnik u : getUcesnici()) {
            if(u.equals(ucesnik)) {
                return false;
            }
        }

        getUcesnici().add(ucesnik);

        return true;
    }
}