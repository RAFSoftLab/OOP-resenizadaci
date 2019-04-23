package rs.raf.dvoboj;

/**
* @author Lazar Jelic - ljelic17@raf.rs
*/

public class GlavnaKlasa {

    public static void main(String[] args) {
        Covek covek = new Covek("John Cena", 100);
        Racunar racunar = new Racunar();

        Partija partija = new Partija(covek, racunar);

        Karta karta1 = new ObicnaKarta("Cudoviste1", TipKarte.CUDOVISTE, Polozaj.NAPAD, 100, 100);
        Karta karta2 = new ObicnaKarta("Cudoviste2", TipKarte.CUDOVISTE, Polozaj.ODBRANA, 1, 99);
        Karta karta3 = new RetkaKarta("Cudoviste3", TipKarte.CUDOVISTE, Polozaj.NAPAD, -2, 50, 0.5);
        Karta karta4 = new RetkaKarta("Cudoviste4", TipKarte.CUDOVISTE, Polozaj.ODBRANA, 75, 25, 0.9);

        partija.dodajKartu(karta1);
        partija.dodajKartu(karta2);
        partija.dodajKartu(karta3);
        partija.dodajKartu(karta4);

        Karta karta5 = new ObicnaKarta("Zamka1", TipKarte.ZAMKA, Polozaj.NAPAD, 0, 0);
        Karta karta6 = new ObicnaKarta("Zamka2", TipKarte.ZAMKA, Polozaj.ODBRANA, 1, 1);
        Karta karta7 = new RetkaKarta("Zamka3", TipKarte.ZAMKA, Polozaj.NAPAD, 101, 101, 0.4);
        Karta karta8 = new RetkaKarta("Zamka4", TipKarte.ZAMKA, Polozaj.ODBRANA, -3, 2, 0.1);

        partija.dodajKartu(karta5);
        partija.dodajKartu(karta6);
        partija.dodajKartu(karta7);
        partija.dodajKartu(karta8);

        partija.podeliKarte();
        partija.odigrajPartiju();

        partija.getTalon().odstampajTalon();
    }
}
