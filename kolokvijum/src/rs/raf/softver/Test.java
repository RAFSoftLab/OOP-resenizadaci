package rs.raf.softver;

import java.util.ArrayList;

/**
* @author Lazar Jelic - ljelic17@raf.rs
*/

public class Test {

    public static void main(String[] args) {
        SoftverskaKompanija kompanija = new SoftverskaKompanija("Ecloga Apps");

        Menadzer menadzer = new Menadzer("John", "Doe", 3000.5);
        kompanija.getZaposleni().add(menadzer);

        MenadzerLjudskihResursa menadzerResurs = new MenadzerLjudskihResursa("John", "Doe", 2000.5);
        kompanija.getZaposleni().add(menadzerResurs);

        Kupac kupac = new Kupac("Marco", "Polo");
        kompanija.getProjekti().add(menadzer.sklopiPosao(kupac));

        ArrayList<Kandidat> kandidati = new ArrayList<>();
        kandidati.add(new Kandidat("Forest", "Gump", -1));
        kandidati.add(new Kandidat("Abraham", "Lincoln", 1));
        kandidati.add(new Kandidat("John", "Cena", 6));
        kandidati.add(new Kandidat("Samuel", "Jackson", 11));
        kandidati.add(new Kandidat("Jordan", "Belford", 21));

        for(Kandidat k : kandidati) {
            InzenjerskaPozicija pozicija = menadzerResurs.intervju(k);

            if(pozicija != null) {
                kompanija.getZaposleni().add(new Inzenjer(k.getIme(), k.getPrezime(), k.getKvalitet(), pozicija));
            }
        }

        Projekat projekat = kompanija.getProjekti().get(0);

        if(projekat != null) {
            System.out.println("Sklopljen " + projekat);

            for(Zaposleni z : kompanija.getZaposleni()) {
                if(z instanceof Inzenjer) {
                    projekat.dodajNaProjekat((Inzenjer) z);
                }
            }

            while(!projekat.isGotovProjekat()) {
                System.out.println("Status projekta pre rada: " + projekat.getStatus());

                projekat.radiNaProjektu();

                System.out.println("Status projekta pre testa: " + projekat.getStatus());

                projekat.testirajProjekat();

                System.out.println("Status projekta nakon testa: " + projekat.getStatus());
            }

            System.out.println("Gotov " + projekat);
        }
	}

	public static Inzenjer kreirajInzenjera(String ime, String prezime, InzenjerskaPozicija pozicija) {
        return new Inzenjer(ime, prezime, 0, pozicija);
    }
}
