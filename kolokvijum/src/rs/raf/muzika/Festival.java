package rs.raf.muzika;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Festival {

    private String naziv;
    private ArrayList<Dogadjaj> dogadjaji;

    public Festival(String naziv) {
        this.naziv = naziv;

        dogadjaji = new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public boolean dodajDogadjaj(Dogadjaj dogadjaj) {
        dogadjaji.add(dogadjaj);

        return true;
    }

    public void odrziGlasanje() {
        Random r = new Random();
        // prolazimo kroz sve dogadjaje
        // samo takmicenja imaju ucesnike (koji glasaju)
        for (Dogadjaj d : dogadjaji) {
            if (d instanceof Takmicenje) {
                Takmicenje t = (Takmicenje) d;
                // svaki ucesnik moze da glasa
                for (Ucesnik u : t.getUcesnici()) {
                    // prolazi kroz sve dogadjaje
                    // ako na tom dogadjaju moze da se glasa
                    // onda glasa
                    for (Dogadjaj d1 : dogadjaji) {
                        // ako je koncert, glasace za njega
                        // sa verovatnocom 0.2
                        if (d1 instanceof Koncert) {
                            // vratice broj od 0-1 za koji postoji
                            // 0.2 sansa da ce biti manji od 0.2
                            if (r.nextDouble() < 0.2) {
                                ((Koncert) d1).dodajGlas();
                            }
                        }
                        // ako je izborno takmicenje
                        // za neke od ucesnika glasa sa verovatnocom 0.3
                        else if (d1 instanceof Izbor) {
                            for (Ucesnik u1 : ((Izbor) d1).getUcesnici()) {
                                if (r.nextDouble() < 0.3) {
                                    u1.dodajGlas();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void ispisiProgram(String nazivFajla) {
        File out = new File(nazivFajla);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(out);

            // soritramo dogadjaje u prirodnom poretku
            // koji je implementiran pomocu Comparable interfejsa
            Collections.sort(dogadjaji);

            for(Dogadjaj d : dogadjaji) {
                writer.append(d.toString());
                writer.append("\n");
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public void ispisiRangListe() {
        File out = new File("rang_lista.txt");
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(out);

            Collections.sort(dogadjaji);

            for(Dogadjaj d : dogadjaji) {
                if(d instanceof Turnir) {
                    Turnir turnir = (Turnir) d;

                    writer.append(turnir.getNaziv());
                    writer.append(" - ");
                    writer.append(turnir.odrediPobednika().toString());
                    writer.append("\n");
                }else if(d instanceof Izbor) {
                    Izbor izbor = (Izbor) d;

                    writer.append(izbor.getNaziv());
                    writer.append("\n");

                    ArrayList<Ucesnik> ucesnici = izbor.getUcesnici();

                    Collections.sort(ucesnici);

                    for(int i = 0; i < ucesnici.size(); i++) {
                        Ucesnik u = ucesnici.get(i);

                        writer.append(String.valueOf(i));
                        writer.append(". ");
                        writer.append(u.toString());
                        writer.append(" – ");
                        writer.append(String.valueOf(u.getBrojGlasova()));
                        writer.append("\n");
                    }
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}