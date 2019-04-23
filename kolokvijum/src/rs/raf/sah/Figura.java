package rs.raf.sah;

import java.util.List;

public abstract class Figura {

    private BojaFigure bojaFigure;
    private Pozicija pozicijaFigure;

    public Figura(BojaFigure bojaFigure, Pozicija pozicijaFigure) {
        this.bojaFigure = bojaFigure;
        this.pozicijaFigure = pozicijaFigure;
    }

    public BojaFigure getBojaFigure() {
        return bojaFigure;
    }

    public void setBojaFigure(BojaFigure bojaFigure) {
        this.bojaFigure = bojaFigure;
    }

    public Pozicija getPozicijaFigure() {
        return pozicijaFigure;
    }

    public void setPozicijaFigure(Pozicija pozicijaFigure) {
        this.pozicijaFigure = pozicijaFigure;
    }

    public boolean pomeriFiguru(Pozicija narednaPozicija) {
        for(Pozicija p : vratiMogucePozicije()) {
            if(p.equals(narednaPozicija)) {
                setPozicijaFigure(narednaPozicija);
                return true;
            }
        }

        return false;
    }

    public abstract List<Pozicija> vratiMogucePozicije();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getBojaFigure() + " " + getPozicijaFigure();
    }
}