package core;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Utils {

	public static void greska(String poruka) {
		System.out.println("Greska: " + poruka);
		
		Alert alert = new Alert(AlertType.ERROR, poruka);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.show();
	}
}