package rs.raf.restoran.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rs.raf.restoran.controller.AkcijeController;
import rs.raf.restoran.model.Jelo;
import rs.raf.restoran.model.Kupac;
import rs.raf.restoran.model.Obrok;
import java.util.List;

public final class MeniScene extends Stage {

    private List<Jelo> jela;
    private Kupac kupac;
    private AkcijeController akcije;

    public MeniScene(List<Jelo> jela, Kupac kupac) {
        this.jela = jela;
        this.kupac = kupac;

        akcije = new AkcijeController();

        inicijalizuj();
    }

    private void inicijalizuj() {
        final ComboBox<Obrok> cbObrok = new ComboBox<Obrok>();
        cbObrok.setItems(FXCollections.observableArrayList(Obrok.dorucak, Obrok.rucak, Obrok.vecera));

        final ListView<Jelo> lvLevaJela = new ListView<Jelo>();
        lvLevaJela.setPrefHeight(100);
        lvLevaJela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        final Button btnPrikazi = new Button("Prikazi");
        btnPrikazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.prikazi(jela, cbObrok.getSelectionModel().getSelectedItem(), lvLevaJela);
            }
        });

        HBox hbObrok = new HBox(10);
        hbObrok.setAlignment(Pos.CENTER_LEFT);
        hbObrok.getChildren().addAll(
                cbObrok,
                btnPrikazi
        );

        VBox vbLevo = new VBox(10);
        vbLevo.setAlignment(Pos.CENTER_LEFT);
        vbLevo.getChildren().addAll(
                hbObrok,
                lvLevaJela
        );

        final ListView<Jelo> lvDesnaJela = new ListView<Jelo>();
        lvDesnaJela.setPrefHeight(150);

        final TextField tfUkupno = new TextField();
        tfUkupno.setEditable(false);

        Button btnIzaberi = new Button("Izaberi");
        btnIzaberi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.izaberi(lvLevaJela.getSelectionModel().getSelectedItems(), lvDesnaJela, tfUkupno, kupac.getBudzet());
            }
        });

        HBox hbPlacanje = new HBox(10);
        hbPlacanje.setAlignment(Pos.CENTER_LEFT);
        hbPlacanje.getChildren().addAll(
                new Label("Za placanje"),
                tfUkupno
        );

        VBox vbDesno = new VBox(10);
        vbDesno.setAlignment(Pos.CENTER_LEFT);
        vbDesno.getChildren().addAll(
                new Label("Izabrana jela"),
                lvDesnaJela,
                hbPlacanje
        );

        HBox hbListe = new HBox(10);
        hbListe.setAlignment(Pos.CENTER);
        hbListe.getChildren().addAll(
                vbLevo,
                btnIzaberi,
                vbDesno
        );

        Button btnSacuvaj = new Button("Sacuvaj");
        btnSacuvaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.sacuvaj(kupac, jela);
                close();
            }
        });

        VBox vbMain = new VBox(40);
        vbMain.setAlignment(Pos.CENTER);
        vbMain.setPadding(new Insets(20));
        vbMain.getChildren().addAll(
                new Label("Izbor menija za kupca: " + kupac.getIme() + ", Adresa: " + kupac.getAdresa() + "; Broj telefona: " + kupac.getTelefon()),
                hbListe,
                btnSacuvaj
        );

        setScene(new Scene(vbMain));
        setTitle("Ispit OOP - grupa 2");
        setAlwaysOnTop(true);
        initModality(Modality.APPLICATION_MODAL);
    }
}