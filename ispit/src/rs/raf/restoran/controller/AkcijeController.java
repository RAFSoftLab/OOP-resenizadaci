package rs.raf.restoran.controller;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import rs.raf.restoran.Config;
import rs.raf.restoran.Utils;
import rs.raf.restoran.model.Jelo;
import rs.raf.restoran.model.Kupac;
import rs.raf.restoran.model.Obrok;
import rs.raf.restoran.view.MeniScene;
import java.util.List;

public class AkcijeController {

    public void prikazi(List<Jelo> jela, Obrok obrok, ListView<Jelo> lvJela) {
        lvJela.getItems().clear();

        for(Jelo jelo : jela) {
            if(jelo.getObrok().equals(obrok)) {
                lvJela.getItems().add(jelo);
            }
        }
    }

    public void izaberi(List<Jelo> jela, ListView<Jelo> lvJela, TextField tfUkupno, float budzet) {
        float ukupno = 0;

        if(!tfUkupno.getText().isEmpty()) {
            ukupno = Float.parseFloat(tfUkupno.getText());
        }

        for(Jelo jelo : jela) {
            ukupno += jelo.getCena();
        }

        if(ukupno > budzet) {
            Utils.greska("Nedovoljno novca");
            return;
        }

        lvJela.getItems().addAll(jela);

        tfUkupno.setText(String.valueOf(ukupno));
    }

    public void sacuvaj(Kupac kupac, List<Jelo> jela) {
        Utils.ispisiRacun(kupac, jela, Config.racunPutanja);
    }

    public void meni(List<Jelo> jela, String ime, String adresa, String telefon, String budzet) {
        if(ime.isEmpty() || adresa.isEmpty() || telefon.isEmpty() || budzet.isEmpty()) {
            Utils.greska("Sva polja su obavezna");
            return;
        }

        Kupac kupac = new Kupac();
        kupac.setIme(ime);
        kupac.setAdresa(adresa);
        kupac.setTelefon(telefon);

        try {
            kupac.setBudzet(Float.parseFloat(budzet));
        }catch(NumberFormatException e) {
            Utils.greska("Budzet nije ispravan");
            return;
        }

        new MeniScene(jela, kupac).show();
    }
}