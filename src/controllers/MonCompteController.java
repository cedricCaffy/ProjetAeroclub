package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import view.popup.PopupError;
import view.popup.PopupException;
import exceptions.DAOConfigurationException;
import exceptions.DAOException;
import application.MainApp;
import bd.ConnexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import model.classes.membres.Membre;
import model.classes.membres.Pilote;
import model.classes.paiement.Paiement;
import model.classes.vol.Vol;
import model.dao.PaiementDAO;
import model.dao.PaiementDAOImpl;
import model.dao.VolDAO;
import model.dao.VolDAOImpl;

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
	private void afficherVols() throws DAOException,DAOConfigurationException{
		ConnexionBD connexion = ConnexionBD.getInstance();
		VolDAO volDao = new VolDAOImpl(connexion);
		List<Vol> listeVol = new ArrayList<Vol>();
		ObservableList<Vol> list = FXCollections.observableList(listeVol);
		list = volDao.getVolsFromMembre(this.membre.getIdMembre());
		colonne_dateVol.setCellValueFactory((cellData)->cellData.getValue().getDateVolProperty());
		colonne_departVol.setCellValueFactory((cellData) -> cellData.getValue().getAerodromeDepartProperty());
		colonne_destinationVol.setCellValueFactory((cellData) -> cellData.getValue().getAerodromeArriveeProperty());
		colonne_typeVol.setCellValueFactory((cellData) -> cellData.getValue().getTypeProperty());
		colonne_tempsVol.setCellValueFactory((cellData) -> cellData.getValue().getTempsVolStringProperty());
		tv_vols.setItems(list);
	}

	/**
	 * Affiche les differents paiements que le membre connecte
	 * a effectue
	 */
	private void afficherPaiement() throws DAOException,DAOConfigurationException{
		ConnexionBD connexion = ConnexionBD.getInstance();
		PaiementDAO paiementDao = new PaiementDAOImpl(connexion);
		List<Paiement> listePaiement = new ArrayList<Paiement>();
		ObservableList<Paiement> list = FXCollections.observableList(listePaiement);
		list = paiementDao.getPaiementsFromMembre(this.membre.getIdMembre());
		colonne_datePaiement.setCellValueFactory((cellData)->cellData.getValue().getDatePaiementProperty());
		colonne_montantPaiement.setCellValueFactory((cellData)-> cellData.getValue().getMontantProperty());
		colonne_moyenPaiement.setCellValueFactory((cellData)-> cellData.getValue().getTypePaiementProperty());
		tv_paiements.setItems(list);
	}

	/**
	 * Action qui suit le chargement de la page
	 * (initialisation des champs)
	 */
	private void actionApresChargement(){
		try{
			afficherSolde();
			afficherVols();
		}catch(DAOException e){
			new PopupException(e);
		}catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de configuration",e.getMessage());
		}
		/*afficherPaiement();*/
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
