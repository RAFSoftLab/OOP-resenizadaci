package rs.raf.dvoboj;

public class ObicnaKarta extends Karta {

    public ObicnaKarta(String naziv, TipKarte tipKarte, Polozaj polozaj, int napad, int odbrana) {
        super(naziv, tipKarte, polozaj, napad, odbrana);
    }

    @Override
    public boolean napadni(Karta karta) {
        setPolozaj(Polozaj.NAPAD);
        karta.primiNapad(this);

        return karta.isUnistena();
    }

    @Override
    public void primiNapad(Karta karta) {
        if(getTipKarte() == TipKarte.ZAMKA) {
            karta.primiNapad(this);
            setUnistena(true);
        }else {
            if(getPolozaj() == Polozaj.NAPAD) {
                umanjuNapad(karta.getNapad());

                if(getNapad() == 0) {
                    setUnistena(true);
                }
            }else if(getPolozaj() == Polozaj.ODBRANA) {
                umanjuOdbranu(karta.getOdbrana());

                if(getOdbrana() == 0) {
                    setUnistena(true);
                }
            }
        }
    }
}