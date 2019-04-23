package rs.raf.film.model;

import java.util.ArrayList;
import java.util.List;

public class Film {

    private int id, godina;
    private String naslov, reziser, zanr;
    private List<String> glumci;
    private List<Revizija> revizije;

    public Film() {
        revizije = new ArrayList<Revizija>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public void setGlumci(List<String> glumci) {
        this.glumci = glumci;
    }

    public int getId() {
        return id;
    }

    public String getNaslov() {
        return naslov;
    }

    public int getGodina() {
        return godina;
    }

    public float getProsecnaOcena() {
        int sum = 0;
        int count;

        for(count = 0; count < revizije.size(); count++) {
            sum += revizije.get(count).getOcena();
        }

        if(count == 0) {
            return 0;
        }

        return (float) sum / count;
    }

    public String getZanr() {
        return zanr;
    }

    public String getReziser() {
        return reziser;
    }

    public String getGlumci() {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < glumci.size(); i++) {
            if(i > 0) {
                builder.append(", ");
            }

            builder.append(glumci.get(i));
        }

        return builder.toString();
    }

    public void dodajReviziju(Revizija revizija) {
        revizije.add(revizija);
    }
}