package rs.raf.restoran;

import javafx.application.Application;
import javafx.stage.Stage;
import rs.raf.restoran.controller.GlavniController;

/**
 * @author Lazar Jelic - ljelic17@raf.rs
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        new GlavniController().pokreni();
    }

    public static void main(String[] args) {
        launch(args);
    }
}