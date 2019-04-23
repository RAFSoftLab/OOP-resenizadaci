package rs.raf.sah;

public class Covek extends Sahista {

    private int brojPoena;

    public Covek(BojaFigure bojaFigure) {
        super(bojaFigure);
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    @Override
    public void upisiPobedu(Sahista sahista) {
        if(sahista instanceof Racunar) {
            Racunar racunar = (Racunar) sahista;

            if(racunar.getTezina() == Tezina.LAKO) {
                this.brojPoena += 5;
            }else if(racunar.getTezina() == Tezina.TESKO) {
                this.brojPoena += 10;
            }
        }else if(sahista instanceof Covek) {
            Covek covek = (Covek) sahista;

            if(covek.getBrojPoena() > this.brojPoena) {
                this.brojPoena += 10;
            }else {
                this.brojPoena += 5;
            }

            int brojPoena = covek.getBrojPoena() - 3;

            covek.setBrojPoena((brojPoena < 0) ? 0 : brojPoena);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + getBrojPoena();
    }
}