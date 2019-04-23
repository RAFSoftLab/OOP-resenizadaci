package rs.raf.biblioteka.model;

public class Clan {

    private int godina;
    private String ime, prezime, adresa, opstina;
    private Datum datum;
    private Kategorija kategorija;

    public Clan(String ime, String prezime, int godina, String adresa, String opstina, Datum datum, Kategorija kategorija) {
        this.ime = ime;
        this.prezime = prezime;
        this.godina = godina;
        this.adresa = adresa;
        this.opstina = opstina;
        this.datum = datum;
        this.kategorija = kategorija;
    }

    public int getGodina() {
        return godina;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getOpstina() {
        return opstina;
    }

    public Datum getDatum() {
        return datum;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getIme());
        builder.append(";");
        builder.append(getPrezime());
        builder.append(";");
        builder.append(getGodina());
        builder.append(";");
        builder.append(getAdresa());
        builder.append(";");
        builder.append(getOpstina());
        builder.append(";");
        builder.append(getKategorija());
        builder.append(";");
        builder.append(getDatum());
        builder.append(";");

        return builder.toString();
    }
}