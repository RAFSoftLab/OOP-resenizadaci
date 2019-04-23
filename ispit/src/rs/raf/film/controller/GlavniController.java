package rs.raf.film.controller;

import rs.raf.film.Config;
import rs.raf.film.Utils;
import rs.raf.film.model.Film;
import rs.raf.film.model.Revizija;
import rs.raf.film.view.PretragaStage;
import java.util.List;

public class GlavniController {

    public void pokreni() {
        List<Film> filmovi = Utils.ucitajFilmove(Config.filmoviPutanja);
        List<Revizija> revizije = Utils.ucitajRevizije(Config.revizijePutanja);

        // Ovo moze brze ako se mapiraju revizije na ID filma
        for(Film film : filmovi) {
            for(Revizija revizija : revizije) {
                if(revizija.getFilmId() == film.getId()) {
                    film.dodajReviziju(revizija);
                }
            }
        }

        new PretragaStage(filmovi).show();
    }
}