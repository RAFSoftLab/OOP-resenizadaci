package rs.raf.knjige.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import rs.raf.knjige.model.Izdavac;
import rs.raf.knjige.model.Knjiga;
import rs.raf.knjige.model.Kriterijum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class GlavniStage extends AbstractStage {

    private List<Knjiga> knjige;
    private Map<String, Izdavac> izdavaci;

    public GlavniStage(Object... arg) {
        super(arg);
    }

    @Override
    protected void inicijalizuj(Object... arg) {
        knjige = (List<Knjiga>) arg[0];
        izdavaci = (Map<String, Izdavac>) arg[1];
    }

    @Override
    protected Scene prikazi() {
        final TextField tfNaslov = new TextField();
        final TextField tfAutor = new TextField();
        final TextField tfGodina = new TextField();
        final CheckBox cbTacanNaslov = new CheckBox("Tacan naslov");
        final CheckBox cbTacanAutor = new CheckBox("Tacan autor");
        final ComboBox<Izdavac> cmbIzdavac = new ComboBox<Izdavac>();
        final TableView<Knjiga> tvKnjige = new TableView<Knjiga>();

        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Iterator<Knjiga> iter = tvKnjige.getItems().iterator();

                while(iter.hasNext()) {
                    Knjiga knjiga = iter.next();

                    if(knjiga.getStanje() <= 0) {
                        iter.remove();
                    }
                }

                tvKnjige.refresh();
            }
        });

        Button btnPretrazi = new Button("Pretrazi");
        btnPretrazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Kriterijum kriterijum = new Kriterijum();
                kriterijum.naslov = tfNaslov.getText();
                kriterijum.autor = tfAutor.getText();

                if(tfGodina.getText().isEmpty()) {
                    kriterijum.godina = -1;
                }else {
                    kriterijum.godina = Integer.parseInt(tfGodina.getText());
                }

                kriterijum.tacanNaslov = cbTacanNaslov.isSelected();
                kriterijum.tacanAutor = cbTacanAutor.isSelected();
                kriterijum.izdavac = cmbIzdavac.getSelectionModel().getSelectedItem();

                akcije.pretrazi(kriterijum, knjige, tvKnjige);
            }
        });

        for(String izdavacNaziv : izdavaci.keySet()) {
            cmbIzdavac.getItems().add(izdavaci.get(izdavacNaziv));
        }

        GridPane gpInput = new GridPane();
        gpInput.setPadding(new Insets(10, 0, 10, 0));
        gpInput.setHgap(10);
        gpInput.setVgap(10);
        gpInput.setAlignment(Pos.CENTER);
        gpInput.add(new Label("Naslov: "), 0, 0);
        gpInput.add(new Label("Autor: "), 0, 1);
        gpInput.add(new Label("Godina izdanja: "), 0, 2);
        gpInput.add(new Label("Izdavac: "), 0, 3);
        gpInput.add(btnPretrazi, 0, 4);
        gpInput.add(tfNaslov, 1, 0);
        gpInput.add(tfAutor, 1, 1);
        gpInput.add(tfGodina, 1, 2);
        gpInput.add(cmbIzdavac, 1, 3);
        gpInput.add(cbTacanNaslov, 2, 0);
        gpInput.add(cbTacanAutor, 2, 1);

        TableColumn<Knjiga, String> colNaslov = new TableColumn<Knjiga, String>("Naslov");
        colNaslov.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("naslov"));

        TableColumn<Knjiga, String> colAutor = new TableColumn<Knjiga, String>("Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("autor"));

        TableColumn<Knjiga, String> colGodina = new TableColumn<Knjiga, String>("Godina izdanja");
        colGodina.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("godina"));

        TableColumn<Knjiga, String> colIzdavac = new TableColumn<Knjiga, String>("Izdavac");
        colIzdavac.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("izdavac"));

        TableColumn<Knjiga, String> colCena = new TableColumn<Knjiga, String>("Cena");
        colCena.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("cena"));

        TableColumn<Knjiga, String> colStanje = new TableColumn<Knjiga, String>("Stanje");
        colStanje.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("stanje"));

        tvKnjige.getColumns().addAll(
                colNaslov,
                colAutor,
                colGodina,
                colIzdavac,
                colCena,
                colStanje
        );
        tvKnjige.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tvKnjige.setItems(FXCollections.observableArrayList(knjige));
        tvKnjige.setRowFactory(new Callback<TableView<Knjiga>, TableRow<Knjiga>>() {
            @Override
            public TableRow<Knjiga> call(TableView<Knjiga> param) {
                final TableRow<Knjiga> row = new TableRow<Knjiga>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getClickCount() == 2) {
                            akcije.prikaziProdaju(tvKnjige.getSelectionModel().getSelectedItems());
                        }
                    }
                });

                return row;
            }
        });
        tvKnjige.requestFocus();

        Button btnProdaja = new Button("Prodaja");
        btnProdaja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.prikaziProdaju(tvKnjige.getSelectionModel().getSelectedItems());
            }
        });

        Button btnSacuvaj = new Button("Sacuvaj novo stanje");
        btnSacuvaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.sacuvaj(tvKnjige.getItems());
            }
        });

        HBox hbDugmici = new HBox(10);
        hbDugmici.setAlignment(Pos.CENTER);
        hbDugmici.getChildren().addAll(
                btnProdaja,
                btnSacuvaj
        );

        VBox vbMain = new VBox();
        vbMain.setAlignment(Pos.CENTER);
        vbMain.getChildren().addAll(
                gpInput,
                tvKnjige,
                hbDugmici
        );

        return new Scene(vbMain, 800, 400);
    }
}