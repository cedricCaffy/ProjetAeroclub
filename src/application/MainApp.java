package application;
	
import controllers.ConnexionController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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
		afficherFenetrePrincipale();
	}
	/**
	 * Affiche l'ecran principal de l'application
	 */
	public void afficherFenetrePrincipale(){
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/Connexion.fxml"));
			this.rootLayout=(AnchorPane) loader.load();
			Scene rootScene=new Scene(this.rootLayout);
			rootScene.getStylesheets().add("view/css/MainApp.css");
			this.primaryStage.setOnCloseRequest((event)->quitterProgramme());
			this.primaryStage.setScene(rootScene);
			this.primaryStage.show();
			ConnexionController controller = loader.getController();
			controller.setMainApp(this);
		} catch(Exception e) {
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
