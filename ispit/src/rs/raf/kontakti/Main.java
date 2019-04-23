package rs.raf.kontakti;

import javafx.application.Application;
import javafx.stage.Stage;
import rs.raf.kontakti.view.GlavniProzor;

/**
 * @author Lazar Jelic - ljelic17@raf.rs
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Utils.ucitajKontakte();
        new GlavniProzor().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}