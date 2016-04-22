package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import model.classes.membres.Membre;
import model.classes.paiement.Paiement;
import application.MainApp;

public class SaisirPaiementController {
	private MainApp mainApp;
	private Membre membre;
	private Paiement paiement;
	private final static int CHEQUE=0;
	private final static int ESPECE=1;
	@FXML
	private Button b_annuler;
	
	@FXML
	private Label l_pilote;
	
	@FXML
	private ComboBox<String> cb_typePaiement;
	
	@FXML
	private TextField tf_banque;
	
	@FXML
	private TextField tf_numeroCheque;
	
	public SaisirPaiementController(){}
	
	@FXML
	public void initialize(){
		
	}
	
	@FXML
	private void actionBoutonAnnuler(){
		mainApp.afficherEcranAccueil(this.membre);
	}
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	private void actionApresChargement(){
		initialiserLabelPilote();
		initialiserComboBoxTypePaiement();
	}
	
	private void initialiserTextField(int typePaiement){
		
	}
	
	private void initialiserComboBoxTypePaiement() {
		cb_typePaiement.getItems().add(SaisirPaiementController.CHEQUE,"Chèque");
		cb_typePaiement.getItems().add(SaisirPaiementController.ESPECE,"Espèce");
		cb_typePaiement.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {
	            if(cb_typePaiement.getSelectionModel().getSelectedIndex()==SaisirPaiementController.ESPECE){
	            	tf_banque.setEditable(false);
	            	//Background des textfield a changer
	            	//tf_banque.setBackground(new Background(Fill));
	            	tf_numeroCheque.setEditable(false);
	            }else{
	            	tf_banque.setEditable(true);
	            	tf_numeroCheque.setEditable(true);
	            }
	          }    
	      });
	}

	private void initialiserLabelPilote(){
		l_pilote.setText(l_pilote.getText()+this.membre.getNom()+" "+this.membre.getPrenom());
	}
	public void setMembre(Membre membre){
		this.membre=membre;
		actionApresChargement();
	}
}
