package rs.raf.sah;

public class Pozicija {

    private int kolona, red;

    public Pozicija(int kolona, int red) {
        this.kolona = kolona;
        this.red = red;
    }

    public boolean isValidnaPozicija() {
        if(kolona < 0 || red < 0) {
            return false;
        }

        if(kolona >= SahovskaTabla.BR_REDOVA_KOLONA || red >= SahovskaTabla.BR_REDOVA_KOLONA) {
            return false;
        }

        return true;
    }

    public int getKolona() {
        return kolona;
    }

    public void setKolona(int kolona) {
        this.kolona = kolona;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Pozicija) {
            Pozicija p = (Pozicija) obj;

            if(p.getKolona() == getKolona() && p.getRed() == getRed()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Pozicija: " + getKolona() + " " + getRed();
    }
}