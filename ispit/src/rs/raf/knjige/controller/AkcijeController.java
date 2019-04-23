package rs.raf.knjige.controller;

import javafx.scene.control.TableView;
import rs.raf.knjige.Config;
import rs.raf.knjige.Utils;
import rs.raf.knjige.model.Knjiga;
import rs.raf.knjige.model.Kriterijum;
import rs.raf.knjige.view.ProdajaStage;
import java.util.List;

public class AkcijeController {

    public void prikaziProdaju(List<Knjiga> knjige) {
        new ProdajaStage(knjige).show();
    }

    public void pretrazi(Kriterijum kriterijum, List<Knjiga> knjige, TableView<Knjiga> tvKnjige) {
        tvKnjige.getItems().clear();

        for(Knjiga knjiga : knjige) {
            if(kriterijum.tacanNaslov && !knjiga.getNaslov().equalsIgnoreCase(kriterijum.naslov)) {
                continue;
            }

            if(!kriterijum.tacanNaslov && !knjiga.getNaslov().toLowerCase().contains(kriterijum.naslov.toLowerCase())) {
                continue;
            }

            if(kriterijum.tacanAutor && !knjiga.getAutor().equalsIgnoreCase(kriterijum.autor)) {
                continue;
            }

            if(!kriterijum.tacanAutor && !knjiga.getAutor().toLowerCase().contains(kriterijum.autor.toLowerCase())) {
                continue;
            }

            if(kriterijum.godina != -1 && knjiga.getGodina() != kriterijum.godina) {
                continue;
            }

            if(kriterijum.izdavac != null && knjiga.getIzdavac() != kriterijum.izdavac) {
                continue;
            }

            tvKnjige.getItems().add(knjiga);
        }
    }

    public void sacuvaj(List<Knjiga> knjige) {
        Utils.sacuvajStanje(knjige, Config.knjigePutanja);
    }

    public float obracunaj(String sSaPopustom, String sDodatniPopust) {
        sSaPopustom = sSaPopustom.trim();
        sDodatniPopust = sDodatniPopust.trim();

        float saPopstom = 0;
        float dodatniPopust = 0;

        try {
            if(!sSaPopustom.isEmpty()) {
                saPopstom = Float.parseFloat(sSaPopustom);
            }

            if(!sDodatniPopust.isEmpty()) {
                dodatniPopust = Float.parseFloat(sDodatniPopust);
            }
        }catch(NumberFormatException e) {
            Utils.greska("Popusti nisu ispravni");
        }

        return saPopstom * (1 - dodatniPopust / 100);
    }

    public boolean prodaj(List<Knjiga> knjige) {
        for(Knjiga knjiga : knjige) {
            knjiga.prodaj();
        }

        return true;
    }

    public boolean odustani() {
        return true;
    }
}