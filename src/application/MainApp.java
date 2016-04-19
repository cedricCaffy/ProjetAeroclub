package application;
	
import model.classes.membres.Membre;
import controllers.AccueilController;
import controllers.ConnexionController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class MainApp extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	public MainApp(){}
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Aéroclub Périgueux Bassillac");
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest((event)->quitterProgramme());
		afficherFenetrePrincipale();
	}
	
	/**
	 * Affiche l'ecran de connexion
	 */
	public void afficherFenetrePrincipale(){
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
			controller.setMainApp(this);
			controller.setMembre(membre);
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
