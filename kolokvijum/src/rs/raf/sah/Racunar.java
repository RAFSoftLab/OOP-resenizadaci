package rs.raf.sah;

public class Racunar extends Sahista {

    private Tezina tezina;

    public Racunar(BojaFigure bojaFigure) {
        super(bojaFigure);
    }

    public Tezina getTezina() {
        return tezina;
    }

    public void setTezina(Tezina tezina) {
        this.tezina = tezina;
    }

    @Override
    public void upisiPobedu(Sahista sahista) {
        if(sahista instanceof Covek) {
            Covek covek = (Covek) sahista;

            int brojPoena = covek.getBrojPoena() - 3;

            covek.setBrojPoena((brojPoena < 0) ? 0 : brojPoena);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + getTezina();
    }
}