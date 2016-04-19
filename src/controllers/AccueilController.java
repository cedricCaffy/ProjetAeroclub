package controllers;

import model.classes.membres.Membre;
import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AccueilController {
	private MainApp mainApp;
	@FXML
	private Button b_monCompte;
	@FXML
	private Button b_saisirVol;
	@FXML
	private Button b_saisirPaiement;
	@FXML
	private Button b_preparerVol;
	@FXML
	private Button b_administration;
	
	private Membre membre;
	
	public AccueilController(){}
	
	@FXML
	private void initialize(){
		
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public MainApp getMainApp(){
		return this.mainApp;
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
	}
	
	public Membre getMembre(){
		return this.membre;
	}
}
