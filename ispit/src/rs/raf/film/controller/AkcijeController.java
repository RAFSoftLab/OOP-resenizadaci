package rs.raf.film.controller;

import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import rs.raf.film.Config;
import rs.raf.film.Utils;
import rs.raf.film.model.Film;
import rs.raf.film.model.Revizija;
import rs.raf.film.view.RevizijaStage;
import java.util.List;

public class AkcijeController {

    public void pretrazi(String naslov, ToggleGroup tgNaslov, List<Film> filmovi, TableView tvFilmovi) {
        tvFilmovi.getItems().clear();

        boolean tacanNaslov = tgNaslov.getToggles().get(0).isSelected();
        boolean deoNaslova = tgNaslov.getToggles().get(1).isSelected();

        if(!tacanNaslov && !deoNaslova) {
            Utils.greska("Nacin pretrage nije izabran");
        }

        for(Film film : filmovi) {
            if((tacanNaslov && film.getNaslov().equalsIgnoreCase(naslov))
                    || (deoNaslova && film.getNaslov().toLowerCase().contains(naslov.toLowerCase()))) {
                tvFilmovi.getItems().add(film);
            }
        }
    }

    public void oceni(Film film) {
        if(film == null) {
            Utils.greska("Film iz liste nije odabran");
            return;
        }

        new RevizijaStage(film).show();
    }

    public boolean sacuvaj(Film film, String ocena, String komentar) {
        if(ocena.isEmpty() || komentar.isEmpty()) {
            Utils.greska("Ocena i komentar su obavezna polja");
            return false;
        }

        try {
            Revizija revizija = new Revizija()
                    .setFilmId(film.getId())
                    .setOcena(Integer.parseInt(ocena))
                    .setKomentar(komentar);

            if(Utils.upisiReviziju(revizija, Config.revizijePutanja)) {
                film.dodajReviziju(revizija);
                return true;
            }
        }catch(NumberFormatException e) {
            Utils.greska("Ocena mora biti ceo broj od 1 do 10");
        }

        return false;
    }

    public boolean odustani() {
        return true;
    }
}