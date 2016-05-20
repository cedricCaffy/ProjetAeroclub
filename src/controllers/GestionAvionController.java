package controllers;

import model.classes.avion.Avion;
import model.classes.membres.Membre;
import model.dao.AvionDAO;
import model.dao.AvionDAOImpl;
import view.popup.PopupError;
import view.popup.PopupInfoConfirmation;

import java.util.ArrayList;
import java.util.List;

import application.MainApp;
import bd.ConnexionBD;
import exceptions.DAOConfigurationException;
import exceptions.DAOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionAvionController {
	private MainApp mainApp;
	private Membre membre;
	private Avion avion;

	@FXML
	private TableView<Avion> tv_avion;

	@FXML
	private TableColumn<Avion, String> tc_immatriculation;

	@FXML
	private TableColumn<Avion, String> tc_nom;

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

	@FXML
	private void actionBoutonAjouterAvion(){
		this.mainApp.afficherEcranAjouterAvion(membre);
	}

	@FXML
	private void actionBoutonEditerAvion(){
		if (avion != null) {
			this.mainApp.afficherEcranEditerAvion(membre,avion);
		} else {
			new PopupError("Avion non sélectionné","","Veuillez sélectionner un avion pour pouvoir l'éditer");
		}
	}

	@FXML
	private void actionBoutonSupprimerAvion() {
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		ConnexionBD connexion = ConnexionBD.getInstance();
		AvionDAO avionDao = new AvionDAOImpl(connexion);
		if (avion != null) {
			try {
				popup.afficherPopup("Confirmation","Suppression","Souhaitez-vous vraiment supprimer cet avion ?\n");
				if(popup.getButtonClicked().get()==ButtonType.OK){
					avionDao.supprimerAvion(avion.getId());
					initialiserListeAvion();
				}
			} catch (DAOException e) {
				new PopupError("Erreur de base de données","",e.getMessage());
			} catch(DAOConfigurationException e){
				new PopupError("Erreur","Erreur de configuration",e.getMessage());
			}
		} else {
			new PopupError("Avion non sélectionné","","Veuillez sélectionner un avion pour pouvoir le supprimer");
		}
	}

	@FXML
	private void initialiserListeAvion(){
		AvionDAO avionDao;
		List<Avion> listAvion;
		ConnexionBD connexion = ConnexionBD.getInstance();
		avionDao = new AvionDAOImpl(connexion);
		listAvion = new ArrayList<Avion>();
		listAvion = avionDao.getAllAvion();
		tc_immatriculation.setCellValueFactory(cellData -> cellData.getValue().getImmatriculationProperty());
		tc_nom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		tv_avion.setItems((ObservableList<Avion>) listAvion);
		tv_avion.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> setAvion(newValue));
	}

	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

	public void setMembre(Membre membre){
		this.membre=membre;
		initialiserListeAvion();
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}
}
