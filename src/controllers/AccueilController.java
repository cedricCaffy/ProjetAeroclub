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
	
	/**
	 * Action qui suit le click sur le bouton Mon Compte
	 */
	@FXML
	private void actionBoutonMonCompte(){
		mainApp.afficherEcranMonCompte(this.membre);
	}
	
	/**
	 * Action qui suit le click sur le bouton saisir vol
	 */
	@FXML
	private void actionBoutonSaisirVol(){
		mainApp.afficherEcranSaisirVol(this.membre);
	}
	
	/**
	 * Action qui suit le click sur le bouton
	 * saisir paiement
	 */
	@FXML
	private void actionBoutonSaisirPaiement(){
		mainApp.afficherEcranSaisirPaiement(this.membre);
	}
	/**
	 * Affichage du message "Bonjour + nomMembre"
	 */
	private void afficherMessageBienvenue(){
		l_messageBienvenue.setText(l_messageBienvenue.getText()+" "+membre.getNom()+" "+membre.getPrenom());
	}
	
	/**
	 * Permet de setter le mainApp
	 * @param mainApp le mainApp qui utilise le controller
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	/**
	 * Recupere le mainApp
	 * @return le mainApp actuel
	 */
	public MainApp getMainApp(){
		return this.mainApp;
	}
	
	/**
	 * Permet de mettre a jour le membre
	 * qui est sur l'accueil
	 * @param membre le membre qui utilise la page d'accueil
	 */
	public void setMembre(Membre membre){
		this.membre=membre;
		afficherMessageBienvenue();
	}
	
	/**
	 * Retourne le membre qui utilise cette interface
	 * @return membre le membre qui utilise cette interface
	 */
	public Membre getMembre(){
		return this.membre;
	}
}
