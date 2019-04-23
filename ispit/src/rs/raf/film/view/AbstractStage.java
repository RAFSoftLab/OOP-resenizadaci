package rs.raf.film.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import rs.raf.film.controller.AkcijeController;

abstract class AbstractStage extends Stage {

    protected AkcijeController akcije;

    AbstractStage(Object arg) {
        akcije = new AkcijeController();

        inicijalizuj(arg);

        setTitle("Ispit OOP - septembar");
        setScene(prikazi());
    }

    protected abstract void inicijalizuj(Object arg);
    protected abstract Scene prikazi();
}