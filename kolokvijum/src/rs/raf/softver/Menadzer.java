package rs.raf.softver;

import java.util.Random;

public class Menadzer extends Zaposleni {

    private double bonus;

    public Menadzer(String ime, String prezime, double plata) {
        super(ime, prezime, plata);
    }

    @Override
    public double getPlata() {
        return super.getPlata() + getBonus();
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Projekat sklopiPosao(Kupac kupac) {
        if(new Random().nextInt(100) < 70) {
            Projekat projekat = new Projekat("ImeProjekta");
            projekat.setKupac(kupac);

            return projekat;
        }

        return null;
    }

    public void promeniPozicijuInzenjera(Inzenjer inzenjer, InzenjerskaPozicija pozicija) {
        inzenjer.setPozicija(pozicija);
    }
}