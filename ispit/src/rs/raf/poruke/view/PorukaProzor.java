package rs.raf.poruke.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rs.raf.poruke.core.Baza;
import rs.raf.poruke.core.Transfer;
import rs.raf.poruke.core.Utils;
import rs.raf.poruke.model.Datum;
import rs.raf.poruke.model.Poruka;

import java.util.Date;

public class PorukaProzor extends Stage {

    public PorukaProzor() {
        setTitle("Ispit OOP - oktobar");
        setScene(updateScene());
    }

    private Scene updateScene() {
        final TextField tfPrimalac = new TextField();
        tfPrimalac.setPrefWidth(200);

        final TextField tfNaslov = new TextField();
        tfNaslov.setPrefWidth(200);

        final TextArea taTekst = new TextArea();
        taTekst.setPrefRowCount(8);
        taTekst.setPrefWidth(200);

        Button btnPosalji = new Button("Posalji");
        btnPosalji.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String primalac = tfPrimalac.getText();
                String posljalac = Transfer.getInstance().email;
                Datum datum = new Datum(new Date());
                String naslov = tfNaslov.getText();
                String tekst = taTekst.getText();

                if(primalac.equals(posljalac)) {
                    Utils.error("Ne mozete sami sebi poslati poruku");
                    return;
                }

                new Baza().sacuvajPoruku(new Poruka(
                        primalac,
                        posljalac,
                        naslov,
                        tekst,
                        datum));

                close();
            }
        });

        GridPane gpGlavni = new GridPane();
        gpGlavni.setVgap(10);
        gpGlavni.setHgap(20);
        gpGlavni.setPadding(new Insets(20));
        gpGlavni.add(new Label("Primalac:"), 0, 0);
        gpGlavni.add(new Label("Naslov:"), 0, 1);
        gpGlavni.add(new Label("Tekst poruke:"), 0, 2);
        gpGlavni.add(btnPosalji, 0, 3);
        gpGlavni.add(tfPrimalac, 1, 0);
        gpGlavni.add(tfNaslov, 1, 1);
        gpGlavni.add(taTekst, 1, 2);

        return new Scene(gpGlavni);
    }
}