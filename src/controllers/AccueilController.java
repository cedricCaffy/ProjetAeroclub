package controllers;

import model.classes.membres.Membre;
import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AccueilController {
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
	@FXML
	private Label l_messageBienvenue;
	
	private MainApp mainApp;
	
	private Membre membre;
	
	public AccueilController(){}
	
	@FXML
	private void initialize(){

	}
	
	@FXML
	private void actionBoutonMonCompte(){
		mainApp.afficherEcranMonCompte(this.membre);
	}
	
	private void afficherMessageBienvenue(){
		l_messageBienvenue.setText(l_messageBienvenue.getText()+" "+membre.getNom()+" "+membre.getPrenom());
	}
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public MainApp getMainApp(){
		return this.mainApp;
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
		afficherMessageBienvenue();
	}
	
	public Membre getMembre(){
		return this.membre;
	}
}
