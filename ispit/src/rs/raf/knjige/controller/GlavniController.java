package rs.raf.knjige.controller;

import rs.raf.knjige.Utils;
import rs.raf.knjige.Config;
import rs.raf.knjige.model.Izdavac;
import rs.raf.knjige.model.Knjiga;
import rs.raf.knjige.model.KnjigaFactory;
import rs.raf.knjige.view.GlavniStage;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GlavniController {

    public void pokreni() {
        List<Knjiga> knjige =  Utils.ucitajKnjige(Config.knjigePutanja);
        Map<String, Izdavac> izdavaci =  Utils.ucitajIzdavace(Config.izdavaciPutanja);

        KnjigaFactory factory = new KnjigaFactory();

        Iterator<Knjiga> iter = knjige.iterator();

        while(iter.hasNext()) {
            Knjiga knjiga = iter.next();

            String izdavacNaziv = knjiga.getIzdavac().getNaziv();

            if(!izdavaci.containsKey(izdavacNaziv)) {
                Utils.greska("Ne postoji izdavac: " + izdavacNaziv + ". Knjiga " + knjiga.getNaslov() + " ce biti uklonjena");
                iter.remove();
                continue;
            }

            factory.setIzdavac(knjiga, izdavaci.get(izdavacNaziv));
        }

        new GlavniStage(knjige, izdavaci).show();
    }
}