package rs.raf.poruke.core;

import rs.raf.poruke.model.Poruka;
import java.util.ArrayList;

public class Transfer {

    private static Transfer instance;

    public String email;
    public ArrayList<Poruka> odlazne, dolazne;

    private Transfer() {
        odlazne = new ArrayList<Poruka>();
        dolazne = new ArrayList<Poruka>();
    }

    public static Transfer getInstance() {
        if(instance == null) {
            instance = new Transfer();
        }

        return instance;
    }
}
