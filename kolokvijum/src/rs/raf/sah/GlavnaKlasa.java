package rs.raf.sah;

import java.util.Random;

/**
* @author Lazar Jelic - ljelic17@raf.rs
*/

public class GlavnaKlasa {

    public static void main(String[] args) {
        SahovskaTabla tabla = new SahovskaTabla();

        Kralj beliKralj = new Kralj(BojaFigure.BELA, new Pozicija(0, 3));
        Kralj crniKralj = new Kralj(BojaFigure.CRNA, new Pozicija(7, 4));

        Skakac beliSkakac = new Skakac(BojaFigure.BELA, new Pozicija(0, 1));
        Skakac crniSkakac = new Skakac(BojaFigure.BELA, new Pozicija(7, 6));

        tabla.dodajFiguru(beliKralj);
        tabla.dodajFiguru(crniKralj);

        tabla.dodajFiguru(beliSkakac);
        tabla.dodajFiguru(crniSkakac);

        Covek covek = new Covek(BojaFigure.BELA);
        Racunar racunar = new Racunar(BojaFigure.CRNA);

        int brojPoteza = 10;
        Random random = new Random();

        for(int i = 0; i < brojPoteza; i++) {
            System.out.println("Potez " + i);

            covek.odigrajPotez(tabla, tabla.getBeleFigure().get(random.nextInt(tabla.getBeleFigure().size())),
                    new Pozicija(random.nextInt(SahovskaTabla.BR_REDOVA_KOLONA),
                            random.nextInt(SahovskaTabla.BR_REDOVA_KOLONA)), racunar);

            racunar.odigrajPotez(tabla, tabla.getCrneFigure().get(random.nextInt(tabla.getCrneFigure().size())),
                    new Pozicija(random.nextInt(SahovskaTabla.BR_REDOVA_KOLONA),
                            random.nextInt(SahovskaTabla.BR_REDOVA_KOLONA)), covek);
        }

        System.out.println("Igra zavrsena");
        tabla.odstampajTablu();
    }
}
