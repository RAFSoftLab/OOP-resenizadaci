package rs.raf.film.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import rs.raf.film.model.Film;

public final class RevizijaStage extends AbstractStage {

    private Film film;

    private TextField tfOcena;
    private TextArea taKomentar;

    public RevizijaStage(Object arg) {
        super(arg);
    }

    @Override
    protected void inicijalizuj(Object arg) {
        film = (Film) arg;

        tfOcena = new TextField();
        tfOcena.setPrefColumnCount(12);

        taKomentar = new TextArea();
        taKomentar.setPrefColumnCount(12);
        taKomentar.setPrefRowCount(2);
    }

    @Override
    protected Scene prikazi() {
        setAlwaysOnTop(true);
        initModality(Modality.APPLICATION_MODAL);

        VBox vbInfo = new VBox(10);
        vbInfo.setPadding(new Insets(25, 20, 0, 20));
        vbInfo.setAlignment(Pos.CENTER_LEFT);
        vbInfo.getChildren().addAll(
                new Label("Naslov: " + film.getNaslov()),
                new Label("Godina: " + film.getGodina()),
                new Label("Zanr: " + film.getZanr()),
                new Label("Reziser: " + film.getReziser()),
                new Label("Glumci: " + film.getGlumci())
        );

        GridPane gpInput = new GridPane();
        gpInput.setHgap(50);
        gpInput.setVgap(10);
        gpInput.setAlignment(Pos.CENTER_LEFT);
        gpInput.add(new Label("Ocena: "), 0, 0);
        gpInput.add(new Label("Komentar: "), 0, 1);
        gpInput.add(tfOcena, 1, 0);
        gpInput.add(taKomentar, 1, 1);

        Button btnSacuvaj = new Button("Sacuvaj ocenu");
        btnSacuvaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(akcije.sacuvaj(film, tfOcena.getText(), taKomentar.getText())) {
                    close();
                }
            }
        });

        Button btnOdustani = new Button("Odustani");
        btnOdustani.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(akcije.odustani()) {
                    close();
                }
            }
        });

        HBox hbDugmici = new HBox(10);
        hbDugmici.getChildren().addAll(
                btnSacuvaj,
                btnOdustani
        );

        VBox vbInput = new VBox(20);
        vbInput.setAlignment(Pos.CENTER_LEFT);
        vbInput.setPadding(new Insets(10, 50, 20, 50));
        vbInput.getChildren().addAll(
                gpInput,
                hbDugmici
        );

        VBox vbMain = new VBox(10);
        vbMain.setAlignment(Pos.CENTER);
        vbMain.getChildren().addAll(
                vbInfo,
                vbInput
        );

        return new Scene(vbMain);
    }
}