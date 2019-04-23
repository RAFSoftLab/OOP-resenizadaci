package rs.raf.restoran.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.restoran.controller.AkcijeController;
import rs.raf.restoran.model.Jelo;

import java.util.List;

public final class KupacScene extends Stage {

    private List<Jelo> jela;
    private AkcijeController akcije;

    public KupacScene(List<Jelo> jela) {
        this.jela = jela;

        akcije = new AkcijeController();

        inicijalizuj();
    }

    private void inicijalizuj() {
        final TextField tfIme = new TextField();
        final TextField tfAdresa = new TextField();
        final TextField tfTelefon = new TextField();
        final TextField tfBudzet = new TextField();

        float sumDorucak = 0;
        float sumRucak = 0;
        float sumVecera = 0;

        int countDorucak = 0;
        int countRucak = 0;
        int countVecera = 0;

        float avgDorucak = 0;
        float avgRucak = 0;
        float avgVecera = 0;

        for(Jelo jelo : jela) {
            switch(jelo.getObrok()) {
                case dorucak:
                    sumDorucak += jelo.getCena();
                    countDorucak++;
                    break;
                case rucak:
                    sumRucak += jelo.getCena();
                    countRucak++;
                    break;
                case vecera:
                    sumVecera += jelo.getCena();
                    countVecera++;
                    break;
            }
        }

        if(countDorucak > 0) {
            avgDorucak = sumDorucak / countDorucak;
        }

        if(countRucak > 0) {
            avgRucak = sumRucak / countRucak;
        }

        if(countVecera > 0) {
            avgVecera = sumVecera / countVecera;
        }

        Label lblDorucak = new Label("dorucak - " + avgDorucak);
        Label lblRucak = new Label("rucak - " + avgRucak);
        Label lblVecera = new Label("vecera - " + avgVecera);

        GridPane gpInput = new GridPane();
        gpInput.setPadding(new Insets(0, 60, 0, 60));
        gpInput.setHgap(30);
        gpInput.setVgap(10);
        gpInput.setAlignment(Pos.CENTER);
        gpInput.add(new Label("Ime kupca"), 0, 0);
        gpInput.add(new Label("Adresa"), 0, 1);
        gpInput.add(new Label("Broj telefona"), 0, 2);
        gpInput.add(new Label("Budzet"), 0, 3);
        gpInput.add(tfIme, 1, 0);
        gpInput.add(tfAdresa, 1, 1);
        gpInput.add(tfTelefon, 1, 2);
        gpInput.add(tfBudzet, 1, 3);

        Button btnMeni = new Button("Izbor menija");
        btnMeni.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                akcije.meni(jela, tfIme.getText(), tfAdresa.getText(), tfTelefon.getText(), tfBudzet.getText());
            }
        });

        VBox vbInfo = new VBox(10);
        vbInfo.setAlignment(Pos.CENTER);
        vbInfo.getChildren().addAll(
             new Label("Presecne cene u restoranu"),
             lblDorucak,
             lblRucak,
             lblVecera
        );

        VBox vbMain = new VBox(40);
        vbMain.setPadding(new Insets(20));
        vbMain.setAlignment(Pos.CENTER);
        vbMain.getChildren().addAll(
                gpInput,
                vbInfo,
                btnMeni
        );

        setScene(new Scene(vbMain));
        setTitle("Ispit OOP - grupa 2");
    }
}