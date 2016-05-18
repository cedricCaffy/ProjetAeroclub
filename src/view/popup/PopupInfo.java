package view.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopupInfo implements Popup{

	@Override
	public void afficherPopup(String titre, String headerMessage,String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titre);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);

		alert.showAndWait();
	}

}
