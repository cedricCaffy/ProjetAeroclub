package controllers;

import application.MainApp;
import javafx.fxml.FXML;
import model.classes.membres.Membre;

public class MonCompteController {
	private Membre membre;
	private MainApp mainApp;
	
	public MonCompteController(){}
	
	@FXML
	public void initialize(){
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}
