package rs.raf.kontakti.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rs.raf.kontakti.Utils;
import rs.raf.kontakti.Transfer;
import rs.raf.kontakti.model.Kontakt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KreirajNoviProzor extends Stage {

    public KreirajNoviProzor() {
        setScene(getNewScene());
    }

    private Scene getNewScene() {
        List<String> grupeList = new ArrayList<String>();

        for(Kontakt kontakt : Transfer.getInstance().kontakti) {
            if(!grupeList.contains(kontakt.getGrupa())) {
                grupeList.add(kontakt.getGrupa());
            }
        }

        final ListView<Kontakt> lvKontakti = new ListView<Kontakt>();
        lvKontakti.setPrefHeight(200);
        lvKontakti.setItems(FXCollections.observableList(Transfer.getInstance().kontakti));

        final TextField tfIme = new TextField();
        tfIme.setPromptText("Ime");

        final TextField tfPrezime = new TextField();
        tfPrezime.setPromptText("Prezime");

        final TextField tfAdresa = new TextField();
        tfAdresa.setPromptText("Adresa");

        final TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");

        final TextField tfTelefon = new TextField();
        tfTelefon.setPromptText("Telefon");

        final ListView<String> lvTelefoni = new ListView<String>();
        lvTelefoni.setPrefHeight(100);

        Button btnDodaj = new Button("Dodaj");
        btnDodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lvTelefoni.getItems().add(tfTelefon.getText());
                tfTelefon.clear();
            }
        });

        final ComboBox<String> cmbGrupa = new ComboBox<String>();
        cmbGrupa.setItems(FXCollections.observableList(grupeList));

        Button btnSacuvaj = new Button("Sacuvaj");
        btnSacuvaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Kontakt kontakt = new Kontakt();
                kontakt.setIme(tfIme.getText());
                kontakt.setPrezime(tfPrezime.getText());
                kontakt.setAdresa(tfAdresa.getText());
                kontakt.setEmail(tfEmail.getText());
                kontakt.setTelefoni(lvTelefoni.getItems());
                kontakt.setGrupa(cmbGrupa.getSelectionModel().getSelectedItem());

                Utils.sacuvajKontakt(kontakt);

                close();
            }
        });

        GridPane gpDetalji = new GridPane();
        gpDetalji.setPadding(new Insets(10));
        gpDetalji.setHgap(10);
        gpDetalji.setVgap(10);
        gpDetalji.add(new Label("Ime:"), 0, 0);
        gpDetalji.add(new Label("Prezime:"), 0, 1);
        gpDetalji.add(new Label("Adresa:"), 0, 2);
        gpDetalji.add(new Label("Email:"), 0, 3);
        gpDetalji.add(new Label("Broj telefona:"), 0, 4);
        gpDetalji.add(new Label("Grupa:"), 0, 6);
        gpDetalji.add(tfIme, 1, 0);
        gpDetalji.add(tfPrezime, 1, 1);
        gpDetalji.add(tfAdresa, 1, 2);
        gpDetalji.add(tfEmail, 1, 3);
        gpDetalji.add(tfTelefon, 1, 4);
        gpDetalji.add(lvTelefoni, 1, 5);
        gpDetalji.add(cmbGrupa, 1, 6);
        gpDetalji.add(btnSacuvaj, 1, 7);
        gpDetalji.add(btnDodaj, 2, 4);

        setTitle("Ispit OOP - oktobar 2");

        return new Scene(gpDetalji);
    }
}