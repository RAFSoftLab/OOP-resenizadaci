package rs.raf.poruke.core;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Utils {

    public static void error(String message) {
        System.out.println("Error: " + message);

        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        alert.show();
    }
}