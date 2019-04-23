package rs.raf.poruke.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import rs.raf.poruke.model.Datum;
import rs.raf.poruke.model.Poruka;

public class Baza {

    public void ucitajPoruke(String email) {
        Transfer.getInstance().email = email;

        Scanner s = null;

        try {
            s = new Scanner(new File(Config.porukePutanja));

            while(s.hasNextLine()) {
                String[] line = s.nextLine().split(",");

                Poruka poruka = new Poruka(
                        line[1],
                        line[0],
                        line[3],
                        line[4],
                        new Datum(line[2]));

                if(poruka.getPosiljalac().equals(email)) {
                    Transfer.getInstance().odlazne.add(poruka);
                }else if(poruka.getPrimalac().equals(email)) {
                    Transfer.getInstance().dolazne.add(poruka);
                }
            }
        }catch(Exception e) {
            Utils.error(e.getMessage());
        }finally {
            if(s != null) {
                s.close();
            }
        }
    }

    public void sacuvajPoruku(Poruka poruka) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(Config.porukePutanja, true));
            writer.println(poruka);

            Transfer.getInstance().odlazne.add(poruka);
        }catch(IOException e) {
            Utils.error(e.getMessage());
        }finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}