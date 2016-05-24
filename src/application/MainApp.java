package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.classes.avion.Avion;
import model.classes.membres.Membre;
import model.dao.AeroclubDAO;
import view.popup.PopupError;
import view.popup.PopupException;
import bd.ConnexionBD;
import controllers.AccueilController;
import controllers.AdministrationController;
import controllers.AjouterAvionController;
import controllers.AjouterMembreController;
import controllers.ConnexionController;
import controllers.EditerAvionController;
import controllers.EditerMembreController;
import controllers.GestionAvionController;
import controllers.GestionMembreController;
import controllers.MonCompteController;
import controllers.SaisirPaiementController;
import controllers.SaisirVolController;
import exceptions.DAOConfigurationException;
import exceptions.DAOException;


public class MainApp extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;

	public MainApp(){}

	@Override
	public void start(Stage primaryStage){
		this.primaryStage=primaryStage;
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest((event)->quitterProgramme());
		try{
			ConnexionBD connexion=ConnexionBD.getInstance();
			AeroclubDAO aeroclubDAO=connexion.getAeroclubDAO();
			this.primaryStage.setTitle(aeroclubDAO.getNomAeroclub());
			afficherEcranConnexion();
		}catch(DAOConfigurationException e){
			new PopupError("Erreur de connexion à la base de données","Erreur",e.getMessage());
		}catch(DAOException e){
			new PopupException(e);
		}
	}

	/**
	 * Affiche l'ecran de connexion
	 */
	public void afficherEcranConnexion(){
		try {
			/*Loader du fxml*/
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/Connexion.fxml"));
			/*Mise en place du rootLayout*/
			this.rootLayout=(AnchorPane) loader.load();
			/*Creation de la scene*/
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/MainApp.css");
			/*Mise en place de la scene dans le stage*/
			this.primaryStage.setScene(rootScene);
			/*Affichage du stage*/
			this.primaryStage.show();
			/*Recuperation du controller*/
			ConnexionController controller = loader.getController();
			controller.setMainApp(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Affichage de l'ecran d'accueil
	 * @param membre le membre qui s'est loggé
	 */
	public void afficherEcranAccueil(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/Accueil.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/MainApp.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			AccueilController controller = loader.getController();
			controller.setMembre(membre);
			controller.setMainApp(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void afficherEcranAdministration(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/Administration.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/Administration.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			AdministrationController controller = loader.getController();
			controller.setMembre(membre);
			controller.setMainApp(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Affiche l'ecran de gestion des membres
	 * @param membre le membre qui accede a l'ecran de gestion de membres
	 */
	public void afficherEcranGestionMembre(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/GestionMembre.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/GestionMembre.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			GestionMembreController controller = loader.getController();
			controller.setMembre(membre);
			controller.setMainApp(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void afficherEcranAjouterMembre(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/AjouterMembre.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/AjouterMembre.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			AjouterMembreController controller = loader.getController();
			controller.setMembre(membre);
			controller.setMainApp(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void afficherEcranGestionAvion(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/GestionAvion.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/GestionAvion.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			GestionAvionController controller = loader.getController();
			controller.setMembre(membre);
			controller.setMainApp(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Affiche l'ecran mon compte
	 * @param membre le membre qui s'est connecte a l'application
	 */
	public void afficherEcranMonCompte(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/MonCompte.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/MonCompte.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			MonCompteController controller = loader.getController();
			controller.setMainApp(this);
			controller.setMembre(membre);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Affiche l'ecran de saisie d'un vol
	 * @param membre le membre qui saisit son vol
	 */
	public void afficherEcranSaisirVol(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/SaisirVol.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/SaisirVol.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			SaisirVolController controller = loader.getController();
			controller.setMainApp(this);
			controller.setMembre(membre);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * affiche l'ecran de saisie d'un paiement
	 * @param membre le membre qui va saisir un paiement
	 */
	public void afficherEcranSaisirPaiement(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/SaisirPaiement.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/SaisirPaiement.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			SaisirPaiementController controller = loader.getController();
			controller.setMainApp(this);
			controller.setMembre(membre);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Affiche l'ecran de modification d'un membre
	 * @param membre
	 */
	public void afficherEcranEditerMembre(Membre membre, Membre membreSelec){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/EditerMembre.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/AjouterMembre.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			EditerMembreController controller = loader.getController();
			controller.setMainApp(this);
			controller.setMembre(membre);
			controller.setMembreSelec(membreSelec);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void afficherEcranAjouterAvion(Membre membre){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/AjouterAvion.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/AjouterModifierAvion.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			AjouterAvionController controller = loader.getController();
			controller.setMainApp(this);
			controller.setMembre(membre);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void afficherEcranEditerAvion(Membre membre, Avion avionAEditer){
		try{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/EditerAvion.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/AjouterModifierAvion.css");
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			EditerAvionController controller = loader.getController();
			controller.setMainApp(this);
			controller.setMembre(membre);
			controller.setAvionAEditer(avionAEditer);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Quitte le programme
	 */
	public void quitterProgramme(){
		Platform.exit();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
