package rs.raf.poruke.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import rs.raf.poruke.core.Baza;
import rs.raf.poruke.core.Utils;

public class PrijavaProzor extends Stage {

    public PrijavaProzor() {
        setTitle("Ispit OOP - oktobar");
        setScene(updateScene());
    }

    private Scene updateScene() {
        final TextField tfEmail = new TextField();

        Button btnPregled = new Button("Pregled poruka");
        btnPregled.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String email = tfEmail.getText();

                if(email.isEmpty()) {
                    Utils.error("Email nije unet");
                    return;
                }

                new Baza().ucitajPoruke(email);
                new PregledProzor().show();
                close();
            }
        });

        HBox hbGlavni = new HBox(10);
        hbGlavni.setPadding(new Insets(50));
        hbGlavni.getChildren().add(new Label("Unesite svoj email:"));
        hbGlavni.getChildren().add(tfEmail);
        hbGlavni.getChildren().add(btnPregled);

        return new Scene(hbGlavni);
    }
}