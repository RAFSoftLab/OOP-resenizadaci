package rs.raf.poruke.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.poruke.core.Transfer;
import rs.raf.poruke.core.Utils;
import rs.raf.poruke.model.Datum;
import rs.raf.poruke.model.Poruka;
import rs.raf.poruke.model.TipSanduce;
import java.util.Arrays;
import java.util.List;

public class PregledProzor extends Stage {

    public PregledProzor() {
        setTitle("Ispit OOP - oktobar");
        setResizable(false);
        setScene(updateScene());
    }

    private Scene updateScene() {
        final TableView<Poruka> tvPoruke = new TableView<Poruka>();
        tvPoruke.setPrefHeight(200);

        Label lblBrojac = new Label("Ukupno poruka: "
                + Transfer.getInstance().dolazne.size()
                + " dolazne i "
                + Transfer.getInstance().odlazne.size()
                + " odlazne");

        final ComboBox<TipSanduce> cmbSanduce = new ComboBox<TipSanduce>();
        cmbSanduce.setItems(FXCollections.observableList(Arrays.asList(TipSanduce.values())));
        cmbSanduce.getSelectionModel().select(0);

        Button btnFilter = new Button("Prikaži");
        btnFilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tvPoruke.setItems(getPoruke(cmbSanduce.getSelectionModel().getSelectedItem()));
            }
        });
        btnFilter.fire();

        HBox hbFilter = new HBox(10);
        hbFilter.setAlignment(Pos.CENTER);
        hbFilter.getChildren().add(cmbSanduce);
        hbFilter.getChildren().add(btnFilter);

        TableColumn<Poruka, String> tcPrimalac = new TableColumn<Poruka, String>("Primalac");
        tcPrimalac.setCellValueFactory(new PropertyValueFactory<Poruka, String>("primalac"));

        TableColumn<Poruka, String> tcPosiljalac = new TableColumn<Poruka, String>("Posiljalac");
        tcPosiljalac.setCellValueFactory(new PropertyValueFactory<Poruka, String>("posiljalac"));

        TableColumn<Poruka, Datum> tcDatum = new TableColumn<Poruka, Datum>("Datum slanja");
        tcDatum.setCellValueFactory(new PropertyValueFactory<Poruka, Datum>("datum"));

        TableColumn<Poruka, String> tcNaslov = new TableColumn<Poruka, String>("Naslov");
        tcNaslov.setCellValueFactory(new PropertyValueFactory<Poruka, String>("naslov"));

        tvPoruke.getColumns().add(tcPrimalac);
        tvPoruke.getColumns().add(tcPosiljalac);
        tvPoruke.getColumns().add(tcDatum);
        tvPoruke.getColumns().add(tcNaslov);

        final TextArea taPoruka = new TextArea();
        taPoruka.setEditable(false);

        Button btnPrikazi = new Button("Prikaži tekst");
        btnPrikazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Poruka selektovana = tvPoruke.getSelectionModel().getSelectedItem();

                if(selektovana == null) {
                    Utils.error("Poruka nije selektovana");
                    return;
                }

                taPoruka.setText(selektovana.getTekst());
            }
        });

        Button btnNovaPoruka = new Button("Nova poruka");
        btnNovaPoruka.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new PorukaProzor().showAndWait();
                setScene(updateScene());
            }
        });

        VBox vbGlavni = new VBox(10);
        vbGlavni.setPadding(new Insets(5));
        vbGlavni.setAlignment(Pos.CENTER);
        vbGlavni.getChildren().add(lblBrojac);
        vbGlavni.getChildren().add(hbFilter);
        vbGlavni.getChildren().add(tvPoruke);
        vbGlavni.getChildren().add(btnPrikazi);
        vbGlavni.getChildren().add(taPoruka);
        vbGlavni.getChildren().add(btnNovaPoruka);

        return new Scene(vbGlavni, 600, 500);
    }

    private ObservableList<Poruka> getPoruke(TipSanduce tip) {
        if(tip == TipSanduce.DOLAZNE) {
            return FXCollections.observableList(Transfer.getInstance().dolazne);
        }

        return FXCollections.observableList(Transfer.getInstance().odlazne);
    }
}