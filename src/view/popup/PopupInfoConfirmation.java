package view.popup;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class PopupInfoConfirmation implements Popup{
	private Optional<ButtonType> buttonClicked;
	public PopupInfoConfirmation(){
		
	}
	
	@Override
	public void afficherPopup(String titre, String headerMessage,String contentMessage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titre);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);

		this.buttonClicked = alert.showAndWait();
	}
	
	/**
	 * Retourne le bouton clique
	 * @return buttonClicked le bouton clique
	 */
	public Optional<ButtonType> getButtonClicked(){
		return this.buttonClicked;
	}

	/**
	 * Apres avoir affiche la popup, tester la reponse avec le
	 * code suivant :
	 * 
	 * 		if (buttonClicked.get() == ButtonType.OK){
		    // ... user chose OK
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	 */
}
