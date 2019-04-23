package rs.raf.biblioteka.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.biblioteka.core.Baza;
import rs.raf.biblioteka.core.Transfer;
import rs.raf.biblioteka.core.Utils;
import rs.raf.biblioteka.model.Clan;
import rs.raf.biblioteka.model.Datum;
import rs.raf.biblioteka.model.Kategorija;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlavniProzor extends Stage {

    public GlavniProzor() {
        setTitle("Ispit OOP - grupa1");
        setScene(updateScene());
    }

    private Scene updateScene() {
        final TextField tfIme = new TextField();
        final TextField tfPrezime = new TextField();
        final TextField tfOpstina = new TextField();

        final ComboBox<Kategorija> cmbKategorija = new ComboBox<Kategorija>();
        cmbKategorija.setItems(FXCollections.observableList(Arrays.asList(Kategorija.values())));

        final CheckBox cbIme = new CheckBox("Tačno ime");
        final CheckBox cbPrezime = new CheckBox("Tačno prezime");

        final TableView<Clan> tvClanovi = new TableView<Clan>();

        Button btnPretrazi = new Button("Pretraži");
        btnPretrazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Clan> filtrirani = new ArrayList<Clan>();

                boolean tacnoIme = cbIme.isSelected();
                boolean tacnoPrezime = cbPrezime.isSelected();

                String ime = tfIme.getText();
                String prezime = tfPrezime.getText();
                String opstina = tfOpstina.getText();
                Kategorija kategorija = cmbKategorija.getSelectionModel().getSelectedItem();

                for(Clan clan : Transfer.getInstance().clanovi) {
                    if(!ime.isEmpty() && ((tacnoIme && !clan.getIme().equals(ime))
                            || (!tacnoIme && !clan.getIme().toLowerCase().contains(ime.toLowerCase())))) {
                        continue;
                    }

                    if(!prezime.isEmpty() && ((tacnoPrezime && !clan.getPrezime().equals(prezime))
                            || (!tacnoPrezime && !clan.getPrezime().toLowerCase().contains(prezime.toLowerCase())))) {
                        continue;
                    }

                    if(!opstina.isEmpty() && !clan.getOpstina().equals(opstina)) {
                        continue;
                    }

                    if(kategorija != null && clan.getKategorija() != kategorija) {
                        continue;
                    }

                    System.out.println(clan.getIme());

                    filtrirani.add(clan);
                }

                tvClanovi.setItems(FXCollections.observableList(filtrirani));
            }
        });

        GridPane gpFilter = new GridPane();
        gpFilter.setAlignment(Pos.CENTER);
        gpFilter.setPadding(new Insets(10, 100, 10, 100));
        gpFilter.setVgap(10);
        gpFilter.setHgap(10);
        gpFilter.add(new Label("Ime:"), 0, 0);
        gpFilter.add(new Label("Prezime:"), 0, 1);
        gpFilter.add(new Label("Opština:"), 0, 2);
        gpFilter.add(new Label("Kategorija:"), 0, 3);
        gpFilter.add(btnPretrazi, 0, 4);
        gpFilter.add(tfIme, 1, 0);
        gpFilter.add(tfPrezime, 1, 1);
        gpFilter.add(tfOpstina, 1, 2);
        gpFilter.add(cmbKategorija, 1, 3);
        gpFilter.add(cbIme, 2, 0);
        gpFilter.add(cbPrezime, 2, 1);

        TableColumn<Clan, String> tcIme = new TableColumn<Clan, String>("Ime");
        tcIme.setCellValueFactory(new PropertyValueFactory<Clan, String>("ime"));

        TableColumn<Clan, String> tcPrezime = new TableColumn<Clan, String>("Prezime");
        tcPrezime.setCellValueFactory(new PropertyValueFactory<Clan, String>("prezime"));

        TableColumn<Clan, Integer> tcGodina = new TableColumn<Clan, Integer>("Godina rodjenja");
        tcGodina.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("godina"));

        TableColumn<Clan, String> tcUlica = new TableColumn<Clan, String>("Ulica");
        tcUlica.setCellValueFactory(new PropertyValueFactory<Clan, String>("adresa"));

        TableColumn<Clan, String> tcOpstina = new TableColumn<Clan, String>("Opstina");
        tcOpstina.setCellValueFactory(new PropertyValueFactory<Clan, String>("opstina"));

        TableColumn<Clan, Kategorija> tcKategorija = new TableColumn<Clan, Kategorija>("Kategorija");
        tcKategorija.setCellValueFactory(new PropertyValueFactory<Clan, Kategorija>("kategorija"));

        TableColumn<Clan, Datum> tcDatum = new TableColumn<Clan, Datum>("Datum vazenja");
        tcDatum.setCellValueFactory(new PropertyValueFactory<Clan, Datum>("datum"));

        tvClanovi.setPrefHeight(200);
        tvClanovi.getColumns().add(tcIme);
        tvClanovi.getColumns().add(tcPrezime);
        tvClanovi.getColumns().add(tcGodina);
        tvClanovi.getColumns().add(tcUlica);
        tvClanovi.getColumns().add(tcOpstina);
        tvClanovi.getColumns().add(tcKategorija);
        tvClanovi.getColumns().add(tcDatum);
        tvClanovi.setItems(FXCollections.observableList(Transfer.getInstance().clanovi));

        Button btnProduzi = new Button("Produži članarinu");
        btnProduzi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Clan selektovan = tvClanovi.getSelectionModel().getSelectedItem();

                if(selektovan == null) {
                    Utils.error("Clan mora biti oznacen");
                    return;
                }

                new ProduzenjeProzor(selektovan).showAndWait();

                setScene(updateScene());
            }
        });

        Button btnSacuvaj = new Button("Sačuvaj novo stanje");
        btnSacuvaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Baza().sacuvajClanove();
            }
        });

        HBox hbDugmici = new HBox(10);
        hbDugmici.setAlignment(Pos.CENTER);
        hbDugmici.getChildren().add(btnProduzi);
        hbDugmici.getChildren().add(btnSacuvaj);

        VBox vbGlavni = new VBox();
        vbGlavni.setAlignment(Pos.CENTER);
        vbGlavni.getChildren().add(gpFilter);
        vbGlavni.getChildren().add(tvClanovi);
        vbGlavni.getChildren().add(hbDugmici);

        return new Scene(vbGlavni, 750, 400);
    }
}