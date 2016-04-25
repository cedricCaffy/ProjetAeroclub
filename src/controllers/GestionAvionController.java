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
	
	/**
	 * Action qui suit le click du bouton retour
	 */
	@FXML
	private void actionBoutonRetour(){
		this.mainApp.afficherEcranAdministration(membre);
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
	}
}
