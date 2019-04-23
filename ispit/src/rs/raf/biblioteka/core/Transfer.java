package rs.raf.biblioteka.core;

import rs.raf.biblioteka.model.Clan;
import java.util.ArrayList;

public class Transfer {

    private static Transfer instance;

    public ArrayList<Clan> clanovi;

    private Transfer() {
        clanovi = new ArrayList<Clan>();
    }

    public static Transfer getInstance() {
        if(instance == null) {
            instance = new Transfer();
        }

        return instance;
    }
}