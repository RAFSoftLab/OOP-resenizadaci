package rs.raf.dvoboj;

import java.util.Random;

public class RetkaKarta extends Karta {

    private double kriticnaVerovatnoca;

    public RetkaKarta(String naziv, TipKarte tipKarte, Polozaj polozaj, int napad, int odbrana, double kriticnaVerovatnoca) {
        super(naziv, tipKarte, polozaj, napad, odbrana);

        this.kriticnaVerovatnoca = kriticnaVerovatnoca;
    }

    @Override
    public boolean napadni(Karta karta) {
        setPolozaj(Polozaj.NAPAD);

        if(new Random().nextInt(100) < kriticnaVerovatnoca * 100) {
            setNapad(getNapad() + 5000);
            karta.primiNapad(this);
            setNapad(getNapad() - 5000);
        }

        return karta.isUnistena();
    }

    @Override
    public void primiNapad(Karta karta) {
        if(getTipKarte() == TipKarte.ZAMKA) {
            if(new Random().nextInt(100) < kriticnaVerovatnoca * 100) {
                setNapad(getNapad() + 5000);
                karta.primiNapad(this);
                setNapad(getNapad() - 5000);
            }

            setUnistena(true);
        }else {
            if(getPolozaj() == Polozaj.NAPAD) {
                umanjuNapad(karta.getNapad());

                if(getNapad() == 0) {
                    setUnistena(true);
                }
            }else if(getPolozaj() == Polozaj.ODBRANA) {
                if(new Random().nextInt(100) < (1 - kriticnaVerovatnoca) * 100) {
                    umanjuOdbranu(karta.getOdbrana());
                }

                setUnistena(true);
            }
        }
    }

    public double getKriticnaVerovatnoca() {
        return kriticnaVerovatnoca;
    }

    public void setKriticnaVerovatnoca(double kriticnaVerovatnoca) {
        this.kriticnaVerovatnoca = kriticnaVerovatnoca;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getKriticnaVerovatnoca();
    }
}