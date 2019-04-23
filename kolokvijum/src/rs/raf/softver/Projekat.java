package rs.raf.softver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Projekat {

    private int status;
    private String imeProjekta;
    private Kupac kupac;
    private List<Inzenjer> inzenjeri;

    public Projekat(String imeProjekta) {
        this.imeProjekta = imeProjekta;

        inzenjeri = new ArrayList<>();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if(status < 0) {
            this.status = 0;
        }else if(status > 100) {
            this.status = 100;
        }else {
            this.status = status;
        }
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public boolean dodajNaProjekat(Inzenjer inzenjer) {
        for(Inzenjer i : inzenjeri) {
            if(i.getIme().equals(inzenjer.getIme())
                    && i.getPrezime().equals(inzenjer.getPrezime())) {
                return false;
            }
        }

        inzenjeri.add(inzenjer);

        return true;
    }

    public boolean isGotovProjekat() {
        return status == 100;
    }

    public void radiNaProjektu() {
        for(Inzenjer i : inzenjeri) {
            if(i.getPozicija() != InzenjerskaPozicija.TESTER) {
                setStatus(status + i.getKvalitet());
            }
        }
    }

    public void testirajProjekat() {
        for(Inzenjer i : inzenjeri) {
            if(i.getPozicija() == InzenjerskaPozicija.TESTER) {
                if(new Random().nextInt(100) < 30) {
                    setStatus(status - 5);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Projekat: " + imeProjekta + " " + getStatus() + " " + getKupac().toString() + " " + inzenjeri.toString();
    }
}