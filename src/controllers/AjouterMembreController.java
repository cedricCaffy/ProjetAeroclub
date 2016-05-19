package controllers;

import view.popup.PopupInfoConfirmation;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.classes.membres.Membre;
import application.MainApp;

public class AjouterMembreController {
	private MainApp mainApp;
	private Membre membre;

	@FXML
	private TextField tv_nom;

	@FXML
	private TextField tv_prenom;

	@FXML
	private DatePicker dp_datenaissance;

	@FXML
	private TextField tv_rue;

	@FXML
	private TextField tv_numreorue;

	@FXML
	private TextField tv_codepostal;

	@FXML
	private TextField tv_ville;

	@FXML
	private TextField tv_email;

	@FXML
	private TextField tv_numtel;

	@FXML
	private TextField tv_nummobile;

	@FXML
	private TextField tv_login;

	@FXML
	private TextField tv_motdepasse;

	@FXML
	private CheckBox cb_pilote;

	@FXML
	private CheckBox cb_mecanicien;

	@FXML
	private CheckBox cb_instructeur;

	@FXML
	private CheckBox cb_administrateur;

	@FXML
	private DatePicker dp_datevvm;

	@FXML
	private TextField tv_numreoinstructeur;

	@FXML
	private TextField tv_couthoraire;

	public AjouterMembreController(){}

	@FXML
	private void initialize(){}

	@FXML
	private void actionBoutonAnnuler(){
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		popup.afficherPopup("Confirmation","Annulation","L'annulation de la saisie va supprimer tout ce que vous avez saisi.\n"
				+ "Voulez-vous vraiment continuer ?");
		if(popup.getButtonClicked().get()==ButtonType.OK){
			mainApp.afficherEcranGestionMembre(membre);
		}
	}

	public MainApp getMainApp() {
		return mainApp;
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	public Membre getMembre() {
		return membre;
	}
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
}
