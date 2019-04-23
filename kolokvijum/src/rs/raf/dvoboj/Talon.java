package rs.raf.dvoboj;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Talon {

    private Igrac prvi, drugi;
    private ArrayList<Karta> karte1, karte2;

    public Talon(Igrac prvi, Igrac drugi) {
        this.prvi = prvi;
        this.drugi = drugi;

        karte1 = new ArrayList<>();
        karte2 = new ArrayList<>();
    }

    public void dodajKartu(Karta karta, Igrac igrac) {
        if(igrac == prvi) {
            karte1.add(karta);
        }else if(igrac == drugi) {
            karte2.add(karta);
        }
    }

    public void odstampajTalon() {
        File out = new File("talon.txt");
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(out);

            writer.append("Prvi igrac: \n");

            for(Karta karta : karte1) {
                writer.append(karta.toString());
                writer.append("\n");
            }

            writer.append("Drugi igrac: \n");

            for(Karta karta : karte2) {
                writer.append(karta.toString());
                writer.append("\n");
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public ArrayList<Karta> vratiCudovistaIgraca(Igrac igrac) {
        ArrayList<Karta> cudovista = new ArrayList<>();
        ArrayList<Karta> karte = null;

        if(igrac == prvi) {
            karte = karte1;
        }else if(igrac == drugi) {
            karte = karte2;
        }

        if(karte != null) {
            for(Karta karta : karte) {
                if(karta.getTipKarte() == TipKarte.CUDOVISTE) {
                    cudovista.add(karta);
                }
            }
        }

        return cudovista;
    }

    public ArrayList<Karta> vratiZiveKarteIgraca(Igrac igrac) {
        ArrayList<Karta> ziveKarte = new ArrayList<>();
        ArrayList<Karta> karte = null;

        if(igrac == prvi) {
            karte = karte1;
        }else if(igrac == drugi) {
            karte = karte2;
        }

        if(karte != null) {
            for(Karta karta : karte) {
                if(!karta.isUnistena()) {
                    ziveKarte.add(karta);
                }
            }
        }

        return ziveKarte;
    }

    public boolean isUnisten(Igrac igrac) {
        ArrayList<Karta> karte = vratiCudovistaIgraca(igrac);

        for(Karta karta : karte) {
            if(!karta.isUnistena()) {
                return false;
            }
        }

        return true;
    }

    public Igrac getPrvi() {
        return prvi;
    }

    public Igrac getDrugi() {
        return drugi;
    }

    public ArrayList<Karta> getKarte1() {
        return karte1;
    }

    public ArrayList<Karta> getKarte2() {
        return karte2;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getPrvi() + " " +
                getDrugi() + " " + getKarte1() + " " + getKarte2();
    }
}