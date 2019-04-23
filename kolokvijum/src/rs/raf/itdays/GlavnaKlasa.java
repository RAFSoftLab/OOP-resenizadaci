package rs.raf.itdays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
* @author Lazar Jelic - ljelic17@raf.rs
* @author Milan Tomic - mtomic@raf.rs (detaljno napisani komentari)
*/

public class GlavnaKlasa {

    public static void main(String[] args) throws ParseException {
        ArrayList<Ucionica> ucionice = new ArrayList<>();
        ucionice.add(new Ucionica("Kec", true, false, 3));
        ucionice.add(new Ucionica("Dvojka", false, false, 4));
        ucionice.add(new Ucionica("Sedmica", true, true, 5));
        ucionice.add(new Ucionica("Trojka", false, true, 6));

        RacunarskiFakultet raf = new RacunarskiFakultet("RAF IT Days 2018", ucionice);

        raf.prijavaKandidata("Lazar Jelic", "Krusevac");
        raf.prijavaKandidata("Deda Mraz", "Nis");
        raf.prijavaKandidata("Petar Petrovic", "Kragujevac");
        raf.prijavaKandidata("Janko Jankovic", "Beograd");
        raf.prijavaKandidata("Patak Daca", "Krusevac");
        raf.prijavaKandidata("Opet Deda Mraz", "Beograd");
        raf.prijavaKandidata("John Cena", "Krusevac");
        raf.prijavaKandidata("Jacky Johnny", "Vrsac");
        raf.prijavaKandidata("Miroslav Ilic", "Novi Sad");
        raf.prijavaKandidata("Au Brate", "Beograd");

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        raf.getDogadjaji().add(new Radionica("Masinsko ucenje", "Milos Milosevic", format.parse("01.04.2018 09:00")));
        raf.getDogadjaji().add(new Radionica("Robotika", "Jovan Jovanovic", format.parse("01.04.2018 10:00")));
        raf.getDogadjaji().add(new Radionica("Java programiranje", "Lazar Jelic", format.parse("02.04.2018 11:00")));
        raf.getDogadjaji().add(new Radionica("Internet stvari", "Mister No", format.parse("02.04.2018 12:00")));
        raf.getDogadjaji().add(new Radionica("Moze opet Java sto da ne", "Baba Mraz", format.parse("03.04.2018 13:00")));

        raf.getDogadjaji().add(new Predavanje("Android programiranje", "Lazar Jelic", format.parse("01.04.2018 11:00"), false));
        raf.getDogadjaji().add(new Predavanje("Skript jezici", "Jovana Petrovic", format.parse("01.04.2018 12:00"), true));
        raf.getDogadjaji().add(new Predavanje("Operativni sistemi", "Milica Nikolic", format.parse("02.04.2018 13:00"), false));
        raf.getDogadjaji().add(new Predavanje("Ciscenje prasine sa monitora", "Nikola Jovanovic", format.parse("02.04.2018 14:00"), true));
        raf.getDogadjaji().add(new Predavanje("Istorija racunara", "Ana Markovic", format.parse("03.04.2018 14:00"), false));

        raf.polaganjeTesta();
        raf.odaberiDogadjaje();
        raf.odstampajDogadjajeIUcesnike();
    }
}
