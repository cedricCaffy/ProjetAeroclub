package controllers;


import model.classes.membres.Membre;
import model.dao.MembresBD;
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
	@FXML
	private Button b_valider;
	
	private MainApp mainApp;
	
	public ConnexionController(){}
	
	@FXML
	private void initialize(){
		tf_login.setText("cedric");
		pf_password.setText("cedric");
		iv_photoAccueil.setImage(new Image("view/images/cessna-f-gcnp-en-vol1.jpg"));
	}
	
	@FXML
	private void actionBoutonQuitter(){
		mainApp.quitterProgramme();
	}
	
	@FXML
	private void actionBoutonValider(){
		Membre membre;
		MembresBD bd=new MembresBD();
		membre=bd.getMembreByLogin(tf_login.getText());
		if(membre!=null){
			if(membre.getMotDePasse().equals(pf_password.getText())){
				mainApp.afficherEcranAccueil(membre);
			}else{
				System.out.println("Erreur de login");
			}
		}
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}