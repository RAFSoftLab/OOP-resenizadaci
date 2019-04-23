package rs.raf.knjige;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import rs.raf.film.model.Film;
import rs.raf.film.model.Revizija;
import rs.raf.knjige.model.Izdavac;
import rs.raf.knjige.model.Knjiga;
import rs.raf.knjige.model.KnjigaFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Utils {

    public static List<Knjiga> ucitajKnjige(String putanja) {
        ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();

        Scanner s = null;

        KnjigaFactory factory = new KnjigaFactory();

        try {
            s = new Scanner(new File(putanja));

            while(s.hasNextLine()) {
                String[] args = s.nextLine().split(";");

                if(args.length != 6) {
                    throw new IOException("Fajl nije ispravno formatiran");
                }

                HashMap<String, String> argsMap = new HashMap<String, String>();
                argsMap.put("naslov", args[0]);
                argsMap.put("autor", args[1]);
                argsMap.put("godina", args[2]);
                argsMap.put("izdavac", args[3]);
                argsMap.put("cena", args[4]);
                argsMap.put("stanje", args[5]);

                knjige.add(factory.getKnjiga(argsMap));
            }
        }catch(IOException e) {
            greska(e.getMessage());
        }catch(NumberFormatException e) {
            greska("Godina, stanje i cena moraju biti celi brojevi");
        }finally {
            if(s != null) {
                s.close();
            }
        }

        return knjige;
    }

    public static Map<String, Izdavac> ucitajIzdavace(String putanja) {
        Map<String, Izdavac> izdavaci = new HashMap<String, Izdavac>();

        Scanner s = null;

        try {
            s = new Scanner(new File(putanja));

            while(s.hasNextLine()) {
                String[] args = s.nextLine().split(";");

                if(args.length != 2) {
                    throw new IOException("Fajl nije ispravno formatiran");
                }

                Izdavac izdavac = new Izdavac(args[0], Float.parseFloat(args[1]));

                izdavaci.put(izdavac.getNaziv(), izdavac);
            }
        }catch(IOException e) {
            greska(e.getMessage());
        }catch(NumberFormatException e) {
            greska("Popust mora biti ceo brojevi");
        }finally {
            if(s != null) {
                s.close();
            }
        }

        return izdavaci;
    }

    public static void sacuvajStanje(List<Knjiga> knjige, String putanja) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(putanja, false));

            for(Knjiga knjiga : knjige) {
                writer.append(knjiga.getNaslov());
                writer.append(";");
                writer.append(knjiga.getAutor());
                writer.append(";");
                writer.append(String.valueOf(knjiga.getGodina()));
                writer.append(";");
                writer.append(knjiga.getIzdavac().getNaziv());
                writer.append(";");
                writer.append(String.valueOf(knjiga.getCena()));
                writer.append(";");
                writer.append(String.valueOf(knjiga.getStanje()));
                writer.append("\n");
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
        Alert alert = new Alert(Alert.AlertType.ERROR, poruka);
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        alert.show();
    }
}