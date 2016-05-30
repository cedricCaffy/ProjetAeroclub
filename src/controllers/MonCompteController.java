package controllers;

import java.util.ArrayList;
import java.util.List;

import util.DateUtil;
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
import model.classes.paiement.Paiement;
import model.classes.paiement.PaiementUtil;
import model.classes.vol.Vol;
import model.dao.ChequeDAO;
import model.dao.ChequeDAOImpl;
import model.dao.EspeceDAO;
import model.dao.EspeceDAOImpl;
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
	private TableColumn<Vol,String> colonne_dateVol;

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
	private TableColumn<Paiement,String> colonne_datePaiement;

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
		list.sort((v1,v2)->v2.getDateVol().compareTo(v1.getDateVol()));
		colonne_dateVol.setCellValueFactory((cellData)->DateUtil.format(cellData.getValue().getDateVol()));
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
		ChequeDAO chequeDAO=new ChequeDAOImpl(connexion);
		EspeceDAO especeDAO=new EspeceDAOImpl(connexion);
		ObservableList<Paiement> list = FXCollections.observableArrayList();
		list.addAll(chequeDAO.getListeChequeByIdMembre(this.membre.getIdMembre()));
		list.addAll(especeDAO.getEspeceFromIdMembre(this.membre.getIdMembre()));
		list.sort((p1,p2)-> (p2.getDatePaiement().compareTo(p1.getDatePaiement())));
		colonne_datePaiement.setCellValueFactory((cellData)-> DateUtil.format(cellData.getValue().getDatePaiement()));
		colonne_montantPaiement.setCellValueFactory((cellData)-> cellData.getValue().getMontantProperty());
		colonne_moyenPaiement.setCellValueFactory((cellData)-> PaiementUtil.getTypePaiement(cellData.getValue()));
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
			afficherPaiement();
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
