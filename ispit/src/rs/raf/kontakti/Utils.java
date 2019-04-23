package rs.raf.kontakti;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import rs.raf.kontakti.model.Kontakt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {

    public static void ucitajKontakte() {
        Transfer.getInstance().kontakti.clear();

        Scanner s = null;

        try {
            s = new Scanner(new File(Config.kontaktiPutanja));

            while(s.hasNextLine()) {
                String[] line = s.nextLine().split(";");

                Kontakt kontakt = new Kontakt();
                kontakt.setIme(line[0]);
                kontakt.setPrezime(line[1]);
                kontakt.setAdresa(line[2]);
                kontakt.setEmail(line[3]);
                kontakt.setTelefoni(Arrays.asList(line[4].split(",")));
                kontakt.setGrupa(line[5]);

                Transfer.getInstance().kontakti.add(kontakt);
            }
        }catch(IOException e) {
            greska("Greska pri ucitavanju fajla " + Config.kontaktiPutanja);
        }finally {
            if(s != null) {
                s.close();
            }
        }
    }

    public static void sacuvajKontakt(Kontakt kontakt) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(Config.kontaktiPutanja, true));

            StringBuilder builder = new StringBuilder();
            builder.append(kontakt.getIme());
            builder.append(";");
            builder.append(kontakt.getPrezime());
            builder.append(";");
            builder.append(kontakt.getAdresa());
            builder.append(";");
            builder.append(kontakt.getEmail());
            builder.append(";");
            builder.append(kontakt.getTelefoni().toString().replace("[", "").replace("]", "").replace(", ", ","));
            builder.append(";");
            builder.append(kontakt.getGrupa());

            writer.println(builder.toString());
        }catch(IOException e) {
            greska("Greska pri ucitavanju fajla " + Config.kontaktiPutanja);
        }finally {
            if(writer != null) {
                writer.close();
            }
        }

        Transfer.getInstance().kontakti.add(kontakt);
    }

    public static void greska(String poruka) {
        Alert alert = new Alert(Alert.AlertType.ERROR, poruka);
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        alert.show();
    }
}