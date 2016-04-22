package controllers;

import java.time.LocalDate;

import application.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import model.classes.membres.Membre;
import model.classes.membres.Pilote;
import model.classes.paiement.Cheque;
import model.classes.paiement.Paiement;
import model.classes.vol.Vol;

public class MonCompteController {
	private Membre membre;
	private MainApp mainApp;
	
	@FXML
	private Button b_retour;
	
	@FXML
	private Label l_solde;
	
	@FXML
	private TableView<Vol> tv_vols;
	
	@FXML
	private TableColumn<Vol,LocalDate> colonne_dateVol;
	
	@FXML
	private TableColumn<Vol,String> colonne_departVol;
	
	@FXML
	private TableColumn<Vol,String> colonne_destinationVol;
	
	@FXML
	private TableColumn<Vol,String> colonne_typeVol;
	
	@FXML
	private TableColumn<Vol,String> colonne_tempsVol;
	
	@FXML
	private TableView<Paiement> tv_paiements;
	
	@FXML
	private TableColumn<Paiement,LocalDate> colonne_datePaiement;
	
	@FXML
	private TableColumn<Paiement,Number> colonne_montantPaiement;
	
	@FXML
	private TableColumn<Paiement,String> colonne_moyenPaiement;
	
	public MonCompteController(){}
	
	@FXML
	public void initialize(){
	
	}
	
	/**
	 * Action qui suit le click sur le bouton retour
	 */
	@FXML
	private void actionBoutonRetour(){
		mainApp.afficherEcranAccueil(this.membre);
	}
	
	/**
	 * Set le membre qui regarde son compte
	 * @param membre
	 */
	public void setMembre(Membre membre){
		this.membre=membre;
		actionApresChargement();
	}
	
	/**
	 * Affiche les vols dans la table des vols
	 */
	private void afficherVols(){
		colonne_dateVol.setCellValueFactory((cellData)->cellData.getValue().getDateVolProperty());
		colonne_departVol.setCellValueFactory((cellData) -> cellData.getValue().getAerodromeDepart().getIdentifiantProperty());
		colonne_destinationVol.setCellValueFactory((cellData) -> cellData.getValue().getAerodromeArrivee().getIdentifiantProperty());
		colonne_typeVol.setCellValueFactory((cellData) -> cellData.getValue().getTypeProperty());
		colonne_tempsVol.setCellValueFactory((cellData) -> cellData.getValue().getTempsVol().toStringProperty());
		tv_vols.setItems(((Pilote)this.membre).getVols());
	}
	
	/**
	 * Affiche les differents paiements que le membre connecte
	 * a effectue
	 */
	private void afficherPaiement(){
		colonne_datePaiement.setCellValueFactory((cellData)->cellData.getValue().getDatePaiementProperty());
		colonne_montantPaiement.setCellValueFactory((cellData)-> cellData.getValue().getMontantProperty());
		colonne_moyenPaiement.setCellValueFactory((cellData)-> {
			if(cellData.getValue() instanceof Cheque){
				return new SimpleStringProperty("Chèque");
			}else{
				return new SimpleStringProperty("Espèces");
			}
		});
		tv_paiements.setItems(this.membre.getPaiements());
	}
	
	/**
	 * Action qui suit le chargement de la page
	 * (initialisation des champs)
	 */
	private void actionApresChargement(){
		afficherSolde();
		if(membre instanceof Pilote){
			afficherVols();
		}
		afficherPaiement();
	}
	
	/**
	 * Affiche le solde du membre connecte
	 */
	private void afficherSolde(){
		if(this.membre.getSolde()<0){
			l_solde.setTextFill(Color.RED);
		}else{
			l_solde.setTextFill(Color.GREEN);
		}
		l_solde.setText(l_solde.getText()+this.membre.getSolde()+"€");
	}
	
	/**
	 * Set le mainApp
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}
