package rs.raf.poruke;

import javafx.application.Application;
import javafx.stage.Stage;
import rs.raf.poruke.view.PregledProzor;
import rs.raf.poruke.view.PrijavaProzor;

/**
 * @author Lazar Jelic - ljelic17@raf.rs
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        new PrijavaProzor().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
