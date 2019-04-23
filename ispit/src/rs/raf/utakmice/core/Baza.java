package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Utakmica;

public class Baza implements IBaza {
	
	public void ucitaj() {
        Scanner s = null;

        try {
            s = new Scanner(new File(Config.spPutanja));

            String grupa = null;
            
            while(s.hasNextLine()) {
            	String tmp = s.nextLine();
            	
            	if(tmp.length() == 1) {
            		grupa = tmp;
            		Transfer.getInstance().utakmice.put(grupa, new ArrayList<Utakmica>());
            		tmp = s.nextLine();
            	}
            	
                String[] line = tmp.split(",");
                String[] timovi = line[0].split("-");
                String[] golovi = line[1].split(":");
                
                Utakmica utakmica = new Utakmica(timovi[0], timovi[1], grupa, Integer.parseInt(golovi[0]), Integer.parseInt(golovi[1]));
                
                Transfer.getInstance().utakmice.get(grupa).add(utakmica);
            }
        }catch(Exception e) {
        	e.printStackTrace();
            Utils.greska(e.getMessage());
        }finally {
            if(s != null) {
                s.close();
            }
        }
    }
}
