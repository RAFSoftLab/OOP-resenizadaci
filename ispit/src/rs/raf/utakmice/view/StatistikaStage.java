package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import core.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Reprezentacija;
import model.Utakmica;

public class StatistikaStage extends AbstractStage {

	private List<Utakmica> utakmice;
	
	public StatistikaStage(List<Utakmica> utakmice) {
		this.utakmice = utakmice;
		
		setScene(updateScene());
	}
	
	@Override
	protected Scene updateScene() {
		TextField tfUtakmica = new TextField();
		tfUtakmica.setEditable(false);
		tfUtakmica.setText(utakmicaSaNajviseGolova());

		TextField tfReprezentacija = new TextField();
		tfReprezentacija.setEditable(false);
		tfReprezentacija.setText(reprezentacijaSaNajviseGolova());
		
		Button btnZatvori = new Button("Zatvori");
		btnZatvori.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				close();
			}
		});
		
		ListView<String> lvPobede = new ListView<String>();
		lvPobede.setItems(FXCollections.observableList(reprezentacijeSaPobedama(3)));
		lvPobede.setPrefHeight(100);
		
		GridPane gpGlavni = new GridPane();
		gpGlavni.setVgap(10);
		gpGlavni.setHgap(10);
		gpGlavni.setPadding(new Insets(10, 30, 10, 30));
		gpGlavni.add(new Label("Utakmica sa najvise golova:"), 0, 0);
		gpGlavni.add(new Label("Reprezentacija sa najvise datih golova:"), 0, 1);
		gpGlavni.add(new Label("Reprezentacija sa tri podebe:"), 0, 2);
		gpGlavni.add(btnZatvori, 0, 3);
		gpGlavni.add(tfUtakmica, 1, 0);
		gpGlavni.add(tfReprezentacija, 1, 1);
		gpGlavni.add(lvPobede, 1, 2);
		
		return new Scene(gpGlavni);
	}
	
	private String utakmicaSaNajviseGolova() {
		String out = "";
		int max = -1;
		
		for(Utakmica utakmica : utakmice) {
			if(utakmica.getBrojGolova() >= max) {
				max = utakmica.getBrojGolova();
				out = utakmica.getTim1() + " - " + utakmica.getTim2() + " - " + utakmica.getBrojGolova();
			}
		}
		
		return out;
	}
	
	private String reprezentacijaSaNajviseGolova() {
		ArrayList<Reprezentacija> timovi = new ArrayList<Reprezentacija>();
		
		for(Utakmica utakmica : utakmice) {
			Reprezentacija tim1 = new Reprezentacija(utakmica.getTim1());
			tim1.dodajGolove(utakmica.getGolovi1());
			
			Reprezentacija tim2 = new Reprezentacija(utakmica.getTim2());
			tim2.dodajGolove(utakmica.getGolovi2());
			
			if(timovi.contains(tim1)) {
				for(Reprezentacija r : timovi) {
					if(r.equals(tim1)) {
						r.dodajGolove(tim1.getGolovi());
					}
				}
			}else {
				timovi.add(tim1);
			}
			
			if(timovi.contains(tim2)) {
				for(Reprezentacija r : timovi) {
					if(r.equals(tim2)) {
						r.dodajGolove(tim2.getGolovi());
					}
				}
			}else {
				timovi.add(tim2);
			}
		}
		
		timovi.sort(null);
		
		return timovi.get(0).getIme();
	}
	
	private ArrayList<String> reprezentacijeSaPobedama(int pobede) {
		ArrayList<Reprezentacija> timovi = new ArrayList<Reprezentacija>();
		
		for(Utakmica utakmica : utakmice) {
			Reprezentacija tim1 = new Reprezentacija(utakmica.getTim1());
			Reprezentacija tim2 = new Reprezentacija(utakmica.getTim2());
			
			if(utakmica.getGolovi1() > utakmica.getGolovi2()) {
				tim1.dodajPobede(1);
			}else if(utakmica.getGolovi1() < utakmica.getGolovi2()) {
				tim2.dodajPobede(1);
			}
			
			if(timovi.contains(tim1)) {
				for(Reprezentacija r : timovi) {
					if(r.equals(tim1)) {
						r.dodajPobede(tim1.getPobede());
					}
				}
			}else {
				timovi.add(tim1);
			}
			
			if(timovi.contains(tim2)) {
				for(Reprezentacija r : timovi) {
					if(r.equals(tim2)) {
						r.dodajPobede(tim2.getPobede());
					}
				}
			}else {
				timovi.add(tim2);
			}
		}
		
		ArrayList<String> out = new ArrayList<String>();
		
		for(Reprezentacija r : timovi) {
			if(r.getPobede() == pobede) {
				out.add(r.getIme());
			}
		}
		
		return out;
	}
}