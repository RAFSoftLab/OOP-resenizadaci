package rs.raf.kontakti;

import rs.raf.kontakti.model.Kontakt;

import java.util.ArrayList;
import java.util.List;

public class Transfer {

    // Singleton stuff

    private static Transfer instance;

    private Transfer() {
        kontakti = new ArrayList<Kontakt>();
    }

    public static Transfer getInstance() {
        if(instance == null) {
            instance = new Transfer();
        }

        return instance;
    }

    // Transfer data

    public List<Kontakt> kontakti;
}