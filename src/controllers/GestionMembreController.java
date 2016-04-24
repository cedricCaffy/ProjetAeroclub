package controllers;

import javafx.fxml.FXML;
import model.classes.membres.Membre;
import application.MainApp;

public class GestionMembreController {
	private MainApp mainApp;
	private Membre membre;
	
	public GestionMembreController(){}
	
	@FXML
	private void initialize(){}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
	}
}
