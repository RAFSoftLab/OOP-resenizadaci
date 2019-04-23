package rs.raf.dvoboj;

import java.util.ArrayList;
import java.util.Random;

public class Partija {

    private boolean podeljeneKarte;
    private Igrac prviIgrac, drugiIgrac;
    private Talon talon;
    private ArrayList<Karta> sveKarte;

    public Partija(Igrac prviIgrac, Igrac drugiIgrac) {
        this.prviIgrac = prviIgrac;
        this.drugiIgrac = drugiIgrac;

        talon = new Talon(prviIgrac, drugiIgrac);

        sveKarte = new ArrayList<>();
    }

    public void dodajKartu(Karta karta) {
        sveKarte.add(karta);
    }

    public boolean podeliKarte() {
        if(sveKarte.size() < 8) {
            return false;
        }

        Random random = new Random();

        int i = 0;

        while(sveKarte.size() > 0) {
            Karta karta = sveKarte.get(random.nextInt(sveKarte.size()));

            if(++i % 2 == 0) {
                talon.dodajKartu(karta, prviIgrac);
            }else {
                talon.dodajKartu(karta, drugiIgrac);
            }

            sveKarte.remove(karta);
        }

        setPodeljeneKarte(true);

        return true;
    }

    public Igrac odigrajPartiju() {
        if(!isPodeljeneKarte()) {
            return null;
        }

        Random random = new Random();

        int i = 0;

        while(true) {
            ArrayList<Karta> cudovista;
            ArrayList<Karta> ziveKarte;

            if(++i % 2 == 0) {
                cudovista = talon.vratiCudovistaIgraca(prviIgrac);
                ziveKarte = talon.vratiZiveKarteIgraca(drugiIgrac);
            }else {
                cudovista = talon.vratiCudovistaIgraca(drugiIgrac);
                ziveKarte = talon.vratiZiveKarteIgraca(prviIgrac);
            }

            Karta cudoviste = cudovista.get(random.nextInt(cudovista.size()));

            if(random.nextInt(100) > 50) {
                cudoviste.setPolozaj(Polozaj.NAPAD);
            }else {
                cudoviste.setPolozaj(Polozaj.ODBRANA);
            }

            cudoviste.napadni(ziveKarte.get(random.nextInt(ziveKarte.size())));

            if(talon.isUnisten(prviIgrac)) {
                drugiIgrac.pobedi(prviIgrac);
                return drugiIgrac;
            }

            if(talon.isUnisten(drugiIgrac)) {
                prviIgrac.pobedi(drugiIgrac);
                return prviIgrac;
            }
        }
    }

    public boolean isPodeljeneKarte() {
        return podeljeneKarte;
    }

    public Igrac getPrviIgrac() {
        return prviIgrac;
    }

    public Igrac getDrugiIgrac() {
        return drugiIgrac;
    }

    public Talon getTalon() {
        return talon;
    }

    public ArrayList<Karta> getSveKarte() {
        return sveKarte;
    }

    public void setPodeljeneKarte(boolean podeljeneKarte) {
        this.podeljeneKarte = podeljeneKarte;
    }

    public void setPrviIgrac(Igrac prviIgrac) {
        this.prviIgrac = prviIgrac;
    }

    public void setDrugiIgrac(Igrac drugiIgrac) {
        this.drugiIgrac = drugiIgrac;
    }

    public void setTalon(Talon talon) {
        this.talon = talon;
    }

    public void setSveKarte(ArrayList<Karta> sveKarte) {
        this.sveKarte = sveKarte;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + isPodeljeneKarte() + " " +
                getPrviIgrac() + " " + getDrugiIgrac() + " " + getTalon() + "  " + getSveKarte();
    }
}