package rs.raf.predmeti;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import rs.raf.predmeti.model.Predmet;
import rs.raf.predmeti.model.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

public class Utils {

    public static void ucitajPredmete() {
        Scanner s = null;

        try {
            s = new Scanner(new File(Config.predmetiPutanja));

            Bundle.getInstance().predmeti.clear();

            while(s.hasNextLine()) {
                String[] line = s.nextLine().split(",");

                Predmet predmet = new Predmet(line[0],
                        Integer.parseInt(line[1]),
                        Boolean.parseBoolean(line[2]),
                        Integer.parseInt(line[3]));

                Bundle.getInstance().predmeti.add(predmet);

                if(predmet.isObavezan()) {
                    Bundle.getInstance().student.addPredmet(predmet);
                }
            }
        }catch(Exception e) {
            greska(e.getMessage());
        }finally {
            if(s != null) {
                s.close();
            }
        }
    }

    public static void sacuvajStudenta(Student student) {
        PrintWriter writer = null;

        Bundle.getInstance().student = student;

        try {
            writer = new PrintWriter(new FileWriter(Config.studentPutanja, false));

            writer.println(student);

            Collections.sort(student.getPredmeti());

            for(Predmet predmet : student.getPredmeti()) {
                writer.println(predmet);
            }
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