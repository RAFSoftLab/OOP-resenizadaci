package rs.raf.kontakti.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.kontakti.Transfer;
import rs.raf.kontakti.model.Kontakt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlavniProzor extends Stage {

    public GlavniProzor() {
        setScene(getNewScene());
    }

    private Scene getNewScene() {
        Map<String, Integer> grupeMap = new HashMap<String, Integer>();

        for(Kontakt kontakt : Transfer.getInstance().kontakti) {
            if(grupeMap.containsKey(kontakt.getGrupa())) {
                grupeMap.put(kontakt.getGrupa(), grupeMap.get(kontakt.getGrupa()) + 1);
            }else {
                grupeMap.put(kontakt.getGrupa(), 1);
            }
        }

        // Gore

        List<String> grupeList = new ArrayList<String>();

        for(String grupa : grupeMap.keySet()) {
            grupeList.add(grupa + " - " + grupeMap.get(grupa));
        }

        final ComboBox<String> cmbGrupa = new ComboBox<String>();
        cmbGrupa.setItems(FXCollections.observableList(grupeList));

        final ListView<Kontakt> lvKontakti = new ListView<Kontakt>();
        lvKontakti.setPrefHeight(200);
        lvKontakti.setItems(FXCollections.observableList(Transfer.getInstance().kontakti));

        Button btnPrikazi = new Button("Prikazi");
        btnPrikazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Kontakt> filtrirani = new ArrayList<Kontakt>();

                for(Kontakt kontakt : Transfer.getInstance().kontakti) {
                    if(kontakt.getGrupa().equals(cmbGrupa.getSelectionModel().getSelectedItem().split(" - ")[0])) {
                        filtrirani.add(kontakt);
                    }
                }

                lvKontakti.setItems(FXCollections.observableList(filtrirani));
            }
        });

        HBox hbGore = new HBox();
        hbGore.setSpacing(10);
        hbGore.getChildren().add(cmbGrupa);
        hbGore.getChildren().add(btnPrikazi);

        // Centar

        final TextField tfImePrezime = new TextField();
        tfImePrezime.setPromptText("Ime i prezime");
        tfImePrezime.setEditable(false);

        final TextField tfAdresa = new TextField();
        tfAdresa.setPromptText("Adresa");
        tfImePrezime.setEditable(false);

        final TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");
        tfImePrezime.setEditable(false);

        final ListView<String> lvTelefoni = new ListView<String>();
        lvTelefoni.setPrefHeight(100);

        Button btnPrikaziDetalje = new Button("Prikazi detalje");
        btnPrikaziDetalje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Kontakt selektovan = lvKontakti.getSelectionModel().getSelectedItem();

                tfImePrezime.setText(selektovan.toString());
                tfAdresa.setText(selektovan.getAdresa());
                tfEmail.setText(selektovan.getEmail());
                lvTelefoni.setItems(FXCollections.observableList(selektovan.getTelefoni()));
            }
        });

        GridPane gpDetalji = new GridPane();
        gpDetalji.setHgap(10);
        gpDetalji.setVgap(10);
        gpDetalji.add(new Label("Ime i prezime:"), 0, 0);
        gpDetalji.add(new Label("Adresa:"), 0, 1);
        gpDetalji.add(new Label("Email:"), 0, 2);
        gpDetalji.add(new Label("Brojevi telefona:"), 0, 3);
        gpDetalji.add(tfImePrezime, 1, 0);
        gpDetalji.add(tfAdresa, 1, 1);
        gpDetalji.add(tfEmail, 1, 2);
        gpDetalji.add(lvTelefoni, 1, 3);

        HBox hbCentar = new HBox();
        hbCentar.setSpacing(20);
        hbCentar.setAlignment(Pos.CENTER);
        hbCentar.getChildren().add(lvKontakti);
        hbCentar.getChildren().add(btnPrikaziDetalje);
        hbCentar.getChildren().add(gpDetalji);

        // Glavni

        Button btnKreirajNovi = new Button("Kreiraj novi");
        btnKreirajNovi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new KreirajNoviProzor().showAndWait();
                setScene(getNewScene());
            }
        });

        VBox vbGlavni = new VBox();
        vbGlavni.setAlignment(Pos.CENTER);
        vbGlavni.setSpacing(10);
        vbGlavni.setPadding(new Insets(20));
        vbGlavni.getChildren().add(hbGore);
        vbGlavni.getChildren().add(hbCentar);
        vbGlavni.getChildren().add(btnKreirajNovi);

        setTitle("Ispit OOP - oktobar 2");

        return new Scene(vbGlavni);
    }
}