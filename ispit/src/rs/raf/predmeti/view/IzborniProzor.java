package rs.raf.predmeti.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.predmeti.Bundle;
import rs.raf.predmeti.Utils;
import rs.raf.predmeti.model.Predmet;
import rs.raf.predmeti.model.Student;

import java.util.ArrayList;
import java.util.List;

public class IzborniProzor extends Stage {

    public IzborniProzor() {
        setTitle("Ispit OOP - grupa1");
        setScene(getNewScene());
    }

    private Scene getNewScene() {
        final Student student = Bundle.getInstance().student;
        final List<Predmet> predmeti = Bundle.getInstance().predmeti;

        final ComboBox<String> cmbGodina = new ComboBox<String>();
        cmbGodina.getItems().add("I godina");
        cmbGodina.getItems().add("II godina");
        cmbGodina.getItems().add("III godina");
        cmbGodina.getItems().add("IV godina");

        final ListView<Predmet> lvSvi = new ListView<Predmet>();
        lvSvi.setPrefWidth(270);
        lvSvi.setPrefHeight(150);
        lvSvi.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for(Predmet p : predmeti) {
            if(!p.isObavezan()) {
                lvSvi.getItems().add(p);
            }
        }

        Button btnPrikazi = new Button("Prikazi");
        btnPrikazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Predmet> filtrirani = new ArrayList<Predmet>();

                for(Predmet p : predmeti) {
                    if(!p.isObavezan() && p.getGodina() == cmbGodina.getSelectionModel().getSelectedIndex() + 1) {
                        filtrirani.add(p);
                    }
                }

                lvSvi.setItems(FXCollections.observableList(filtrirani));
            }
        });

        HBox hbGodina = new HBox(10);
        hbGodina.getChildren().add(cmbGodina);
        hbGodina.getChildren().add(btnPrikazi);

        VBox vbLevo = new VBox(10);
        vbLevo.setAlignment(Pos.CENTER_LEFT);
        vbLevo.getChildren().add(hbGodina);
        vbLevo.getChildren().add(lvSvi);

        final ListView<Predmet> lvIzabrani = new ListView<Predmet>();
        lvIzabrani.setPrefWidth(340);
        lvIzabrani.setPrefHeight(200);

        final TextField tfEspb = new TextField();
        tfEspb.setEditable(false);
        tfEspb.setText(String.valueOf(getUkupnoEspb(predmeti, true)));

        Button btnIzaberi = new Button("Izaberi");
        btnIzaberi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Predmet> izabrani = lvSvi.getSelectionModel().getSelectedItems();

                if(izabrani.isEmpty()) {
                    Utils.greska("Bar jedan predmet mora biti oznacen");
                    return;
                }

                tfEspb.setText(String.valueOf(getUkupnoEspb(predmeti, true) + getUkupnoEspb(izabrani, false)));

                lvIzabrani.getItems().addAll(izabrani);
                student.getPredmeti().addAll(izabrani);
            }
        });

        HBox hbEspb = new HBox(10);
        hbEspb.getChildren().add(new Label("Ukupno ESPB (obavezni + izborni)"));
        hbEspb.getChildren().add(tfEspb);

        VBox vbDesno = new VBox(10);
        vbDesno.setAlignment(Pos.CENTER_LEFT);
        vbDesno.getChildren().add(new Label("Spisak izbornih predmeta"));
        vbDesno.getChildren().add(lvIzabrani);
        vbDesno.getChildren().add(hbEspb);

        HBox hbCentar = new HBox(10);
        hbCentar.setAlignment(Pos.CENTER_LEFT);
        hbCentar.getChildren().add(vbLevo);
        hbCentar.getChildren().add(btnIzaberi);
        hbCentar.getChildren().add(vbDesno);

        Button btnSacuvaj = new Button("Sacuvaj");
        btnSacuvaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.sacuvajStudenta(student);
                close();
            }
        });

        VBox vbGlavni = new VBox(20);
        vbGlavni.setAlignment(Pos.CENTER);
        vbGlavni.setPadding(new Insets(20));
        vbGlavni.getChildren().add(new Label("Izbor predmeta za studenta: " + student.getIme() + ", " + student.getIndeks()));
        vbGlavni.getChildren().add(hbCentar);
        vbGlavni.getChildren().add(btnSacuvaj);

        return new Scene(vbGlavni);
    }

    private int getUkupnoEspb(List<Predmet> predmeti, boolean obavezni) {
        int ukupnoEspb = 0;

        for(Predmet p : predmeti) {
            if(p.isObavezan() == obavezni) {
                ukupnoEspb += p.getEspb();
            }
        }

        return ukupnoEspb;
    }
}