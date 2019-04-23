package rs.raf.poruke.model;

public class Poruka {

    private String primalac, posiljalac, naslov, tekst;
    private Datum datum;

    public Poruka(String primalac, String posiljalac, String naslov, String tekst, Datum datum) {
        this.primalac = primalac;
        this.posiljalac = posiljalac;
        this.naslov = naslov;
        this.tekst = tekst;
        this.datum = datum;
    }

    public String getPrimalac() {
        return primalac;
    }

    public String getPosiljalac() {
        return posiljalac;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public Datum getDatum() {
        return datum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(posiljalac);
        builder.append(",");
        builder.append(primalac);
        builder.append(",");
        builder.append(datum);
        builder.append(",");
        builder.append(naslov);
        builder.append(",");
        builder.append(tekst);

        return builder.toString();
    }
}