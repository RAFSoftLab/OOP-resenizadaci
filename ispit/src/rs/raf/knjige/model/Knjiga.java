package rs.raf.knjige.model;

public class Knjiga {

    private String autor, naslov;
    private int godina, stanje;
    private float cena;
    private Izdavac izdavac;

    Knjiga() {
        // Package-private konstruktor zato sto se
        // ova klasa instancira samo u 'KnjigaFactory'
        // zato sto zelimo da sprecimo ilegalno instanciranje
    }

    void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    void setAutor(String autor) {
        this.autor = autor;
    }

    void setGodina(int godina) {
        this.godina = godina;
    }

    void setIzdavac(Izdavac izdavac) {
        this.izdavac = izdavac;
    }

    void setCena(float cena) {
        this.cena = cena;
    }

    void setStanje(int stanje) {
        this.stanje = stanje;
    }

    public String getAutor() {
        return autor;
    }

    public String getNaslov() {
        return naslov;
    }

    public Izdavac getIzdavac() {
        return izdavac;
    }

    public int getGodina() {
        return godina;
    }

    public int getStanje() {
        return stanje;
    }

    public float getCena() {
        return cena;
    }

    public void prodaj() {
        stanje--;
    }

    @Override
    public String toString() {
        return getNaslov() + "," + getAutor() + "," + getGodina() + "," + getIzdavac().getNaziv();
    }
}