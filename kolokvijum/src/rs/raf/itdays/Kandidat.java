package rs.raf.itdays;

public class Kandidat implements Comparable, Test {

    private String imeIPrezime, grad;
    private int brojPoena;

    public Kandidat(String imeIPrezime, String grad) {
        this.imeIPrezime = imeIPrezime;
        this.grad = grad;
    }

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public String getGrad() {
        return grad;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    @Override
    public void polaganjeTesta() {

    }

    @Override
    public int compareTo(Object o) {
        try {
            // sortiranje u opadajucem redosledu po broju poena
            // onaj koji ima manji broj poena treba da bude posle onog koji ima veci broj poena
            // ako je prvi "manji" od drugog (tj. ima veci broj poena) onda se vraca -1 i obratno...

            /*
                return brojPoena > k.brojPoena ? -1 :
				brojPoena < k.brojPoena ? 1 : 0;
			*/

            // prosledjujemo u obrnutom redosledu da bi se dobio -1 ako je k.brojPoena manji, tj. ako je this.brojPoena veci
            return Integer.compare(((Kandidat) o).brojPoena, brojPoena);
        }catch(ClassCastException e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getImeIPrezime() + " " + getGrad() + " " + getBrojPoena();
    }
}