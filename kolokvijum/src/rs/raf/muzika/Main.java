package rs.raf.muzika;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
* @author Lazar Jelic - ljelic17@raf.rs
* @author Milan Tomic - mtomic@raf.rs (detaljno napisani komentari)
*/

public class Main {

    public static void main(String[] args) throws ParseException {
        Festival festival = new Festival("EXIT");

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        Koncert koncert1 = new Koncert("Samo ljubav", format.parse("01.01.2018 12:00"), "Hot Dog");
        Koncert koncert2 = new Koncert("Rokenrolcina klasicna", format.parse("10.06.1998 15:15"), "Brackica");
        Koncert koncert3 = new Koncert("Nesto chill", format.parse("01.02.2013 19:59"), "Kobaja Grande");

        festival.dodajDogadjaj(koncert1);
        festival.dodajDogadjaj(koncert2);
        festival.dodajDogadjaj(koncert3);

        Turnir turnir = new Turnir("Turnir brate", format.parse("05.10.2018 06:00"));
        Izbor izbor = new Izbor("Eurovision", format.parse("10.50.2018 09:05"));

        festival.dodajDogadjaj(turnir);
        festival.dodajDogadjaj(izbor);

        Ucesnik muzicar1 = new Ucesnik("Kobaja Grande", TipUcesnika.MUZICAR);
        Ucesnik muzicar2 = new Ucesnik("Brackica", TipUcesnika.MUZICAR);
        Ucesnik muzicar3 = new Ucesnik("PSY", TipUcesnika.MUZICAR);
        Ucesnik muzicar4 = new Ucesnik("FOX", TipUcesnika.MUZICAR);
        Ucesnik muzicar5 = new Ucesnik("The Glitch Mob", TipUcesnika.MUZICAR);

        izbor.dodajUcesnika(muzicar1);
        izbor.dodajUcesnika(muzicar2);
        izbor.dodajUcesnika(muzicar3);
        izbor.dodajUcesnika(muzicar4);
        izbor.dodajUcesnika(muzicar5);

        turnir.dodajUcesnika(muzicar1);
        turnir.dodajUcesnika(muzicar2);
        turnir.dodajUcesnika(muzicar3);
        turnir.dodajUcesnika(muzicar4);
        turnir.dodajUcesnika(muzicar5);

        Ucesnik gost1 = new Ucesnik("Paja Patak", TipUcesnika.GOST);
        Ucesnik gost2 = new Ucesnik("Miki Maus", TipUcesnika.GOST);
        Ucesnik gost3 = new Ucesnik("Mini Maus", TipUcesnika.GOST);
        Ucesnik gost4 = new Ucesnik("Snupi", TipUcesnika.GOST);
        Ucesnik gost5 = new Ucesnik("Sundjer Bob", TipUcesnika.GOST);

        turnir.dodajUcesnika(gost1);
        turnir.dodajUcesnika(gost2);
        turnir.dodajUcesnika(gost3);
        turnir.dodajUcesnika(gost4);
        turnir.dodajUcesnika(gost5);

        festival.odrziGlasanje();
        festival.ispisiProgram("ispis_programa.txt");
        festival.ispisiRangListe();
    }
}
