package rs.raf.sah;

import java.util.List;

public abstract class Sahista {

    private BojaFigure bojaFigure;

    public Sahista(BojaFigure bojaFigure) {
        this.bojaFigure = bojaFigure;
    }

    public BojaFigure getBojaFigure() {
        return bojaFigure;
    }

    public void setBojaFigure(BojaFigure bojaFigure) {
        this.bojaFigure = bojaFigure;
    }

    public boolean odigrajPotez(SahovskaTabla tabla, Figura figura, Pozicija pozicija, Sahista sahista) {
        if(figura.getBojaFigure() != getBojaFigure()) {
            return false;
        }

        if(!figura.getPozicijaFigure().isValidnaPozicija()) {
            return false;
        }

        boolean naTabli = false;

        for(Figura f : tabla.getBeleFigure()) {
            if(f.equals(figura)) {
                naTabli = true;
                break;
            }
        }

        for(Figura f : tabla.getCrneFigure()) {
            if(f.equals(figura)) {
                naTabli = true;
                break;
            }
        }

        if(!naTabli) {
            return false;
        }

        boolean moguce = false;

        List<Pozicija> mogucePozicije = figura.vratiMogucePozicije();

        for(Pozicija p : mogucePozicije) {
            if(p.equals(pozicija)) {
                moguce = true;
                break;
            }
        }

        if(!moguce) {
            return false;
        }

        for(Figura f : tabla.getBeleFigure()) {
            if(f.getPozicijaFigure().equals(figura.getPozicijaFigure())) {
                if(f.getBojaFigure() == figura.getBojaFigure()) {
                    if(!f.equals(figura)) {
                        return false;
                    }
                }else {
                    figura.pomeriFiguru(pozicija);

                    f.getPozicijaFigure().setKolona(-1);
                    f.getPozicijaFigure().setRed(-1);

                    if(f instanceof Kralj) {
                        upisiPobedu(sahista);
                        tabla.ukloniSveFigure();
                    }

                    return true;
                }
            }
        }

        for(Figura f : tabla.getCrneFigure()) {
            if(f.getPozicijaFigure().equals(figura.getPozicijaFigure())) {
                if(f.getBojaFigure() == figura.getBojaFigure()) {
                    if(!f.equals(figura)) {
                        return false;
                    }
                }else {
                    figura.pomeriFiguru(pozicija);

                    f.getPozicijaFigure().setKolona(-1);
                    f.getPozicijaFigure().setRed(-1);

                    if(f instanceof Kralj) {
                        upisiPobedu(sahista);
                        tabla.ukloniSveFigure();
                    }

                    return true;
                }
            }
        }

        figura.pomeriFiguru(pozicija);

        return true;
    }

    public abstract void upisiPobedu(Sahista sahista);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getBojaFigure();
    }
}