package rs.raf.restoran.controller;

import rs.raf.restoran.Config;
import rs.raf.restoran.Utils;
import rs.raf.restoran.view.KupacScene;

public class GlavniController {

    public void pokreni() {
        new KupacScene(Utils.ucitajJela(Config.jelaPutanja)).show();
    }
}