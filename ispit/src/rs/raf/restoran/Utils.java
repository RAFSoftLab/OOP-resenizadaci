package rs.raf.restoran;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import rs.raf.restoran.model.Jelo;
import rs.raf.restoran.model.Kupac;
import rs.raf.restoran.model.Obrok;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static List<Jelo> ucitajJela(String putanja) {
        ArrayList<Jelo> jela = new ArrayList<Jelo>();

        Scanner s = null;

        try {
            s = new Scanner(new File(putanja));

            while(s.hasNextLine()) {
                String[] args = s.nextLine().split(";");

                jela.add(new Jelo(args[0], Obrok.valueOf(args[1].toLowerCase()), Float.parseFloat(args[2])));
            }
        }catch(IOException e) {
            greska(e.getMessage());
        }catch(NumberFormatException e) {
            greska("Cena mora biti decimalan broj");
        }catch(IllegalArgumentException e) {
            greska("Obrok nije ispravan");
        }finally {
            if(s != null) {
                s.close();
            }
        }

        return jela;
    }

    private static void ispisiJela(List<Jelo> jela, Obrok obrok, PrintWriter writer) {
        for(Jelo jelo : jela) {
            if(jelo.getObrok() == obrok) {
                writer.append(jelo.getNaziv());
                writer.append(" ");
                writer.append(String.valueOf(jelo.getCena()));
                writer.append("\n");
            }
        }
    }

    public static void ispisiRacun(Kupac kupac, List<Jelo> jela, String putanja) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(putanja);

            writer.append("Podaci o kupcu:\n");
            writer.append(kupac.toString());
            writer.append("\nPodaci o jelima:\n");

            ispisiJela(jela, Obrok.dorucak, writer);
            ispisiJela(jela, Obrok.rucak, writer);
            ispisiJela(jela, Obrok.vecera, writer);

            float ukupno = 0;

            for(Jelo jelo : jela) {
                ukupno += jelo.getCena();
            }

            writer.append("Ukupno za placanje: ");
            writer.append(String.valueOf(ukupno));
            writer.append("\n");
        }catch(IOException e) {
            greska(e.getMessage());
        }finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

    public static void greska(String poruka) {
        System.out.println("Greska: " + poruka);

        Alert alert = new Alert(Alert.AlertType.ERROR, poruka);
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        alert.show();
    }
}