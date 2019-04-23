package rs.raf.restoran.model;

public class Kupac {

    private String ime, adresa, telefon;
    private float budzet;

    public String getIme() {
        return ime;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public float getBudzet() {
        return budzet;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setBudzet(float budzet) {
        this.budzet = budzet;
    }

    @Override
    public String toString() {
        return getIme() + " " + getAdresa() + " " + getTelefon() + " " + getBudzet();
    }
}