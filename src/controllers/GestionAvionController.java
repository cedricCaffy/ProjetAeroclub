package controllers;

import model.classes.membres.Membre;
import application.MainApp;
import javafx.fxml.FXML;

public class GestionAvionController {
	private MainApp mainApp;
	private Membre membre;
	
	public GestionAvionController(){}
	
	@FXML
	private void initialize(){}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
	}
}
