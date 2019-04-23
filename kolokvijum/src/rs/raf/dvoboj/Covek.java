package rs.raf.dvoboj;

public class Covek implements Igrac {

    private String ime;
    private int brojPoena, bonus;

    public Covek(String ime, int brojPoena) {
        this.ime = ime;
        this.brojPoena = brojPoena;
    }

    public String getIme() {
        return ime;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public int getBonus() {
        return bonus;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public void pobedi(Igrac igrac) {
        setBrojPoena(getBrojPoena() + 10);

        if(igrac instanceof Covek) {
            setBrojPoena(getBrojPoena() + ((Covek) igrac).getBonus());
        }

        System.out.println("Covek je pobedio " + getBrojPoena());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getIme() + " " + getBrojPoena() + " " + getBonus();
    }
}