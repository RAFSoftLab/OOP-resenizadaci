package rs.raf.softver;

import java.util.ArrayList;
import java.util.List;

public class SoftverskaKompanija {

    private String ime;
    private List<Zaposleni> zaposleni;
    private List<Projekat> projekti;

    public SoftverskaKompanija(String ime) {
        this.ime = ime;

        zaposleni = new ArrayList<>();
        projekti = new ArrayList<>();
    }

    public List<Zaposleni> getZaposleni() {
        return zaposleni;
    }

    public List<Projekat> getProjekti() {
        return projekti;
    }

    public void povecajPlatuMenadzerima(double bonus) {
        for(Zaposleni z : zaposleni) {
            if(z instanceof Menadzer) {
                Menadzer m = (Menadzer) z;
                m.setBonus(m.getBonus() + bonus);
            }
        }
    }

    @Override
    public String toString() {
        return "Kandidat: " + ime + " " + zaposleni.toString() + " " + projekti.toString();
    }
}