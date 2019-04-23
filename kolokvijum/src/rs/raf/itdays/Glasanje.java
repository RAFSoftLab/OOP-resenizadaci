package rs.raf.itdays;

public interface Glasanje extends Comparable {

    void glasaj();

    @Override
    int compareTo(Object o);
}