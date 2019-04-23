package rs.raf.softver;

public class Inzenjer extends Zaposleni implements Usavrsavanje {

    private int kvalitet;
    private InzenjerskaPozicija pozicija;

    public Inzenjer(String ime, String prezime, double plata, InzenjerskaPozicija pozicija) {
        super(ime, prezime, plata);

        switch(pozicija) {
            case JUNIOR_PROGRAMER:
                kvalitet = 5;
                break;
            case SENIOR_PROGRAMER:
            case TESTER:
                kvalitet = 10;
                break;
            case ARHITEKTA:
                kvalitet = 20;
                break;
        }
    }

    public int getKvalitet() {
        return kvalitet;
    }

    public void setKvalitet(int kvalitet) {
        this.kvalitet = kvalitet;
    }

    public InzenjerskaPozicija getPozicija() {
        return pozicija;
    }

    public void setPozicija(InzenjerskaPozicija pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public void seminar() {
        kvalitet++;
    }
}