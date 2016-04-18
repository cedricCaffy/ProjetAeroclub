package controllers;


import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConnexionController {
	@FXML
	private TextField tf_login;
	@FXML
	private PasswordField pf_password;
	@FXML
	private ImageView iv_photoAccueil;
	@FXML
	private Button b_quitter;
	
	private MainApp mainApp;
	
	public ConnexionController(){}
	
	@FXML
	private void initialize(){
		tf_login.setText("Cedric");
		pf_password.setText("cedric");
		iv_photoAccueil.setImage(new Image("view/images/cessna-f-gcnp-en-vol1.jpg"));
		actionBoutonQuitter();
	}
	
	private void actionBoutonQuitter(){
		b_quitter.setOnAction((event)->mainApp.quitterProgramme());
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}