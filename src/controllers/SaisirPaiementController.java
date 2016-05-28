package controllers;

import java.time.LocalDate;

import bd.ConnexionBD;
import exceptions.DAOConfigurationException;
import exceptions.DAOException;
import exceptions.FormulaireException;
import util.DateUtil;
import util.TextFieldManager;
import view.popup.PopupError;
import view.popup.PopupException;
import view.popup.PopupInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.classes.membres.Membre;
import model.classes.paiement.Cheque;
import model.classes.paiement.Paiement;
import model.dao.ChequeDAO;
import model.dao.ChequeDAOImpl;
import model.dao.EspeceDAO;
import model.dao.EspeceDAOImpl;
import model.dao.PaiementDAO;
import model.dao.PaiementDAOImpl;
import application.MainApp;

public class SaisirPaiementController {
	private MainApp mainApp;
	private Membre membre;
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
	private TextField tf_nomEmetteur;
	
	@FXML
	private Label l_date;
	
	LocalDate datePaiement;
	
	private ChequeDAO chequeDAO;
	
	public SaisirPaiementController(){}
	
	@FXML
	public void initialize(){
		this.datePaiement=LocalDate.now();
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
		try {
			controlerComboboxPaiement();
			controlerMontant(tf_montant.getText());
			if(isCheque()){
				controlerBanque();
				controlerNumeroCheque();
				controlerNomEmetteur();
			}
			enregistrerDonnees();
			new PopupInfo().afficherPopup("Confirmation", "Confirmation de paiement","Votre paiement a bien été effectué, merci.");
			mainApp.afficherEcranAccueil(membre);
		} catch (FormulaireException e) {
			new PopupError("Erreur de saisie du formulaire","",e.getMessage());
		} catch (DAOException e){
			new PopupException(e);
		} catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de configuration",e.getMessage());
		}
		
	}
	
	private void controlerNomEmetteur() throws FormulaireException{
		if(tf_nomEmetteur.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un nom d'émetteur");
		}
		if(tf_nomEmetteur.getText().length()>30){
			throw new FormulaireException("Le nom de l'émetteur ne doit pas dépasser 30 caractères");
		}
	}
	
	private void enregistrerDonnees() throws DAOException,DAOConfigurationException{
		Integer idPaiement=null;
		idPaiement=enregistrerPaiement();
		if(isCheque()){
			enregistrerCheque(idPaiement);
		}else{
			enregistrerEspece(idPaiement);
		}
	}
	
	private Integer enregistrerPaiement() throws DAOException,DAOConfigurationException{
		Integer idPaiement=null;
		Paiement paiement=new Paiement(getMontant(),getDatePaiement());
		ConnexionBD connexion=ConnexionBD.getInstance();
		PaiementDAO paiementDAO=new PaiementDAOImpl(connexion);
		idPaiement=paiementDAO.insererPaiement(paiement,this.membre.getIdMembre());
		return idPaiement;
	}
	
	private void enregistrerCheque(Integer idPaiement) throws DAOException,DAOConfigurationException{
		ConnexionBD connexion=ConnexionBD.getInstance();
		ChequeDAO chequeDAO=new ChequeDAOImpl(connexion);
		Cheque cheque=new Cheque(getMontant(),getDatePaiement(), getNomEmetteur(),getBanqueDebiteur(),getNumeroCheque());
		chequeDAO.enregistrerCheque(idPaiement,cheque);
	}
	
	private void enregistrerEspece(Integer idPaiement) throws DAOException,DAOConfigurationException{
		ConnexionBD connexion=ConnexionBD.getInstance();
		EspeceDAO especeDAO=new EspeceDAOImpl(connexion);
		especeDAO.ajouterEspece(idPaiement);
	}
	
	private Double getMontant(){
		return Double.parseDouble(tf_montant.getText());
	}
	
	private LocalDate getDatePaiement(){
		return this.datePaiement;
	}
	
	private String getNomEmetteur(){
		return this.tf_nomEmetteur.getText();
	}
	
	private String getBanqueDebiteur(){
		return this.tf_banque.getText();
	}
	
	private Integer getNumeroCheque(){
		return Integer.parseInt(tf_numeroCheque.getText());
	}
	
	private boolean isCheque(){
		if(cb_typePaiement.getSelectionModel().getSelectedIndex()==CHEQUE){
			return true;
		}
		return false;
	}
	
	private void controlerBanque() throws FormulaireException{
		if(tf_banque.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un nom de banque");
		}
		if(tf_banque.getText().length()>30){
			throw new FormulaireException("Le nom de la banque saisie ne doit pas dépasser 30 caractères");
		}
	}
	
	private void controlerNumeroCheque() throws FormulaireException{
		try{
			Integer.parseInt(tf_numeroCheque.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie du numéro de chèque incorrecte : veuillez saisir un chiffre");
		}
	}
	
	private void controlerComboboxPaiement() throws FormulaireException{
		if(cb_typePaiement.getSelectionModel().getSelectedItem()==null){
			throw new FormulaireException("Veuillez saisir un moyen de paiement");
		}
	}
	private void controlerMontant(String montant) throws FormulaireException{
		Double montantParse;
		try{
			montantParse=Double.parseDouble(montant);
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie du montant incorrecte : veuillez saisir un chiffre");
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
		l_date.setText(l_date.getText()+DateUtil.format(getDatePaiement()).get());
	}
	
	/**
	 * Action qui suit la selection du type de paiement
	 * "Espece"
	 */
	private void especeSelected(){
		TextFieldManager.desactiverTextField(tf_banque);
		TextFieldManager.desactiverTextField(tf_numeroCheque);
		TextFieldManager.desactiverTextField(tf_nomEmetteur);
	}
	
	/**
	 * Action qui suit la selection du type de paiement
	 * "Cheque"
	 */
	private void chequeSelected(){
		TextFieldManager.activerTextField(tf_banque);
		TextFieldManager.activerTextField(tf_numeroCheque);
		TextFieldManager.activerTextField(tf_nomEmetteur);
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
