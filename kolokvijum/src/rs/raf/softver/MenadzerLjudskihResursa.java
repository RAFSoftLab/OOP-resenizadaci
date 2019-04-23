package rs.raf.softver;

import java.util.Random;

public class MenadzerLjudskihResursa extends Zaposleni {

    public MenadzerLjudskihResursa(String ime, String prezime, double plata) {
        super(ime, prezime, plata);
    }

    public InzenjerskaPozicija intervju(Kandidat kandidat) {
        Random random = new Random();

        if(random.nextInt(6) < 3) {
            return null;
        }

        int kvalitet = kandidat.getKvalitet();

        if(kvalitet < 5) {
            return InzenjerskaPozicija.JUNIOR_PROGRAMER;
        }else if(kvalitet > 10) {
            return InzenjerskaPozicija.ARHITEKTA;
        }else {
            if(random.nextBoolean()) {
                return InzenjerskaPozicija.SENIOR_PROGRAMER;
            }else {
                return InzenjerskaPozicija.TESTER;
            }
        }
    }

    @Override
    public String toString() {
        return "Menadzer ljudskih resursa: " + getIme() + " " + getPrezime() + " " + getPlata();
    }
}
