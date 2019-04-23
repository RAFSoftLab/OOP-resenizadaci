package rs.raf.predmeti.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.predmeti.Bundle;
import rs.raf.predmeti.Utils;
import rs.raf.predmeti.model.Predmet;
import rs.raf.predmeti.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ObavezniProzor extends Stage {

    public ObavezniProzor() {
        setTitle("Ispit OOP - grupa1");
        setScene(getNewScene());
    }

    private Scene getNewScene() {
        List<Predmet> predmeti = Bundle.getInstance().predmeti;

        final TextField tfIme = new TextField();
        final TextField tfIndeks = new TextField();
        TextField tfEspb = new TextField();

        VBox vbGore = new VBox(10);
        vbGore.setAlignment(Pos.CENTER_LEFT);
        vbGore.getChildren().add(new Label("Ime i prezime studenta"));
        vbGore.getChildren().add(tfIme);
        vbGore.getChildren().add(new Label("Broj indeksa"));
        vbGore.getChildren().add(tfIndeks);
        vbGore.getChildren().add(new Label("Potreban broj ESPB"));
        vbGore.getChildren().add(tfEspb);

        List<Predmet> obavezni = new ArrayList<Predmet>();
        int ukupnoEspb = 0;

        for(Predmet p : predmeti) {
            if(p.isObavezan()) {
                ukupnoEspb += p.getEspb();
                obavezni.add(p);
            }
        }

        ListView<Predmet> lvPredmeti = new ListView<Predmet>();
        lvPredmeti.setPrefHeight(200);
        lvPredmeti.setItems(FXCollections.observableList(obavezni));

        VBox vbCentar = new VBox(10);
        vbCentar.setAlignment(Pos.CENTER_LEFT);
        vbCentar.getChildren().add(new Label("Spisak obaveznih predmeta"));
        vbCentar.getChildren().add(new Label("Ukupno ESPB:" + ukupnoEspb));
        vbCentar.getChildren().add(lvPredmeti);

        Button btnIzborniPredmeti = new Button("Izborni predmeti");
        btnIzborniPredmeti.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Student student = Bundle.getInstance().student;
                student.setIme(tfIme.getText());
                student.setIndeks(tfIndeks.getText());

                if(!student.hasData()) {
                    Utils.greska("Podaci o studentu su obavezni");
                    return;
                }

                new IzborniProzor().showAndWait();
            }
        });

        VBox vbGlavni = new VBox(10);
        vbGlavni.setAlignment(Pos.CENTER);
        vbGlavni.setPadding(new Insets(0, 80, 20, 80));
        vbGlavni.getChildren().add(vbGore);
        vbGlavni.getChildren().add(vbCentar);
        vbGlavni.getChildren().add(btnIzborniPredmeti);

        return new Scene(vbGlavni);
    }
}