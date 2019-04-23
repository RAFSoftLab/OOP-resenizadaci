package rs.raf.sah;

import java.util.ArrayList;
import java.util.List;

public class Skakac extends Figura {

    public Skakac(BojaFigure bojaFigure, Pozicija pozicijaFigure) {
        super(bojaFigure, pozicijaFigure);
    }

    @Override
    public List<Pozicija> vratiMogucePozicije() {
        int kolona = getPozicijaFigure().getKolona();
        int red = getPozicijaFigure().getRed();

        ArrayList<Pozicija> pozicije = new ArrayList<>();
        pozicije.add(new Pozicija(kolona + 1, red + 2));
        pozicije.add(new Pozicija(kolona - 1, red + 2));
        pozicije.add(new Pozicija(kolona + 1, red - 2));
        pozicije.add(new Pozicija(kolona - 1, red - 2));
        pozicije.add(new Pozicija(kolona + 2, red + 1));
        pozicije.add(new Pozicija(kolona + 2, red - 1));
        pozicije.add(new Pozicija(kolona - 2, red + 1));
        pozicije.add(new Pozicija(kolona - 2, red - 1));

        return pozicije;
    }
}