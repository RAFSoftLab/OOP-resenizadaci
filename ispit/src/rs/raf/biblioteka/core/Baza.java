package rs.raf.biblioteka.core;

import rs.raf.biblioteka.model.Clan;
import rs.raf.biblioteka.model.Datum;
import rs.raf.biblioteka.model.Kategorija;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Baza {

    public void ucitajClanove() {
        Scanner s = null;

        Transfer.getInstance().clanovi.clear();

        try {
            s = new Scanner(new File(Config.clanoviPutanja));

            while(s.hasNextLine()) {
                String[] line = s.nextLine().split(";");

                Transfer.getInstance().clanovi.add(new Clan(
                        line[0],
                        line[1],
                        Integer.parseInt(line[2]),
                        line[3],
                        line[4],
                        new Datum(new SimpleDateFormat(Config.datumFormat).parse(line[6])),
                        Kategorija.valueOf(line[5].toUpperCase())));
            }
        }catch(Exception e) {
            Utils.error(e.getMessage());
        }finally {
            if(s != null) {
                s.close();
            }
        }
    }

    public void sacuvajClanove() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(Config.clanoviPutanja, false));

            for(Clan clan : Transfer.getInstance().clanovi) {
                writer.println(clan);
            }
        }catch(Exception e) {
            Utils.error(e.getMessage());
        }finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}