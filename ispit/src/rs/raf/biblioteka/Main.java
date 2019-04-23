package rs.raf.biblioteka;

import javafx.application.Application;
import javafx.stage.Stage;
import rs.raf.biblioteka.core.Baza;
import rs.raf.biblioteka.view.GlavniProzor;

/**
 * @author Lazar Jelic - ljelic17@raf.rs
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        new Baza().ucitajClanove();
        new GlavniProzor().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}