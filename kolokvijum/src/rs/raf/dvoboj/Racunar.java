package rs.raf.dvoboj;

public class Racunar implements Igrac {

    @Override
    public void pobedi(Igrac igrac) {
        System.out.println("Racunar je pobedio");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}