package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.classes.membres.Membre;
import application.MainApp;

public class AdministrationController {
	private MainApp mainApp;
	private Membre membre;
	@FXML
	private Button b_retour;
	@FXML
	private Button b_gestionMembre;
	
	public AdministrationController(){}
	
	@FXML
	private void actionBoutonRetour(){
		this.mainApp.afficherEcranAccueil(membre);
	}
	
	@FXML
	private void actionBoutonGestionMembre(){
		this.mainApp.afficherEcranGestionMembre(membre);
	}
	
	@FXML
	private void actionBoutonGestionAvion(){
		this.mainApp.afficherEcranGestionAvion(membre);
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
	}
}
