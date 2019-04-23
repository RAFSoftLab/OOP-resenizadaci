package rs.raf.knjige.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import rs.raf.knjige.controller.AkcijeController;

public abstract class AbstractStage extends Stage {

    protected AkcijeController akcije;

    AbstractStage(Object... arg) {
        akcije = new AkcijeController();

        inicijalizuj(arg);

        setTitle("Ispit OOP - grupa1");
        setScene(prikazi());
    }

    protected abstract void inicijalizuj(Object... arg);
    protected abstract Scene prikazi();
}