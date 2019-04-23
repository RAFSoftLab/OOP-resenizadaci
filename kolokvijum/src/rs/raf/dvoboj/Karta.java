package rs.raf.dvoboj;

public abstract class Karta {

    private String naziv;
    private int napad, odbrana;
    private boolean unistena;
    private TipKarte tipKarte;
    private Polozaj polozaj;

    public Karta(String naziv, TipKarte tipKarte, Polozaj polozaj, int napad, int odbrana) {
        this.naziv = naziv;
        this.tipKarte = tipKarte;
        this.polozaj = polozaj;
        this.napad = napad;
        this.odbrana = odbrana;
    }

    public abstract boolean napadni(Karta karta);

    public abstract void primiNapad(Karta karta);

    public int umanjuNapad(int energija) {
        return napad = (napad < energija) ? 0 : napad - energija;
    }

    public int umanjuOdbranu(int energija) {
        return odbrana = (odbrana < energija) ? 0 : odbrana - energija;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getNapad() {
        return napad;
    }

    public int getOdbrana() {
        return odbrana;
    }

    public boolean isUnistena() {
        return unistena;
    }

    public TipKarte getTipKarte() {
        return tipKarte;
    }

    public Polozaj getPolozaj() {
        return polozaj;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setNapad(int napad) {
        this.napad = napad;
    }

    public void setOdbrana(int odbrana) {
        this.odbrana = odbrana;
    }

    public void setUnistena(boolean unistena) {
        this.unistena = unistena;
    }

    public void setTipKarte(TipKarte tipKarte) {
        this.tipKarte = tipKarte;
    }

    public void setPolozaj(Polozaj polozaj) {
        this.polozaj = polozaj;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getNaziv() + " " +
                getNapad() + " " + getOdbrana() + " " + isUnistena();
    }
}