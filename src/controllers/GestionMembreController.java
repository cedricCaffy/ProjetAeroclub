package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.classes.membres.Membre;
import model.dao.AdresseDAO;
import model.dao.AdresseDAOImpl;
import model.dao.MembresDAO;
import model.dao.MembresDAOImpl;
import view.popup.PopupError;
import view.popup.PopupException;
import view.popup.PopupInfoConfirmation;

import java.util.ArrayList;
import java.util.List;

import application.MainApp;
import bd.ConnexionBD;
import exceptions.DAOConfigurationException;
import exceptions.DAOException;

public class GestionMembreController {
	private MainApp mainApp;
	private Membre membre;
	private Membre membreSelec;

	@FXML
	private TableView<Membre> tv_membre;

	@FXML
	private TableColumn<Membre, String> tc_nom;

	@FXML
	private TableColumn<Membre, String> tc_prenom;

	@FXML
	private TableColumn<Membre, String> tc_telephone;

	@FXML
	private TableColumn<Membre, String> tc_email;

	public GestionMembreController(){}

	@FXML
	private void initialize(){}

	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

	/**
	 * Action qui suit le click sur le bouton retour
	 */
	@FXML
	private void actionBoutonRetour(){
		this.mainApp.afficherEcranAdministration(membre);
	}

	@FXML
	private void actionBoutonAjouterMembre(){
		this.mainApp.afficherEcranAjouterMembre(membre);
	}

	@FXML
	private void actionBoutonEditerMembre(){
		if (membreSelec != null) {
			this.mainApp.afficherEcranEditerMembre(membre, membreSelec);
		} else {
			new PopupError("Membre non sélectionné","","Veuillez sélectionner un membre pour pouvoir l'éditer");
		}
	}

	@FXML
	private void actionBoutonSupprimerMembre() {
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		ConnexionBD connexion = ConnexionBD.getInstance();
		MembresDAO membreDao = new MembresDAOImpl(connexion);
		AdresseDAO adresseDao = new AdresseDAOImpl(connexion);
		if (membreSelec != null) {
			try {
				popup.afficherPopup("Confirmation","Suppression","Souhaitez-vous vraiment supprimer ce membre ?\n");
				if(popup.getButtonClicked().get()==ButtonType.OK){
					membreDao.supprimerMembre(this.membreSelec.getIdMembre());
					adresseDao.supprimerAdresse(this.membreSelec.getAdresse().getIdAdresse());
					initialiserListeMembre();
				}
			} catch (DAOException e) {
				new PopupException(e);
			} catch(DAOConfigurationException e){
				new PopupError("Erreur","Erreur de configuration",e.getMessage());
			}
		} else {
			new PopupError("Membre non sélectionné","","Veuillez sélectionner un membre pour pouvoir le supprimer");
		}
	}

	@FXML
	private void initialiserListeMembre(){
		MembresDAO membreDao;
		List<Membre> listMembre;
		ConnexionBD connexion = ConnexionBD.getInstance();
		membreDao = new MembresDAOImpl(connexion);
		listMembre = new ArrayList<Membre>();
		listMembre = membreDao.getAllMembre();
		tc_nom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		tc_prenom.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());
		tc_telephone.setCellValueFactory(cellData -> cellData.getValue().getNumeroTelephoneProperty());
		tc_email.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
		tv_membre.setItems((ObservableList<Membre>) listMembre);
		tv_membre.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> setMembreSelec(newValue));
	}

	public void setMembre(Membre membre){
		this.membre=membre;
		initialiserListeMembre();
	}

	public void setMembreSelec(Membre membreSelec) {
		this.membreSelec = membreSelec;
	}
}
