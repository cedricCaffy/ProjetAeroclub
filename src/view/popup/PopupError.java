package view.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopupError implements Popup{
	public PopupError(String titre,String headerMessage,String contentMessage){
		afficherPopup(titre,headerMessage,contentMessage);
	}
	
	public void afficherPopup(String titre,String headerMessage,String contentMessage){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titre);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);

		alert.showAndWait();
	}
}
