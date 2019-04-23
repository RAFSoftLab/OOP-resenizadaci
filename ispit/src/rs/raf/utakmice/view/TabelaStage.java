package view;

import java.util.ArrayList;
import java.util.Arrays;

import core.Transfer;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Reprezentacija;
import model.Utakmica;

public class TabelaStage extends AbstractStage {

	public TabelaStage() {
		setScene(updateScene());
	}
	
	@Override
	protected Scene updateScene() {
		ArrayList<Utakmica> utakmice = new ArrayList<Utakmica>();
		ArrayList<String> grupe = new ArrayList<String>();
		
		for(String grupa : Transfer.getInstance().utakmice.keySet()) {
			utakmice.addAll(Transfer.getInstance().utakmice.get(grupa));
			grupe.add(grupa);
		}
		
		TableColumn<Utakmica, String> tcTim1 = new TableColumn<Utakmica, String>("Tim1");
		tcTim1.setCellValueFactory(new PropertyValueFactory<Utakmica, String>("tim1"));
		
		TableColumn<Utakmica, String> tcTim2 = new TableColumn<Utakmica, String>("Tim2");
		tcTim2.setCellValueFactory(new PropertyValueFactory<Utakmica, String>("tim2"));
		
		TableColumn<Utakmica, Integer> tcGolovi1 = new TableColumn<Utakmica, Integer>("Golovi1");
		tcGolovi1.setCellValueFactory(new PropertyValueFactory<Utakmica, Integer>("golovi1"));
		
		TableColumn<Utakmica, Integer> tcGolovi2 = new TableColumn<Utakmica, Integer>("Golovi2");
		tcGolovi2.setCellValueFactory(new PropertyValueFactory<Utakmica, Integer>("golovi2"));
		
		TableColumn<Utakmica, String> tcGrupa = new TableColumn<Utakmica, String>("Grupa");
		tcGrupa.setCellValueFactory(new PropertyValueFactory<Utakmica, String>("grupa"));
		
		final TableView<Utakmica> tvSve = new TableView<Utakmica>();
		tvSve.setPrefHeight(200);
		tvSve.getColumns().add(tcTim1);
		tvSve.getColumns().add(tcTim2);
		tvSve.getColumns().add(tcGolovi1);
		tvSve.getColumns().add(tcGolovi2);
		tvSve.getColumns().add(tcGrupa);
		tvSve.setItems(FXCollections.observableList(utakmice));
		
		ComboBox<String> cmbGolovi = new ComboBox<String>();
		cmbGolovi.setItems(FXCollections.observableList(Arrays.asList("=", ">", "<")));
		
		TextField tfGolovi = new TextField();
		
		Button btnFiltriraj = new Button("Filtriraj");
		btnFiltriraj.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(tfGolovi.getText().isEmpty()) {
					Utils.greska("Broj golova nije unet");
					return;
				}
				
				if(cmbGolovi.getSelectionModel().getSelectedItem() == null) {
					Utils.greska("Nacin filtriranja nije postavljen");
					return;
				}
				
				ArrayList<Utakmica> utakmice = new ArrayList<Utakmica>();
				
				for(String grupa : Transfer.getInstance().utakmice.keySet()) {
					for(Utakmica utakmica : Transfer.getInstance().utakmice.get(grupa)) {
						int selekcija = cmbGolovi.getSelectionModel().getSelectedIndex();
						int golovi = 0;
						
						try {
							golovi = Integer.parseInt(tfGolovi.getText());
						}catch(NumberFormatException e) {
							Utils.greska("Broj golova treba da bude ceo broj");
							return;
						}
						
						if(selekcija == 0 && utakmica.getBrojGolova() != golovi) {
							continue;
						}

						if(selekcija == 1 && utakmica.getBrojGolova() <= golovi) {
							continue;
						}
						
						if(selekcija == 2 && utakmica.getBrojGolova() >= golovi) {
							continue;
						}
						
						utakmice.add(utakmica);
					}
				}
				
				tvSve.setItems(FXCollections.observableList(utakmice));
			}
		});
		
		Button btnPrikaziSve = new Button("Prikazi sve");
		btnPrikaziSve.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArrayList<Utakmica> utakmice = new ArrayList<Utakmica>();
				
				for(String grupa : Transfer.getInstance().utakmice.keySet()) {
					utakmice.addAll(Transfer.getInstance().utakmice.get(grupa));
				}
				
				tvSve.setItems(FXCollections.observableList(utakmice));
				
				cmbGolovi.getSelectionModel().select(-1);
				tfGolovi.clear();
			}
		});
		
		Button btnStatistika = new Button("Statistika");
		btnStatistika.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(tvSve.getItems() == null || tvSve.getItems().isEmpty()) {
					Utils.greska("Nema utakmica");
					return;
				}
				
				new StatistikaStage(tvSve.getItems()).showAndWait();
			}
		});
		
		HBox hbGoreChild = new HBox(10);
		hbGoreChild.setPadding(new Insets(0, 50, 0, 00));
		hbGoreChild.getChildren().add(new Label("Broj golova"));
		hbGoreChild.getChildren().add(cmbGolovi);
		hbGoreChild.getChildren().add(tfGolovi);
		hbGoreChild.getChildren().add(btnFiltriraj);
		hbGoreChild.getChildren().add(btnPrikaziSve);
		
		HBox hbGore = new HBox(10);
		hbGore.setPadding(new Insets(10, 50, 0, 50));
		hbGore.setAlignment(Pos.CENTER);
		hbGore.getChildren().add(hbGoreChild);
		hbGore.getChildren().add(btnStatistika);
		
		ComboBox<String> cmbGrupa = new ComboBox<String>();
		cmbGrupa.setItems(FXCollections.observableList(grupe));
		
		TableColumn<Reprezentacija, String> tcTim = new TableColumn<Reprezentacija, String>("Tim");
		tcTim.setCellValueFactory(new PropertyValueFactory<Reprezentacija, String>("ime"));
		
		TableColumn<Reprezentacija, Integer> tcPobede = new TableColumn<Reprezentacija, Integer>("Pobeda");
		tcPobede.setCellValueFactory(new PropertyValueFactory<Reprezentacija, Integer>("pobede"));
		
		TableColumn<Reprezentacija, Integer> tcNereseni = new TableColumn<Reprezentacija, Integer>("Neresenih");
		tcNereseni.setCellValueFactory(new PropertyValueFactory<Reprezentacija, Integer>("nereseni"));
		
		TableColumn<Reprezentacija, Integer> tcPorazi = new TableColumn<Reprezentacija, Integer>("Poraza");
		tcPorazi.setCellValueFactory(new PropertyValueFactory<Reprezentacija, Integer>("porazi"));
		
		TableColumn<Reprezentacija, Integer> tcGolovi = new TableColumn<Reprezentacija, Integer>("Golovi");
		tcGolovi.setCellValueFactory(new PropertyValueFactory<Reprezentacija, Integer>("sviGolovi"));
		
		TableColumn<Reprezentacija, Integer> tcPoeni = new TableColumn<Reprezentacija, Integer>("Poeni");
		tcPoeni.setCellValueFactory(new PropertyValueFactory<Reprezentacija, Integer>("poeni"));
		
		final TableView<Reprezentacija> tvGrupa = new TableView<Reprezentacija>();
		tvGrupa.setPrefHeight(150);
		tvGrupa.getColumns().add(tcTim);
		tvGrupa.getColumns().add(tcPobede);
		tvGrupa.getColumns().add(tcNereseni);
		tvGrupa.getColumns().add(tcPorazi);
		tvGrupa.getColumns().add(tcGolovi);
		tvGrupa.getColumns().add(tcPoeni);
		
		Button btnPrikaziTabelu = new Button("Prikazi tabelu");
		btnPrikaziTabelu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String grupa = cmbGrupa.getSelectionModel().getSelectedItem();
				
				if(grupa == null) {
					Utils.greska("Nacin filtriranja nije postavljen");
					return;
				}
				
				ArrayList<Reprezentacija> timovi = new ArrayList<Reprezentacija>();
				
				for(String utakmicaGrupa : Transfer.getInstance().utakmice.keySet()) {
					for(Utakmica utakmica : Transfer.getInstance().utakmice.get(grupa)) {
						Reprezentacija tim1 = new Reprezentacija(utakmica.getTim1());
						tim1.setGrupa(utakmicaGrupa);
						tim1.dodajGolove(utakmica.getGolovi1());
						tim1.dodajPrimljene(utakmica.getGolovi2());
						
						Reprezentacija tim2 = new Reprezentacija(utakmica.getTim2());
						tim2.setGrupa(utakmicaGrupa);
						tim2.dodajGolove(utakmica.getGolovi2());
						tim2.dodajPrimljene(utakmica.getGolovi1());
						
						if(utakmica.getGolovi1() > utakmica.getGolovi2()) {
							tim1.dodajPobede(1);
							tim2.dodajPorazi(1);
						}else if(utakmica.getGolovi1() < utakmica.getGolovi2()) {
							tim1.dodajPorazi(1);
							tim2.dodajPobede(1);
						}else {
							tim1.dodajNereseni(1);
							tim2.dodajNereseni(1);
						}
						
						if(timovi.contains(tim1)) {
							for(Reprezentacija r : timovi) {
								if(r.equals(tim1)) {
									r.dodajPobede(tim1.getPobede());
									r.dodajPorazi(tim1.getPorazi());
									r.dodajNereseni(tim1.getNereseni());
									r.dodajGolove(tim1.getGolovi());
									r.dodajPrimljene(tim1.getPrimljeni());
								}
							}
						}else {
							timovi.add(tim1);
						}
						
						if(timovi.contains(tim2)) {
							for(Reprezentacija r : timovi) {					
								if(r.equals(tim2)) {
									r.dodajPobede(tim2.getPobede());
									r.dodajPorazi(tim2.getPorazi());
									r.dodajNereseni(tim2.getNereseni());
									r.dodajGolove(tim2.getGolovi());
									r.dodajPrimljene(tim2.getPrimljeni());
								}
							}
						}else {
							timovi.add(tim2);
						}
					}
				}
				
				ArrayList<Reprezentacija> out = new ArrayList<Reprezentacija>();
				
				for(Reprezentacija r : timovi) {
					if(r.getGrupa().equals(grupa)) {
						out.add(r);
					}
				}
				
				tvGrupa.setItems(FXCollections.observableList(out));
			}
		});
	
		HBox hbGrupa = new HBox(10);
		hbGrupa.setPadding(new Insets(0, 0, 10, 0));
		hbGrupa.setAlignment(Pos.CENTER);
		hbGrupa.getChildren().add(new Label("Grupa"));
		hbGrupa.getChildren().add(cmbGrupa);
		hbGrupa.getChildren().add(btnPrikaziTabelu);
		
		VBox vbGlavni = new VBox(10);
		vbGlavni.setAlignment(Pos.CENTER);
		vbGlavni.getChildren().add(hbGore);
		vbGlavni.getChildren().add(tvSve);
		vbGlavni.getChildren().add(hbGrupa);
	    vbGlavni.getChildren().add(tvGrupa);
		
		return new Scene(vbGlavni);
	}
}