package controllers;

import view.popup.PopupInfoConfirmation;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import model.classes.membres.Membre;
import application.MainApp;

public class EditerAvionController {
	private MainApp mainApp;
	private Membre membre;
	public EditerAvionController(){}
	
	@FXML
	private void initialize(){}

	@FXML
	private void actionBoutonAnnuler(){
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		popup.afficherPopup("Confirmation","Annulation","Aucune modification ne sera enregistrée.\n"
				+ "Voulez-vous vraiment continuer ?");
		if(popup.getButtonClicked().get()==ButtonType.OK){
			mainApp.afficherEcranGestionAvion(membre);
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
