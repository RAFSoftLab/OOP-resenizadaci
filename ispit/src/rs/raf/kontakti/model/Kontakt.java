package rs.raf.kontakti.model;

import java.util.List;

public class Kontakt {

    private String ime, prezime, adresa, email, grupa;
    private List<String> telefoni;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public List<String> getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(List<String> telefoni) {
        this.telefoni = telefoni;
    }

    @Override
    public String toString() {
        return getIme() + " " + getPrezime();
    }
}