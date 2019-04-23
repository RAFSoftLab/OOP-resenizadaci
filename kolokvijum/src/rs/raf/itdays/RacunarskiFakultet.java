package rs.raf.itdays;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RacunarskiFakultet {

    private String nazivKonferencije;
    private ArrayList<Ucionica> ucionice;
    private ArrayList<Dogadjaj> dogadjaji;
    private ArrayList<Ucesnik> ucesniciKonferencije;
    private ArrayList<Kandidat> prijavljeniKandidati;

    public RacunarskiFakultet(String nazivKonferencije, ArrayList<Ucionica> ucionice) {
        this.nazivKonferencije = nazivKonferencije;
        this.ucionice = ucionice;

        dogadjaji = new ArrayList<>();
        ucesniciKonferencije = new ArrayList<>();
        prijavljeniKandidati = new ArrayList<>();
    }

    public ArrayList<Ucionica> getUcionice() {
        return ucionice;
    }

    public ArrayList<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }

    public ArrayList<Ucesnik> getUcesniciKonferencije() {
        return ucesniciKonferencije;
    }

    public ArrayList<Kandidat> getPrijavljeniKandidati() {
        return prijavljeniKandidati;
    }

    public void prijavaKandidata(String imeIPrezime, String grad) {
        prijavljeniKandidati.add(new Kandidat(imeIPrezime, grad));
    }

    private int brojMogucihMesta() {
        int brojMesta = 0;

        if(getUcionice() != null) {
            for(Ucionica u : getUcionice()) {
                brojMesta += u.getBrojMesta();
            }
        }

        return brojMesta;
    }

    private List<Glasanje> vratiDogadjajeGlasanje() {
        List<Glasanje> dogadjaji = new ArrayList<>();

        for(Dogadjaj d : getDogadjaji()) {
            if(d instanceof Glasanje) {
                dogadjaji.add((Glasanje) d);
            }
        }

        return dogadjaji;
    }

    public void polaganjeTesta() {
        // svaki kandidat polaze test
        for (Kandidat k: prijavljeniKandidati) {
            k.polaganjeTesta();
        }
        // sortiramo listu kandidata
        Collections.sort(prijavljeniKandidati);
        // odredjivanje broja ucesnika
        int brojUcesnika = brojMogucihMesta();
        // dodavanje ucesnika:
        for (int i = 0; i < brojUcesnika && i < prijavljeniKandidati.size(); i++) {
            // pravimo ucesnika
            Ucesnik u = new Ucesnik(prijavljeniKandidati.get(i));
            // dodajemo ga u listu ucesnika
            ucesniciKonferencije.add(u);
        }
    }

    public void odaberiDogadjaje() {
        // svaki ucesnik glasa za neki od dogadjaja
        List<Glasanje> svaGlasanja = vratiDogadjajeGlasanje();
        for (Ucesnik u : ucesniciKonferencije) {
            u.glasaj(svaGlasanja);
        }

        // odredjujemo dogadjaje na seminaru tako sto ih sortiramo po broju glasova
        svaGlasanja.sort(null);

        int ucSaProjektorima = 0;
        int ucBezProjektora = 0;
        int ucSaRacunarima = 0;

        for (Ucionica uc: ucionice) {
            if (uc.isSaRacunarima()) {
                ucSaRacunarima++;
            } else if (uc.isImaProjektor()) {
                ucSaProjektorima++;
            } else {
                ucBezProjektora++;
            }
        }

        ArrayList<Dogadjaj> odabraniDogadjaji = new ArrayList<>();

        for (Dogadjaj d : dogadjaji) {
            if (d instanceof Radionica && ucSaRacunarima > 0) {
                ucSaRacunarima--;
                odabraniDogadjaji.add(d);
            }
        }

        for (Glasanje g : svaGlasanja) {
            Predavanje p = (Predavanje)g;
            if (p.isPotrebanProjektor() && ucSaProjektorima > 0) {
                ucSaProjektorima--;
                odabraniDogadjaji.add(p);
            } else if (ucBezProjektora > 0) {
                ucBezProjektora--;
                odabraniDogadjaji.add(p);
            } else if (ucSaProjektorima > 0) {
                ucSaProjektorima--;
                odabraniDogadjaji.add(p);
            }
        }

        dogadjaji.clear();
        dogadjaji.addAll(odabraniDogadjaji);
    }

    public void odstampajDogadjajeIUcesnike() {
        try {
            PrintWriter pw1 = new PrintWriter(new File("dogadjaji.txt"));
            PrintWriter pw2 = new PrintWriter(new File("ucesnici.txt"));

            for (int i = 0; i < dogadjaji.size(); i++) {
                pw1.println(dogadjaji.get(i) + " " + ucionice.get(i));
            }

            for (Ucesnik u : ucesniciKonferencije) {
                pw2.println(u.toString());
            }

            pw1.close();
            pw2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}