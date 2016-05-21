package controllers;

import view.popup.PopupError;
import view.popup.PopupException;
import view.popup.PopupInfo;
import view.popup.PopupInfoConfirmation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.MainApp;
import bd.ConnexionBD;
import exceptions.DAOConfigurationException;
import exceptions.DAOException;
import exceptions.FormulaireException;
import model.classes.membres.Adresse;
import model.classes.membres.Instructeur;
import model.classes.membres.Membre;
import model.classes.membres.Pilote;
import model.dao.AdresseDAO;
import model.dao.AdresseDAOImpl;
import model.dao.DroitsDAO;
import model.dao.DroitsDAOImpl;
import model.dao.InstructeurDAO;
import model.dao.InstructeurDAOImpl;
import model.dao.MembresDAO;
import model.dao.MembresDAOImpl;
import model.dao.PiloteDAO;
import model.dao.PiloteDAOImpl;
import util.TextFieldManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditerMembreController {
	private Membre membre;
	private MainApp mainApp;
	private Membre membreSelec;
	private Adresse adresse;
	private List<String> droits;
	private Pilote pilote;
	private Instructeur instructeur;

	private static final String REGEX_CP = "^[2A||2B||[0-9]]{2}[0-9]{3}$";
	private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String REGEX_NUMTEL = "^0[1-9][0-9]{8}$";
	private static final String REGEX_NUMEROINSTRUCTEUR = "^FI-[0-9]{6}$";

	@FXML
	private TextField tf_nom;

	@FXML
	private TextField tf_prenom;

	@FXML
	private DatePicker dp_datenaissance;

	@FXML
	private TextField tf_rue;

	@FXML
	private TextField tf_numerorue;

	@FXML
	private TextField tf_codepostal;

	@FXML
	private TextField tf_ville;

	@FXML
	private TextField tf_email;

	@FXML
	private TextField tf_numtel;

	@FXML
	private TextField tf_nummobile;

	@FXML
	private TextField tf_login;

	@FXML
	private TextField tf_motdepasse;

	@FXML
	private CheckBox cb_pilote;

	@FXML
	private CheckBox cb_mecanicien;

	@FXML
	private CheckBox cb_instructeur;

	@FXML
	private CheckBox cb_administrateur;

	@FXML
	private DatePicker dp_datevvm;

	@FXML
	private TextField tf_numeroinstructeur;

	@FXML
	private TextField tf_couthoraire;

	public EditerMembreController(){}

	@FXML
	private void initialize(){}

	@FXML
	private void actionBoutonAnnuler(){
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		popup.afficherPopup("Confirmation","Annulation","Aucune modification ne sera enregistrée\n"
				+ "Voulez-vous vraiment continuer ?");
		if(popup.getButtonClicked().get()==ButtonType.OK){
			mainApp.afficherEcranGestionMembre(membre);
		}
	}

	@FXML
	private void actionBoutonEnregistrer() {
		try {
			controlerNomMembre();
			controlerPrenomMembre();
			controlerDateNaissance();
			controlerRue();
			controlerNumeroRue();
			controlerCodePostal();
			controlerVille();
			controlerEmail();
			controlerNumTel();
			controlerNumMobile();
			controlerLogin();
			controlerMotDePasse();
			if (cb_pilote.isSelected()) {
				controlerDateVVM();
				if (cb_instructeur.isSelected()) {
					controlerNumeroInstructeur();
					controlerCoutHoraire();
				}
			}
			enregistrerDonnees();
			new PopupInfo().afficherPopup("Confirmation", "Confirmation d'édition","Le membre a bien été édité, merci.");
			mainApp.afficherEcranGestionMembre(membre);
		} catch (FormulaireException e) {
			new PopupError("Erreur de saisie du formulaire","",e.getMessage());
		} catch (DAOException e) {
			new PopupException(e);
		} catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de configuration",e.getMessage());
		}
	}

	private void chargementMembreSelec() {
		ConnexionBD connexion = ConnexionBD.getInstance();
		AdresseDAO adresseDao = new AdresseDAOImpl(connexion);
		MembresDAO membreDao = new MembresDAOImpl(connexion);
		DroitsDAO droitsDao = new DroitsDAOImpl(connexion);
		PiloteDAO piloteDao = new PiloteDAOImpl(connexion);
		InstructeurDAO instructeurDao = new InstructeurDAOImpl(connexion);
		this.membreSelec = membreDao.getMembreFromId(this.membreSelec.getIdMembre());
		this.adresse = adresseDao.getAdrFromId(this.membreSelec.getIdMembre());
		this.membreSelec.setAdresse(this.adresse);
		this.droits = droitsDao.getDroitsByIdMembre(this.membreSelec.getIdMembre());

		tf_nom.setText(this.membreSelec.getNom());
		tf_prenom.setText(this.membreSelec.getPrenom());
		dp_datenaissance.setValue(this.membreSelec.getDateNaissance());
		tf_email.setText(this.membreSelec.getEmail());
		tf_numtel.setText(this.membreSelec.getNumeroTelephone());
		tf_nummobile.setText(this.membreSelec.getNumeroMobile());
		tf_login.setText(this.membreSelec.getLogin());
		tf_motdepasse.setText(this.membreSelec.getMotDePasse());
		tf_rue.setText(this.adresse.getRue());
		if (adresse.getNumero() != 0) tf_numerorue.setText(this.adresse.getNumero().toString());
		tf_codepostal.setText(this.adresse.getCodePostal());
		tf_ville.setText(this.adresse.getVille());
		if (this.droits.get(3)!=null) {
			cb_pilote.setSelected(true);
			this.pilote = piloteDao.getPiloteFromId(this.membreSelec.getIdMembre());
			dp_datevvm.setValue(this.pilote.getDateVVM());
		}
		if (this.droits.get(0)!=null) {
			cb_instructeur.setSelected(true);
			this.instructeur = instructeurDao.getInstructeurFromId(pilote.getIdPilote());
			tf_numeroinstructeur.setText(this.instructeur.getNumeroInstructeur());
			tf_couthoraire.setText(this.instructeur.getCoutHoraire().toString());
		}
		if (this.droits.get(1)!=null) cb_administrateur.setSelected(true);
		if (this.droits.get(2)!=null) cb_mecanicien.setSelected(true);
	}

	private void controlerNomMembre() throws FormulaireException{
		if(tf_nom.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un nom");
		}
		if(tf_nom.getText().length()>20){
			throw new FormulaireException("Le nom du membre ne doit pas dépasser 20 caractères");
		}
	}

	private void controlerPrenomMembre() throws FormulaireException{
		if(tf_prenom.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un prénom");
		}
		if(tf_prenom.getText().length()>20){
			throw new FormulaireException("Le prénom du membre ne doit pas dépasser 20 caractères");
		}
	}

	private void controlerDateNaissance() throws FormulaireException{
		if(dp_datenaissance.getValue() == null){
			throw new FormulaireException("Veuillez saisir une date de naissance");
		}
	}

	private void controlerRue() throws FormulaireException{
		if(tf_rue.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir une rue");
		}
		if(tf_rue.getText().length()>50){
			throw new FormulaireException("Le nom de la rue ne doit pas dépasser 50 caractères");
		}
	}

	private void controlerNumeroRue() throws FormulaireException{
		Integer numeroRueParse = 0;
		try{
			if (!tf_numerorue.getText().trim().isEmpty())
				numeroRueParse=Integer.parseInt(tf_numerorue.getText());
		}catch(NumberFormatException nfe){
			throw new FormulaireException("Saisie du numéro de rue incorrecte : veuillez saisir un nombre entier");
		}
		if (!tf_numerorue.getText().trim().isEmpty()) {
			if(numeroRueParse<0){
				throw new FormulaireException("Le numéro de rue saisi doit être un nombre positif");
			}
		}
	}

	private void controlerCodePostal() throws FormulaireException{
		Pattern p = Pattern.compile(EditerMembreController.REGEX_CP);
		Matcher m;
		if(tf_codepostal.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un code postal");
		}
		m = p.matcher(tf_codepostal.getText());
		if(!m.matches()){
			throw new FormulaireException("Le format du code postal est incorrect");
		}
	}

	private void controlerVille() throws FormulaireException{
		if(tf_ville.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir une ville");
		}
		if(tf_ville.getText().length()>30){
			throw new FormulaireException("Le nom de la ville ne doit pas dépasser 30 caractères");
		}
	}

	private void controlerEmail() throws FormulaireException{
		Pattern p = Pattern.compile(EditerMembreController.REGEX_EMAIL);
		Matcher m;
		if(tf_email.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir une adresse email");
		}
		m = p.matcher(tf_email.getText());
		if(!m.matches()){
			throw new FormulaireException("Le format de l'adresse email est incorrect");
		}
	}

	private void controlerNumTel() throws FormulaireException{
		Pattern p = Pattern.compile(EditerMembreController.REGEX_NUMTEL);
		Matcher m;
		if(tf_numtel.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un numéro de téléphone");
		}
		m = p.matcher(tf_numtel.getText());
		if(!m.matches()){
			throw new FormulaireException("Le format du numéro de téléphone est incorrect");
		}
	}

	private void controlerNumMobile() throws FormulaireException{
		Pattern p = Pattern.compile(EditerMembreController.REGEX_NUMTEL);
		Matcher m;
		if(tf_nummobile.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un numéro de mobile");
		}
		m = p.matcher(tf_nummobile.getText());
		if(!m.matches()){
			throw new FormulaireException("Le format du numéro de mobile est incorrect");
		}
	}

	private void controlerLogin() throws FormulaireException{
		if(tf_login.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un login");
		}
		if(tf_login.getText().length()>20){
			throw new FormulaireException("Le login ne doit pas dépasser 20 caractères");
		}
	}

	private void controlerMotDePasse() throws FormulaireException{
		if(tf_motdepasse.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un mot de passe");
		}
		if(tf_motdepasse.getText().length()>20){
			throw new FormulaireException("Le mot de passe ne doit pas dépasser 20 caractères");
		}
	}

	private void controlerDateVVM() throws FormulaireException{
		if(dp_datevvm.getValue() == null){
			throw new FormulaireException("Veuillez saisir une date de validité de la visite médicale");
		}
	}

	private void controlerNumeroInstructeur() throws FormulaireException{
		Pattern p = Pattern.compile(EditerMembreController.REGEX_NUMEROINSTRUCTEUR);
		Matcher m;
		if(tf_numeroinstructeur.getText().trim().isEmpty()){
			throw new FormulaireException("Veuillez saisir un numéro d'instructeur");
		}
		m = p.matcher(tf_numeroinstructeur.getText());
		if(!m.matches()){
			throw new FormulaireException("Le format du numéro d'instructeur est incorrect");
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

	private void enregistrerDonnees() throws DAOException,DAOConfigurationException {
		enregistrerAdresse();
		enregistrerMembre();
		enregistrerDroits();
		if (cb_pilote.isSelected()) {
			enregistrerPilote();
			if (cb_instructeur.isSelected())
				enregistrerInstructeur();
		}
	}

	private void enregistrerMembre() throws DAOException,DAOConfigurationException {
		MembresDAO membreDao;
		Membre membreAEditer;
		ConnexionBD connexion = ConnexionBD.getInstance();
		membreDao = new MembresDAOImpl(connexion);

		membreAEditer = new Membre(0,tf_nom.getText(),tf_prenom.getText(),tf_login.getText(),tf_motdepasse.getText(),
				tf_email.getText(),tf_numtel.getText(),tf_nummobile.getText(),dp_datenaissance.getValue(),0,null,this.adresse,null);

		membreDao.editerMembre(this.membreSelec.getIdMembre(), membreAEditer);
	}

	private void enregistrerAdresse() throws DAOException,DAOConfigurationException {
		Adresse adresseAEditer;
		AdresseDAO adresseDao;
		ConnexionBD connexion = ConnexionBD.getInstance();
		adresseDao = new AdresseDAOImpl(connexion);
		Integer numeroRue = (!tf_numerorue.getText().trim().isEmpty()) ? Integer.parseInt(tf_numerorue.getText()) : 0;
		adresseAEditer = new Adresse(tf_rue.getText(), tf_ville.getText(), tf_codepostal.getText(), numeroRue);
		adresseDao.editerAdresse(this.adresse.getIdAdresse(),adresseAEditer);
	}

	private void enregistrerDroits() throws DAOException,DAOConfigurationException {
		DroitsDAO droitsDao;
		ConnexionBD connexion = ConnexionBD.getInstance();
		droitsDao = new DroitsDAOImpl(connexion);
		List<String> droits = new ArrayList<String>();
		droits.add(cb_instructeur.isSelected() ? "INSTRUCTEUR":"NULL");
	    droits.add(cb_administrateur.isSelected() ? "ADMINISTRATEUR" :"NULL");
	    droits.add(cb_mecanicien.isSelected() ? "MECANICIEN" :"NULL");
	    droits.add(cb_pilote.isSelected() ? "PILOTE" :"NULL");
	    droitsDao.editerDroits(this.membreSelec.getIdMembre(), droits);
	}

	private void enregistrerPilote() throws DAOException,DAOConfigurationException {
		Pilote piloteAAjouter;
		PiloteDAO piloteDao;
		ConnexionBD connexion = ConnexionBD.getInstance();
		piloteDao = new PiloteDAOImpl(connexion);
		piloteAAjouter = new Pilote(dp_datevvm.getValue());
		piloteDao.editerPilote(this.membreSelec.getIdMembre(), piloteAAjouter);
	}

	private void enregistrerInstructeur() throws DAOException,DAOConfigurationException {
		Instructeur instructeurAAjouter;
		InstructeurDAO instructeurDao;
		ConnexionBD connexion = ConnexionBD.getInstance();
		instructeurDao = new InstructeurDAOImpl(connexion);
		instructeurAAjouter = new Instructeur(tf_numeroinstructeur.getText(), Double.parseDouble(tf_couthoraire.getText()));
		instructeurDao.editerInstructeur(this.pilote.getIdPilote(), instructeurAAjouter);
	}

	private void initialiserCheckBoxInstructeur() {
		cb_instructeur.selectedProperty().addListener(new ChangeListener<Boolean>()  {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> ov,
		        Boolean old_val, Boolean new_val) {
		        if (new_val)
		            instructeurSelected();
		        else
		        	instructeurUnselected();
		    }});
	}

	private void initialiserCheckBoxPilote() {
		cb_pilote.selectedProperty().addListener(new ChangeListener<Boolean>()  {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> ov,
		        Boolean old_val, Boolean new_val) {
		        if (new_val)
		            activerDateVVM();
		        else
		        	desactiverDateVVM();
		    }});
	}

	private void actionApresChargement() {
		TextFieldManager.desactiverTextField(tf_numeroinstructeur);
		TextFieldManager.desactiverTextField(tf_couthoraire);
		initialiserCheckBoxInstructeur();
		initialiserCheckBoxPilote();
		desactiverDateVVM();
		chargementMembreSelec();
	}

	private void instructeurSelected() {
		TextFieldManager.activerTextField(tf_numeroinstructeur);
		TextFieldManager.activerTextField(tf_couthoraire);
		bloquerCheckBoxPilote();
	}

	private void instructeurUnselected() {
		TextFieldManager.desactiverTextField(tf_numeroinstructeur);
		TextFieldManager.desactiverTextField(tf_couthoraire);
		debloquerCheckBoxPilote();
	}

	private void bloquerCheckBoxPilote() {
		cb_pilote.setDisable(true);
		cb_pilote.setMouseTransparent(true);
		cb_pilote.setFocusTraversable(false);
		cb_pilote.setSelected(true);
	}

	private void debloquerCheckBoxPilote() {
		cb_pilote.setDisable(false);
		cb_pilote.setMouseTransparent(false);
		cb_pilote.setFocusTraversable(true);
		cb_pilote.setSelected(false);
	}

	private void activerDateVVM() {
		dp_datevvm.setEditable(true);
		dp_datevvm.setMouseTransparent(false);
		dp_datevvm.setFocusTraversable(true);
		dp_datevvm.setStyle(null);
	}

	private void desactiverDateVVM() {
		dp_datevvm.setEditable(false);
		dp_datevvm.setMouseTransparent(true);
		dp_datevvm.setFocusTraversable(false);
		dp_datevvm.setPromptText("");
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setMembreSelec(Membre membreSelec) {
		this.membreSelec = membreSelec;
		actionApresChargement();
	}
}
