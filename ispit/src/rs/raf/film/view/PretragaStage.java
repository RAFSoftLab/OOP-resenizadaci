package rs.raf.film.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import rs.raf.film.model.Film;
import java.util.List;

public final class PretragaStage extends AbstractStage {

    private List<Film> filmovi;

    private TextField tfNaslov;
    private ToggleGroup tgNaslov;
    private TableView<Film> tvFilmovi;

    public PretragaStage(Object arg) {
        super(arg);
    }

    @Override
    protected void inicijalizuj(Object arg) {
        filmovi = (List<Film>) arg;

        tfNaslov = new TextField();
        tgNaslov = new ToggleGroup();
        tvFilmovi = new TableView<Film>();
    }

    @Override
    protected Scene prikazi() {
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tvFilmovi.refresh();
            }
        });

        HBox hbNaslov = new HBox(20);
        hbNaslov.setAlignment(Pos.CENTER_LEFT);
        hbNaslov.getChildren().addAll(
                new Label("Naslov:"),
                tfNaslov
        );

        RadioButton rbTacan = new RadioButton("Tacan naslov");
        rbTacan.setToggleGroup(tgNaslov);

        RadioButton rbDeo = new RadioButton("Deo naslova");
        rbDeo.setToggleGroup(tgNaslov);

        HBox hbToggle = new HBox(5);
        hbToggle.setAlignment(Pos.CENTER_LEFT);
        hbToggle.getChildren().addAll(
                rbTacan,
                rbDeo
        );

        Button btnPretrazi = new Button("Pretrazi");
        btnPretrazi.setAlignment(Pos.CENTER_LEFT);
        btnPretrazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.pretrazi(tfNaslov.getText(), tgNaslov, filmovi, tvFilmovi);
            }
        });

        VBox vbPretraga = new VBox(10);
        vbPretraga.setPadding(new Insets(10, 50, 10, 50));
        vbPretraga.getChildren().addAll(
                hbNaslov,
                hbToggle,
                btnPretrazi
        );

        TableColumn<Film, String> colNaslov = new TableColumn<Film, String>("Naslov");
        colNaslov.setCellValueFactory(new PropertyValueFactory<Film, String>("naslov"));

        TableColumn<Film, String> colGodina = new TableColumn<Film, String>("Godina");
        colGodina.setCellValueFactory(new PropertyValueFactory<Film, String>("godina"));

        TableColumn<Film, String> colProsecnaOcena = new TableColumn<Film, String>("Prosecna ocena");
        colProsecnaOcena.setCellValueFactory(new PropertyValueFactory<Film, String>("prosecnaOcena"));

        TableColumn<Film, String> colZanr = new TableColumn<Film, String>("Zanr");
        colZanr.setCellValueFactory(new PropertyValueFactory<Film, String>("zanr"));

        tvFilmovi.getColumns().addAll(
                colNaslov,
                colGodina,
                colProsecnaOcena,
                colZanr
        );
        tvFilmovi.setRowFactory(new Callback<TableView<Film>, TableRow<Film>>() {
            @Override
            public TableRow<Film> call(TableView<Film> param) {
                final TableRow<Film> row = new TableRow<Film>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getClickCount() == 2) {
                            akcije.oceni(row.getItem());
                        }
                    }
                });

                return row;
            }
        });
        tvFilmovi.setItems(FXCollections.observableArrayList(filmovi));

        Button btnOceni = new Button("Oceni");
        btnOceni.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.oceni(tvFilmovi.getSelectionModel().getSelectedItem());
            }
        });

        VBox vbMain = new VBox();
        vbMain.setAlignment(Pos.CENTER);
        vbMain.getChildren().addAll(
                vbPretraga,
                tvFilmovi,
                btnOceni
        );

        return new Scene(vbMain, 350, 300);
    }
}