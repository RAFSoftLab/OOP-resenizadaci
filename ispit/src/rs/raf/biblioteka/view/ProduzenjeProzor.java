package rs.raf.biblioteka.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.biblioteka.core.Config;
import rs.raf.biblioteka.core.Utils;
import rs.raf.biblioteka.model.Clan;
import rs.raf.biblioteka.model.Kategorija;

import java.text.ParseException;
import java.util.Arrays;

public class ProduzenjeProzor extends Stage {

    public ProduzenjeProzor(Clan clan) {
        setTitle("Ispit OOP - grupa1");
        setScene(updateScene(clan));
    }

    private Scene updateScene(final Clan clan) {
        final ComboBox<Kategorija> cmbKategorija = new ComboBox<Kategorija>();
        cmbKategorija.setItems(FXCollections.observableList(Arrays.asList(Kategorija.values())));

        HBox hbKategorija = new HBox(10);
        hbKategorija.setAlignment(Pos.CENTER);
        hbKategorija.getChildren().add(new Label("Kategorija: "));
        hbKategorija.getChildren().add(cmbKategorija);

        VBox vbGore = new VBox(10);
        vbGore.setAlignment(Pos.CENTER);
        vbGore.getChildren().add(new Label("Ime i prezime: " + clan.getIme() + " " + clan.getPrezime()));
        vbGore.getChildren().add(new Label("Adresa: " + clan.getAdresa() + ", " + clan.getOpstina()));
        vbGore.getChildren().add(new Label("Članarina važi do: " + clan.getDatum()));
        vbGore.getChildren().add(hbKategorija);

        final TextField tfGodine = new TextField();

        final TextField tfBezPopusta = new TextField();
        tfBezPopusta.setEditable(false);

        final TextField tfSaPopustom = new TextField();
        tfSaPopustom.setEditable(false);

        Button btnObracun = new Button("Obracun");
        btnObracun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double bezPopusta = getGodine(tfGodine.getText()) * Config.cenaOsnovica;

                if(bezPopusta < 0) {
                    tfBezPopusta.clear();
                    tfSaPopustom.clear();
                    return;
                }

                Kategorija kategorija = cmbKategorija.getSelectionModel().getSelectedItem();

                if(kategorija == null) {
                    kategorija = clan.getKategorija();
                }

                tfBezPopusta.setText(String.valueOf(bezPopusta));
                tfSaPopustom.setText(String.valueOf(bezPopusta - kategorija.getCena(bezPopusta)));
            }
        });

        Button btnProduzi = new Button("Produzi članarinu");
        btnProduzi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int godine = getGodine(tfGodine.getText());

                if(godine < 0) {
                    return;
                }

                try {
                    clan.getDatum().dodajGodine(godine);
                }catch(Exception e) {
                    Utils.error(e.getMessage());
                }

                close();
            }
        });

        Button btnOdustani = new Button("Odustani");
        btnOdustani.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });

        GridPane gpDetalji = new GridPane();
        gpDetalji.setPadding(new Insets(10, 100, 10, 100));
        gpDetalji.setVgap(10);
        gpDetalji.setHgap(10);
        gpDetalji.add(new Label("Broj godina:"), 0, 0);
        gpDetalji.add(btnObracun, 0, 1);
        gpDetalji.add(new Label("Cena bez popusta:"), 0, 2);
        gpDetalji.add(new Label("Cena sa popustom:"), 0, 3);
        gpDetalji.add(new Label("Novi datum vazenja:"), 0, 4);
        gpDetalji.add(btnProduzi, 0, 5);
        gpDetalji.add(tfGodine, 1, 0);
        gpDetalji.add(tfBezPopusta, 1, 2);
        gpDetalji.add(tfSaPopustom, 1, 3);
        gpDetalji.add(btnOdustani, 1, 5);

        VBox vbGlavni = new VBox(10);
        vbGlavni.setPadding(new Insets(0, 20, 20, 20));
        vbGlavni.getChildren().add(vbGore);
        vbGlavni.getChildren().add(gpDetalji);

        return new Scene(vbGlavni);
    }

    private int getGodine(String text) {
        int godine = -1;

        try {
            godine = Integer.parseInt(text);
        }catch(NumberFormatException e) {
            Utils.error("Broj godina mora biti ceo broj");
        }

        return godine;
    }
}