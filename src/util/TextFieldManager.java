package util;

import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

/**
 * Classe pour gerer l'apercu des TextField
 * @author Cedric
 *
 */
public class TextFieldManager {
	public TextFieldManager(){}
	
	/**
	 * Desactive le textfield passe en parametre
	 * @param textField le textfield a desactiver
	 */
	public static void desactiverTextField(TextField textField){
		textField.setEditable(false);
		textField.setMouseTransparent(true);
		textField.setFocusTraversable(false);
		textField.setText("");
		textField.setStyle("-fx-background-color:#cccccc");
	}
	
	/**
	 * Active le textfield passe en parametre
	 * @param textField le textfield a activer
	 */
	public static void activerTextField(TextField textField){
		textField.setEditable(true);
		textField.setMouseTransparent(false);
		textField.setFocusTraversable(true);
		textField.setStyle(null);
	}
}
