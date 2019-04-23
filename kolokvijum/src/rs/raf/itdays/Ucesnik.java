package rs.raf.itdays;

import java.util.List;
import java.util.Random;

public class Ucesnik {

    private String imeIPrezime;
    private KategorijaUcesnika kategorijaUcesnika;

    public Ucesnik(Kandidat kandidat) {
        this.imeIPrezime = kandidat.getImeIPrezime();

        // equalsIgnoreCase poredi stringove ne obracajuci paznju na velika i mala slova
        if(kandidat.getGrad().equalsIgnoreCase("beograd")) {
            kategorijaUcesnika = KategorijaUcesnika.DOMACI;
        }else {
            kategorijaUcesnika = KategorijaUcesnika.GOSTI;
        }
    }

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public KategorijaUcesnika getKategorijaUcesnika() {
        return kategorijaUcesnika;
    }

    public void glasaj(List<Glasanje> dogadjaji) {
        if(dogadjaji == null) {
            return;
        }

        dogadjaji.get(new Random().nextInt(dogadjaji.size())).glasaj();
    }
}