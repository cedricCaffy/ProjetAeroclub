package controllers;


import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.classes.membres.Membre;
import model.dao.MembresBD;
import view.popup.PopupError;
import application.MainApp;
import bd.ConnexionBD;

import com.mysql.jdbc.Statement;

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
		/*Valeurs pour tests*/
		tf_login.setText("cedric");
		pf_password.setText("cedric");
		iv_photoAccueil.setImage(new Image("view/images/cessna-f-gcnp-en-vol1.jpg"));
	}
	
	/**
	 * Action qui suit le clic sur le bouton quitter
	 */
	@FXML
	private void actionBoutonQuitter(){
		mainApp.quitterProgramme();
	}
	
	/**
	 * Action qui suit le clic sur le bouton valider
	 */
	@FXML
	private void actionBoutonValider(){
		ResultSet rs;
		ConnexionBD connexion=ConnexionBD.getInstance();
		try {
			java.sql.Statement st=connexion.getConnexion().createStatement();
			String sql = "SELECT * FROM MEMBRE";
			rs=st.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("nom"));
			}
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Membre membre;
		MembresBD bd=new MembresBD();
		membre=bd.getMembreByLogin(tf_login.getText());
		if(membre!=null){
			if(membre.getMotDePasse().equals(pf_password.getText())){
				mainApp.afficherEcranAccueil(membre);
			}else{
				new PopupError("Erreur de connexion",null,"Identifiant ou mot de passe incorrect");
			}
		}else{
			new PopupError("Erreur de connexion",null,"Identifiant ou mot de passe incorrect");
		}
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}