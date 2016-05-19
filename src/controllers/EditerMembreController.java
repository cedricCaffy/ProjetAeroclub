package controllers;

import view.popup.PopupInfoConfirmation;
import application.MainApp;
import model.classes.membres.Membre;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

public class EditerMembreController {
	private Membre membre;
	private MainApp mainApp;
	private Membre membreSelec;

	public EditerMembreController(){}

	@FXML
	private void initialize(){}

	@FXML
	private void actionBoutonAnnuler(){
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		popup.afficherPopup("Confirmation","Annulation","Aucune modification ne sera enregistrée\n"
				+ "Voulez-vous vraiment continuer ?");
		if(popup.getButtonClicked().get()==ButtonType.OK){
			mainApp.afficherEcranGestionMembre(membre);
		}
	}
	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setMembreSelec(Membre membreSelec) {
		this.membreSelec = membreSelec;
	}
}
