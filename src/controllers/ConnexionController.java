package controllers;


import java.sql.SQLException;

import exceptions.DAOConfigurationException;
import exceptions.DAOException;
import bd.ConnexionBD;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.classes.membres.Membre;
import model.dao.MembresDAO;
import model.dao.MembresDAOImpl;
import view.popup.PopupError;
import application.MainApp;

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
		Membre membre=null;
		try{
			ConnexionBD connexion=ConnexionBD.getInstance();
			MembresDAO membresDAO=connexion.getMembreDAO();
			membre=membresDAO.getMembreByLogin(tf_login.getText());
			if(membre!=null){
				if(membre.getMotDePasse().equals(pf_password.getText())){
					mainApp.afficherEcranAccueil(membre);
				}else{
					new PopupError("Erreur de connexion",null,"Identifiant ou mot de passe incorrect");
				}
			}else{
				new PopupError("Erreur de connexion",null,"Identifiant ou mot de passe incorrect");
			}
		}catch(DAOException e){
			new PopupError("Erreur","Erreur de requete",e.getMessage());
		}catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de connexion à la base de données",e.getMessage());
		}
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}