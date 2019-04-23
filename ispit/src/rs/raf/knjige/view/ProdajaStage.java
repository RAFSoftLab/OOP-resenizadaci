package rs.raf.knjige.view;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import rs.raf.knjige.model.Knjiga;
import java.util.List;

public final class ProdajaStage extends AbstractStage {

    private static final int ROW_HEIGHT = 27;

    private List<Knjiga> knjige;

    public ProdajaStage(Object... arg) {
        super(arg);
    }

    @Override
    protected void inicijalizuj(Object... arg) {
        knjige = (List<Knjiga>) arg[0];
    }

    @Override
    protected Scene prikazi() {
        setAlwaysOnTop(true);
        initModality(Modality.APPLICATION_MODAL);

        float bezPopusta = 0, saPopustom = 0;

        for(Knjiga knjiga : knjige) {
            bezPopusta += knjiga.getCena();
            saPopustom += knjiga.getCena() * (1 - knjiga.getIzdavac().getPopust() / 100);
        }

        final TextField tfBezPopusta = new TextField();
        tfBezPopusta.setEditable(false);
        tfBezPopusta.setText(String.valueOf(bezPopusta));

        final TextField tfSaPopustom = new TextField();
        tfSaPopustom.setEditable(false);
        tfSaPopustom.setText(String.valueOf(saPopustom));

        final TextField tfDodatniPopust = new TextField();
        tfDodatniPopust.setText("0");
        tfBezPopusta.requestFocus();

        final Label lblUkupno = new Label();

        Button btnObracun = new Button("Obracun");
        btnObracun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblUkupno.setText("Ukupno za uplatu: " + akcije.obracunaj(tfSaPopustom.getText(), tfDodatniPopust.getText()));
            }
        });
        btnObracun.fire();

        GridPane gpInput = new GridPane();
        gpInput.setPadding(new Insets(0, 20, 0, 20));
        gpInput.setHgap(10);
        gpInput.setVgap(10);
        gpInput.setAlignment(Pos.CENTER);
        gpInput.add(new Label("Ukupna cena bez popusta: "), 0, 0);
        gpInput.add(new Label("Ukupna sa popustom izdavaca: "), 0, 1);
        gpInput.add(new Label("Dodatni popust: "), 0, 2);
        gpInput.add(btnObracun, 0, 3);
        gpInput.add(tfBezPopusta, 1, 0);
        gpInput.add(tfSaPopustom, 1, 1);
        gpInput.add(tfDodatniPopust, 1, 2);
        gpInput.add(lblUkupno, 1, 3);

        Button btnPodaja = new Button("Prodaja");
        btnPodaja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(akcije.prodaj(knjige)) {
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
        hbDugmici.setAlignment(Pos.CENTER);
        hbDugmici.getChildren().addAll(
                btnPodaja,
                btnOdustani
        );

        ListView<Knjiga> lvKnjige = new ListView<Knjiga>();
        lvKnjige.setItems(FXCollections.observableArrayList(knjige));
        lvKnjige.setPrefHeight(lvKnjige.getItems().size() * ROW_HEIGHT);

        VBox vbMain = new VBox(40);
        vbMain.getChildren().addAll(
                lvKnjige,
                gpInput,
                hbDugmici
        );

        return new Scene(vbMain);
    }
}