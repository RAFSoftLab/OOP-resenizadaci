package rs.raf.film;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import rs.raf.film.model.Film;
import rs.raf.film.model.Revizija;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static ArrayList<Film> ucitajFilmove(String putanja) {
        ArrayList<Film> filmovi = new ArrayList<Film>();

        Scanner s = null;

        try {
            s = new Scanner(new File(putanja));

            while(s.hasNextLine()) {
                String[] args = s.nextLine().split(";");

                if(args.length != 6) {
                    throw new IOException("Fajl nije ispravno formatiran");
                }

                Film film = new Film();
                film.setId(Integer.parseInt(args[0]));
                film.setNaslov(args[1]);
                film.setGodina(Integer.parseInt(args[2]));
                film.setZanr(args[3]);
                film.setReziser(args[4]);

                List<String> glumci = new ArrayList<String>();
                glumci.addAll(Arrays.asList(args[5].split(",")));
                film.setGlumci(glumci);

                filmovi.add(film);
            }
        }catch(IOException e) {
            greska(e.getMessage());
        }catch(NumberFormatException e) {
            greska("Jedinstveni broj i godina moraju biti celi brojevi");
        }finally {
            if(s != null) {
                s.close();
            }
        }

        return filmovi;
    }

    public static ArrayList<Revizija> ucitajRevizije(String putanja) {
        ArrayList<Revizija> revizije = new ArrayList<Revizija>();

        Scanner s = null;

        try {
            s = new Scanner(new File(putanja));

            while(s.hasNextLine()) {
                String[] args = s.nextLine().split(";");

                if(args.length != 3) {
                    throw new IOException("Fajl nije ispravno formatiran");
                }

                revizije.add(new Revizija()
                        .setFilmId(Integer.parseInt(args[0]))
                        .setOcena(Integer.parseInt(args[1]))
                        .setKomentar(args[2]));
            }
        }catch(IOException e) {
            greska(e.getMessage());
        }catch(NumberFormatException e) {
            greska("Jedinstveni broj i ocena moraju biti celi brojevi");
        }finally {
            if(s != null) {
                s.close();
            }
        }

        return revizije;
    }

    public static boolean upisiReviziju(Revizija revizija, String putanja) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(putanja, true));
            writer.append(String.valueOf(revizija.getFilmId()));
            writer.append(";");
            writer.append(String.valueOf(revizija.getOcena()));
            writer.append(";");
            writer.append(revizija.getKomentar());
            return true;
        }catch(IOException e) {
            greska(e.getMessage());
        }finally {
            if(writer != null) {
                writer.close();
            }
        }

        return false;
    }

    public static void greska(String poruka) {
        System.out.println("Greska: " + poruka);

        Alert alert = new Alert(Alert.AlertType.ERROR, poruka);
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        alert.show();
    }
}