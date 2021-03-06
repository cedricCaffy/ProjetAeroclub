package controllers;

import view.popup.PopupError;
import view.popup.PopupException;
import view.popup.PopupInfo;
import view.popup.PopupInfoConfirmation;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.classes.avion.Avion;
import model.classes.avion.Disponibilite;
import model.classes.membres.Membre;
import model.dao.AvionDAO;
import model.dao.AvionDAOImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.MainApp;
import bd.ConnexionBD;
import exceptions.DAOConfigurationException;
import exceptions.DAOException;
import exceptions.FormulaireException;

public class EditerAvionController {
	private MainApp mainApp;
	private Membre membre;
	private Avion avionAEditer;

	private static final String REGEX_IMMATRICULATION = "^F-[A-Z]{4}$";
	private static final int VOYAGE = 0;
	private static final int ECOLE = 1;
	private static final int VOLTIGE = 2;

	@FXML
	private TextField tf_nom;

	@FXML
	private TextField tf_immatriculation;

	@FXML
	private ComboBox<String> cb_type;

	@FXML
	private TextField tf_couthoraire;

	@FXML
	private TextField tf_capacitereservoir;

	@FXML
	private TextField tf_massemaximale;

	@FXML
	private TextField tf_nbplaces;

	@FXML
	private TextField tf_vitessecroisiere;

	@FXML
	private TextField tf_consommation;


	public EditerAvionController(){}

	@FXML
	private void initialize(){}

	@FXML
	private void actionBoutonAnnuler(){
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		popup.afficherPopup("Confirmation","Annulation","Aucune modification ne sera enregistrée.\n"
				+ "Voulez-vous vraiment continuer ?");
		if(popup.getButtonClicked().get()==ButtonType.OK){
			mainApp.afficherEcranGestionAvion(membre);
		}
	}

	@FXML
	private void actionBoutonEnregistrer() {
		try {
			controlerNomAvion();
			controlerImmatriculation();
			controlerType();
			controlerCoutHoraire();
			controlerCapaciteReservoir();
			controlerNbPlaces();
			controlerMasseMaximale();
			controlerVitesseCroisiere();
			controlerConsommation();
			enregistrerAvion();
			new PopupInfo().afficherPopup("Confirmation", "Confirmation d'édition","L'avion a bien été éditer, merci.");
			mainApp.afficherEcranAccueil(membre);
		} catch (FormulaireException e) {
			new PopupError("Erreur de saisie du formulaire","",e.getMessage());
		} catch (DAOException e) {
			new PopupException(e);
		} catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de configuration",e.getMessage());
		}
	}

	private void actionApresChargement() {
		ConnexionBD connexion = ConnexionBD.getInstance();
		AvionDAO avionDao = new AvionDAOImpl(connexion);
		this.avionAEditer = avionDao.getAvionFromId(this.avionAEditer.getId());
		tf_nom.setText(this.avionAEditer.getNom());
		tf_immatriculation.setText(this.avionAEditer.getImmatriculation());
		initialiserComboBoxType();
		tf_couthoraire.setText(this.avionAEditer.getCoutHoraire().toString());
		tf_capacitereservoir.setText(this.avionAEditer.getCapaciteReservoir().toString());
		tf_nbplaces.setText(this.avionAEditer.getNombrePlace().toString());
		tf_massemaximale.setText(this.avionAEditer.getMasseMaximale().toString());
		tf_vitessecroisiere.setText(this.avionAEditer.getVitesseCroisiere().toString());
		tf_consommation.setText(this.avionAEditer.getConsommation().toString());
	}

	private void controlerNomAvion() throws FormulaireException {
		if(tf_nom.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un nom d'avion");
		}
		if(tf_nom.getText().length()>20){
			throw new FormulaireException("Le nom de l'avion ne doit pas dépasser 20 caractères");
		}
	}

	private void controlerImmatriculation() throws FormulaireException {
		Pattern p = Pattern.compile(EditerAvionController.REGEX_IMMATRICULATION);
		Matcher m;
		if(tf_immatriculation.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir une immatriculation");
		}
		m = p.matcher(tf_immatriculation.getText());
		if(!m.matches()){
			throw new FormulaireException("Le format de l'immatriculation est incorrecte (F-XXXX)");
		}
	}

	private void controlerType() throws FormulaireException {
		if(cb_type.getSelectionModel().getSelectedItem()==null){
			throw new FormulaireException("Veuillez saisir un type d'avion");
		}
	}

	private void controlerCoutHoraire() throws FormulaireException {
		Double coutHoraireParse;
		try{
			coutHoraireParse=Double.parseDouble(tf_couthoraire.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie du coût horaire incorrecte : veuillez saisir un chiffre");
		}catch(NullPointerException e){
			throw new FormulaireException("Veuillez saisir un coût horaire");
		}
		if(coutHoraireParse<0){
			throw new FormulaireException("Le coût horaire saisi doit être un nombre positif");
		}
	}

	private void controlerCapaciteReservoir() throws FormulaireException {
		Integer capaciteReservoirParse;
		try{
			capaciteReservoirParse=Integer.parseInt(tf_capacitereservoir.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie de la capacité du réservoir incorrecte : veuillez saisir un nombre entier");
		}catch(NullPointerException e){
			throw new FormulaireException("Veuillez saisir la capacité du réservoir");
		}
		if(capaciteReservoirParse<0){
			throw new FormulaireException("La capacité du réservoir saisie doit être un nombre positif");
		}
	}

	private void controlerNbPlaces() throws FormulaireException {
		Integer nbPlacesParse;
		try{
			nbPlacesParse=Integer.parseInt(tf_nbplaces.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie du nombre de places incorrecte : veuillez saisir un nombre entier");
		}catch(NullPointerException e){
			throw new FormulaireException("Veuillez saisir le nombre de places");
		}
		if(nbPlacesParse<0){
			throw new FormulaireException("Le nombre de places saisi doit être un nombre positif");
		}
	}

	private void controlerMasseMaximale() throws FormulaireException {
		Integer masseMaximaleParse;
		try{
			masseMaximaleParse=Integer.parseInt(tf_massemaximale.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie de la masse maximale incorrecte : veuillez saisir un nombre entier");
		}catch(NullPointerException e){
			throw new FormulaireException("Veuillez saisir la masse maximale");
		}
		if(masseMaximaleParse<0){
			throw new FormulaireException("La masse maximale saisie doit être un nombre positif");
		}
	}

	private void controlerVitesseCroisiere() throws FormulaireException {
		Double vitesseCroisiereParse;
		try{
			vitesseCroisiereParse=Double.parseDouble(tf_vitessecroisiere.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie de la vitesse de croisière incorrecte : veuillez saisir un nombre");
		}catch(NullPointerException e){
			throw new FormulaireException("Veuillez saisir la vitesse de croisière");
		}
		if(vitesseCroisiereParse<0){
			throw new FormulaireException("La vitesse de croisière saisie doit être un nombre positif");
		}
	}

	private void controlerConsommation() throws FormulaireException {
		Integer consommationParse;
		try{
			consommationParse=Integer.parseInt(tf_consommation.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie de la consommation incorrecte : veuillez saisir un nombre entier");
		}catch(NullPointerException e){
			throw new FormulaireException("Veuillez saisir la consommation");
		}
		if(consommationParse<0){
			throw new FormulaireException("La consommation saisie doit être un nombre positif");
		}
	}

	public void enregistrerAvion() throws DAOException,DAOConfigurationException {
		Avion nouvAvion;
		ConnexionBD connexion = ConnexionBD.getInstance();
		AvionDAO avionDao = new AvionDAOImpl(connexion);
		nouvAvion = new Avion(-1,tf_nom.getText(), cb_type.getSelectionModel().getSelectedItem(), tf_immatriculation.getText(),Integer.parseInt(tf_consommation.getText()),
				Integer.parseInt(tf_capacitereservoir.getText()),Integer.parseInt(tf_nbplaces.getText()), Integer.parseInt(tf_massemaximale.getText()),
				Double.parseDouble(tf_couthoraire.getText()),Disponibilite.Disponible,Double.parseDouble(tf_vitessecroisiere.getText()),null,null);
		avionDao.editerAvion(avionAEditer.getId(),nouvAvion);
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public Avion getAvionAEditer() {
		return avionAEditer;
	}

	public void setAvionAEditer(Avion avionAEditer) {
		this.avionAEditer = avionAEditer;
		actionApresChargement();
	}

	private void initialiserComboBoxType() {
		cb_type.getItems().add(EditerAvionController.VOYAGE, "Voyage");
		cb_type.getItems().add(EditerAvionController.ECOLE, "Ecole");
		cb_type.getItems().add(EditerAvionController.VOLTIGE, "Voltige");
		cb_type.getSelectionModel().select(this.avionAEditer.getType());
	}
}
