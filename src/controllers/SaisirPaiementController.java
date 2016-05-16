package controllers;

import java.time.LocalDate;

import exceptions.FormulaireException;
import util.DateUtil;
import util.TextFieldManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	
	@FXML
	private TextField tf_montant;
	
	@FXML
	private Label l_date;
	
	public SaisirPaiementController(){}
	
	@FXML
	public void initialize(){
		
	}
	
	/**
	 * Action qui suit le click sur le bouton annuler
	 */
	@FXML
	private void actionBoutonAnnuler(){
		mainApp.afficherEcranAccueil(this.membre);
	}
	
	@FXML
	private void actionBoutonEnregistrer(){
		Paiement paiement;
		try {
			/*** A CONTINUER ****/
			if(cb_typePaiement.getSelectionModel().getSelectedItem()!=""){
				controlerMontant(tf_montant.getText());
			}
		} catch (FormulaireException e) {
			e.printStackTrace();
		}
		
	}
	
	private void controlerMontant(String montant) throws FormulaireException{
		Double montantParse;
		try{
			montantParse=Double.parseDouble(montant);
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie du montant incorrect : veuillez saisir un chiffre");
		}catch(NullPointerException e){
			throw new FormulaireException("Veuillez saisir un montant");
		}
		if(montantParse<0){
			throw new FormulaireException("Le montant saisi doit être un nombre positif");
		}
	}
	/**
	 * Set le mainApp
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	/**
	 * Actions a faire apres le chargement de la page
	 * (initialisation des differents champs)
	 */
	private void actionApresChargement(){
		initialiserLabelPilote();
		initialiserComboBoxTypePaiement();
		initialiserDate();
	}
	
	/**
	 * Initialise la date
	 */
	private void initialiserDate() {
		LocalDate date=LocalDate.now();
		l_date.setText(l_date.getText()+DateUtil.format(date));
	}
	
	/**
	 * Action qui suit la selection du type de paiement
	 * "Espece"
	 */
	private void especeSelected(){
		TextFieldManager.desactiverTextField(tf_banque);
		TextFieldManager.desactiverTextField(tf_numeroCheque);
	}
	
	/**
	 * Action qui suit la selection du type de paiement
	 * "Cheque"
	 */
	private void chequeSelected(){
		TextFieldManager.activerTextField(tf_banque);
		TextFieldManager.activerTextField(tf_numeroCheque);
	}
	/**
	 * Initialise le combobox du type de paiement
	 */
	private void initialiserComboBoxTypePaiement() {
		cb_typePaiement.getItems().add(SaisirPaiementController.CHEQUE,"Chèque");
		cb_typePaiement.getItems().add(SaisirPaiementController.ESPECE,"Espèce");
		cb_typePaiement.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {
	            if(cb_typePaiement.getSelectionModel().getSelectedIndex()==SaisirPaiementController.ESPECE){
	            	especeSelected();
	            }else{
	            	chequeSelected();
	            }
	          }    
	      });
	}

	/**
	 * Initialise le label contenant le nom du pilote
	 */
	private void initialiserLabelPilote(){
		l_pilote.setText(l_pilote.getText()+this.membre.getNom()+" "+this.membre.getPrenom());
	}
	/**
	 * Set le membre de l'interface de paiement
	 * @param membre le membre qui saisit son paiement
	 */
	public void setMembre(Membre membre){
		this.membre=membre;
		actionApresChargement();
	}
}
