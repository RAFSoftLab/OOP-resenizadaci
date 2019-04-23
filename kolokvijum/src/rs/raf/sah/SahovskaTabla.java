package rs.raf.sah;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SahovskaTabla {

    public static final int BR_REDOVA_KOLONA = 8;

    private List<Figura> beleFigure, crneFigure;

    public SahovskaTabla() {
        beleFigure = new ArrayList<>();
        crneFigure = new ArrayList<>();
    }

    public List<Figura> getBeleFigure() {
        return beleFigure;
    }

    public void setBeleFigure(List<Figura> beleFigure) {
        this.beleFigure = beleFigure;
    }

    public List<Figura> getCrneFigure() {
        return crneFigure;
    }

    public void setCrneFigure(List<Figura> crneFigure) {
        this.crneFigure = crneFigure;
    }

    public void dodajFiguru(Figura figura) {
        if(figura.getBojaFigure() == BojaFigure.BELA) {
            beleFigure.add(figura);
        }else if(figura.getBojaFigure() == BojaFigure.CRNA) {
            crneFigure.add(figura);
        }
    }

    public Figura vratiFiguruNaPolju(Pozicija pozicija) {
        if(!pozicija.isValidnaPozicija()) {
            return null;
        }

        for(Figura f : beleFigure) {
            if(f.getPozicijaFigure().equals(pozicija)) {
                return f;
            }
        }

        for(Figura f : crneFigure) {
            if(f.getPozicijaFigure().equals(pozicija)) {
                return f;
            }
        }

        return null;
    }

    public void ukloniSveFigure() {
        for(Figura f : beleFigure) {
            f.getPozicijaFigure().setKolona(-1);
            f.getPozicijaFigure().setRed(-1);
        }

        for(Figura f : crneFigure) {
            f.getPozicijaFigure().setKolona(-1);
            f.getPozicijaFigure().setRed(-1);
        }
    }

    public void odstampajTablu() {
        File out = new File("sahovskaTabla.txt");
        PrintWriter writer = null;

        try {
             writer = new PrintWriter(out);

             writer.append("Dimenzije: ");
             writer.append(String.valueOf(BR_REDOVA_KOLONA));
             writer.append("x");
             writer.append(String.valueOf(BR_REDOVA_KOLONA));
             writer.append("\n");

            for(Figura f : beleFigure) {
                writer.append(f.toString());
                writer.append("\n");
            }

            for(Figura f : crneFigure) {
                writer.append(f.toString());
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
}